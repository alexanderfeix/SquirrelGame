package hs.augsburg.squirrelgame.util;

public class XY {

    private final int x;
    private final int y;

    public XY(int posX, int posY) {
        this.x = posX;
        this.y = posY;
    }

    /**
     * @return a random position in the eight surrounding positions
     */
    public XY getRandomPosition(Direction direction) {
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

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
