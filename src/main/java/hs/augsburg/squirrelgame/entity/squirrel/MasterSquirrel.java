package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.XY;

public class MasterSquirrel extends Entity {
    //MasterSquirrel is actually a movable entity but can't implement the move() method, because move() provides
    //a random position, so we use the HandOperatedMasterSquirrel class to move the squirrel manually

    private static final int startEnergy = 500;

    public MasterSquirrel(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.MASTER_SQUIRREL, position, startEnergy);
        setEntity(this);
    }

    public Entity createMiniSquirrel(XY position, int energy) {
        MiniSquirrel miniSquirrel = new MiniSquirrel(position, energy);
        this.updateEnergy(energy);
        return miniSquirrel;
    }

    public void onCollision(Entity enemy){

    }

}
