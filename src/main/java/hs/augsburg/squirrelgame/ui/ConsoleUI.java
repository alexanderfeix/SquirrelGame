package hs.augsburg.squirrelgame.ui;

import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.util.Direction;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class ConsoleUI implements UI, KeyListener {

    private Direction nextDirection;

    @Override
    public void render(BoardView view) {
        //Get entity by view.getEntity(x, y)
        EntitySet.getEntityInformations();
    }

    @Override
    public Direction getNextDirection() {
        return this.nextDirection;
    }

    public void setNextDirection(Direction nextDirection) {
        this.nextDirection = nextDirection;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP){
            setNextDirection(Direction.UP);
        }else if(e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT){
            setNextDirection(Direction.LEFT);
        }else if(e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN){
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
}
