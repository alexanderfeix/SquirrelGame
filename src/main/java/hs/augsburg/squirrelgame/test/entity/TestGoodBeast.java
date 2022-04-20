package hs.augsburg.squirrelgame.test.entity;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.util.XY;

public class TestGoodBeast extends GoodBeast {
    public TestGoodBeast() {
        super(new XY(2, 2));
    }

    public void nextStep(EntityContext entityContext){
        if(getMoveCounter() != 0){
            setMoveCounter(getMoveCounter()-1);
            System.out.println("Current MoveCounter Good Beast: " + getMoveCounter());
            return;
        }
        entityContext.move(getEntity(), checkNearbyRadius(entityContext, getEntity()));
        setMoveCounter(4);
    }

    public void onCollision(Entity enemy){;
        if(enemy.getEntityType() == EntityType.MASTER_SQUIRREL || enemy.getEntityType() == EntityType.MINI_SQUIRREL){
            XY currentPosition = getEntity().getPosition();
            while(currentPosition == getEntity().getPosition()){
                getEntity().updatePosition(getEntity().getPosition().getRandomPosition());
            }
            getEntity().updatePosition(currentPosition);
            enemy.updateEnergy(getEntity().getEnergy());
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
}
