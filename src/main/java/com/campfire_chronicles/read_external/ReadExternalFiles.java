package com.campfire_chronicles.read_external;

import com.campfire_chronicles.character.CharacterSelect;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.campfire_chronicles.game.GameScreens;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.*;
import java.util.Map;

public class ReadExternalFiles {
    public static Map readJSONFile(String fileName) {
        try (Reader reader = new InputStreamReader(CharacterSelect.class.getClassLoader().getResourceAsStream(fileName))) {
            Gson gson = new Gson();
            Map<String,CharacterSelect> characterMap = gson.fromJson(reader, new TypeToken<Map<String,CharacterSelect>>(){}.getType());
            return characterMap;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static JSONObject getJSONFromFile(String filename) throws Exception {
        String json = "";
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while((line = reader.readLine())!= null){
                json += line;
            }
            reader.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(json);
        JSONObject jsonObject = (JSONObject) obj;

        return jsonObject;
    }

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
