package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.ui.BoardView;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;

import java.util.ArrayList;

public class FlattenedBoard implements BoardView, EntityContext {

    private final Entity[][] gameBoard;
    private final EntitySet entitySet;
    private final Board board;
    private FlattenedBoard flattenedBoard;

    public FlattenedBoard(Board board, EntitySet entitySet) {
        System.out.println("New flattened board created!");
        this.board = board;
        this.entitySet = entitySet;
        this.gameBoard = new Entity[BoardConfig.COLUMNS][BoardConfig.ROWS];
        this.flattenedBoard = this;
        fillGameBoard();
    }

    public Entity[][] getGameBoard() {
        return gameBoard;
    }

    @Override
    public Entity getEntity(int x, int y) {
        if (getGameBoard()[x][y] != null) {
            return getGameBoard()[x][y];
        }
        return null;
    }

    @Override
    public void move(Entity entity, Direction direction) {
        XY currentPosition = entity.getPosition();
        XY movePosition = currentPosition.getRandomPosition(direction);
        if (getEntity(movePosition.getX(), movePosition.getY()) != null && getEntity(movePosition.getX(), movePosition.getY()).getId() != entity.getId()) {
            //collision
            System.out.println("Avoided collision! (" + currentPosition.getX() + ", " + currentPosition.getY() + ") -> (" + movePosition.getX() + ", " + movePosition.getY() + "), ID: " + entity.getId() + " on " + getEntity(movePosition.getX(), movePosition.getY()).getId());
        } else {
            entity.updatePosition(movePosition);
        }

    }


    /**
     * Sets the entities to their positions on the gameBoard
     */
    private void fillGameBoard() {
        ArrayList<Entity> entities = getEntitySet().getEntities();
        for (Entity entity : entities) {
            XY position = entity.getPosition();
            gameBoard[position.getX()][position.getY()] = entity;
        }
    }

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public Board getBoard() {
        return board;
    }

    public FlattenedBoard getFlattenedBoard() {
        return flattenedBoard;
    }
}
