package hs.augsburg.squirrelgame.ui;

import com.github.kwhat.jnativehook.keyboard.NativeKeyEvent;
import com.github.kwhat.jnativehook.keyboard.NativeKeyListener;
import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.command.CommandScanner;
import hs.augsburg.squirrelgame.command.GameCommandType;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.util.Direction;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ConsoleUI implements UI, NativeKeyListener {

    private static Direction nextDirection;

    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private final CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader);

    @Override
    public void render(BoardView view) {
        view.getBoard().getEntityInformation();
        printBoard(view.getGameBoard());
        setNextDirection(null);
    }

    @Override
    public Direction getNextDirection() {
        return nextDirection;
    }

    @Override
    public Command getCommand() {
        return commandScanner.next();
    }

    public void setNextDirection(Direction nextDirection) {
        ConsoleUI.nextDirection = nextDirection;
    }

    /**
     * Works overall the same as the normal KeyListener
     * @param e
     */
    public void nativeKeyPressed(NativeKeyEvent e){
        if (e.getKeyCode() == NativeKeyEvent.VC_UP || e.getKeyCode() == NativeKeyEvent.VC_W) {
            setNextDirection(Direction.UP);
        } else if (e.getKeyCode() == NativeKeyEvent.VC_LEFT || e.getKeyCode() == NativeKeyEvent.VC_A) {
            setNextDirection(Direction.LEFT);
        } else if (e.getKeyCode() == NativeKeyEvent.VC_DOWN || e.getKeyCode() == NativeKeyEvent.VC_S) {
            setNextDirection(Direction.DOWN);
        } else if (e.getKeyCode() == NativeKeyEvent.VC_RIGHT || e.getKeyCode() == NativeKeyEvent.VC_D) {
            setNextDirection(Direction.RIGHT);
        }
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
                if (!gameBoard[col][row].getEntity().isAlive()) {
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
