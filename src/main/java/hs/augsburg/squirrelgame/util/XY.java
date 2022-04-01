package hs.augsburg.squirrelgame.util;

import java.util.Random;

public class XY {

    private final int x;
    private final int y;

    public XY(int posX, int posY){
        this.x = posX;
        this.y = posY;
    }

    /**
     * @return a random number from 0-7
     */
    private int randomMove(){
        return new Random().nextInt(8);
    }

    /**
     * @return a random position in the eight surrounding positions
     */
    public XY getRandomPosition(){
        int move = randomMove();
        XY newPosition;
        switch (move){
            case 0: //Move left-up
                newPosition = new XY(getX() - 1, getY() + 1);
                break;
            case 1: //Move up
                newPosition = new XY(getX(), getY() + 1);
                break;
            case 2: //Move right-up
                newPosition = new XY(getX() + 1, getY() + 1);
                break;
            case 3: //Move right
                newPosition = new XY(getX() + 1, getY());
                break;
            case 4: //Move right-down
                newPosition = new XY(getX() + 1, getY() - 1);
                break;
            case 5: //Move down
                newPosition = new XY(getX(), getY() - 1);
                break;
            case 6: //Move left-down
                newPosition = new XY(getX() - 1, getY() - 1);
                break;
            case 7: //Move left
                newPosition = new XY(getX() - 1, getY());
                break;
            default:
                newPosition = new XY(getX(), getY());
        }
        if(newPosition.getX() < 0 || newPosition.getY() > 0){
            //New position is out of the field, so generate a new one
            return getRandomPosition();
        }else{
            return newPosition;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
