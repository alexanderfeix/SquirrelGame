package hs.augsburg.squirrelgame.entity.squirrel;


import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;
import hs.augsburg.squirrelgame.util.Direction;

public class HandOperatedMasterSquirrel extends MasterSquirrel {


    public HandOperatedMasterSquirrel(hs.augsburg.squirrelgame.util.XY position) {
        super(position);
    }

    @Override
    public void nextStep() {
        ConsoleUI consoleUI = new ConsoleUI();
        if(consoleUI.getNextDirection() == Direction.UP){
            System.out.println("UP");
            State.getFlattenedBoard().move(getEntity(), Direction.UP);
        }else if (consoleUI.getNextDirection() == Direction.RIGHT){
            System.out.println("RIGHT");
            State.getFlattenedBoard().move(getEntity(), Direction.RIGHT);
        }else if(consoleUI.getNextDirection() == Direction.DOWN){
            System.out.println("DOWN");
            State.getFlattenedBoard().move(getEntity(), Direction.DOWN);
        }else if(consoleUI.getNextDirection() == Direction.LEFT){
            System.out.println("LEFT");
            State.getFlattenedBoard().move(getEntity(), Direction.LEFT);
        }
    }
}

