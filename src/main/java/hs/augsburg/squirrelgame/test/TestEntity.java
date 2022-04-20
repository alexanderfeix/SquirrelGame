package hs.augsburg.squirrelgame.test;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;

public class TestEntity extends Entity {
    private static final int startEnergy = 50;
    private static boolean gotActivated = false;

    public TestEntity(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.TEST, position, startEnergy);
    }

    public static boolean getStatus() {
        return gotActivated;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        gotActivated = true;
    }

}