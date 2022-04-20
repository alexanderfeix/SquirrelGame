package hs.augsburg.squirrelgame.test.entity;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.HandOperatedMasterSquirrel;
import hs.augsburg.squirrelgame.ui.ConsoleUI;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;

public class TestMasterSquirrel extends HandOperatedMasterSquirrel {
    public TestMasterSquirrel() {
        super(new XY(2, 1));
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if(getMoveCounter() != 0){
            setMoveCounter(getMoveCounter()-1);
            System.out.println("Current MoveCounter: " + getMoveCounter());
            return;
        }
        ConsoleUI consoleUI = new ConsoleUI();
        if (consoleUI.getNextDirection() == Direction.UP) {
            System.out.println("UP");
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition(Direction.UP));
        } else if (consoleUI.getNextDirection() == Direction.RIGHT) {
            System.out.println("RIGHT");
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition(Direction.RIGHT));
        } else if (consoleUI.getNextDirection() == Direction.DOWN) {
            System.out.println("DOWN");
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition(Direction.DOWN));
        } else if (consoleUI.getNextDirection() == Direction.LEFT) {
            System.out.println("LEFT");
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition(Direction.LEFT));
        }
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
