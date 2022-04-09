package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.MovableEntity;

public class BadBeast extends MovableEntity {

    private static final int startEnergy = -150;

    public BadBeast(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.BAD_BEAST, position, startEnergy);
        setEntity(this);
    }


    @Override
    public void move(Entity entity, hs.augsburg.squirrelgame.util.XY randomPosition) {

    }
}
