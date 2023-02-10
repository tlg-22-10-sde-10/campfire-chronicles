package com.campfire_chronicles.map;

import com.campfire_chronicles.character.CharacterSelect;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class MapLocation<S, M> {
    private String name;
    private String id;
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
        this.name = location.name;
        this.id = location.id;
        this.north = location.north;
        this.east = location.east;
        this.south = location.south;
        this.west = location.west;
        this.item = location.item;
        this.description = location.description;
    }
    public String getName() {
        return name;
    }
    public String getId() {
        return id;
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

    public void setItem(String item) {
        this.item = item;
    }

    public String getDescription() {
        return description;
    }

    public static void main(String[] args) {
        new MapLocation<>("campfire").getDescription();
    }
}
