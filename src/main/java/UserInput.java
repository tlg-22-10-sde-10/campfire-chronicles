import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class UserInput {
    public UserInput() {
    }
    public static void titleInput() throws Exception {
        UserInput newUserInput = new UserInput();
        final String homePageInput = Game.getHomePage();
        if (homePageInput.equals("1")) {
            System.out.println("New Game has been selected");
            newUserInput.getAction();

        } else if (homePageInput.equals("2")) {
            System.out.println("Developement in progress");

        } else if (homePageInput.equals("3")) {
            Game.displayHelp();
        } else if (homePageInput.equals("4")) {
        } else {
            System.out.println("Wrong input, try again by typing a digit from 1-4");
        }
    }

    public String getAction() throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String toParse;
        String action;
        do {
            System.out.print("Command: ");
            toParse = input.readLine();
            action = parseInput(toParse);
            System.out.println(action);

        }
        while (!toParse.equals("quit"));

        return action;
    }

    private String parseInput(String toParse) {
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

    private String processVerbNoun(List<String> words) {
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
}