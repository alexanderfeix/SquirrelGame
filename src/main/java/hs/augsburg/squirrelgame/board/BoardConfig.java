package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.botAPI.BotControllerFactory;
import hs.augsburg.squirrelgame.botAPI.BotControllerFactoryImpl;
import hs.augsburg.squirrelgame.botimpls.Group1101FactoryImpl;
import hs.augsburg.squirrelgame.entity.EntityType;

import java.util.ArrayList;
import java.util.HashMap;

public class BoardConfig {

    public static int COLUMNS = 60;
    public static int ROWS = 60;
    public static HashMap<EntityType, Integer> SPAWN_RATES = new HashMap<>() {{
        put(EntityType.GOOD_BEAST, 16*2);
        put(EntityType.BAD_BEAST, 16*2);
        put(EntityType.GOOD_PLANT, 24*2);
        put(EntityType.BAD_PLANT, 24*2);
        put(EntityType.WALL, 10*2);
    }};

    public static int STEPS = 50;
    public static int REMAINING_STEPS = STEPS;
    public static int CURRENT_ROUND = 1;

    public static HashMap<String, Class<? extends BotControllerFactory>> MASTER_BOT_IMPLEMENTATIONS = new HashMap<>() {{
        put("DistanceCheck_v1.0#1", BotControllerFactoryImpl.class);
        put("DistanceCheck_v1.0#2", BotControllerFactoryImpl.class);
        put("RandomMove#1", Group1101FactoryImpl.class);
        put("RandomMove#2", Group1101FactoryImpl.class);
    }};

    public static HashMap<String, ArrayList<Integer>> HIGHSCORES = new HashMap<>();
}
