package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.MovableEntity;

public class MiniSquirrel extends MovableEntity {

    public MiniSquirrel(hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(EntityType.MINI_SQUIRREL, position, energy);
        setEntity(this);
    }

    @Override
    public void move(Entity entity, hs.augsburg.squirrelgame.util.XY randomPosition) {

    }

    public void nextStep(EntityContext entityContext){
        entityContext.move(getEntity(), getPosition().getRandomPosition());
    }

    public void onCollision(Entity enemy){

    }
}
