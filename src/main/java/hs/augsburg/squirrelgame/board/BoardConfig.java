package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.EntityType;
import java.util.HashMap;

public class BoardConfig {

    public static int COLUMNS = 20;
    public static int ROWS = 18;
    public static int GAME_SIZE = COLUMNS * ROWS;
    public static HashMap<EntityType, Integer> SPAWN_RATES = new HashMap<>(){{
        put(EntityType.MASTER_SQUIRREL, 1);
        put(EntityType.GOOD_BEAST, 3);
        put(EntityType.BAD_BEAST, 3);
        put(EntityType.GOOD_PLANT, 3);
        put(EntityType.BAD_PLANT, 3);
        //put(EntityType.WALL, 10);
    }};

}
