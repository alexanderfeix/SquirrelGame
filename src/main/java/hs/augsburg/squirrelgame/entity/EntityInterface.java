package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.util.XY;

public interface EntityInterface {

     void updateEnergy(int energy);

     void updatePosition(XY position);

     void nextStep();

     boolean equals(Entity entity);
}
