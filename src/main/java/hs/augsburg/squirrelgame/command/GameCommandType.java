package hs.augsburg.squirrelgame.command;

public enum GameCommandType {

    SPAWN_MINI("spawn_mini", "<energy> * spawns a new mini squirrel", int.class),
    MASTER_ENERGY("master_energy", " * returns the current energy of the master squirrel"),
    HELP("help", " * list all commands"),
    EXIT("exit", " * exit program");

    GameCommandType(String spawn_mini, String s, Class<Integer> integerClass) {
    }

    GameCommandType(String master_energy, String s) {
    }
}
