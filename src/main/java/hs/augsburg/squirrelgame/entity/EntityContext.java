package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.util.XY;

public interface EntityContext {

    void move(Entity entity, XY randomPosition);
    void createStandardMiniSquirrel(MasterSquirrel masterSquirrel, Command command);
    XY getNearbySquirrelPosition(Entity entity);
}
