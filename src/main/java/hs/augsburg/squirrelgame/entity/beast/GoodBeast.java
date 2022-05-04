package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.entity.*;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.util.XY;

public class GoodBeast extends MovableEntity {

    private static final int startEnergy = 300;

    public GoodBeast(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.GOOD_BEAST, position, startEnergy);
        setEntity(this);
    }

    public void nextStep(EntityContext entityContext) {
        if (getMoveCounter() != 0) {
            setMoveCounter(getMoveCounter() - 1);
            System.out.println("Current MoveCounter Good Beast: " + getMoveCounter());
            return;
        }
        entityContext.move(getEntity(), checkNearbyRadius(entityContext, getEntity()));
        setMoveCounter(4);
    }

    public void onCollision(Entity enemy) {
        if (enemy.getEntityType() == EntityType.MASTER_SQUIRREL || enemy.getEntityType() == EntityType.MINI_SQUIRREL) {
            XY currentPosition = getPosition();
            while (currentPosition == getPosition()) {
                updatePosition(getPosition().getRandomPosition());
            }
            enemy.updatePosition(currentPosition);
            enemy.updateEnergy(getEnergy());
        }
    }


    private XY checkNearbyRadius(EntityContext entityContext, Entity entity) {
        XY position = entity.getPosition();
        if (entityContext.getNearbySquirrelPosition(entity) != null) {
            XY enemyPosition = entityContext.getNearbySquirrelPosition(entity);
            if (enemyPosition.getX() > position.getX() && enemyPosition.getY() < position.getY()) {
                //Go right up
                return new XY(position.getX() - 1, position.getY() + 1);
            } else if (enemyPosition.getX() > position.getX() && enemyPosition.getY() > position.getY()) {
                //Go right down
                return new XY(position.getX() - 1, position.getY() - 1);
            } else if (enemyPosition.getX() < position.getX() && enemyPosition.getY() < position.getY()) {
                //Go left up
                return new XY(position.getX() + 1, position.getY() + 1);
            } else if (enemyPosition.getX() < position.getX() && enemyPosition.getY() > position.getY()) {
                //Go left down
                return new XY(position.getX() + 1, position.getY() - 1);
            } else if (enemyPosition.getX() == position.getX() && enemyPosition.getY() > position.getY()) {
                //Go down
                return new XY(position.getX(), position.getY() - 1);
            } else if (enemyPosition.getX() == position.getX() && enemyPosition.getY() < position.getY()) {
                //Go up
                return new XY(position.getX(), position.getY() + 1);
            } else if (enemyPosition.getX() > position.getX() && enemyPosition.getY() == position.getY()) {
                //Go right
                return new XY(position.getX() - 1, position.getY());
            } else if (enemyPosition.getX() < position.getX() && enemyPosition.getY() == position.getY()) {
                //Go left
                return new XY(position.getX() + 1, position.getY());
            }
        }
        return position.getRandomNearbyPosition();
    }

    @Override
    public void move(Entity entity, hs.augsburg.squirrelgame.util.XY movePosition) {

    }

    @Override
    public void createStandardMiniSquirrel(MasterSquirrel masterSquirrel, int energy) {

    }

    @Override
    public hs.augsburg.squirrelgame.util.XY getNearbySquirrelPosition(Entity entity) {
        return null;
    }

}
