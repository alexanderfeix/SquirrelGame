package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.entity.MovableEntity;
import hs.augsburg.squirrelgame.util.XY;

public class MiniSquirrel extends MovableEntity {

    public MiniSquirrel(XY position, int startEnergy) {
        super(position);
        this.setEnergy(startEnergy);
    }
}
