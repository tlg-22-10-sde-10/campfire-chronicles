
public class Camp {
    /**
     * This method is responsible for initiating the beginning of the game, it will display the home page & allow user to chose from the different options
     * 1. new game 2.load a previous game 3. help screen 4. exit
     */
    public static void startGame() throws Exception {
        Game.showTitle();
        UserInput.titleInput();

    }
}

