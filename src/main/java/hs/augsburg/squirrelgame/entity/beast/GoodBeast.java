package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.MovableEntity;

public class GoodBeast extends MovableEntity {

    private static final int startEnergy = 300;

    public GoodBeast(hs.augsburg.squirrelgame.util.XY position) {
        super(position, startEnergy);
    }
}
