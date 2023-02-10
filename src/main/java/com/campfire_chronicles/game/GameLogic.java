package com.campfire_chronicles.game;

import com.campfire_chronicles.character.CharacterSelect;
import com.campfire_chronicles.item.Item;
import com.campfire_chronicles.map.MapLocation;
import com.campfire_chronicles.monster.MonsterSelect;
import com.campfire_chronicles.music.MusicPlayer;
import com.campfire_chronicles.user_Inputs.UserInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Random;
import java.util.StringTokenizer;

public class GameLogic {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_Green = "\u001B[32m";
    public static final String ANSI_RED = "\u001B[31m";
    public static boolean gameRunning = true;
    public static boolean gameWin = false;
    public static boolean gameLose = false;
    public static boolean illumination = false;
    public static int moveCounter;
    private static int drinkCounter;
    private static int illuminationCounter = 10;
    public static CharacterSelect player;
    public static MonsterSelect monster;
    public static ArrayList<String> inventoryList = new ArrayList<>();
    private static MapLocation campfire = new MapLocation("campfire");
    private static MapLocation boysCabin = new MapLocation("boy's cabin");
    private static MapLocation girlsCabin = new MapLocation("girl's cabin");
    private static MapLocation cafeteria = new MapLocation("cafeteria");
    private static MapLocation infirmary = new MapLocation("infirmary");
    private static MapLocation gym  = new MapLocation("gym");
    private static MapLocation office  = new MapLocation("office");
    private static MapLocation schoolBus  = new MapLocation("school bus");
    private static MapLocation woods  = new MapLocation("woods");
    private static MapLocation lake  = new MapLocation("lake");
    private static MapLocation logCabin  = new MapLocation("log cabin");
    public static String currentLocation = campfire.getName();
    public static MapLocation currentMapLocation = campfire;


//    public static String desc;


    public GameLogic() throws Exception {
    }

    public static void startGame(CharacterSelect userCharacter, MonsterSelect userMonster) throws Exception {
        player = userCharacter;
        monster = userMonster;
        String inventory = player.getInventory();
        System.out.println("inventory: " + inventory);
        moveCounter = 10;
        inventoryList.add(inventory);
        System.out.println();
        GameScreens.inGameHelp();
        System.out.println();
        do {
            if (illumination == true) {
                illuminationCounter--;
                if (illuminationCounter < 1) {
                    illumination = false;
                }
            }
            String command = UserInput.getAction();
            processCommand(command);
        }
        while (gameRunning == true && moveCounter > 0);
        System.out.println();
        if (gameWin == true && moveCounter > 0) {
            new MusicPlayer("/game-win.wav").playOnce();
            Thread.sleep(2000);
            System.out.println("congrats! You Win!!");
            System.out.println("Thank you for playing,\n");

        } else if (gameLose == true || moveCounter < 1) {
            new MusicPlayer("/game-over.wav").playOnce();
            Thread.sleep(500);
            System.out.println("Sorry! You Lose!!");
            System.out.println("Thanks for playing our Game");
        }
    }

    /* sends in game command to appropriate function */
    private static void processCommand(String command) throws Exception {
        if (command == "quit") {
            UserInput.quitInput();
        } else if (command.contains("go") || command.contains("move")) {
            command = command.replace("go", "move");
            String direction = command.replace("move", "").trim();
            System.out.println(direction);
            Navigate(direction);
        } else if (command.contains("use")) {
            String item = command.replace("use", "").trim();
            System.out.println("you try to use \"" + item + "\"");
            useItem(item);
        } else if (command.contains("look")) {
            String item = command.replace("look", "").trim();
            System.out.println("you try to look at \"" + item + "\"");
            LookItem(item);
        } else if (command.contains("search")) {
            System.out.println("you take a look around");
            SearchRoom();
        } else if (command.contains("map")) {
            showMap(currentLocation);
        }
    }

