package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.entity.*;
import hs.augsburg.squirrelgame.util.XY;

public class MiniSquirrel extends MovableEntity {

    private int masterSquirrelId;
    private boolean invulnerable = true;

    public MiniSquirrel(hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(EntityType.MINI_SQUIRREL, position, energy);
        setEntity(this);
    }

    public void nextStep(EntityContext entityContext) {
        if(!invulnerable){
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition());
            updateEnergy(-1);
            if (getEnergy() <= 0) {
                setAlive(false);
            }
        }else{
            setInvulnerable(false);
        }
    }


    public void onCollision(Entity enemy) {
        if (enemy.getEntityType() == EntityType.MASTER_SQUIRREL) {
            MasterSquirrel enemySquirrel = (MasterSquirrel) enemy;
            if (enemySquirrel.getId() == getMasterSquirrelId()) {
                enemy.updateEnergy(getEnergy());
            }
            setAlive(false);
        } else if (enemy.getEntityType() == EntityType.MINI_SQUIRREL) {
            MiniSquirrel enemySquirrel = (MiniSquirrel) enemy;
            if (enemySquirrel.getMasterSquirrelId() != getMasterSquirrelId()) {
                enemy.setAlive(false);
                setAlive(false);
            }
        } else if (enemy.getEntityType() == EntityType.BAD_PLANT || enemy.getEntityType() == EntityType.GOOD_PLANT) {
            updateEnergy(enemy.getEnergy());
            XY currentPosition = enemy.getPosition();
            while (currentPosition == enemy.getPosition()) {
                enemy.updatePosition(enemy.getPosition().getRandomPosition());
            }
            updatePosition(currentPosition);
        } else if (enemy.getEntityType() == EntityType.WALL) {
            updateEnergy(enemy.getEnergy());
            setMoveCounter(3);
        } else if (enemy.getEntityType() == EntityType.BAD_BEAST) {
            updateEnergy(enemy.getEnergy());
        } else if (enemy.getEntityType() == EntityType.GOOD_BEAST) {
            updateEnergy(enemy.getEnergy());
        }
    }

    public int getMasterSquirrelId() {
        return masterSquirrelId;
    }

    public void setInvulnerable(boolean invulnerable) {
        this.invulnerable = invulnerable;
    }

    public void setMasterSquirrelId(int masterSquirrelId) {
        this.masterSquirrelId = masterSquirrelId;
    }

    @Override
    public void move(Entity entity, hs.augsburg.squirrelgame.util.XY randomPosition) {

    }

    @Override
    public void createStandardMiniSquirrel(MasterSquirrel masterSquirrel, Command command) {

    }

    @Override
    public hs.augsburg.squirrelgame.util.XY getNearbySquirrelPosition(Entity entity) {
        return null;
    }
}
