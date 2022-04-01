package hs.augsburg.squirrelgame.entity.plant;

import hs.augsburg.squirrelgame.entity.Entity;

public class GoodPlant extends Entity {

    private static final int startEnergy = 150;

    public GoodPlant(hs.augsburg.squirrelgame.util.XY position) {
        super(position, startEnergy);
    }
}
