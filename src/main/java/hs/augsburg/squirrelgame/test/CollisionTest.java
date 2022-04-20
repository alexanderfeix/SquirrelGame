package hs.augsburg.squirrelgame.test;


import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.test.entity.*;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CollisionTest {

    @Test
    void checkWallCollision(){
        Board board = new Board();
        EntitySet entitySet = new EntitySet();
        board.flatten();
        TestMasterSquirrel masterSquirrel = new TestMasterSquirrel();
        entitySet.addEntity(masterSquirrel);
        int energyBeforeCollision = masterSquirrel.getEnergy();
        board.getFlattenedBoard().move(masterSquirrel, masterSquirrel.getPosition().getRandomNearbyPosition(Direction.UP));
        assertEquals(masterSquirrel.getEnergy(), energyBeforeCollision - 30);
    }

    @Test
    void checkMasterSquirrelWithBadBeastCollision(){
        Board board = new Board();
        TestMasterSquirrel masterSquirrel = new TestMasterSquirrel();
        TestBadBeast badBeast = new TestBadBeast();
        board.getEntitySet().addEntity(masterSquirrel);
        board.getEntitySet().addEntity(badBeast);
        board.flatten();
        int squirrelEnergyBeforeCollision = masterSquirrel.getEnergy();
        int badBeastEnergyBeforeCollision = badBeast.getEnergy();
        XY beastPositionBeforeCollision = badBeast.getPosition();
        board.getFlattenedBoard().move(masterSquirrel, masterSquirrel.getPosition().getRandomNearbyPosition(Direction.LEFT));
        assertEquals(squirrelEnergyBeforeCollision + badBeastEnergyBeforeCollision, masterSquirrel.getEnergy());
        //assertNotEquals(badBeast.getPosition(), beastPositionBeforeCollision);
    }

    @Test
    void checkMasterSquirrelWithGoodBeastCollision(){
        Board board = new Board();
        TestMasterSquirrel masterSquirrel = new TestMasterSquirrel();
        TestGoodBeast goodBeast = new TestGoodBeast();
        board.getEntitySet().addEntity(masterSquirrel);
        board.getEntitySet().addEntity(goodBeast);
        board.flatten();
        int squirrelEnergyBeforeCollision = masterSquirrel.getEnergy();
        int goodBeastEnergyBeforeCollision = goodBeast.getEnergy();
        XY beastPositionBeforeCollision = goodBeast.getPosition();
        board.getFlattenedBoard().move(masterSquirrel, masterSquirrel.getPosition().getRandomNearbyPosition(Direction.DOWN));
        assertEquals(squirrelEnergyBeforeCollision + goodBeastEnergyBeforeCollision, masterSquirrel.getEnergy());
        //assertNotEquals(goodBeast.getPosition(), beastPositionBeforeCollision);
    }

    @Test
    void checkMasterSquirrelWithGoodPlantCollision(){
        Board board = new Board();
        TestMasterSquirrel masterSquirrel = new TestMasterSquirrel();
        TestGoodPlant goodPlant = new TestGoodPlant();
        board.getEntitySet().addEntity(masterSquirrel);
        board.getEntitySet().addEntity(goodPlant);
        board.flatten();
        int squirrelEnergyBeforeCollision = masterSquirrel.getEnergy();
        int goodPlantEnergyBeforeCollision = goodPlant.getEnergy();
        XY plantPositionBeforeCollision = goodPlant.getPosition();
        board.getFlattenedBoard().move(masterSquirrel, masterSquirrel.getPosition().getRandomNearbyPosition(Direction.DOWN_RIGHT));
        assertEquals(squirrelEnergyBeforeCollision + goodPlantEnergyBeforeCollision, masterSquirrel.getEnergy());
        assertNotEquals(goodPlant.getPosition(), plantPositionBeforeCollision);
    }

    @Test
    void checkMasterSquirrelWithBadPlantCollision(){
        Board board = new Board();
        TestMasterSquirrel masterSquirrel = new TestMasterSquirrel();
        TestBadPlant badPlant = new TestBadPlant();
        board.getEntitySet().addEntity(masterSquirrel);
        board.getEntitySet().addEntity(badPlant);
        board.flatten();
        int squirrelEnergyBeforeCollision = masterSquirrel.getEnergy();
        int badPlantEnergyBeforeCollision = badPlant.getEnergy();
        XY plantPositionBeforeCollision = badPlant.getPosition();
        board.getFlattenedBoard().move(masterSquirrel, masterSquirrel.getPosition().getRandomNearbyPosition(Direction.DOWN_LEFT));
        assertEquals(squirrelEnergyBeforeCollision + badPlantEnergyBeforeCollision, masterSquirrel.getEnergy());
        assertNotEquals(badPlant.getPosition(), plantPositionBeforeCollision);
    }

    @Test
    void checkMasterSquirrelWithMiniSquirrelCollision(){
        Board board = new Board();
        TestMasterSquirrel masterSquirrel = new TestMasterSquirrel();
        TestMiniSquirrel miniSquirrel = (TestMiniSquirrel) masterSquirrel.createMiniSquirrel(new XY(3, 1), 150);
        board.getEntitySet().addEntity(masterSquirrel);
        board.getEntitySet().addEntity(miniSquirrel);
        board.flatten();
        int squirrelEnergyBeforeCollision = masterSquirrel.getEnergy();
        int miniSquirrelEnergyBeforeCollision = miniSquirrel.getEnergy();
        board.getFlattenedBoard().move(masterSquirrel, masterSquirrel.getPosition().getRandomNearbyPosition(Direction.RIGHT));
        assertEquals(squirrelEnergyBeforeCollision + miniSquirrelEnergyBeforeCollision, masterSquirrel.getEnergy());
        assertFalse(miniSquirrel.isAlive());
    }

}
