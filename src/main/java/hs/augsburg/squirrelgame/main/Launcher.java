package hs.augsburg.squirrelgame.main;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;

import javax.swing.*;

public class Launcher {

    public static void main(String[] args) {
        initializeJFrame();
        State state = new State(new Board());
        Game game = new GameImpl(state);
        game.run();
    }

    private static void initializeJFrame() {
        JFrame frame = new JFrame("SquirrelGame");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);
        frame.setVisible(true);
        frame.addKeyListener(new ConsoleUI());
    }

}
