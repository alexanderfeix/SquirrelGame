package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.board.Board;
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
    @Override
    public void nextStep(EntityContext entityContext){
    }

    public void onCollision(Entity enemy, Board board){

        System.out.println("Squirrel collided with " + enemy.getEntity());
        if (enemy.getEntityType() == EntityType.WALL) {
            getEntity().setEnergy(-10);
            setMoveCounter(3);
            System.out.println("Collided with Wall!");
        } else if (enemy.getEntityType() == EntityType.BAD_PLANT) {
            getEntity().setEnergy(-10);
            System.out.println("Squirrel health now: " + getEntity().getEnergy());
            board.getEntitySet().removeEntity(enemy);
            board.getEntitySet().addEntity(enemy);
            board.getEntitySet().respawnEntity(enemy);
            System.out.println("respawn method aufgerufen");

        } else if (enemy.getEntityType() == EntityType.GOOD_PLANT) {

        } else if (enemy.getEntityType() == EntityType.BAD_BEAST) {
            getEntity().setEnergy(-10);
        } else if (enemy.getEntityType() == EntityType.GOOD_BEAST) {

        } else if (enemy.getEntityType() == EntityType.MINI_SQUIRREL) {

        }

    }

}
