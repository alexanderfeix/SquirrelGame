package hs.augsburg.squirrelgame.ui;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsoleUI implements UI, KeyListener {

    private static Direction nextDirection;

    @Override
    public void render(BoardView view) {
        view.getBoard().getEntityInformation();
        printBoard(view.getGameBoard());
    }

    @Override
    public Direction getNextDirection() {
        return nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        ConsoleUI.nextDirection = nextDirection;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP) {
            setNextDirection(Direction.UP);
        } else if (e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT) {
            setNextDirection(Direction.LEFT);
        } else if (e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN) {
            setNextDirection(Direction.DOWN);
        } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            setNextDirection(Direction.RIGHT);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }


    /**
     * Prints the gameBoard to the console.
     * This method should get deleted once the gui works fine.
     */
    private void printBoard(Entity[][] gameBoard) {
        for (int row = 0; row < gameBoard[0].length; row++) {
            for (int col = 0; col < gameBoard.length; col++) {
                if (gameBoard[col][row] == null) {
                    System.out.print("   ");
                    continue;
                }
                if(!gameBoard[col][row].getEntity().isAlive()){
                    System.out.print("   ");
                    continue;
                }
                switch (gameBoard[col][row].getEntityType()) {
                    case WALL -> System.out.print("W  ");
                    case MASTER_SQUIRREL -> System.out.print("MA ");
                    case BAD_BEAST -> System.out.print("BB ");
                    case GOOD_BEAST -> System.out.print("GB ");
                    case GOOD_PLANT -> System.out.print("GP ");
                    case BAD_PLANT -> System.out.print("BP ");
                    case MINI_SQUIRREL -> System.out.print("MS ");
                    default -> System.out.print("   ");
                }
            }
            System.out.println();
        }
    }

}
