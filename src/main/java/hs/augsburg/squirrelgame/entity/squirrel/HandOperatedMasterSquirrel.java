package hs.augsburg.squirrelgame.entity.squirrel;


import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.util.Direction;

public class HandOperatedMasterSquirrel extends MasterSquirrel implements EntityContext {

    public HandOperatedMasterSquirrel(hs.augsburg.squirrelgame.util.XY position) {
        super(position);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        if (getEnergy() <= 0) {
            setAlive(false);
            return;
        }
        if (getMoveCounter() != 0) {
            setMoveCounter(getMoveCounter() - 1);
            System.out.println("Current MoveCounter HandOperatedMasterSquirrel: " + getMoveCounter());
            return;
        }
        if (Game.getUi().getNextDirection() == Direction.UP) {
            System.out.println("UP");
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition(Direction.UP));
        } else if (Game.getUi().getNextDirection() == Direction.RIGHT) {
            System.out.println("RIGHT");
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition(Direction.RIGHT));
        } else if (Game.getUi().getNextDirection() == Direction.DOWN) {
            System.out.println("DOWN");
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition(Direction.DOWN));
        } else if (Game.getUi().getNextDirection() == Direction.LEFT) {
            System.out.println("LEFT");
            entityContext.move(getEntity(), getPosition().getRandomNearbyPosition(Direction.LEFT));
        }
    }

    @Override
    public void move(Entity entity, hs.augsburg.squirrelgame.util.XY randomPosition) {

    }

    @Override
    public void createStandardMiniSquirrel(MasterSquirrel masterSquirrel, int energy) {

    }

    @Override
    public hs.augsburg.squirrelgame.util.XY getNearbySquirrelPosition(Entity entity) {
        return null;
    }

    @Override
    public Entity getEntity(hs.augsburg.squirrelgame.util.XY position) {
        return null;
    }
}

