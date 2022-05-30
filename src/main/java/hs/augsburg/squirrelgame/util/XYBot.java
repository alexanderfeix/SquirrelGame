package hs.augsburg.squirrelgame.util;

import hs.augsburg.squirrelgame.board.BoardConfig;

import java.util.Random;

public class XYBot extends XY{

    public static final XY ZERO_ZERO = new XY(0, 0);
    public static final XY RIGHT = new XY(1, 0);
    public static final XY LEFT = new XY(-1, 0);
    public static final XY UP = new XY(0, -1);
    public static final XY DOWN = new XY(0, 1);
    public static final XY RIGHT_UP = new XY(1, -1);
    public static final XY RIGHT_DOWN = new XY(1, 1);
    public static final XY LEFT_UP = new XY(-1, -1);
    public static final XY LEFT_DOWN = new XY(-1, 1);

    public XYBot(int posX, int posY) {
        super(posX, posY);
    }

    public XY plus(XY xy){
        int newX = getX() + xy.getX();
        int newY = getY() + xy.getY();
        return new XY(newX, newY);
    }

    public XY minus(XY xy){
        int newX = getX() - xy.getX();
        int newY = getY() - xy.getY();
        return new XY(newX, newY);
    }

    public XY times(int factor){
        return null;
    }

    public double length(){
        return 0.;
    }

    public double distanceFrom(XY xy){
        int xDiff = Math.abs(xy.getX() - getX());
        int yDiff = Math.abs(xy.getY() - getY());
        return Math.pow(xDiff, 2) + Math.pow(yDiff, 2);
    }

    public XYBotSupport getUtils(){
        return new XYBotSupport(getX(), getY());
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }

}
