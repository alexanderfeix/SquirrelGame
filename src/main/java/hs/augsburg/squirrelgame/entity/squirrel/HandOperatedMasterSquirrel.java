package hs.augsburg.squirrelgame.entity.squirrel;


import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.ui.ConsoleUI;
import hs.augsburg.squirrelgame.util.Direction;

public class HandOperatedMasterSquirrel extends MasterSquirrel {


    public HandOperatedMasterSquirrel(hs.augsburg.squirrelgame.util.XY position) {
        super(position);
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
}

