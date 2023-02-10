package com.campfire_chronicles.music;

import javax.sound.sampled.*;
import java.io.IOException;

public class MusicPlayer {
    private static Clip clip;
    private static boolean playing = false;

    public MusicPlayer(String file) {
        try {

            AudioInputStream sound = AudioSystem.getAudioInputStream(MusicPlayer.class.getResourceAsStream(file));
            clip = AudioSystem.getClip();
            clip.open(sound);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }



    public static void setVolume(float volume) {
        FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
        gainControl.setValue(volume);
    }

    public void playLoop() {
        if (!playing) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            playing = true;
        }
    }

     public void playOnce() {
        if (playing == true){
        clip.setFramePosition(0);
        clip.start();
        }

        }

    public static void toggle() {
        if (playing == true) {
            clip.stop();
            clip.flush();
            playing = false;
        }
        else {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            playing = true;
        }
    }
}
