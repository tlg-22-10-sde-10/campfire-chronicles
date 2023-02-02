//package UserInputs;
//
//import ReadExternal.readExternalFiles;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//import java.util.Arrays;
//import java.util.Scanner;
//
//public class NavigateMap {
//    private static final Scanner SC = new Scanner(System.in);
//
//    public static void showStatus(String currentLocal, String description, String[] invent, int moves){
//        System.out.println("--------------------------------");
//        System.out.println("Location: " + currentLocal);
//        System.out.println("Description: " + description);
//        System.out.println("Inventory: " + Arrays.toString(invent));
//        System.out.println("Moves: " + moves);
//    }
//    public static void Navigation() throws Exception {
//        readExternalFiles read = new readExternalFiles();
//        read.readText("src/main/ExternalFiles/Opening.txt");
//        String json = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
//
//        try {
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(json);
//            JSONObject jsonObject = (JSONObject) obj;
//
//            String curLocation = "campfire";
//            String[] inventory = new String[0];
//            int moveCount = 5;
//            System.out.println("\nCurrent Location: " + curLocation + "\nInventory: " + Arrays.toString(inventory));
//            while (moveCount != 0) {
//                moveCount--;
//
//
//                System.out.print(">>>");
//                String userInput = SC.next();
//
//                JSONObject playerLocation = (JSONObject) jsonObject.get(curLocation);
//                String moveTo = (String) playerLocation.get(userInput);
//                curLocation = moveTo;
//                ;
//
//                String desc = (String) playerLocation.get("description");
//                // String items = (String) playerLocation.get("item");
//                //System.out.println(items);
//                ;
//            }
//
//        } catch(ParseException e){
//            e.printStackTrace();
//        }
//    }
//
//
//
//    public static void main(String[] args) throws Exception {
//        readExternalFiles read = new readExternalFiles();
//        read.readText("src/main/ExternalFiles/Opening.txt");
//        String json = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/map.json");
//
//        try {
//            JSONParser parser = new JSONParser();
//            Object obj = parser.parse(json);
//            JSONObject jsonObject = (JSONObject) obj;
//
//            String curLocation = "campfire";
//            String[] inventory = new String[0];
//            int moveCount = 5;
//            System.out.println("Current Location: " + curLocation + " Inventory: " + Arrays.toString(inventory));
//            while (moveCount != 0) {
//                moveCount--;
//
//                // need to fix to have on same line
//                System.out.println(">>>");
//                String userInput = SC.next();
//
//                JSONObject playerLocation = (JSONObject) jsonObject.get(curLocation);
//                String moveTo = (String) playerLocation.get(userInput);
//                curLocation = moveTo;
//                playerLocation = (JSONObject) jsonObject.get(curLocation);
//
//                String desc = (String) playerLocation.get("description");
//               // String items = (String) playerLocation.get("item");
//                //System.out.println(items);
//                showStatus(curLocation, desc, inventory, moveCount);
//            }
//
//        } catch(ParseException e){
//            e.printStackTrace();
//        }
//    }
//}
