package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.ui.BoardView;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

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
            if(getGameBoard()[x][y].isAlive()){
                return getGameBoard()[x][y];
            }
        }
        return null;
    }

    @Override
    public void move(Entity entity, XY movePosition) {
        System.out.println("move call");
        if (getEntity(movePosition.getX(), movePosition.getY()) != null && getEntity(movePosition.getX(), movePosition.getY()).getId() != entity.getId()) {
            System.out.println("on collision call");
            entity.onCollision(getEntity(movePosition.getX(), movePosition.getY()));
        } else {
            entity.updatePosition(movePosition);
        }
    }


    /**
     * Sets the entities to their positions on the gameBoard
     */
    private void fillGameBoard() {
        Enumeration enumeration = getEntitySet().enumerateRandom();
        while (enumeration.hasMoreElements()){
            Entity entity = (Entity) enumeration.nextElement();
            XY position = entity.getPosition();
            gameBoard[position.getX()][position.getY()] = entity;
        }

    }

    @Override
    public XY getNearbySquirrelPosition(Entity entity){
        XY position = entity.getPosition();
        for (int col = position.getX() - 6; col < position.getX() + 6; col++) {
            for (int row = position.getY() - 6; row < position.getY() + 6; row++) {
                try {
                    Entity enemy = getEntity(col, row);
                    if (enemy.getEntityType() == EntityType.MASTER_SQUIRREL || enemy.getEntityType() == EntityType.MINI_SQUIRREL) {
                        return enemy.getPosition();
                    }
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public Board getBoard() {
        return board;
    }
}
