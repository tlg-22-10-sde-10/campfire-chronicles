package Game;

import ReadExternal.readExternalFiles;
import UserInputs.UserInput;
import org.json.simple.JSONObject;
import Character.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class GameLogic {
    public static boolean gameRunning = true;
    public static boolean gameWin = false;
    public static boolean gameLose = false;
    public static boolean illumination = false;
    public static int moveCounter;
    private static int drinkCounter;
    private static int illuminationCounter = 10;
    public static CharacterSelect player;
    public static String currentLocation = "campfire";
    public static ArrayList<String> inventoryList = new ArrayList<>();

//    public static String desc;


    public GameLogic() throws Exception {
    }

    public static void startGame(CharacterSelect userChoice) throws Exception {
        player = userChoice;
        String inventory = player.getInventory();
        System.out.println("inventory: " + inventory);
        moveCounter = 10;
        inventoryList.add(inventory);
        System.out.println();
        GameScreens.inGameHelp();
        System.out.println();
        do {
            if(illumination = true) {
                illuminationCounter--;
                if (illuminationCounter < 1) {
                    illumination = false;}
            }
            String command = UserInput.getAction();
            processCommand(command);
        }
        while (gameRunning = true);
        System.out.println();
        if (gameWin == true) {
            System.out.println("congrats! You Win!!");
            System.out.println("Thank you for playing,\n" +
                    "stay tuned for sprint 2 where the main character is challenged by the terrible monsters");
        } else if (gameLose == true) {
            System.out.println("Sorry! You Lose!!");
        }
    }

    /* sends in game command to appropriate function */
    private static void processCommand(String command) throws Exception {
        if (command == "quit") {
            gameRunning = false;
            System.out.println("Good-Bye!");
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
            String item = command.replace("look", "");
            System.out.println("you try to look at \"" + item + "\"");
            LookItem(item);
        } else if (command.contains("search")) {
            System.out.println("you take a look around");
            SearchRoom();
        }
    }

    /* the go or move verb calls this function to move throughout the map */
    private static void Navigate(String direction) throws NullPointerException {
        String destination = null;
        try {
            JSONObject jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
            jsonObject = (JSONObject) jsonObject.get(currentLocation);
            destination = (String) jsonObject.get(direction);

            if (destination != null) {

                currentLocation = destination;
                moveCounter--;
            } else {
                throw new NullPointerException();
            }
            if (destination.equals("lake")) {
                gameRunning = false;
                gameWin = true;
            }
            showStatus();
        } catch (Exception e) {
            System.out.println("invalid direction");
        }
    }

    /* useItem() checks if item is in inventory and sends to doItemAction() to check use case */
    private static void useItem(String item) throws Exception {
        JSONObject itemDetail = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/items.json");
        itemDetail = (JSONObject) itemDetail.get(item);
        for (int i = 0; i < inventoryList.size(); i++) {
            if (inventoryList.get(i).contains(item)) {
                doItemAction(itemDetail.get("action"), itemDetail, item);
                break;
            }
        }

    }

    /* checks item against items.json, checks action against appropriate use case*/
    private static void doItemAction(Object action, JSONObject itemDetail, String item) {
        switch (action.toString()) {
            case "lights":
                illumination = true;
                System.out.println(itemDetail.get("correct_use"));
                break;
            case "energy_boost":
                moveCounter += 3;
                System.out.println(itemDetail.get("correct_use"));
                System.out.println("+3 moves");
                drinkCounter--;
                if (drinkCounter < 1) {
                    inventoryList.remove("energy drink");
                }
                break;
            case "unlock":
                System.out.println("coming soon: Actual Unlock use");
                System.out.println(itemDetail.get("correct_use"));
                break;
            case "swing_bat":
                if (moveCounter > 10) {
                    System.out.println(itemDetail.get("correct_use"));
                } else {
                    System.out.println("not enough moves");
                    System.out.println(itemDetail.get("incorrect_use"));
                }
                break;
            case "power_up":
                System.out.println(itemDetail.get("correct_use"));
                illuminationCounter += 3;
                break;

            default:
                System.out.println("invalid action");
        }
    }

    /* checks if item is in inventory and prints description */
    private static void LookItem(String item) throws Exception {
        JSONObject itemDetail = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/items.json");
        itemDetail = (JSONObject) itemDetail.get(item);
        for (int i = 0; i < inventoryList.size(); i++) {
            String myItem = inventoryList.get(i);
            if (myItem.contains(item)) {
                System.out.println(item + " is in your inventory");
                System.out.println(itemDetail.get("description"));
            }
        }
    }

    private static void SearchRoom() throws Exception {
        String items;
        moveCounter--;
        if (illumination) {
            System.out.println("the flashlight was used in the search");
        }
        try {
            JSONObject jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
            jsonObject = (JSONObject) jsonObject.get(currentLocation);
            items = (String) jsonObject.get("item");
            String delimin = ",";
            StringTokenizer tokenizer = new StringTokenizer(items, delimin);
            while (tokenizer.hasMoreTokens()) {
                String item = tokenizer.nextToken().strip();
                if (item.equals("energy drink")) {
                    drinkCounter++;
                    inventoryList.add(item);
                }
                else {
                    inventoryList.add(item);
                }
                System.out.println(item + " was added to your inventory");
            }
        } catch (Exception e) {
            System.out.println("nothing here");
        }


    }

    public static void showStatus () throws Exception {
        JSONObject jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
        jsonObject = (JSONObject) jsonObject.get(currentLocation);
        String desc = (String) jsonObject.get("description");
        System.out.println("--------------------------------");
        System.out.println("Location: " + currentLocation);
        System.out.println("Description: " + desc);
        System.out.println("Inventory: " + inventoryList.toString());
        if (drinkCounter > 0) {
            System.out.println("Drinks: " + drinkCounter);
        }
        if (illumination = true) {
            System.out.println("Flashlight battery: " + illuminationCounter);
        }
        System.out.println("Moves: " + moveCounter);

    }


}




