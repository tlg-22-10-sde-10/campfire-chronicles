package com.campfire_chronicles.user_Inputs;

import com.campfire_chronicles.character.CharacterSelect;
import com.campfire_chronicles.game.GameLogic;
import com.campfire_chronicles.game.GameScreens;
import com.campfire_chronicles.read_external.ReadExternalFiles;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import static com.campfire_chronicles.game.GameLogic.showStatus;
import static com.campfire_chronicles.game.GameScreens.inGameHelp;

public class UserInput {
    public UserInput() {
    }

    public static void titleInput() throws Exception {
        System.out.println("Please select an option: ");
        String text = "\" New Game (select 1)      Developer Information (select 2)      Help Screen (select 3)      Quit(select 4)\"\n";
        //Iterating String and printing one character at a time.
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            try {
                Thread.sleep(5);//0.5s pause between characters
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print(">>> ");

        final String homePageInput = GameScreens.getMainMenu();
        if (homePageInput.equals("1")) {
            UserInput.CharacterInput();
        } else if (homePageInput.equals("2")) {
            System.out.println("Development in progress");
            UserInput.helpInput();
        } else if (homePageInput.equals("3")) {
            GameScreens.displayHelp();
        } else if (homePageInput.equals("4")) {
        } else {
            System.out.println("Wrong input, try again by typing a digit from 1-4");
        }
    }

    private static void CharacterInput() throws Exception {
        CharacterSelect player = null;
        System.out.println("New Game has been selected");
        System.out.println("Jock  (select 1)      Popular Girl (select 2)      Band Camp Nerd (select 3)      Easy Kid (select 4)     Back to Main(select 5)   Quit(select 6)");
        UserInput newUserInput = new UserInput();
        System.out.print(">>> ");
        final String CharacterSelectionInput = GameScreens.getCharacterSelect();
        if (CharacterSelectionInput.equals("1")) {
            player = new CharacterSelect("1");
        } else if (CharacterSelectionInput.equals("2")) {
            player = new CharacterSelect("2");
        } else if (CharacterSelectionInput.equals("3")) {
            player = new CharacterSelect("3");
        } else if (CharacterSelectionInput.equals("4")) {
            player = new CharacterSelect("4");
        } else if (CharacterSelectionInput.equals("5")) {
            titleInput();
        } else if (CharacterSelectionInput.equals("6")) {
            System.out.println("Thanks for Playing our Game");
            System.exit(0);
        } else {
            System.out.println("Wrong input, try again by typing a digit from 1-6");
        }
        JournalInput(player);
    }
/*Need to fix method in ReadExternalFiles*/
    private static void JournalInput(CharacterSelect player) throws Exception {
        ReadExternalFiles.readText("Opening.txt");
        System.out.println("\nYou pick to read:");
        System.out.println("Red Journal  (select 1)      Brown Journal (select 2)      Blue Journal (select 3)      Back to Character Selection (select 4)     Back to Main(select 5)   Quit(select 6)");
        System.out.print(">>> ");
        final String JournalSelectionInput = GameScreens.getJournalSelect();
        if (JournalSelectionInput.equals("1")) {
            System.out.println("Suddenly, as you flip through the pages, a swarm of bats flew out of the journal, swarming all around you.\n In the midst of the chaos, a figure appeared, shrouded in darkness. A Vampire has appeared.");
        } else if (JournalSelectionInput.equals("2")) {
            System.out.println("When you open the journal and hear a howl that echoes through the forest, bouncing off the trees and lingering in the air long after the sound has died down.\n Suddenly a large figure emerges from the shadow. Its fur standing on end and its eyes glowing in the dark. A Werewolf has appeared.");
        } else if (JournalSelectionInput.equals("3")) {
            System.out.println("As you open the journal two translucent hands reach out to grab you. You dropped the journal and watch a ghostly figure emerge from the journal and take shape before you eyes. A Ghost has appeared");
        } else if (JournalSelectionInput.equals("4")) {
            CharacterInput();
        } else if (JournalSelectionInput.equals("5")) {
            titleInput();
        } else if (JournalSelectionInput.equals("6")) {
            System.out.println("Thanks for Playing our Game");
            System.exit(0);
        }
        GameLogic.startGame(player);
    }

    public static void helpInput() throws Exception {
        System.out.println("Main-Menu (select 1)      Quit(select 2) ");
        System.out.print(">>> ");
        UserInput newUserInput = new UserInput();
        final String helpPageInput = GameScreens.getHelpSelect();
        if (helpPageInput.equals("1")) {
            titleInput();
        } else if (helpPageInput.equals("2")) {
            System.out.println("Thanks for playing");

        }
    }

    /* getAction() takes and parses a user input so that only valid commands are accepted into the game */
    public static String getAction() throws Exception {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String toParse;
        String action;
        System.out.print("Enter Command>> ");
        toParse = input.readLine();
        action = parseInput(toParse);
        return action;
    }

    /* parseInput() identifies valid commands */
    private static String parseInput(String toParse) throws Exception {
        List<String> words = new ArrayList<>();
        String lowerCase = toParse.trim().toLowerCase();
        String output = "you do nothing";
        if (lowerCase.equals("")) {
            System.out.println(output);
            return output;
        } else if (lowerCase.equals("help")) {
            inGameHelp();
            output = "help";
            return output;
        } else if (lowerCase.equals("quit")) {
            output = "quit";
            return output;
        } else if (lowerCase.equals("status")) {
            showStatus();
            output = "status";
            return output;
        }else if (lowerCase.equals("com/campfire_chronicles/map")) {
            output = "com/campfire_chronicles/map";
            return output;
        }
        else {
            String delimins = " ,.:;!?|\"";
            StringTokenizer tokenizer = new StringTokenizer(lowerCase, delimins);
            String word;
            while (tokenizer.hasMoreTokens()) {
                word = tokenizer.nextToken();
                words.add(word);
            }
            output = processVerbNoun(words);
        }
        return output;
    }

    /* precessVerbNoun processes the user command to output a verb noun combination, using only valid verbs */
    private static String processVerbNoun(List<String> words) {
        String verb = words.get(0);
        String noun = words.get(words.size() - 1);
        String output;
        List<String> commands = new ArrayList<>(Arrays.asList("attack", "move", "look", "hide", "use", "go", "search", "com/campfire_chronicles/map"));
        if (commands.contains(verb)) {
            output = verb + " " + noun;
        }
        else {
            System.out.println("clearly you didn't read the help text");
            output = "try using \"help\"";
        }
        return output;
    }
    public static boolean quitInput() throws Exception {
        System.out.println("Are you Sure You want to Quit");
        System.out.println(" Select 1 for Yes and 2 for No");
        System.out.print(">>> ");
//        UserInput newUserInput = new UserInput();
        final String quitPageInput = GameScreens.getQuitSelect();
        if (quitPageInput.equals("1")) {
            System.out.println("Thanks for Playing our Game");
            System.exit(0);
        }    else if (quitPageInput.equals("2")) {
            return true;    }
        return false;}

}
