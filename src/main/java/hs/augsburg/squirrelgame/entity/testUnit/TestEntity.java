package hs.augsburg.squirrelgame.entity.testUnit;

import hs.augsburg.squirrelgame.entity.Entity;

public class TestEntity extends Entity{
    private static final int startEnergy = 50;
    private static boolean gotActivated = false;

    public static boolean getStatus(){
        return gotActivated;
    }

    @Override
    public void nextStep() {
        gotActivated = true;
    }

    public TestEntity(hs.augsburg.squirrelgame.util.XY position) {
        super(position, startEnergy);
    }

}
