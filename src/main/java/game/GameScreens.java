package game;

import user_Inputs.UserInput;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameScreens {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    private static final Scanner SC = new Scanner(System.in);

    /* shows the game tile screen and options */
    public static void showTitle() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(GameScreens.class.getClassLoader().getResourceAsStream("Title.txt")))) {
            String title;
            //Condition holds true till there is character in a string
            while ((title = reader.readLine()) != null) {
                System.out.println(ANSI_RED + title + ANSI_RESET);
            }
        }

    }

    /**
     * This method is responsible for getting input the main screen
     * [1,2,3,4]
     *
     * @return String
     */
    public static String getMainMenu() {
        String menuInput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4", ""));
        while (!options.contains(menuInput)) {
            System.out.println("Invalid input, try again. valid inputs are 1-4");
            menuInput = SC.next();
        }
        return menuInput;

    }

    /**
     * This method is responsible for getting input the main screen
     * [1,2,3,4]
     *
     * @return String
     */
    public static String getCharacterSelect() {
        String charectorinput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        while (!options.contains(charectorinput)) {
            System.out.println("Invalid input, try again. valid inputs are 1 to 6");
            charectorinput = SC.next();
        }
        return charectorinput;
    }

    /**
     * This method is responsible for getting input the Help Screen
     * [1,2]
     *
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
     *
     * @return String
     */
    public static String getJournalSelect() {
        String journalinput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5", "6"));
        while (!options.contains(journalinput)) {
            System.out.println("Invalid input, try again. valid inputs are 1 to 6");
            journalinput = SC.next();
        }
        return journalinput;
    }
    /**
     * This method is responsible for getting input the Help Screen
     * [1,2]
     *
     * @return String
     */
    public static String getQuitSelect() {
        String quitinput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2"));
        while (!options.contains(quitinput)) {
            System.out.println("Invalid input, try again. valid inputs are 1 and 2");
            quitinput = SC.next();
        }
        return quitinput;
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
                "Move/go(used to Navigate Map)\n" +
                "Look Item(Provides description of item)\n" +
                "Use Items\n" +
                "Help (Camp Counselor)\n" +
                "Map(Shows current location on map)\n"+
                "Search(looks around the room)\n"+
                "Status(show location/inventory)\n" +
                "quit(Give Up)\n");
        System.out.println("Correct Syntax would be move north/south/east/west\n"
                + "Use bat\n"+ "look at bat\n"+"Map\n");


    }



}



