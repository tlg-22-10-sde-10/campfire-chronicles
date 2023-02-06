package Game;

import ReadExternal.readExternalFiles;
import UserInputs.UserInput;
import org.json.simple.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class GameLogic {
    public static boolean gameRunning = true;
    public static boolean gameWin = false;
    public static boolean gameLose = false;
    public static int moveCounter;
    public static String currentLocation = "campfire";
    public static ArrayList<String> inventoryList = new ArrayList<>();
    public static String desc;
    static JSONObject playerLocation;
    static JSONObject roomLocation;
    public static JSONObject jsonObject;

    static {
        try {
            jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public GameLogic() throws Exception {
    }

    public static void startGame(String inventory) throws Exception {
        moveCounter = 10;
        inventoryList.add(inventory);
        System.out.println();
        Game.inGameHelp();
        System.out.println();
        do {
            String command = UserInput.getAction();
            processCommand(command);
        }
        while (gameRunning == true);
        System.out.println();
        if (gameWin == true) {
            System.out.println("congrats! You Win!!");
            System.out.println("Thank you for playing,\n" +
                    "stay tuned for sprint 2 where the main character is challenged by the terrible monsters");
        } else if (gameLose == true) {
            System.out.println("Sorry! You Lose!!");
        }
    }

    private static void processCommand(String command) throws Exception {
        switch (command) {
            case "quit":
                gameRunning = false;
                System.out.println("Good-Bye!");
                break;
            case "status":
                showStatus();
                break;
            case "help":
                Game.inGameHelp();
                break;
            default:
                if (command.contains("go") || command.contains("move")) {
                    command = command.replace("go", "move");
                    String direction = command.replace("move", "").trim();
                    System.out.println(direction);
                    Navigate(direction);
                    break;
                }
                else if (command.contains("use")) {
                    String item = command.replace("use", "").trim();
                    System.out.println("you try to use \"" + item + "\"");
                    UseItem(item);
                    break;
                } else if (command.contains("look")) {
                    String item = command.replace("look","");
                    System.out.println("you try to look at \"" + item + "\"");
                    LookItem(item);
                    break;
                } else if (command.contains("search")) {
                    System.out.println("you take a look around");
                    SearchRoom();
                    break;

                }
        }
    }

    private static void Navigate(String direction) throws NullPointerException {
        String destination = "";
        try {
            JSONObject jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
            jsonObject = (JSONObject) jsonObject.get(currentLocation);
            destination = (String) jsonObject.get(direction);
            if (destination != "") {
                currentLocation = destination;
                moveCounter--;
            }
            else {
                throw new NullPointerException();
            }
            playerLocation = (JSONObject) jsonObject.get(currentLocation);
            if (destination.equals("lake")) {
                gameRunning = false;
                gameWin = true;
            }
            showStatus();
        } catch (Exception e) {
            System.out.println("invalid direction");
        }
    }

    private static void UseItem(String item) throws Exception {
        for (int i = 0; i < inventoryList.size(); i++) {
            String myItem = inventoryList.get(i);
            if (myItem.contains(item)) {
//                inventoryList.remove(i);
                System.out.println(item + " was used");
            } else {
                System.out.println(item + " is not in your inventory");
            }
        }
    }

    private static void LookItem(String item) throws Exception {
        for (int i = 0; i < inventoryList.size(); i++) {
            String myItem = inventoryList.get(i);
            if (myItem.contains(item)) {
                System.out.println(item + " is in your inventory");
            }
        }
    }

    private static void SearchRoom() throws Exception {
        String items;
        try {
            JSONObject jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
            jsonObject= (JSONObject) jsonObject.get(currentLocation);
            items = (String) jsonObject.get("item");
            String delimins = " ,.:;!?|\"";
            StringTokenizer tokenizer = new StringTokenizer(items, delimins);
            while (tokenizer.hasMoreTokens()) {
                String item = tokenizer.nextToken();
                inventoryList.add(item);
                System.out.println(item + " was added to your inventory");
            }
        }
        catch(Exception e){
                System.out.println("something went wrong");
            }
    }

        private static void showStatus () throws Exception {
            JSONObject jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
            jsonObject = (JSONObject) jsonObject.get(currentLocation);
            desc = (String) jsonObject.get("description");
            System.out.println("--------------------------------");
            System.out.println("Location: " + currentLocation);
            System.out.println("Description: " + desc);
            System.out.println("Inventory: " + inventoryList.toString());
            System.out.println("Moves: " + moveCounter);
        }
    }



