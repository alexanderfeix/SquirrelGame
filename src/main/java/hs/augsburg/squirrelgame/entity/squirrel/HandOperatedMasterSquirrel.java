package hs.augsburg.squirrelgame.entity.squirrel;

import hs.augsburg.squirrelgame.util.XY;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class HandOperatedMasterSquirrel extends MasterSquirrel implements KeyListener {


    public HandOperatedMasterSquirrel(hs.augsburg.squirrelgame.util.XY position) {
        super(position);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyChar() == 'w' || e.getKeyCode() == KeyEvent.VK_UP){
            System.out.println("up");
            updatePosition(new XY(getPosition().getX(), getPosition().getY() + 1));
        }else if(e.getKeyChar() == 'a' || e.getKeyCode() == KeyEvent.VK_LEFT){
            updatePosition(new XY(getPosition().getX() - 1, getPosition().getY()));
            System.out.println("left");
        }else if(e.getKeyChar() == 's' || e.getKeyCode() == KeyEvent.VK_DOWN){
            updatePosition(new XY(getPosition().getX(), getPosition().getY() - 1));
            System.out.println("down");
        } else if (e.getKeyChar() == 'd' || e.getKeyCode() == KeyEvent.VK_RIGHT) {
            updatePosition(new XY(getPosition().getX() + 1, getPosition().getY()));
            System.out.println("right");
        }
        System.out.println("New position: " + getPosition().getX() + ", "+ getPosition().getY());
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}

