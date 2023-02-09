package com.campfire_chronicles.read_external;


import com.campfire_chronicles.game.GameScreens;


import java.io.*;


public class ReadExternalFiles {

    public static void readText(String filename)  {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(GameScreens.class.getClassLoader().getResourceAsStream(filename)))){
            String text;
            while ((text = reader.readLine())!= null) {
                System.out.println(text);
                 Thread.sleep(5);//0.5s pause between characters
            }
    } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }



}
