package hs.augsburg.squirrelgame.entity.plant;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;

public class BadPlant extends Entity {

    private static final int startEnergy = -300;

    public BadPlant(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.BAD_PLANT, position, startEnergy);
        setEntity(this);
    }
}
