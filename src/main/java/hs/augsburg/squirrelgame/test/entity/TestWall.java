package hs.augsburg.squirrelgame.test.entity;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.util.Wall;

public class TestWall extends Wall {
    public TestWall(hs.augsburg.squirrelgame.util.XY position) {
        super(position);
    }

    public void nextStep(EntityContext entityContext){

    }

    public void onCollision(Entity enemy){

    }
}
