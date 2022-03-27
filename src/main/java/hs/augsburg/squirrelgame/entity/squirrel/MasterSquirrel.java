package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.Position;

public class MasterSquirrel extends Entity implements Squirrel {

    private static final int startEnergy = 500;

    public MasterSquirrel(Position position) {
        super(position);
        this.setEnergy(startEnergy);
    }


    public Entity createMiniSquirrel(Position position, int energy){
        MiniSquirrel miniSquirrel = new MiniSquirrel(position, energy);
        miniSquirrel.setEnergy(energy);
        this.updateEnergy(energy);
        return miniSquirrel;
    }

    @Override
    public void move(Position position) {

    }
}
