package hs.augsburg.squirrelgame.command;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class CommandScanner {

    private CommandTypeInfo[] commandTypeInfos;
    private BufferedReader inputReader;
    private PrintStream outputStream;


    public CommandScanner(CommandTypeInfo[] commandTypeInfos, BufferedReader inputReader){
        this.commandTypeInfos = commandTypeInfos;
        this.inputReader = inputReader;
    }

    public Command next(){
        try {
            String lineInput = inputReader.readLine();
            if(lineInput == null){
                return null;
            }
            for(CommandTypeInfo commandTypeInfo : commandTypeInfos){
                if(lineInput.toLowerCase().startsWith(commandTypeInfo.getName())){
                    Object[] objects = lineInput.split(" ");
                    reorderArray(objects);
                    return new Command(commandTypeInfo, objects);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Slits the first object away by swapping the elements one element forward
     * @param array
     */
    private void reorderArray(Object[] array){
        if (array.length - 1 >= 0) System.arraycopy(array, 1, array, 0, array.length - 1);
    }

}
