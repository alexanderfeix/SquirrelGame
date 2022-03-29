package hs.augsburg.squirrelgame.entity.squirrel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HandOperatedMasterSquirrel implements KeyListener {


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("up");
        }else if(e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT){
            System.out.println("left");
        }else if(e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN){
            System.out.println("down");
        } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            System.out.println("right");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

