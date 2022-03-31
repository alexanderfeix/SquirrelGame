package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.MovableEntity;
import hs.augsburg.squirrelgame.util.XY;

public class GoodBeast extends MovableEntity {

    private static final int startEnergy = 300;

    public GoodBeast(XY position) {
        super(position);
        this.setEnergy(startEnergy);
    }
}
