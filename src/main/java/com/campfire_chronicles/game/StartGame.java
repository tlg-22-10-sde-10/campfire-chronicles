package com.campfire_chronicles.game;
import com.campfire_chronicles.music.BackgroundMusic;
import com.campfire_chronicles.user_Inputs.UserInput;

public class StartGame {
    public static void main(String[] args) throws Exception {
        GameScreens.showTitle();
        new BackgroundMusic().play();
        UserInput.titleInput();

    }
}
