package com.campfire_chronicles.music;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class BackgroundMusic {
    private static Clip clip;
    private static boolean playing = false;

    public BackgroundMusic() {
        try {
            File file = new File("src/main/resources/Soft-jazzy-chords-loop.wav");
            AudioInputStream sound = AudioSystem.getAudioInputStream(file);
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

    public void play() {
        if (!playing) {
            clip.setFramePosition(0);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
            playing = true;
        }
    }

    public static void stop() {
        if (playing) {
            clip.stop();
            clip.flush();
            playing = false;
        }
    }
}
