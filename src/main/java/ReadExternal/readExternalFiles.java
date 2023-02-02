package ReadExternal;

import java.io.*;

public class readExternalFiles {
    public static String getJSONFromFile(String filename){
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
        return json;
    }
    public static void readText(String filename) throws Exception {
        File file = new File(filename);
        BufferedReader br = new BufferedReader(new FileReader(file));
        String text;
        while ((text = br.readLine())!= null) {
            for (int i = 0; i < text.length(); i++) {
                System.out.printf("%c", text.charAt(i));
                try {
                    Thread.sleep(10);//0.5s pause between characters
                } catch (InterruptedException ex) {
                    Thread.currentThread().interrupt();
                }
            }
        }
    }
}
