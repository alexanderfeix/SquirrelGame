package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.Position;

public class BadBeast extends Entity implements Beast {

    private static final int startEnergy = -150;

    public BadBeast(Position position) {
        super(position);
        this.setEnergy(startEnergy);
    }

    @Override
    public void move(Position position) {

    }
}
