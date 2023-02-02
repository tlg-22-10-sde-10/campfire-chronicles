package Character;

import ReadExternal.readExternalFiles;
import org.json.simple.JSONObject;

import java.util.Map;

import static java.lang.Integer.parseInt;

public class CharacterSelect {
    private String name;
    private Long speed;
    private Long stealth;
    private Long perception;
    private Long strength;
    private Long wisdom;
    private static String location;
    private String[] inventory;
    public static JSONObject jsonObject;

    static {
        try {
            jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/Characters.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public CharacterSelect(String selection) {

        Map characterSelector = (Map) jsonObject.get(selection);
        Map characterStats = (Map) characterSelector.get("stats");
        this.name = (String) characterSelector.get("name");
        this.speed = (Long) characterStats.get("speed");
        this.stealth = (Long) characterStats.get("stealth");
        this.perception = (Long) characterStats.get("perception");
        this.strength = (Long) characterStats.get("strength");
        this.wisdom = (Long) characterStats.get("wisdom");
        System.out.println("name: " + name);
        System.out.println("speed: " +speed);
        System.out.println("stealth: " +stealth);
        System.out.println();

    }

    public static void main(String[] args) {
        CharacterSelect character = new CharacterSelect("3");
        System.out.println(character.name);
        System.out.println(character.speed);
        System.out.println(character.stealth);
        System.out.println(character.perception);
        System.out.println(character.strength);
        System.out.println(character.wisdom);


    }

}
