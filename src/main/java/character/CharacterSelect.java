package character;

import com.google.gson.Gson;
import com.google.gson.annotations.SerializedName;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.lang.Integer.parseInt;

public class CharacterSelect {
    private String name;
    private String description;
    private long speed;
    private long stealth;
    private long perception;
    private long strength;
    private long wisdom;
    @SerializedName("item")
    private String inventory;
    public static JSONObject jsonObject;
    public static final Map<String, CharacterSelect> characterMap;
    public static final List<CharacterSelect> characterList;

    static {
        try (Reader reader = new InputStreamReader(CharacterSelect.class.getClassLoader().getResourceAsStream("Characters.json"))) {
         Gson gson = new Gson();
         characterMap = gson.fromJson(reader, new TypeToken<Map<String,CharacterSelect>>(){}.getType());
         characterList = new ArrayList<CharacterSelect>(characterMap.values());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public CharacterSelect() {
    }

/*
    public CharacterSelect(String selection) throws InterruptedException {

        Map characterSelector = (Map) jsonObject.get(selection);
        Map characterStats = (Map) characterSelector.get("stats");
        this.name = (String) characterSelector.get("name");
        this.description = (String) characterSelector.get("description");
        this.speed = (long) characterStats.get("speed");
        this.stealth = (long) characterStats.get("stealth");
        this.perception = (long) characterStats.get("perception");
        this.strength = (long) characterStats.get("strength");
        this.wisdom = (long) characterStats.get("wisdom");
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
*/

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getSpeed() {
        return speed;
    }

    public void setSpeed(long speed) {
        this.speed = speed;
    }

    public long getStealth() {
        return stealth;
    }

    public void setStealth(long stealth) {
        this.stealth = stealth;
    }

    public long getPerception() {
        return perception;
    }

    public void setPerception(long perception) {
        this.perception = perception;
    }

    public long getStrength() {
        return strength;
    }

    public void setStrength(long strength) {
        this.strength = strength;
    }

    public long getWisdom() {
        return wisdom;
    }

    public void setWisdom(long wisdom) {
        this.wisdom = wisdom;
    }

    public String getInventory() {
        return inventory;
    }

    public void setInventory(String inventory) {
        this.inventory = inventory;
    }

    public static Map<String, CharacterSelect> getCharacterMap() {
        return characterMap;
    }


    public static List<CharacterSelect> getCharacterList() {
        return characterList;
    }
}
