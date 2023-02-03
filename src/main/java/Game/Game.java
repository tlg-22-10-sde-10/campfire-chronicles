package Game;

import ReadExternal.readExternalFiles;
import UserInputs.UserInput;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
         * This method is responsible for getting input the main screen
         * [1,2,3,4]
         * @return String
         */
        public static String getMainMenu() {
            String menuInput = SC.next();
            final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4"));
            while (!options.contains(menuInput)) {
                System.out.println("Invalid input, try again. valid inputs are 1-4");
                menuInput = SC.next();
            }
            return menuInput;
        }
    /**
     * This method is responsible for getting input the main screen
     * [1,2,3,4]
     * @return String
     */
    public static String getCharacterSelect() {
        String charectorinput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4","5","6"));
        while (!options.contains(charectorinput)) {
            System.out.println("Invalid input, try again. valid inputs are 1 to 6");
            charectorinput = SC.next();
        }
        return charectorinput;
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
            System.out.println("Invalid input, try again. valid inputs are 1 and 2");
            helpinput = SC.next();
        }
        return helpinput;
    }

    /**
     * This method is responsible for getting input the Journal Screen
     * [1,2,3,4]
     * @return String
     */
    public static String getJournalSelect() {
        String journalinput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4","5","6"));
        while (!options.contains(journalinput)) {
            System.out.println("Invalid input, try again. valid inputs are 1 to 6");
            journalinput = SC.next();
        }
        return journalinput;
    }

    public static void displayHelp() throws Exception {
        System.out.println(" --- HELP SCREEN ----");
        System.out.println("Main Menu Options");
        System.out.println("Type 1 to start a New game");
        System.out.println("Type 2 to get Developer Information");
        System.out.println("'Type 3 to get to Help screen");
        System.out.println("Type 4 to Quit the game");
        UserInput.helpInput();
    }

    public static void inGameHelp() throws Exception {
        System.out.println(" --- HELP SCREEN ----");
        System.out.println("Valid Commands -\n" +
                "Move/go\n" +
                "Look\n" +
                "Use Items\n" +
                "Help (Camp Counselor)\n" +
                "Status(show location/inventory)\n" +
                "quit(Give Up)\n");
        System.out.println("Correct Syntax would be move north/south/east/west\n"
                 + " Developer notes Add more Stuff later ");


    }
}

