import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class Game {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001B[31m";

    public void showTitle() throws Exception {
        //File path is passed as parameter
        File file = new File("C:\\TLG\\Game\\src\\main\\Title.txt");

        //Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        //Declaring a string variable
        String title;

        //Condition holds true till there is character in astring
        while ((title = br.readLine()) !=null){
            System.out.println(ANSI_RED + title + ANSI_RESET);
        }
        String text = "Press Enter to play";
        //Iterating String and printing one character at a time.
        for(int i = 0; i < text.length(); i++){
            System.out.printf("%c", text.charAt(i));
            try{
                Thread.sleep(500);//0.5s pause between characters
            }catch(InterruptedException ex){
                Thread.currentThread().interrupt();
            }
        }

    }

}

class GameMain{
    public static void main(String[] args) throws Exception {
        Game game = new Game();
        game.showTitle();
    }
}