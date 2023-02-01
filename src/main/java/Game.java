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

        File file = new File("src/main/java/Title.txt");

        //Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        //Declaring a string variable
        String title;


        //Condition holds true till there is character in astring
        while ((title = br.readLine()) != null) {

            System.out.println(ANSI_RED + title + ANSI_RESET);
        }
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
         * Print the main menu.
         */
        public static void displayMenuScreen() {
            System.out.println("------ Main Menu -------");
            System.out.println("Clues (select 1)");
            System.out.println("Go to Home Page (select 2)");
            System.out.println("Close Main Menu (select 3)");
            System.out.println("Help (select 4)");
            System.out.println("Quit Game (select 5)");
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

    public static void displayHelp() {
        System.out.println(" --- HELP SCREEN ----");
        System.out.println("Press 'M' for menu screen in the game.");
        System.out.println("'Yellow' represents the rooms you have unlocked.");
        System.out.println("'Red' represents the room that you haven't visited.");
        System.out.println("'Green' represents the room you are in.");
        System.out.println("Press 'C' for cheats ");
        System.out.println();
    }
}

