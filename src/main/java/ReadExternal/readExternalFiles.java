package ReadExternal;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class readExternalFiles {
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
    public static void readText(String filename) throws Exception {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String text;
        while ((text = br.readLine())!= null) {
                try {
                    System.out.println(text);
                    Thread.sleep(5);//0.5s pause between characters
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }

        }
    }
}
