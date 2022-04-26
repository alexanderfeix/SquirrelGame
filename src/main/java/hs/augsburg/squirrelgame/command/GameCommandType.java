package hs.augsburg.squirrelgame.command;

public enum GameCommandType implements CommandTypeInfo{

    SPAWN_MINI("spawn_mini", "<energy> * spawns a new mini squirrel", int.class),
    MASTER_ENERGY("master_energy", " * returns the current energy of the master squirrel"),
    HELP("help", " * list all commands"),
    EXIT("exit", " * exit program");

    private String name;
    private String helpText;
    private Class<?>[] paramTypes;

    GameCommandType(String name, String helpText, Class<?> paramType) {
        this.name = name;
        this.helpText = helpText;
        this.paramTypes = new Class[]{paramType};
    }

    GameCommandType(String name, String helpText) {
        this.name = name;
        this.helpText = helpText;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getHelpText() {
        return this.helpText;
    }

    @Override
    public Class<?>[] getParamTypes() {
        return this.paramTypes;
    }
}
