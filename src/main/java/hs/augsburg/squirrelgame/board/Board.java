package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.ListElement;
import hs.augsburg.squirrelgame.entity.beast.BadBeast;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.entity.util.Wall;
import hs.augsburg.squirrelgame.util.XY;

import java.util.ArrayList;
import java.util.Random;

public class Board {

    private FlattenedBoard flattenedBoard;

    public Board(){
        spawnBoarderWalls();
        spawnEntitiesRandomly();
        flatten();
    }

    /**
     * @return ArrayList with all entities in the linked list
     * commonly used to get all entities that are currently on the board
     */
    public ArrayList<Entity> getEntities(){
        ArrayList<Entity> entities = new ArrayList<>();
        if(EntitySet.getTail() == null){
            return entities;
        }
        ListElement tempTail = EntitySet.getTail();
        entities.add(tempTail.getEntity());
        while(tempTail.hasPrev()){
            entities.add(tempTail.getPrevItem().getEntity());
            tempTail = tempTail.getPrevItem();
        }
        return entities;
    }

    /**
     * Creates the FlattenedBoard by defining an array of entites
     */
    public void flatten(){
        Entity[][] gameBoard = new Entity[BoardConfig.COLUMNS][BoardConfig.ROWS];
        ArrayList<Entity> entities = getEntities();
        System.out.println(entities.size());
        for (Entity entity : entities) {
            XY position = entity.getPosition();
            gameBoard[position.getX()][position.getY()] = entity;
        }
        setFlattenedBoard(new FlattenedBoard(gameBoard));
    }

    private Entity getNewEntityFromType(XY position, EntityType entityType){
        return switch (entityType) {
            case MASTER_SQUIRREL -> new MasterSquirrel(position);
            case GOOD_BEAST -> new GoodBeast(position);
            case BAD_BEAST -> new BadBeast(position);
            case GOOD_PLANT -> new GoodPlant(position);
            case BAD_PLANT -> new BadPlant(position);
            case WALL -> new Wall(position);
            default -> null;
        };
    }

    /**
     * Spawns all entities randomly on the board and checks that two entities can't spawn on the same position
     */
    private void spawnEntitiesRandomly(){
        ArrayList<XY> spawnPositions = new ArrayList<>();
        Random random = new Random();
        for(int i = 0; i < BoardConfig.SPAWN_RATES.size(); i++){
            for(int j = 0; j < (int) BoardConfig.SPAWN_RATES.values().toArray()[i]; j++){
                int spawnX = random.nextInt(BoardConfig.COLUMNS - 2) + 1;
                int spawnY = random.nextInt(BoardConfig.ROWS - 2) + 1;
                XY spawnPosition = new XY(spawnX, spawnY);
                if(spawnPositions.contains(spawnPosition)){
                    j--;
                }else{
                    Object entityType = BoardConfig.SPAWN_RATES.keySet().toArray()[i];
                    Entity entity = getNewEntityFromType(spawnPosition, (EntityType) entityType);
                    EntitySet.addEntity(entity);
                    spawnPositions.add(spawnPosition);
                }
            }
        }
    }

    /**
     * Creates the surrounding walls
     */
    private void spawnBoarderWalls(){
        for(int column = 0; column < BoardConfig.COLUMNS; column++){
            EntitySet.addEntity(new Wall(new XY(column, 0)));
            EntitySet.addEntity(new Wall(new XY(column, BoardConfig.ROWS - 1)));
        }
        for(int row = 0; row < BoardConfig.ROWS; row++){
            EntitySet.addEntity(new Wall(new XY(0, row)));
            EntitySet.addEntity(new Wall(new XY(BoardConfig.COLUMNS - 1, row)));
        }
    }

    public void setFlattenedBoard(FlattenedBoard flattenedBoard) {
        this.flattenedBoard = flattenedBoard;
    }

    public FlattenedBoard getFlattenedBoard() {
        return flattenedBoard;
    }
}