    /* the go or move verb calls this function to move throughout the map */
    private static void Navigate(String direction) throws NullPointerException {
        String destination = null;
        try {
            if (direction.equals("north")) {
                destination = currentMapLocation.getNorth();
            } else if (direction.equals("east")) {
                destination = currentMapLocation.getEast();
            } else if (direction.equals("south")) {
                destination = currentMapLocation.getSouth();
            } else if (direction.equals("west")) {
                destination = currentMapLocation.getWest();
            }

            if (destination != null) {

                currentLocation = destination;
                MapLocation newLocation = new MapLocation(currentLocation);
                if(campfire.getId().equals(newLocation.getId())) {
                    currentMapLocation = campfire;
                }else if(office.getId().equals(newLocation.getId())) {
                    currentMapLocation = office;
                }else if(woods.getId().equals(newLocation.getId())) {
                    currentMapLocation = woods;
                }else if(boysCabin.getId().equals(newLocation.getId())) {
                    currentMapLocation = boysCabin;
                }else if(girlsCabin.getId().equals(newLocation.getId())) {
                    currentMapLocation = girlsCabin;
                }else if(lake.getId().equals(newLocation.getId())) {
                    currentMapLocation = lake;
                }else if(logCabin.getId().equals(newLocation.getId())) {
                    currentMapLocation = logCabin;
                }else if(schoolBus.getId().equals(newLocation.getId())) {
                    currentMapLocation = schoolBus;
                }else if(cafeteria.getId().equals(newLocation.getId())) {
                    currentMapLocation = cafeteria;
                }else if(infirmary.getId().equals(newLocation.getId())) {
                    currentMapLocation = infirmary;
                }else if(gym.getId().equals(newLocation.getId())) {
                    currentMapLocation = gym;
                }
                moveCounter--;
            } else {
                throw new NullPointerException();
            }
            showStatus();
        } catch (Exception e) {
            System.out.println("invalid direction");
        }
    }

    /* useItem() checks if item is in inventory and sends to doItemAction() to check use case */
    private static void useItem(String selection) throws Exception {
        try {
            Item useItem = new Item(selection);
            for (int i = 0; i < inventoryList.size(); i++) {
                if (inventoryList.get(i).contains(selection)) {
                    doItemAction(useItem.getAction(), useItem, selection);
                    break;
                }
            }
        } catch (NullPointerException e) {
            System.out.println("you waved around your hands and nothing happens");
            System.out.println("try /'use (item name)/'");
        }
    }


    /* checks item against items.json, checks action against appropriate use case*/
    private static void doItemAction(Object action, Item itemDetail, String item) throws Exception {
//        testWinCondition(action.toString());
        switch (action.toString()) {
            case "lights":
                illumination = true;
                System.out.println(itemDetail.getCorrect_use());
                break;
            case "energy_boost":
                moveCounter += 3;
                new MusicPlayer("/drink-soda.wav").playOnce();
                System.out.println(itemDetail.getCorrect_use());
                System.out.println("+3 moves");
                drinkCounter--;
                if (drinkCounter < 1) {
                    inventoryList.remove("energy drink");
                }
                break;
            case "unlock":
                if (currentLocation.equals("office")) {
                    System.out.println(itemDetail.getCorrect_use());
                    inventoryList.add("the colt");
                    System.out.println("The colt was added to inventory");
                } else {

                    System.out.println(itemDetail.getIncorrect_use());
                }
                break;
            case "swing_bat":
                if (moveCounter > 10 && currentLocation.equals(office)) {
                    System.out.println(itemDetail.getCorrect_use());
                    inventoryList.add("the colt");
                    System.out.println("The colt was added to inventory");
                } else {
                    System.out.println(itemDetail.getIncorrect_use());
                }
                break;
            case "power_up":
                System.out.println(itemDetail.getCorrect_use());
                illuminationCounter += 3;
                break;
            case "cheat":
                moveCounter += 999;
                inventoryList.add("the colt");
                inventoryList.add("gasoline");
                inventoryList.add("baseball bat");
                inventoryList.add("car battery");
                inventoryList.add("boating manual");
                System.out.println("several items were added to your inventory type \"status\" to check");
                inventoryList.remove("golden coin");
                break;
            case "bus":
                if (inventoryList.contains("gasoline")
                        && inventoryList.contains("car battery")
                        && currentLocation.equals("school bus")) {
                    System.out.println(itemDetail.getCorrect_use());
                    gameWin = true;
                    gameRunning = false;
                } else {
                    System.out.println("You don't quite seem to have all the items you need");
                }
                break;
            case "shoot":
                if (currentLocation.equals("school bus")){
                    System.out.println(itemDetail.getCorrect_use());
                    gameWin = true;
                    gameRunning = false;
                }
                else {
                    System.out.println(itemDetail.getIncorrect_use());
                    System.out.println(currentLocation);
                }
                break;
            default:
                System.out.println("invalid action");
        }
    }

