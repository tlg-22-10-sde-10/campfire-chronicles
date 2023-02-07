package Character;

import ReadExternal.readExternalFiles;
import org.json.simple.JSONObject;

import java.util.Arrays;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class CharacterSelect {
    private String name;
    private String description;
    private Long speed;
    private Long stealth;
    private Long perception;
    private Long strength;
    private Long wisdom;
    private static String location;
    private String inventory;
    public static JSONObject jsonObject;

    static {
        try {
            jsonObject = readExternalFiles.getJSONFromFile("src/main/ExternalFiles/Characters.json");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public CharacterSelect(String selection) throws InterruptedException {

        Map characterSelector = (Map) jsonObject.get(selection);
        Map characterStats = (Map) characterSelector.get("stats");
        this.name = (String) characterSelector.get("name");
        this.description = (String) characterSelector.get("description");
        this.speed = (Long) characterStats.get("speed");
        this.stealth = (Long) characterStats.get("stealth");
        this.perception = (Long) characterStats.get("perception");
        this.strength = (Long) characterStats.get("strength");
        this.wisdom = (Long) characterStats.get("wisdom");
        this.inventory = (String) characterSelector.get("item");
        System.out.println("You chose character:");
        System.out.println("name: " + name);
        System.out.println("description: " + description);
        System.out.println("Stats");
        //System.out.println("-----------------");
        System.out.println("| speed: " + speed + "      |");
        System.out.println("| stealth: " + stealth + "    |");
        System.out.println("| perception: " + perception + " |");
        System.out.println("| strength: " + strength + "   |");
        System.out.println("| wisdom: " + wisdom + "     |");
        // System.out.println("------------");
        System.out.println("Inventory: " + inventory);
        System.out.println();
        Thread.sleep(1500);

    }

    public String getInventory() {
        return inventory;
    }

    public String getInventory(String selection) {
        Map characterSelect = (Map) jsonObject.get(selection);
        this.inventory = (String) characterSelect.get("item");
        return inventory;
    }


    public static void main(String[] args) throws InterruptedException {
        CharacterSelect character = new CharacterSelect("3");
        System.out.println(character.name);
        System.out.println(character.speed);
        System.out.println(character.stealth);
        System.out.println(character.perception);
        System.out.println(character.strength);
        System.out.println(character.wisdom);


    }
}
