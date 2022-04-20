package hs.augsburg.squirrelgame.test.entity;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.beast.BadBeast;
import hs.augsburg.squirrelgame.util.XY;

public class TestBadBeast extends BadBeast {

    private int bites = 0;

    public TestBadBeast() {
        super(new XY(1, 1));
    }

    public void nextStep(EntityContext entityContext) {
        if (getMoveCounter() != 0) {
            setMoveCounter(getMoveCounter() - 1);
            System.out.println("Current MoveCounter Bad Beast: " + getMoveCounter());
            return;
        }
        entityContext.move(getEntity(), checkNearbyRadius(entityContext, getEntity()));
        setMoveCounter(4);
    }

    public void onCollision(Entity enemy) {
        if (enemy.getEntityType() == EntityType.MASTER_SQUIRREL || enemy.getEntityType() == EntityType.MINI_SQUIRREL) {
            bites++;
            enemy.updateEnergy(getEnergy());
            if (bites >= 7) {
                bites = 0;
                updatePosition(getPosition().getRandomPosition());
            }
        }
    }

    private XY checkNearbyRadius(EntityContext entityContext, Entity entity) {
        XY position = entity.getPosition();
        if (entityContext.getNearbySquirrelPosition(entity) != null) {
            XY enemyPosition = entityContext.getNearbySquirrelPosition(entity);
            if (enemyPosition.getX() > position.getX() && enemyPosition.getY() < position.getY()) {
                //Go right up
                return new XY(position.getX() + 1, position.getY() - 1);
            } else if (enemyPosition.getX() > position.getX() && enemyPosition.getY() > position.getY()) {
                //Go right down
                return new XY(position.getX() + 1, position.getY() + 1);
            } else if (enemyPosition.getX() < position.getX() && enemyPosition.getY() < position.getY()) {
                //Go left up
                return new XY(position.getX() - 1, position.getY() - 1);
            } else if (enemyPosition.getX() < position.getX() && enemyPosition.getY() > position.getY()) {
                //Go left down
                return new XY(position.getX() - 1, position.getY() + 1);
            } else if (enemyPosition.getX() == position.getX() && enemyPosition.getY() > position.getY()) {
                //Go down
                return new XY(position.getX(), position.getY() + 1);
            } else if (enemyPosition.getX() == position.getX() && enemyPosition.getY() < position.getY()) {
                //Go up
                return new XY(position.getX(), position.getY() - 1);
            } else if (enemyPosition.getX() > position.getX() && enemyPosition.getY() == position.getY()) {
                //Go right
                return new XY(position.getX() + 1, position.getY());
            } else if (enemyPosition.getX() < position.getX() && enemyPosition.getY() == position.getY()) {
                //Go left
                return new XY(position.getX() - 1, position.getY());
            }
        }
        return position.getRandomNearbyPosition();
    }

}
