package character;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class CharacterSelect {
    private String item;
    private String name;
    private String description;
    private long speed;
    private long stealth;
    private long perception;
    private long strength;
    private long wisdom;
    private String inventory;
    public static final Map<String, CharacterSelect> characterMap;

    static {
        try (Reader reader = new InputStreamReader(CharacterSelect.class.getClassLoader().getResourceAsStream("Characters.json"))) {
            Gson gson = new Gson();
            characterMap = gson.fromJson(reader, new TypeToken<Map<String, CharacterSelect>>() {
            }.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CharacterSelect() {
    }

    public CharacterSelect(String selection) throws InterruptedException {


        CharacterSelect characterSelector = characterMap.get(selection);


        this.name = characterSelector.name;
        this.description = characterSelector.description;
        this.speed = characterSelector.speed;
        this.stealth = characterSelector.stealth;
        this.perception = characterSelector.perception;
        this.strength = characterSelector.strength;
        this.wisdom = characterSelector.wisdom;
        this.inventory = characterSelector.item;
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


    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }

    public long getSpeed() {
        return speed;
    }


    public long getStealth() {
        return stealth;
    }


    public long getPerception() {
        return perception;
    }


    public long getStrength() {
        return strength;
    }


    public long getWisdom() {
        return wisdom;
    }


    public String getInventory() {
        return inventory;
    }


    public static Map<String, CharacterSelect> getCharacterMap() {
        return characterMap;
    }


}
