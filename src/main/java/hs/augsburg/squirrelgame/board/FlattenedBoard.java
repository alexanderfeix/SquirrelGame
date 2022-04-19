package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.ui.BoardView;
import hs.augsburg.squirrelgame.util.XY;

import java.util.ArrayList;

public class FlattenedBoard implements BoardView, EntityContext {

    private final Entity[][] gameBoard;
    private final EntitySet entitySet;
    private final Board board;

    public FlattenedBoard(Board board, EntitySet entitySet) {
        this.board = board;
        this.entitySet = entitySet;
        this.gameBoard = new Entity[BoardConfig.COLUMNS][BoardConfig.ROWS];
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
    public void move(Entity entity, XY movePosition) {
        XY currentPosition = entity.getPosition();
        if (getEntity(movePosition.getX(), movePosition.getY()) != null && getEntity(movePosition.getX(), movePosition.getY()).getId() != entity.getId()) {
            entity.onCollision(getEntity(movePosition.getX(), movePosition.getY()), board);
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
}
