package hs.augsburg.squirrelgame.entity.plant;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.EntityType;

public class BadPlant extends Entity {

    private static final int startEnergy = -300;

    public BadPlant(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.BAD_PLANT, position, startEnergy);
        setEntity(this);
    }

    public void nextStep(EntityContext entityContext){

    }

    public void onCollision(Entity enemy, Board board){

    }

}
