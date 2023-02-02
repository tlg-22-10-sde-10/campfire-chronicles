package Game;

import ReadExternal.readExternalFiles;
import UserInputs.UserInput;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.Arrays;

public class GameLogic {
    public static boolean gameRunning = true;
    public static int moveCounter;
    public static String currentLocation = "campfire";
    public static String[] inventory;
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


    public static void startGame() throws Exception {
        moveCounter = 10;

        do {
            String command = UserInput.getAction();
            processCommand(command);
        }
        while (gameRunning == true && moveCounter > 0);
    }

    private static void processCommand(String command) throws Exception {
        switch(command) {
            case "exit":
                gameRunning = false;
                break;
            case "status":
                showStatus();
                break;
            case "help":
                Game.displayHelp();
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

    private static void Navigate(String direction) throws Exception {
        moveCounter--;
        JSONObject jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
        playerLocation = (JSONObject) jsonObject.get(currentLocation);
        String destination = (String) playerLocation.get(direction);
        currentLocation = destination;
        playerLocation = (JSONObject) jsonObject.get(currentLocation);
        showStatus();

        }


    private static void showStatus() throws Exception {
        playerLocation =  (JSONObject) jsonObject.get(currentLocation);
        desc = (String) playerLocation.get("description");

        System.out.println("--------------------------------");
        System.out.println("Location: " + currentLocation);
        System.out.println("Description: " + desc);
        System.out.println("Inventory: " + Arrays.toString(inventory));
        System.out.println("Moves: " + moveCounter);
    }
}


