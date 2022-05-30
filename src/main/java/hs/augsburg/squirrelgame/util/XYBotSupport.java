package hs.augsburg.squirrelgame.util;

import hs.augsburg.squirrelgame.board.BoardConfig;

import java.util.Random;

public class XYBotSupport extends XYBot{

    public XYBotSupport(int posX, int posY) {
        super(posX, posY);
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

}
