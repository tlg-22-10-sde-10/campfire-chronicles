package UserInputs;

import Game.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import Character.*;
import ReadExternal.readExternalFiles;

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
            }
            catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print(">>> ");
        UserInput newUserInput = new UserInput();
        final String homePageInput = Game.getMainMenu();
        if (homePageInput.equals("1")) {
            UserInput.CharectorInput();
        }
        else if (homePageInput.equals("2")) {
            System.out.println("Development in progress");
            UserInput.helpInput();
        }
        else if (homePageInput.equals("3")) {
            Game.displayHelp();
        }
        else if (homePageInput.equals("4")) {
        }
        else {System.out.println("Wrong input, try again by typing a digit from 1-4");}
    }
    private static void CharectorInput() throws Exception {
        CharacterSelect player = null;
        String inventory = null;
        System.out.println("New Game has been selected");
        System.out.println("Jock  (select 1)      Popular Girl (select 2)      Band Camp Nerd (select 3)      Easy Kid (select 4)     Back to Main(select 5)   Quit(select 6)");
        UserInput newUserInput = new UserInput();
        System.out.print(">>> ");
        final String CharectorSelectionInput = Game.getCharacterSelect();
        if (CharectorSelectionInput.equals("1")) {
            player = new CharacterSelect("1");
            inventory = player.getInventory("1");
        }
        else if (CharectorSelectionInput.equals("2")) {
            player = new CharacterSelect("2");
            inventory = player.getInventory("2");
        }
        else if (CharectorSelectionInput.equals("3")) {
            player = new CharacterSelect("3");
            inventory = player.getInventory("3");
        }
        else if (CharectorSelectionInput.equals("4")) {
            player = new CharacterSelect("4");
            inventory = player.getInventory("4");
        }
        else if(CharectorSelectionInput.equals("5")) {
            titleInput();
        }
        else if(CharectorSelectionInput.equals("6")) {
            System.out.println("Thanks for Playing our Game");
            System.exit(0);
        }
        else {System.out.println("Wrong input, try again by typing a digit from 1-6");}
        JournalInput(inventory);
    }

    private static void JournalInput(String inventory) throws Exception {
        readExternalFiles.readText("src/main/ExternalFiles/Opening.txt");
        System.out.println("\nYou pick to read:");
        System.out.println("Red Journal  (select 1)      Brown Journal (select 2)      Blue Journal (select 3)      Back to Character Selection (select 4)     Back to Main(select 5)   Quit(select 6)");
        UserInput newUserInput = new UserInput();
        System.out.print(">>> ");
        final String JournalselectionInput = Game.getJournalSelect();
        if (JournalselectionInput.equals("1")) {
            System.out.println("Suddenly, as you flip through the pages, a swarm of bats flew out of the journal, swarming all around you.\n In the midst of the chaos, a figure appeared, shrouded in darkness. A Vampire has appeared.");
        }
        else if (JournalselectionInput.equals("2")) {
            System.out.println("When you open the journal and hear a howl that echoes through the forest, bouncing off the trees and lingering in the air long after the sound has died down.\n Suddenly a large figure emerges from the shadow. Its fur standing on end and its eyes glowing in the dark. A Werewolf has appeared.");
        }
        else if (JournalselectionInput.equals("3")) {
            System.out.println("As you open the journal two translucent hands reach out to grab you. You dropped the journal and watch a ghostly figure emerge from the journal and take shape before you eyes. A Ghost has appeared");
        }
        else if (JournalselectionInput.equals("4")) {
            CharectorInput();
        }
        else if(JournalselectionInput.equals("5")) {
            titleInput();
        }
        else if(JournalselectionInput.equals("6")) {
            System.out.println("Thanks for Playing our Game");
            System.exit(0);
        }
        GameLogic.startGame(inventory);
    }


    public static String getAction() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String toParse;
        String action;
            System.out.print("Enter Command>> ");
            toParse = input.readLine();
            action = parseInput(toParse);
        return action;
    }

    private static String parseInput(String toParse) {
        List<String> words = new ArrayList<>();
        String lowerCase = toParse.trim().toLowerCase();
        String output = "you do nothing";
        if (lowerCase.equals("")) {
            return output;
        }
        else if (lowerCase.equals("help")) {
            output = "help";
        }
        else if (lowerCase.equals("quit")) {
            output = "quit";
        }
        else if (lowerCase.equals("status")) {
            output = "status";
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
    private static String processVerbNoun(List<String> words) {
        String verb = words.get(0);
        String noun = words.get(words.size() - 1);
        String output;
        List<String> commands = new ArrayList<>(Arrays.asList("attack", "move", "look", "hide", "use", "go"));
        List<String> objects = new ArrayList<>(Arrays.asList("north", "south", "east", "west", "bat", "drink", "key", ""));
        if (commands.contains(verb) && objects.contains(noun)) {
            output = verb + " " + noun;
        } else {
            output = "invalid command";
        }
        return output;
    }

    public static void helpInput() throws Exception {
        System.out.println("Main-Menu (select 1)      Quit(select 2) ");
        System.out.print(">>> ");
        UserInput newUserInput = new UserInput();
        final String helpPageInput = Game.getHelpSelect();
        if (helpPageInput.equals("1")) {
            titleInput();
        }
        else if (helpPageInput.equals("2")) {
            System.out.println("Thanks for playing");

        }
    }
}
