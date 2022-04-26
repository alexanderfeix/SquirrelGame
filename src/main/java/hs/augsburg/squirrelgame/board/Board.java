package hs.augsburg.squirrelgame.board;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.beast.BadBeast;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.entity.squirrel.HandOperatedMasterSquirrel;
import hs.augsburg.squirrelgame.entity.util.Wall;
import hs.augsburg.squirrelgame.util.XY;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Random;

public class Board {

    private final Board board;
    private final EntitySet entitySet;
    private FlattenedBoard flattenedBoard;

    public Board() {
        this.board = this;
        this.entitySet = new EntitySet();
        spawnBoarderWalls();
        spawnEntitiesRandomly();
        //spawnEntities();
    }

    /**
     * Creates the FlattenedBoard
     */
    public FlattenedBoard flatten() {
        FlattenedBoard flattenedBoard = new FlattenedBoard(getBoard(), getEntitySet());
        this.flattenedBoard = flattenedBoard;
        return flattenedBoard;
    }

    /**
     * @param position creates the new entity on this position
     * @return the new entity
     */
    private Entity getNewEntityFromType(XY position, EntityType entityType) {
        return switch (entityType) {
            case MASTER_SQUIRREL -> new HandOperatedMasterSquirrel(position);
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
    private void spawnEntitiesRandomly() {
        ArrayList<XY> spawnPositions = new ArrayList<>();
        Random random = new Random();
        for (int i = 0; i < BoardConfig.SPAWN_RATES.size(); i++) {
            for (int j = 0; j < (int) BoardConfig.SPAWN_RATES.values().toArray()[i]; j++) {
                int spawnX = random.nextInt(BoardConfig.COLUMNS - 2) + 1;
                int spawnY = random.nextInt(BoardConfig.ROWS - 2) + 1;
                XY spawnPosition = new XY(spawnX, spawnY);
                if (spawnPositions.contains(spawnPosition)) {
                    j--;
                } else {
                    Object entityType = BoardConfig.SPAWN_RATES.keySet().toArray()[i];
                    Entity entity = getNewEntityFromType(spawnPosition, (EntityType) entityType);
                    getEntitySet().addEntity(entity);
                    spawnPositions.add(spawnPosition);
                }
            }
        }
    }


    private void spawnEntities() {
        getEntitySet().addEntity(new GoodBeast(new XY(2, 3)));
        //getEntitySet().addEntity(new BadBeast(new XY(1, 1)));
        //getEntitySet().addEntity(new BadPlant(new XY(3, 1)));
        //getEntitySet().addEntity(new GoodPlant(new XY(2,2)));
        getEntitySet().addEntity(new HandOperatedMasterSquirrel(new XY(2, 1)));
    }

    /**
     * Creates the surrounding walls
     */
    private void spawnBoarderWalls() {
        for (int column = 0; column < BoardConfig.COLUMNS; column++) {
            getEntitySet().addEntity(new Wall(new XY(column, 0)));
            getEntitySet().addEntity(new Wall(new XY(column, BoardConfig.ROWS - 1)));
        }
        for (int row = 0; row < BoardConfig.ROWS; row++) {
            getEntitySet().addEntity(new Wall(new XY(0, row)));
            getEntitySet().addEntity(new Wall(new XY(BoardConfig.COLUMNS - 1, row)));
        }
    }

    /**
     * Comparable to a toString() method
     */
    public void getEntityInformation() {
        System.out.println("\n----------\n");
        Enumeration enumeration = getEntitySet().enumerateBackwards();
        while (enumeration.hasMoreElements()) {
            Entity entity = (Entity) enumeration.nextElement();
            if (entity.getEntityType() != EntityType.WALL) {
                System.out.println("ID: " + entity.getId() + ", Energy: " + entity.getEnergy() + ", Position: " + entity.getPosition().getX() + ", " + entity.getPosition().getY() + " | " + entity.getEntityType());
            }
        }
        System.out.println("\n----------\n");
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
