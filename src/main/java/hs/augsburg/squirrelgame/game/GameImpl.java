package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.ui.ConsoleUI;

public class GameImpl extends Game{
    public GameImpl(State state) {
        super(state);
    }

    @Override
    public void processInput() {
        //Currently, this method does nothing, because we implemented the manual steering in HandOperatedMasterSquirrel
        //because of using a key-listener
    }

    @Override
    public void render() {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.render(State.getFlattenedBoard());
    }

}
