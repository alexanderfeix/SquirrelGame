package hs.augsburg.squirrelgame.entity.plant;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.Position;

public class BadPlant extends Entity implements Plant {

    private static final int startEnergy = -300;

    public BadPlant(Position position) {
        super(position);
        this.setEnergy(startEnergy);
    }
}
