package hs.augsburg.squirrelgame.command;

import java.io.BufferedReader;
import java.io.PrintStream;

public class CommandScanner {

    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputStream;


    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){

    }

    public Command next(){
        return null;
    }

}
