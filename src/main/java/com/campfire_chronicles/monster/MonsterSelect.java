package com.campfire_chronicles.monster;


import com.campfire_chronicles.character.CharacterSelect;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Map;

public class MonsterSelect {
    private String name;
    private String intro;
    private String description;
    private String stage_entrance;
    private String player_loss;
    private String player_win;
    private String player_escape;
    public static final Map<String, MonsterSelect> monsterMap;

    static {
            try (Reader reader = new InputStreamReader(MonsterSelect.class.getClassLoader().getResourceAsStream("monsters.json"))) {
                Gson gson = new Gson();
                monsterMap = gson.fromJson(reader, new TypeToken<Map<String, MonsterSelect>>() {
                }.getType());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    public MonsterSelect(String selection){
        MonsterSelect monsterSelector = monsterMap.get(selection);
        this.name =  monsterSelector.name;
        this.intro =  monsterSelector.intro;
        this.description =  monsterSelector.description;
        this.stage_entrance =  monsterSelector.stage_entrance;
        this.player_loss =  monsterSelector.player_loss;
        this.player_win =  monsterSelector.player_win;
        this.player_escape =  monsterSelector.player_escape;
    }

    public String getName() {
        return name;
    }

    public String getIntro() {
        return intro;
    }

    public String getDescription() {
        return description;
    }

    public String getStage_entrance() {
        return stage_entrance;
    }

    public String getPlayer_loss() {
        return player_loss;
    }

    public String getPlayer_win() {
        return player_win;
    }

    public String getPlayer_escape() {
        return player_escape;
    }
}
