package hs.augsburg.squirrelgame.entity.plant;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.Position;

public class GoodPlant extends Entity implements Plant {

    private static final int startEnergy = 150;

    public GoodPlant(Position position) {
        super(position);
        this.setEnergy(startEnergy);
    }
}
