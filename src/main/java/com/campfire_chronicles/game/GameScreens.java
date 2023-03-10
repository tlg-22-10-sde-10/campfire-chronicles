package com.campfire_chronicles.game;

import com.campfire_chronicles.read_external.ReadExternalFiles;
import com.campfire_chronicles.user_Inputs.UserInput;

import java.io.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class GameScreens {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_Green = "\u001B[32m";
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

    public static void showWin() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(GameScreens.class.getClassLoader().getResourceAsStream("win.txt")))) {
            String title;
            //Condition holds true till there is character in a string
            while ((title = reader.readLine()) != null) {
                System.out.println(ANSI_Green + title + ANSI_RESET);
            }
        }
    }

    public static void showLose() throws Exception {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(GameScreens.class.getClassLoader().getResourceAsStream("lose.txt")))) {
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
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5"));
        while (!options.contains(menuInput)) {
            System.out.println("Invalid input, try again. valid inputs are 1-5");
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
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5", "513"));
        while (!options.contains(charectorinput)) {
            System.out.println("Invalid input, try again. valid inputs are 1 to 5");
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

    public static String getSoundSelect() {
        String soundInput = SC.next();
        final Set<String> options = new HashSet<>(Arrays.asList("1", "2", "3", "4", "5"));
        while (!options.contains(soundInput)) {
            System.out.println("Invalid input, try again. valid inputs are 1 - 5");
            soundInput = SC.next();
        }
        return soundInput;
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


    public static void inGameHelp() throws Exception {
        ReadExternalFiles.readText("inGameHelp.txt");
    }


}



