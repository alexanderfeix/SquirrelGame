package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.EntityType;

import java.util.HashMap;

public class BoardConfig {

    public static int COLUMNS = 60;
    public static int ROWS = 60;
    public static HashMap<EntityType, Integer> SPAWN_RATES = new HashMap<>() {{
        put(EntityType.GOOD_BEAST, 16);
        put(EntityType.BAD_BEAST, 6);
        put(EntityType.GOOD_PLANT, 24);
        put(EntityType.BAD_PLANT, 6);
    }};

}
