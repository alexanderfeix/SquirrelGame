package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.ui.BoardView;
import hs.augsburg.squirrelgame.util.XY;
import hs.augsburg.squirrelgame.util.exception.NotEnoughEnergyException;

import java.util.ArrayList;
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
    public void createStandardMiniSquirrel(MasterSquirrel masterSquirrel, Command command) {
        try {
            int energy = Integer.parseInt((String) command.getParams()[0]);
            if(energy > 0){
                if(energy > masterSquirrel.getEnergy()){
                    throw new NotEnoughEnergyException();
                }
                if (energy < 100) {
                    try {
                        throw new Exception("Energy to create a new mini squirrel must be over a hundred!");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
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
        }catch (NumberFormatException e){
            System.out.println("Please correct your input!");
            System.out.println(command.getCommandType().getHelpText());
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
     * Gets the nearest master or mini squirrel in six block radius.
     * Working of the method: 3-times nestes loop:
     * First loop for the number of circles around the entity.
     * Second and third loop for x and y positions.
     * The ArrayList "checkedPositions" is used for caching the already checked positions.
     *
     * @param entity
     * @return
     */
    @Override
    public XY getNearbySquirrelPosition(Entity entity) {
        XY position = entity.getPosition();
        ArrayList<String> checkedPositions = new ArrayList<>();
        checkedPositions.add(position.toString());
        for (int radius = 1; radius <= 6; radius++) {
            for (int row = position.getY() - radius; row <= position.getY() + radius; row++) {
                for (int col = position.getX() - radius; col <= position.getX() + radius; col++) {
                    XY currentPosition = new XY(col, row);
                    if (!checkedPositions.contains(currentPosition.toString())) {
                        checkedPositions.add(currentPosition.toString());
                        try {
                            Entity enemy = getEntity(col, row);
                            if (enemy.getEntityType() == EntityType.MASTER_SQUIRREL || enemy.getEntityType() == EntityType.MINI_SQUIRREL) {
                                return enemy.getPosition();
                            }
                        } catch (Exception ignored) {}
                    }
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
