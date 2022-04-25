package hs.augsburg.squirrelgame.entity;


import hs.augsburg.squirrelgame.util.XY;

public interface EntityContext {

    void move(Entity entity, XY randomPosition);
    XY getNearbySquirrelPosition(Entity entity);
}
