package com.campfire_chronicles.game;
import com.campfire_chronicles.music.MusicPlayer;
import com.campfire_chronicles.user_Inputs.UserInput;

public class StartGame {
    public static void main(String[] args) throws Exception {
        GameScreens.showTitle();
        new MusicPlayer("/Soft-jazzy-chords-loop.wav").playLoop();
        UserInput.titleInput();

    }
}
