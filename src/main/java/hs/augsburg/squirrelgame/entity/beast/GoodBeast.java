package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.XY;

public class GoodBeast extends Entity implements Beast {

    private static final int startEnergy = 300;

    public GoodBeast(XY position) {
        super(position);
        this.setEnergy(startEnergy);
    }

    @Override
    public void move(XY position) {

    }
}
