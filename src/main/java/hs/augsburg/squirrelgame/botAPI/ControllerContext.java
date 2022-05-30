package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;
import hs.augsburg.squirrelgame.util.XYBot;

public interface ControllerContext {
    XYBot getViewLowerLeft();
    XYBot getViewUpperRight();
    EntityType getEntityAt(XYBot xy);
    void move(XYBot direction);
    void spawnMiniBot(XYBot position, int energy);
    XYBot locate();
    void implode(int implodeRadius);
    Direction directionOfMaster();
    Entity getEntity();
    boolean isMine(XYBot xy);
    int getEnergy();
    long getRemainingSteps();


}
