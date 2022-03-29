package hs.augsburg.squirrelgame.entity.squirrel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HandOperatedMasterSquirrel implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getKeyChar() == 'w' ){
            System.out.println("up");
        }else if(e.getKeyChar() == 'a' ){
            System.out.println("left");
        }else if(e.getKeyChar() == 's' ){
            System.out.println("down");
        } else if (e.getKeyChar() == 'd' ) {
            System.out.println("right");
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

