package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.XY;

public class MiniSquirrel extends Entity implements Squirrel {

    public MiniSquirrel(XY position, int startEnergy) {
        super(position);
        this.setEnergy(startEnergy);
    }


    @Override
    public void move(XY position) {

    }
}
