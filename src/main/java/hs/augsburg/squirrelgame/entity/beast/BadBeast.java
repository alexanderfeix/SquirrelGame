package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.MovableEntity;
import hs.augsburg.squirrelgame.util.XY;

public class BadBeast extends MovableEntity {

    private static final int startEnergy = -150;

    public BadBeast(XY position) {
        super(position);
        this.setEnergy(startEnergy);
    }

}
