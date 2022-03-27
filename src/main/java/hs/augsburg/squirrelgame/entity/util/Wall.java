package hs.augsburg.squirrelgame.entity.util;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.Position;

public class Wall extends Entity {

    private static final int startEnergy = -30;

    public Wall(Position position) {
        super(position);
        this.setEnergy(startEnergy);
    }
}
