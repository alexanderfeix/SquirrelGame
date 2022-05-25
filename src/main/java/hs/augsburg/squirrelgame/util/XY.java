package hs.augsburg.squirrelgame.util;

import hs.augsburg.squirrelgame.board.BoardConfig;
import hs.augsburg.squirrelgame.entity.Entity;

import java.util.Random;

public class XY{

    private final int x;
    private final int y;

    public XY(int posX, int posY) {
        this.x = posX;
        this.y = posY;
    }

    /**
     * @return a random position in the eight surrounding positions
     */
    public XY getRandomNearbyPosition(Direction direction) {
        return switch (direction) {
            case UP_LEFT -> //Move left-up
                    new XY(getX() - 1, getY() - 1);
            case UP -> //Move up
                    new XY(getX(), getY() - 1);
            case UP_RIGHT -> //Move right-up
                    new XY(getX() + 1, getY() - 1);
            case RIGHT -> //Move right
                    new XY(getX() + 1, getY());
            case DOWN_RIGHT -> //Move right-down
                    new XY(getX() + 1, getY() + 1);
            case DOWN -> //Move down
                    new XY(getX(), getY() + 1);
            case DOWN_LEFT -> //Move left-down
                    new XY(getX() - 1, getY() + 1);
            case LEFT -> //Move left
                    new XY(getX() - 1, getY());
        };
    }

    public XY getRandomNearbyPosition() {
        Random random = new Random();
        int directionInt = random.nextInt(8);
        XY newPosition = null;
        switch (directionInt) {
            case 0 -> //Move left-up
                    newPosition = new XY(getX() - 1, getY() - 1);
            case 1 -> //Move up
                    newPosition = new XY(getX(), getY() - 1);
            case 2 -> //Move right-up
                    newPosition = new XY(getX() + 1, getY() - 1);
            case 3 -> //Move right
                    newPosition = new XY(getX() + 1, getY());
            case 4 -> //Move right-down
                    newPosition = new XY(getX() + 1, getY() + 1);
            case 5 -> //Move down
                    newPosition = new XY(getX(), getY() + 1);
            case 6 -> //Move left-down
                    newPosition = new XY(getX() - 1, getY() + 1);
            case 7 -> //Move left
                    newPosition = new XY(getX() - 1, getY());
            default -> throw new IllegalStateException("Unexpected value: " + directionInt);
        }
        if(newPosition.getX() >= BoardConfig.COLUMNS || newPosition.getX() < 0
                    || newPosition.getY() >= BoardConfig.ROWS || newPosition.getY() < 0){
            return getRandomNearbyPosition();
        }
        return newPosition;
    }

    public XY getRandomPosition() {
        Random random = new Random();
        int spawnX = random.nextInt(BoardConfig.COLUMNS - 2) + 1;
        int spawnY = random.nextInt(BoardConfig.ROWS - 2) + 1;
        return new XY(spawnX, spawnY);
    }

    public XY escapeFromEntity(Entity entity){
        XY enemyPosition = entity.getPosition();
        if(enemyPosition.getX() > getX() && enemyPosition.getY() > getY()){
            return new XY(getX() - 1, getY() - 1);
        }else if (enemyPosition.getX() > getX() && enemyPosition.getY() < getY()){
            return new XY(getX() - 1, getY() + 1);
        }else if (enemyPosition.getX() > getX() && enemyPosition.getY() == getY()){
            return new XY(getX() - 1, getY());
        }else if(enemyPosition.getX() < getX() && enemyPosition.getY() > getY()){
            return new XY(getX() + 1, getY() - 1);
        }else if (enemyPosition.getX() < getX() && enemyPosition.getY() < getY()){
            return new XY(getX() + 1, getY() + 1);
        }else if (enemyPosition.getX() < getX() && enemyPosition.getY() == getY()){
            return new XY(getX() + 1, getY());
        }else if (enemyPosition.getX() == getX() && enemyPosition.getY() > getY()){
            return new XY(getX(), getY() - 1);
        }else if (enemyPosition.getX() == getX() && enemyPosition.getY() < getY()){
            return new XY(getX(), getY() + 1);
        }else if (enemyPosition.getX() == getX() && enemyPosition.getY() == getY()){
            return new XY(getX(), getY());
        }
        return this;
    }

    public XY chaseEntity(Entity entity){
        XY enemyPosition = entity.getPosition();
        if(enemyPosition.getX() > getX() && enemyPosition.getY() > getY()){
            return new XY(getX() + 1, getY() + 1);
        }else if (enemyPosition.getX() > getX() && enemyPosition.getY() < getY()){
            return new XY(getX() + 1, getY() - 1);
        }else if (enemyPosition.getX() > getX() && enemyPosition.getY() == getY()){
            return new XY(getX() + 1, getY());
        }else if(enemyPosition.getX() < getX() && enemyPosition.getY() > getY()){
            return new XY(getX() - 1, getY() + 1);
        }else if (enemyPosition.getX() < getX() && enemyPosition.getY() < getY()){
            return new XY(getX() - 1, getY() - 1);
        }else if (enemyPosition.getX() < getX() && enemyPosition.getY() == getY()){
            return new XY(getX() - 1, getY());
        }else if (enemyPosition.getX() == getX() && enemyPosition.getY() > getY()){
            return new XY(getX(), getY() + 1);
        }else if (enemyPosition.getX() == getX() && enemyPosition.getY() < getY()){
            return new XY(getX(), getY() - 1);
        }else if (enemyPosition.getX() == getX() && enemyPosition.getY() == getY()){
            return new XY(getX(), getY());
        }
        return this;
    }

    public String toString() {
        return getX() + ", " + getY();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
