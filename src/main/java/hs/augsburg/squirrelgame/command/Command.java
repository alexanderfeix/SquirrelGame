package hs.augsburg.squirrelgame.command;

public class Command {

    private Object[] params;
    private CommandTypeInfo commandType;

    public Command(CommandTypeInfo commandTypeInfo, Object[] params){
        this.commandType = commandTypeInfo;
        this.params = params;
        handle();
    }

    public void handle(){
        if(commandType.getName().equalsIgnoreCase("spawn_mini")){
            //Do something?
        }
    }

    public Object[] getParams() {
        return params;
    }

    public CommandTypeInfo getCommandType() {
        return commandType;
    }
}
