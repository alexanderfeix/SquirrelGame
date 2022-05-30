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

    public XYBot escapeFromEntity(XY position) {
        if (position.getX() > getX() && position.getY() > getY()) {
            return new XYBot(getX() - 1, getY() - 1);
        } else if (position.getX() > getX() && position.getY() < getY()) {
            return new XYBot(getX() - 1, getY() + 1);
        } else if (position.getX() > getX() && position.getY() == getY()) {
            return new XYBot(getX() - 1, getY());
        } else if (position.getX() < getX() && position.getY() > getY()) {
            return new XYBot(getX() + 1, getY() - 1);
        } else if (position.getX() < getX() && position.getY() < getY()) {
            return new XYBot(getX() + 1, getY() + 1);
        } else if (position.getX() < getX() && position.getY() == getY()) {
            return new XYBot(getX() + 1, getY());
        } else if (position.getX() == getX() && position.getY() > getY()) {
            return new XYBot(getX(), getY() - 1);
        } else if (position.getX() == getX() && position.getY() < getY()) {
            return new XYBot(getX(), getY() + 1);
        } else if (position.getX() == getX() && position.getY() == getY()) {
            return new XYBot(getX(), getY());
        }
        return this;
    }

    public XYBot chaseEntity(XY position) {
        if (position.getX() > getX() && position.getY() > getY()) {
            return new XYBot(getX() + 1, getY() + 1);
        } else if (position.getX() > getX() && position.getY() < getY()) {
            return new XYBot(getX() + 1, getY() - 1);
        } else if (position.getX() > getX() && position.getY() == getY()) {
            return new XYBot(getX() + 1, getY());
        } else if (position.getX() < getX() && position.getY() > getY()) {
            return new XYBot(getX() - 1, getY() + 1);
        } else if (position.getX() < getX() && position.getY() < getY()) {
            return new XYBot(getX() - 1, getY() - 1);
        } else if (position.getX() < getX() && position.getY() == getY()) {
            return new XYBot(getX() - 1, getY());
        } else if (position.getX() == getX() && position.getY() > getY()) {
            return new XYBot(getX(), getY() + 1);
        } else if (position.getX() == getX() && position.getY() < getY()) {
            return new XYBot(getX(), getY() - 1);
        } else if (position.getX() == getX() && position.getY() == getY()) {
            return new XYBot(getX(), getY());
        }
        return this;
    }

    public XYBot getRandomNearbyPosition() {
        Random random = new Random();
        int directionInt = random.nextInt(8);
        XYBot newPosition = null;
        switch (directionInt) {
            case 0 -> //Move left-up
                    newPosition = new XYBot(getX() - 1, getY() - 1);
            case 1 -> //Move up
                    newPosition = new XYBot(getX(), getY() - 1);
            case 2 -> //Move right-up
                    newPosition = new XYBot(getX() + 1, getY() - 1);
            case 3 -> //Move right
                    newPosition = new XYBot(getX() + 1, getY());
            case 4 -> //Move right-down
                    newPosition = new XYBot(getX() + 1, getY() + 1);
            case 5 -> //Move down
                    newPosition = new XYBot(getX(), getY() + 1);
            case 6 -> //Move left-down
                    newPosition = new XYBot(getX() - 1, getY() + 1);
            case 7 -> //Move left
                    newPosition = new XYBot(getX() - 1, getY());
            default -> throw new IllegalStateException("Unexpected value: " + directionInt);
        }
        if (newPosition.getX() >= BoardConfig.COLUMNS || newPosition.getX() < 0
                || newPosition.getY() >= BoardConfig.ROWS || newPosition.getY() < 0) {
            return getRandomNearbyPosition();
        }
        return newPosition;
    }

    @Override
    public String toString() {
        return getX() + ", " + getY();
    }

}
