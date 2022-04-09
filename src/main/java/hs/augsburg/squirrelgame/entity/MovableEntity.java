package hs.augsburg.squirrelgame.entity;

public abstract class MovableEntity extends Entity implements EntityContext {

    public MovableEntity(EntityType entityType, hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(entityType, position, energy);
    }

    public void nextStep(EntityContext entityContext) {
        entityContext.move(getEntity(), getPosition().getRandomPosition());
    }
}
