package hs.augsburg.squirrelgame.entity.squirrel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HandOperatedMasterSquirrel implements KeyListener {
    @Override
    public void keyTyped(KeyEvent e) {
    System.out.println("You typed:" + e.getKeyChar());
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
