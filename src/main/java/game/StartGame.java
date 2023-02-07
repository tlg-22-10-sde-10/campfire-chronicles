package game;

//import UserInputs.NavigateMap;
import user_Inputs.UserInput;
import character.CharacterSelect;

public class StartGame {
    public static void main(String[] args) throws Exception {
//        CharacterSelect.getCharacterMap();
        GameScreens.showTitle();
        UserInput.titleInput();

    }
}
