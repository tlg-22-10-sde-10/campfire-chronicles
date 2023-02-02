package UserInputs;

import Game.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInput {
    public UserInput() {
    }
    public static void titleInput() throws Exception {
        System.out.println("Please select an option: ");
        String text = "\" New Game.Game (select 1)      Developer Information (select 2)      Help Screen (select 3)      Quit(select 4)\"\n";
        //Iterating String and printing one character at a time.
        for (int i = 0; i < text.length(); i++) {
            System.out.printf("%c", text.charAt(i));
            try {
                Thread.sleep(5);//0.5s pause between characters
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.print(">>>");
        UserInput newUserInput = new UserInput();
        final String homePageInput = Game.getMainMenu();
        if (homePageInput.equals("1")) {
//            System.out.println("New Game.Game has been selected");
//            System.out.println("Character  (select 1)      Character (select 2)      Character (select 3)      Character (select 4)     Back to Main(select 5)   Quit(select 6)");
            UserInput.CharectorInput();
            //newUserInput.getAction();

        } else if (homePageInput.equals("2")) {
            System.out.println("Development in progress");

        } else if (homePageInput.equals("3")) {
            Game.displayHelp();
        } else if (homePageInput.equals("4")) {
        } else {
            System.out.println("Wrong input, try again by typing a digit from 1-4");
        }
    }
    public static void CharectorInput() throws Exception {
        System.out.println("New Game.Game has been selected");
        System.out.println("Character  (select 1)      Character (select 2)      Character (select 3)      Character (select 4)     Back to Main(select 5)   Quit(select 6)");
        UserInput newUserInput = new UserInput();
        final String CharectirselectionInput = Game.getCharectorSelect();
        if (CharectirselectionInput.equals("1")) {
            System.out.println("You chose character 1");
        } else if (CharectirselectionInput.equals("2")) {
            System.out.println("You chose character 2");

        } else if (CharectirselectionInput.equals("3")) {
            System.out.println("You chose character 3");
        } else if (CharectirselectionInput.equals("4")) {
            System.out.println("You chose character 4");
        }else if(CharectirselectionInput.equals("5")) {
            titleInput();
        }
        else if(CharectirselectionInput.equals("6")) {
            System.out.println("Thanks for Playing our Game");
            System.exit(0);
        }
        else {
            System.out.println("Wrong input, try again by typing a digit from 1-6");
        }
        JournalInput();
    }

    public static void JournalInput() throws Exception {
        System.out.print("Choose from the following options");
        System.out.println("Vampire  (select 1)      Werwolf (select 2)      Ghost (select 3)      Back to Character Selection (select 4)     Back to Main(select 5)   Quit(select 6)");
        UserInput newUserInput = new UserInput();
        final String JournalselectionInput = Game.getJournalSelect();
        if (JournalselectionInput.equals("1")) {
            System.out.println("You chose option 1 Vampire journal ");
        } else if (JournalselectionInput.equals("2")) {
            System.out.println("You chose option 2 Werewolf journal ");
        } else if (JournalselectionInput.equals("3")) {
            System.out.println("You chose option 3 Ghost journal ");
        } else if (JournalselectionInput.equals("4")) {
        CharectorInput();
        }
        else if(JournalselectionInput.equals("5")) {
            titleInput();}
        else if(JournalselectionInput.equals("6")) {
            System.out.println("Thanks for Playing our Game");
            System.exit(0);
        }
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
            output = "Goodbye";
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
    public static void main(String[] args) {
        UserInput userInput = new UserInput();
        try {
            userInput.getAction();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void helpInput() throws Exception {
        System.out.println("Main-Menu (select 1)      Quit(select 2) ");
        System.out.print(">>>");
        UserInput newUserInput = new UserInput();
        final String helpPageInput = Game.getHelpSelect();
        if (helpPageInput.equals("1")) {
            titleInput();
        } else if (helpPageInput.equals("2")) {
            System.out.println("Thanks for playing");

        }
    }
}
