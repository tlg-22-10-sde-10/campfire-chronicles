package com.campfire_chronicles.game;

import com.campfire_chronicles.character.CharacterSelect;
import com.campfire_chronicles.item.Item;
import com.campfire_chronicles.map.MapLocation;
import com.campfire_chronicles.monster.MonsterSelect;
import com.campfire_chronicles.user_Inputs.UserInput;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GameLogic {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_Green = "\u001B[32m";
    public static boolean gameRunning = true;
    public static boolean gameWin = false;
    public static boolean gameLose = false;
    public static boolean illumination = false;
    public static int moveCounter;
    private static int drinkCounter;
    private static int illuminationCounter = 10;
    public static CharacterSelect player;
    public static MonsterSelect monster;
    public static String currentLocation = "campfire";
    public static ArrayList<String> inventoryList = new ArrayList<>();

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
        while (gameRunning == true && moveCounter >= 0);
        System.out.println();
        if (gameWin == true && moveCounter > 0) {
            System.out.println("congrats! You Win!!");
            System.out.println("Thank you for playing,\n" +
                    "stay tuned for sprint 3 where the main character is challenged by the terrible monsters");
        } else if (gameLose == true || moveCounter < 1) {
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
            MapLocation location = new MapLocation(currentLocation);
            if (direction.equals("north")) {
                destination = location.getNorth();
            } else if (direction.equals("east")) {
                destination = location.getEast();
            } else if (direction.equals("south")) {
                destination = location.getSouth();
            } else if (direction.equals("west")) {
                destination = location.getWest();
            }

            if (destination != null) {

                currentLocation = destination;
                moveCounter--;
            } else {
                throw new NullPointerException();
            }
            if (destination.equals("lake") && inventoryList.contains("baseball bat")) {
                System.out.println("you used your Big brain and just swam away");
                gameRunning = false;
                gameWin = true;
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
                System.out.println(itemDetail.getCorrect_use());
                System.out.println("+3 moves");
                drinkCounter--;
                if (drinkCounter < 1) {
                    inventoryList.remove("energy drink");
                }
                break;
            case "unlock":
                System.out.println("coming soon: Actual Unlock use");
                System.out.println(itemDetail.getCorrect_use());
                break;
            case "swing_bat":
                if (moveCounter > 10) {
                    System.out.println(itemDetail.getCorrect_use());
                } else {
                    System.out.println("not enough moves");
                    System.out.println(itemDetail.getIncorrect_use());
                }
                break;
            case "power_up":
                System.out.println(itemDetail.getCorrect_use());
                illuminationCounter += 3;
                break;
            case "bus":
                if (inventoryList.contains("gasoline")
                        && inventoryList.contains("car battery")
                        && currentLocation.equals("school bus")) {
                    gameWin = true;
                    gameRunning = false;
                } else {
                    System.out.println("You don't quite seem to have all the items you need");
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
            MapLocation location = new MapLocation(currentLocation);
            items = location.getItem();
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
        } catch (Exception e) {
            System.out.println("nothing here");
        }


    }

    public static void showStatus() throws Exception {
        MapLocation location = new MapLocation(currentLocation);
        String desc = location.getDescription();
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
}