    /* checks if item is in inventory and prints description */
    private static void LookItem(String selection) throws Exception {
        Item item;
        int j = inventoryList.size();
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).contains(selection)) {
                try {
                    item = new Item(selection);
                    //j--;
                    System.out.println(item.getDescription());
                    System.out.println(selection + " is in your inventory and has a purpose");
                } catch (Exception e) {
                    System.out.println("you look off into the distance thinking about what lead you to play this game");
                }
                break;
            } else {
                j--;
            }

        }
        if (j == 0) {
            System.out.println(selection+" is not in your inventory");
        }
}

    private static void SearchRoom() throws Exception {
        String items;
        moveCounter--;
        if (illumination == true) {
            System.out.println("the flashlight was used in the search");
        }
        try {
            items = currentMapLocation.getItem();
            String delimin = ",";
            StringTokenizer tokenizer = new StringTokenizer(items, delimin);
            while (tokenizer.hasMoreTokens()) {
                String item = tokenizer.nextToken().strip();
                if (item.equals("energy drink")) {
                    drinkCounter++;
                    System.out.println(item + " was added to your inventory");
                   if( !inventoryList.contains(item)){
                        inventoryList.add(item);
                    }
                   ;
                } else {
                    if( !inventoryList.contains(item)){
                        inventoryList.add(item);
                        System.out.println(item + " was added to your inventory");

                    }
                }

            }
            currentMapLocation.setItem("");
            //System.out.println(location.getItem());

        } catch (Exception e) {
            System.out.println("nothing here");
        }


    }

    public static void showStatus() throws Exception {
        String desc = currentMapLocation.getDescription();
        System.out.println("--------------------------------");
        System.out.println("Location: " + currentLocation);
        System.out.println("Description: " + desc);
        System.out.println("Inventory: " + inventoryList.toString());
        if (drinkCounter > 0) {
            System.out.println("Drinks: " + drinkCounter);
        }
        if (illumination == true) {
            System.out.println("Flashlight battery: " + illuminationCounter);
        }
        System.out.println("Moves: " + moveCounter);
        if(monster.getName().equals("vampire")){
            if(currentLocation.equals("log cabin") || currentLocation.equals("cafeteria") || currentLocation.equals("school bus")) {
                monsterMash(monster);
            }
        }else if(monster.getName().equals("werewolf")){
            if(currentLocation.equals("office") || currentLocation.equals("woods") || currentLocation.equals("school bus")) {
                monsterMash(monster);
            }
        }else if(monster.getName().equals("ghost")){
            if(currentLocation.equals("boys cabin") || currentLocation.equals("infirmary") || currentLocation.equals("school bus")) {
                monsterMash(monster);
            }
        }


    }

    private static void showMap(String location) throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(GameScreens.class.getClassLoader().getResourceAsStream("map.txt")))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(location);
                if (index != -1) {
                    System.out.print(line.substring(0, index));
                    System.out.print(ANSI_Green + location + "*" + ANSI_RESET);
                    System.out.println(line.substring(index + location.length()));
                } else {
                    System.out.println(line);
                }
            }

        }
    }
    public static void monsterMash(MonsterSelect monster){
        Random random = new Random();
        int randomNum =  random.nextInt(2) + 1;
        if(randomNum == 1){
            System.out.println(ANSI_RED + monster.getName() + ": " + monster.getPlayer_loss() + ANSI_RESET);
            System.out.println("You shake in fear and lose 2 moves.");
            moveCounter -= 2;
        }else{
            System.out.println(ANSI_RED + monster.getName() + ": " + monster.getPlayer_escape() + ANSI_RESET);
            System.out.println("You barley escaped an attack from the " + monster.getName());
        }
    }
}




