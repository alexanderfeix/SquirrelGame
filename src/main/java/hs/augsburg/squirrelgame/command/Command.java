package hs.augsburg.squirrelgame.command;

import hs.augsburg.squirrelgame.game.GameImpl;

public class Command {

    private Object[] params;
    private CommandTypeInfo commandType;

    public Command(CommandTypeInfo commandTypeInfo, Object[] params){
        this.commandType = commandTypeInfo;
        this.params = params;
    }

    public Object[] getParams() {
        return params;
    }

    public CommandTypeInfo getCommandType() {
        return commandType;
    }

    public void handle(GameImpl game, Command command){

    }

}
