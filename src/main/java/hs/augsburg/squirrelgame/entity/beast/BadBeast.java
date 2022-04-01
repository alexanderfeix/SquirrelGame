package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.MovableEntity;

public class BadBeast extends MovableEntity {

    private static final int startEnergy = -150;

    public BadBeast(hs.augsburg.squirrelgame.util.XY position) {
        super(position, startEnergy);
    }
}
