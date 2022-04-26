package hs.augsburg.squirrelgame.main;

import com.github.kwhat.jnativehook.GlobalScreen;
import com.github.kwhat.jnativehook.NativeHookException;
import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;

public class Launcher {

    public static void main(String[] args) {
        registerKeyListener();
        State state = new State(new Board());
        Game game = new GameImpl(state);
        game.run();
    }


    private static void registerKeyListener(){
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException e) {
            e.printStackTrace();
        }
        GlobalScreen.addNativeKeyListener(new ConsoleUI());
    }

}
