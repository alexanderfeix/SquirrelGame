package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.botAPI.BotController;

public abstract class MovableEntity extends Entity implements EntityContext {

    public MovableEntity(EntityType entityType, hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(entityType, position, energy);
    }
}
