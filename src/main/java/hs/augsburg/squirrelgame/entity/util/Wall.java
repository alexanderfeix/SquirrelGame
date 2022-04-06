package hs.augsburg.squirrelgame.entity.util;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;

public class Wall extends Entity {

    private static final int startEnergy = -30;

    public Wall(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.WALL, position, startEnergy);
        setEntity(this);
    }
}
