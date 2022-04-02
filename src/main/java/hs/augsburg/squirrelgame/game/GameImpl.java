package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.ui.ConsoleUI;

public class GameImpl extends Game{
    public GameImpl(State state) {
        super(state);
    }

    //I think this class is unnecessary for us because we're using a key-listener. But not sure?

    @Override
    public void processInput() {

    }

    @Override
    public void render() {
        ConsoleUI consoleUI = new ConsoleUI();
        consoleUI.render(State.getFlattenedBoard());
    }

}
