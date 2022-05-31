package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;

public interface ControllerContext {
    XY getViewLowerLeft();
    XY getViewUpperRight();
    EntityType getEntityAt(XY xy);
    void move(XY direction);
    void spawnMiniBot(XY position, int energy);
    XY locate();
    void implode(int implodeRadius);
    Direction directionOfMaster();
    Entity getEntity();
    boolean isMine(XY xy);
    int getEnergy();
    long getRemainingSteps();


}
