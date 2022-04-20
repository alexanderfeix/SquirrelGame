package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.test.entity.TestMiniSquirrel;
import hs.augsburg.squirrelgame.util.XY;

public class MasterSquirrel extends Entity {
    //MasterSquirrel is actually a movable entity but can't implement the move() method, because move() provides
    //a random position, so we use the HandOperatedMasterSquirrel class to move the squirrel manually

    private static final int startEnergy = 500;

    public MasterSquirrel(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.MASTER_SQUIRREL, position, startEnergy);
        setEntity(this);
    }


    public void onCollision(Entity enemy){
        System.out.println("Squirrel collided with " + enemy.getEntity());
        System.out.println("----------");
        System.out.println("Energy of MasterSquirrel BEFORE collision: " + getEnergy());
        System.out.println("Position of MasterSquirrel BEFORE collision: " + getPosition().toString());
        System.out.println("\n");


        if (enemy.getEntityType() == EntityType.WALL) {
            updateEnergy(enemy.getEnergy());
            setMoveCounter(3);
        } else if (enemy.getEntityType() == EntityType.BAD_PLANT || enemy.getEntityType() == EntityType.GOOD_PLANT) {
            updateEnergy(enemy.getEnergy());
            XY currentPosition = enemy.getPosition();
            while(currentPosition == enemy.getPosition()){
                enemy.updatePosition(enemy.getPosition().getRandomPosition());
            }
            updatePosition(currentPosition);
        } else if (enemy.getEntityType() == EntityType.BAD_BEAST || enemy.getEntityType() == EntityType.GOOD_BEAST) {
            updateEnergy(enemy.getEnergy());
            XY currentPosition = enemy.getPosition();
            while(currentPosition == enemy.getPosition()){
                enemy.updatePosition(enemy.getPosition().getRandomPosition());
            }
            updatePosition(currentPosition);
        } else if (enemy.getEntityType() == EntityType.MINI_SQUIRREL) {
            TestMiniSquirrel enemySquirrel = (TestMiniSquirrel) enemy;
            if(enemySquirrel.getMasterSquirrelId() == getId()){
                updateEnergy(enemy.getEnergy());
            }
            enemy.setAlive(false);
        }


        System.out.println("Energy of MasterSquirrel AFTER collision: " + getEnergy());
        System.out.println("Position of MasterSquirrel AFTER collision: " + getPosition().toString());
        System.out.println("----------\n");
    }


    public Entity createMiniSquirrel(XY position, int energy) {
        if(energy < 100){
            return null;
        }else{
            TestMiniSquirrel miniSquirrel = new TestMiniSquirrel(position, energy);
            miniSquirrel.setMasterSquirrelId(getId());
            this.updateEnergy(-energy);
            return miniSquirrel;
        }
    }

}
