package Game;

import ReadExternal.readExternalFiles;
import UserInputs.UserInput;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import Character.*;

public class GameLogic {
    public static boolean gameRunning = true;
    public static boolean gameWin = false;
    public static boolean gameLose = false;
    public static int moveCounter;
    public static String currentLocation = "campfire";
    //public static String[] inventory;
    public static ArrayList<String> inventoryList = new ArrayList<String>();
    public static String desc;
    static JSONObject playerLocation;
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
        while (gameRunning == true );
        System.out.println();
        if (gameWin == true) {
            System.out.println("congrats! You Win!!");
            System.out.println("Thank you for playing,\n" +
                    "stay tuned for sprint 2 where the main character is challenged by the terrible monsters");
        }
        else if (gameLose == true) {
            System.out.println("Sorry! You Lose!!");
        }
    }

    private static void processCommand(String command) throws Exception {
        switch(command) {
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
        };
        }
    }

    private static void Navigate(String direction) throws NullPointerException {
        String destination = null;
        try {
            JSONObject jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
            playerLocation = (JSONObject) jsonObject.get(currentLocation);
            destination = (String) playerLocation.get(direction);
            if (destination != null) {
                currentLocation = destination;
                moveCounter--;
            } else {
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

    private static void showStatus() throws Exception {
        playerLocation =  (JSONObject) jsonObject.get(currentLocation);
        desc = (String) playerLocation.get("description");
        System.out.println("--------------------------------");
        System.out.println("Location: " + currentLocation);
        System.out.println("Description: " + desc);
        System.out.println("Inventory: " + inventoryList.toString());
        System.out.println("Moves: " + moveCounter);
    }
}


