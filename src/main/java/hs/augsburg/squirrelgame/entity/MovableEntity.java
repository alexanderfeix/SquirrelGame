package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.util.Direction;

import java.util.Random;

public abstract class MovableEntity extends Entity implements EntityContext {

    public MovableEntity(EntityType entityType, hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(entityType, position, energy);
    }

    public void nextStep(EntityContext entityContext) {
        Random random = new Random();
        int nextInt = random.nextInt(8);
        if (nextInt == 0) {
            entityContext.move(getEntity(), Direction.UP);
        } else if (nextInt == 1) {
            entityContext.move(getEntity(), Direction.UP_LEFT);
        } else if (nextInt == 2) {
            entityContext.move(getEntity(), Direction.UP_RIGHT);
        } else if (nextInt == 3) {
            entityContext.move(getEntity(), Direction.RIGHT);
        } else if (nextInt == 4) {
            entityContext.move(getEntity(), Direction.DOWN);
        } else if (nextInt == 5) {
            entityContext.move(getEntity(), Direction.DOWN_LEFT);
        } else if (nextInt == 6) {
            entityContext.move(getEntity(), Direction.DOWN_RIGHT);
        } else {
            entityContext.move(getEntity(), Direction.LEFT);
        }
    }
}
