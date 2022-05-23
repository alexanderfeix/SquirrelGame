package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.XY;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    EntityType getEntity(XY xy);
    void move(XY direction);
    void spawnMiniBot(XY direction, int energy);
    int getEnergy();

}