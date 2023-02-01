public class Camp {
    /**
     * This method is responsible for initiating the beginning of the game, it will display the home page & allow user to chose from the different options
     * 1. new game 2.load a previous game 3. help screen 4. exit
     */
    public static void startGame() throws Exception {
        Game.showTitle();
        final String homePageInput = Game.getHomePage();

        if (homePageInput.equals("1")) {

            System.out.println("New Game has been selected");

        } else if (homePageInput.equals("2")) {
            System.out.println("Developement in progress");

        } else if (homePageInput.equals("3")) {
            Game.displayHelp();
        } else if (homePageInput.equals("4")) {
        } else {
            System.out.println("Wrong input, try again by typing a digit from 1-4");
        }
    }
//    /**
//     * Will display a main-menu screen
//     * @param
//     * @return boolean
//     */
//    public static boolean menu() throws Exception {
//        Game.displayMenuScreen();
//        final String menuChoice = Game.getMainMenu();
//
//        if (menuChoice.equals("1")) {
//            System.out.println("new game");
//            return true;
//        } else if (menuChoice.equals("2")) {
//            startGame();
//            return true;
//        } else if (menuChoice.equals("3")) {
//            return true;
//        } else if (menuChoice.equals("4")) {
//            Game.displayHelp();
//        } else {
//            return false;
//        }
//        return true;
//    }


}

