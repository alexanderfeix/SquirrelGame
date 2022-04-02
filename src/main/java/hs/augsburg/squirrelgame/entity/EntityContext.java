package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.util.Direction;

public interface EntityContext {

    void move(Entity entity, Direction direction);

}
