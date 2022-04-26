package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.command.GameCommandType;
import hs.augsburg.squirrelgame.ui.UI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;

public class GameImpl extends Game {


    private UI ui;
    private PrintStream outputStream = System.out;


    public GameImpl(State state, UI ui) {
        super(state, ui);
        this.ui = ui;
    }

    @Override
    public void processInput() {
        Command command = ui.getCommand();
        GameCommandType commandType = (GameCommandType) command.getCommandType();
        switch (commandType){
            case EXIT -> System.exit(0);
            case HELP -> outputStream.println(commandType.getHelpText());
        }
    }

    @Override
    public void render() {
        ui.render(getState().getFlattenedBoard());
    }

}
