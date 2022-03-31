package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.XY;

public class MasterSquirrel extends Entity {
    //MasterSquirrel is actually a movable entity but can't implement the move() method, because move() provides
    //a random position

    private static final int startEnergy = 500;

    public MasterSquirrel(XY position) {
        super(position);
        this.setEnergy(startEnergy);
    }

    public Entity createMiniSquirrel(XY position, int energy){
        MiniSquirrel miniSquirrel = new MiniSquirrel(position, energy);
        miniSquirrel.setEnergy(energy);
        this.updateEnergy(energy);
        return miniSquirrel;
    }

}
