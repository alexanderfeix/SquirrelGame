package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.command.CommandTypeInfo;
import hs.augsburg.squirrelgame.command.GameCommandType;
import hs.augsburg.squirrelgame.command.command.ExitCommand;
import hs.augsburg.squirrelgame.command.command.HelpCommand;
import hs.augsburg.squirrelgame.command.command.SpawnMiniCommand;
import hs.augsburg.squirrelgame.entity.squirrel.HandOperatedMasterSquirrel;
import hs.augsburg.squirrelgame.ui.UI;
import hs.augsburg.squirrelgame.util.XY;

import java.io.PrintStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class GameImpl extends Game {

    private final UI ui;
    private final PrintStream outputStream = System.out;
    private final HandOperatedMasterSquirrel handOperatedMasterSquirrel = new HandOperatedMasterSquirrel(new XY(10, 10));


    public GameImpl(State state, UI ui) {
        super(state, ui);
        this.ui = ui;
        state.getBoard().getEntitySet().addEntity(handOperatedMasterSquirrel);
    }

    @Override
    public void processInput() {
        callCommandClasses();
    }

    @Override
    public void render() {
        ui.render(getState().getFlattenedBoard());
    }

    private void callCommandClasses(){
        Command command = ui.getCommand();
        GameCommandType commandType = null;
        if(command != null){
            commandType = (GameCommandType) command.getCommandType();
        }
        for(GameCommandType gameCommandType : GameCommandType.values()){
            try {
                if(gameCommandType == commandType){
                    Class<?> commandClass = Class.forName(gameCommandType.getClassPath());
                    Class[] args = new Class[2];
                    args[0] = CommandTypeInfo.class;
                    args[1] = Object[].class;
                    Object initClass = commandClass.getDeclaredConstructor(args).newInstance(command.getCommandType(), command.getParams());
                    Method method = commandClass.getMethod("handle", GameImpl.class);
                    method.invoke(initClass, this);
                }
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
    }
    public void setPause(boolean pause){
        if(!pause && PAUSE_MODE){
            this.run();
        }
        PAUSE_MODE = pause;
    }

    public HandOperatedMasterSquirrel getHandOperatedMasterSquirrel() {
        return handOperatedMasterSquirrel;
    }

    public PrintStream getOutputStream() {
        return outputStream;
    }
}
