package hs.augsburg.squirrelgame.test.entity;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.util.XY;

public class TestGoodPlant extends GoodPlant {
    public TestGoodPlant() {
        super(new XY(3, 2));
    }

    public void nextStep(EntityContext entityContext){
        //entityContext.move(getEntity(), new XY(2, 1));
    }


}
