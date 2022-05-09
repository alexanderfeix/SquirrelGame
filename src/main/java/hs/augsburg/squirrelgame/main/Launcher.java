package hs.augsburg.squirrelgame.main;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;

public class Launcher {

    public static void main(String[] args) {
        State state = new State(new Board());
        Game game = new GameImpl(state, new ConsoleUI());
        startGame(game);
    }

    private static void startGame(Game game){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.run();
    }

}
