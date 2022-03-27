package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.Position;

public class GoodBeast extends Entity implements Beast {

    private static final int startEnergy = 300;

    public GoodBeast(Position position) {
        super(position);
        this.setEnergy(startEnergy);
    }

    @Override
    public void move(Position position) {

    }
}
