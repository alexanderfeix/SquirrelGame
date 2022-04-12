package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.MovableEntity;

public class GoodBeast extends MovableEntity {

    private static final int startEnergy = 300;

    public GoodBeast(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.GOOD_BEAST, position, startEnergy);
        setEntity(this);
    }

    public void nextStep(EntityContext entityContext){
        if(getMoveCounter() != 0){
            setMoveCounter(getMoveCounter()-1);
            System.out.println("Current MoveCounter Good Beast: " + getMoveCounter());
            return;
        }
        entityContext.move(getEntity(), getPosition().getRandomPosition());
        setMoveCounter(4);
    }

    public void onCollision(Entity enemy){

    }

    @Override
    public void move(Entity entity, hs.augsburg.squirrelgame.util.XY movePosition) {

    }
}
