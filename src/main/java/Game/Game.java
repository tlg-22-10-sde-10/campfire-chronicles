package Game;

import ReadExternal.readExternalFiles;
import UserInputs.UserInput;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final Scanner SC = new Scanner(System.in);
    private static final char MAIN_MENU_OPTION = 'M';
    private static final char CHEAT = 'C';
    private static final char QUIT_OPTION = 'Q';



    public static void showTitle() throws Exception {
        //File path is passed as parameter "Bug Figure Out how to open in Github"

        File file = new File("src/main/ExternalFiles/Title.txt");

        //Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        //Declaring a string variable
        String title;


        //Condition holds true till there is character in a string
        while ((title = br.readLine()) != null) {

            System.out.println(ANSI_RED + title + ANSI_RESET);
        }
    }




    public static void openingText() throws Exception {
        readExternalFiles read = new readExternalFiles();
        read.readText("src/main/ExternalFiles/Opening.txt");


        String curLocation = "campfire";
        String[] inventory = new String[0];
        System.out.println("\nCurrent Location: " + curLocation + "\nInventory: " + Arrays.toString(inventory));

    }

        /**
         * This method responsible for getting input the home page
         * @return String
         */
        public static String getHomePage() {
            String userA = SC.next();
            final Set<String> set = new HashSet<>(Arrays.asList("1", "2", "3", "4"));

            while (!set.contains(userA)) {
                System.out.println("Invalid input, try again.");
                userA = SC.next();
            }
            return userA;
        }

        /**
         * This method is responsible for getting input the main screen
         * [1,2,3,4]
         * @return String
         */
        public static String getMainMenu() {
            String menuInput = SC.next();
            final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4","5"));
            while (!options.contains(menuInput)) {
                System.out.println("Invalid input, try again.");
                menuInput = SC.next();
            }
            return menuInput;
        }
    /**
     * This method is responsible for getting input the main screen
     * [1,2,3,4]
     * @return String
     */
    public static String getCharectorSelect() {
        String charectorinput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4","5","6"));
        while (!options.contains(charectorinput)) {
            System.out.println("Invalid input, try again.");
            charectorinput = SC.next();
        }
        return charectorinput;
    }


        /**
         * Print the main menu.
         */
        public static void displayMenuScreen() {
            System.out.println("------ Main Menu -------");
            System.out.println("Clues (select 1)");
            System.out.println("Go to Home Page (select 2)");
            System.out.println("Close Main Menu (select 3)");
            System.out.println("Help (select 4)");
            System.out.println("Quit Game.Game (select 5)");
            System.out.println("------ Main Menu ------");
        }
        /**
         * This will get the player's input
         * @return String
         */
        public static String getUserInput() {
            System.out.println("Your Answer:");
            final String userA = SC.next();
            return userA.toLowerCase();
        }

    /**
     * This method is responsible for getting input the Help Screen
     * [1,2]
     * @return String
     */
    public static String getHelpSelect() {
        String helpinput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2"));
        while (!options.contains(helpinput)) {
            System.out.println("Invalid input, try again.");
            helpinput = SC.next();
        }
        return helpinput;
    }

    public static void displayHelp() throws Exception {
        System.out.println(" --- HELP SCREEN ----");
        System.out.println("Press 'M' for menu screen in the game.");
        System.out.println("'Yellow' represents the rooms you have unlocked.");
        System.out.println("'Red' represents the room that you haven't visited.");
        System.out.println("'Green' represents the room you are in.");
        System.out.println("Press 'C' for cheats ");
        UserInput.helpInput();
    }
}

