package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.ui.BoardView;
import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;
import hs.augsburg.squirrelgame.util.exception.NotEnoughEnergyException;

import java.util.Enumeration;

public class FlattenedBoard implements BoardView, EntityContext {

    private final Entity[][] gameBoard;
    private EntitySet entitySet;
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
            if (getGameBoard()[x][y].isAlive()) {
                return getGameBoard()[x][y];
            }
        }
        return null;
    }

    @Override
    public void move(Entity entity, XY movePosition) {
        if (getEntity(movePosition.getX(), movePosition.getY()) != null && getEntity(movePosition.getX(), movePosition.getY()).getId() != entity.getId()) {
            System.out.println("on collision call [" + entity.getId() + " with " + getEntity(movePosition.getX(), movePosition.getY()).getId() + "]");
            entity.onCollision(getEntity(movePosition.getX(), movePosition.getY()));
        } else {
            entity.updatePosition(movePosition);
        }
    }

    @Override
    public void createStandardMiniSquirrel(MasterSquirrel masterSquirrel, int energy) {
            if(energy > masterSquirrel.getEnergy()){
                throw new NotEnoughEnergyException();
            }
            if (energy < 100) {
                throw new RuntimeException("Energy to create a new mini squirrel must be over a hundred!");
            } else {
                MiniSquirrel miniSquirrel = new MiniSquirrel(masterSquirrel.getPosition().getRandomNearbyPosition(), energy);
                miniSquirrel.setMasterSquirrelId(masterSquirrel.getId());
                masterSquirrel.updateEnergy(-energy);
                while(getEntity(miniSquirrel.getPosition().getX(), miniSquirrel.getPosition().getY()) != null){
                    miniSquirrel.updatePosition(masterSquirrel.getPosition().getRandomNearbyPosition());
                }
                getBoard().getEntitySet().addEntity(miniSquirrel);
            }
    }


    /**
     * Sets the entities to their positions on the gameBoard
     */
    private void fillGameBoard() {
        Enumeration enumeration = getEntitySet().enumerateRandom();
        while (enumeration.hasMoreElements()) {
            Entity entity = (Entity) enumeration.nextElement();
            XY position = entity.getPosition();
            gameBoard[position.getX()][position.getY()] = entity;
        }

    }

    /**
     * Gets the nearest squirrel in 6 block radius.
     * Working of the method: Searching out all blocks in six block radius in order to find squirrels.
     * When a squirrel gets found, the distance of the two entities gets calculated and further on compared
     * to the latest measured distance. When the distance is smaller, then the nearest squirrel variable gets
     * replaced, because we have found a squirrel that is nearer than the last one.
     * @param entity
     * @return
     */
    @Override
    public XY getNearbySquirrelPosition(Entity entity){
        XY entityPosition = entity.getPosition();
        Entity nearestSquirrel = null;
        double distanceToSquirrel = Integer.MAX_VALUE;
        for(int row = entityPosition.getY() - 6; row <= entityPosition.getY() + 6; row++){
            for(int col = entityPosition.getX() - 6; col <= entityPosition.getX() + 6; col++){
                try {
                    Entity currentEntity = getEntity(col, row);
                    if (currentEntity.getEntityType() == EntityType.MASTER_SQUIRREL || currentEntity.getEntityType() == EntityType.MINI_SQUIRREL) {
                        XY currentSquirrelPosition = currentEntity.getPosition();
                        double currentDistance = MathUtils.getDistanceFromTwoPoints(entityPosition.getX(), entityPosition.getY(), currentSquirrelPosition.getX(), currentSquirrelPosition.getY());
                        if(currentDistance < distanceToSquirrel){
                            distanceToSquirrel = currentDistance;
                            nearestSquirrel = currentEntity;
                        }
                    }
                }catch (Exception ignored){}
            }
        }
        if(nearestSquirrel != null){
            return nearestSquirrel.getPosition();
        }else{
            return null;
        }
    }

    public EntitySet getEntitySet() {
        return entitySet;
    }

    public Board getBoard() {
        return board;
    }
}
