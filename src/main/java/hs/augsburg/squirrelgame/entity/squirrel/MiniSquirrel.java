package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.entity.*;
import hs.augsburg.squirrelgame.util.XY;

public class MiniSquirrel extends MovableEntity {

    public MiniSquirrel(hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(EntityType.MINI_SQUIRREL, position, energy);
        setEntity(this);
    }

    @Override
    public void move(Entity entity, hs.augsburg.squirrelgame.util.XY randomPosition) {

    }

    @Override
    public hs.augsburg.squirrelgame.util.XY getNearbySquirrelPosition(Entity entity) {
        return null;
    }

    public void nextStep(EntityContext entityContext){
        entityContext.move(getEntity(), getPosition().getRandomNearbyPosition());
    }

    public void onCollision(Entity enemy){
        if (enemy.getEntityType() == EntityType.WALL) {
            getEntity().updateEnergy(-10);
            setMoveCounter(3);
            System.out.println("Collided with Wall!");
        }
    }
}
