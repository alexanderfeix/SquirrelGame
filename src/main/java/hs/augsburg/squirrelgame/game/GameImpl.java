package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.command.GameCommandType;
import hs.augsburg.squirrelgame.entity.squirrel.HandOperatedMasterSquirrel;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.ui.UI;
import hs.augsburg.squirrelgame.util.XY;

import java.io.PrintStream;

public class GameImpl extends Game {

    private UI ui;
    private PrintStream outputStream = System.out;
    private HandOperatedMasterSquirrel handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(new XY(10, 10));


    public GameImpl(State state, UI ui) {
        super(state, ui);
        this.ui = ui;
        state.getBoard().getEntitySet().addEntity(handOperatedMasterSquirrel);
    }

    @Override
    public void processInput() {
        Command command = ui.getCommand();
        if(command != null){
            GameCommandType commandType = (GameCommandType) command.getCommandType();
            switch (commandType){
                case EXIT -> System.exit(0);
                case HELP -> outputStream.println(commandType.getHelpText());
                case SPAWN_MINI -> getState().getFlattenedBoard().createStandardMiniSquirrel(handOperatedMasterSquirrel, command);
            }
        }
    }

    @Override
    public void render() {
        ui.render(getState().getFlattenedBoard());
    }

}
