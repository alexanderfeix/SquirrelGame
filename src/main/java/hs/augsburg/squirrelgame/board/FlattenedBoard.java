package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.ui.BoardView;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;

public class FlattenedBoard implements BoardView, EntityContext {

    public Entity[][] gameBoard;

    public FlattenedBoard(Entity[][] gameBoard){
        this.gameBoard = gameBoard;
    }

    public Entity[][] getGameBoard() {
        return gameBoard;
    }

    @Override
    public Entity getEntity(int x, int y) {
        if(getGameBoard()[x][y] != null){
            return getGameBoard()[x][y];
        }
        return null;
    }

    @Override
    public void move(Entity entity, Direction direction) {
        XY currentPosition = entity.getPosition();
        XY movePosition = switch (direction) {
            case UP -> new XY(currentPosition.getX(), currentPosition.getY() - 1);
            case RIGHT -> new XY(currentPosition.getX() + 1, currentPosition.getY());
            case DOWN -> new XY(currentPosition.getX(), currentPosition.getY() + 1);
            case LEFT -> new XY(currentPosition.getX() - 1, currentPosition.getY());
            default -> currentPosition;
        };
        if(getEntity(movePosition.getX(), movePosition.getY()) != null && getEntity(movePosition.getX(), movePosition.getY()).getId() != entity.getId()){
            //collision
            System.out.println("Avoided collision! (" + currentPosition.getX() + ", " + currentPosition.getY() + ") -> (" + movePosition.getX() + ", " + movePosition.getY() + "), ID: " + entity.getId() + " on " + getEntity(movePosition.getX(), movePosition.getY()).getId());
        }else{
            //field is empty
            entity.updatePosition(movePosition);
        }

    }
}
