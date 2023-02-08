package com.campfire_chronicles.item;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class Item {
    private String name;
    private String description;
    private String correct_use;
    private String incorrect_use;
    private String action;
    public static final Map<String, Item> itemsMap;

    static {
        try (Reader reader = new InputStreamReader(Item.class.getClassLoader().getResourceAsStream("items.json"))) {
            Gson gson = new Gson();
            itemsMap = gson.fromJson(reader, new TypeToken<Map<String,Item>>(){}.getType());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Item(String selection){
        Item item = itemsMap.get(selection);
        this.name = item.name;
        this.description = item.description;
        this.correct_use = item.correct_use;
        this.incorrect_use = item.incorrect_use;
        this.action = item.action;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getCorrect_use() {
        return correct_use;
    }

    public String getIncorrect_use() {
        return incorrect_use;
    }

    public String getAction() {
        return action;
    }

}
