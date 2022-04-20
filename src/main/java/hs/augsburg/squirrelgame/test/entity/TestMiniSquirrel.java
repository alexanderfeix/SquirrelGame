package hs.augsburg.squirrelgame.test.entity;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.util.XY;


public class TestMiniSquirrel extends MiniSquirrel {

    private int masterSquirrelId;

    public TestMiniSquirrel(XY position, int energy) {
        super(position, energy);
    }

    public void nextStep(EntityContext entityContext){
        updateEnergy(getEnergy()-1);
        if(getEnergy() <= 0){
            setAlive(false);
        }
    }

    public void onCollision(Entity enemy){
        if(enemy.getEntityType() == EntityType.MASTER_SQUIRREL){
            TestMiniSquirrel enemySquirrel = (TestMiniSquirrel) enemy;
            if(enemySquirrel.getMasterSquirrelId() == getMasterSquirrelId()){
                enemy.updateEnergy(getEnergy());
            }
            setAlive(false);
        }else if (enemy.getEntityType() == EntityType.MINI_SQUIRREL){
            TestMiniSquirrel enemySquirrel = (TestMiniSquirrel) enemy;
            if(enemySquirrel.getMasterSquirrelId() != getMasterSquirrelId()){
                enemy.setAlive(false);
                setAlive(false);
            }
        } else if (enemy.getEntityType() == EntityType.BAD_PLANT || enemy.getEntityType() == EntityType.GOOD_PLANT) {
            getEntity().updateEnergy(enemy.getEnergy());
            XY currentPosition = enemy.getPosition();
            while(currentPosition == enemy.getPosition()){
                enemy.updatePosition(enemy.getPosition().getRandomPosition());
            }
            getEntity().updatePosition(currentPosition);
        }else if (enemy.getEntityType() == EntityType.WALL) {
            updateEnergy(enemy.getEnergy());
            setMoveCounter(3);
        } else if (enemy.getEntityType() == EntityType.BAD_BEAST) {
            updateEnergy(enemy.getEnergy());
        } else if (enemy.getEntityType() == EntityType.GOOD_BEAST) {
            updateEnergy(enemy.getEnergy());
        }
    }

    public void setMasterSquirrelId(int masterSquirrelId){
        this.masterSquirrelId = masterSquirrelId;
    }

    public int getMasterSquirrelId() {
        return masterSquirrelId;
    }
}
