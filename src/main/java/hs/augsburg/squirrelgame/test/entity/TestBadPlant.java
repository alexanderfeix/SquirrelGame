package hs.augsburg.squirrelgame.test.entity;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.util.XY;

public class TestBadPlant extends BadPlant {
    public TestBadPlant() {
        super(new XY(1, 2));
    }

    public void nextStep(EntityContext entityContext){
        //entityContext.move(getEntity(), new XY(2, 1));
    }

}
