package map;

import character.CharacterSelect;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class MapLocation<S, M> {
    private String north;
    private String east;
    private String south;
    private String west;
    private String item;
    private String description;
    public static final java.util.Map<String, MapLocation> mapperMap;

    static {

        try (Reader reader = new InputStreamReader(CharacterSelect.class.getClassLoader().getResourceAsStream("map.json"))) {
         Gson gson = new Gson();
         mapperMap = gson.fromJson(reader, new TypeToken<Map<String, MapLocation>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public MapLocation(String selection){
        MapLocation location = mapperMap.get(selection);
        this.north = location.north;
        this.east = location.east;
        this.south = location.south;
        this.west = location.west;
        this.item = location.item;
        this.description = location.description;
    }

    public String getNorth() {
        return north;
    }

    public String getEast() {
        return east;
    }

    public String getSouth() {
        return south;
    }

    public String getWest() {
        return west;
    }

    public String getItem() {
        return item;
    }

    public String getDescription() {
        return description;
    }

}
