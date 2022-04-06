package hs.augsburg.squirrelgame.entity.tests;
import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.testUnit.TestEntity;
import hs.augsburg.squirrelgame.entity.testUnit.TestEntity;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.util.XY;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntitySetTest {
    @Test
    void checkIfAddMethodReallyAddsEntities(){
        EntitySet.initializeExamples();
        int firstID = EntitySet.returnLastID();
        Entity test = new BadPlant(new XY(3, -3));
        EntitySet.addEntity(test);
        int secondID = EntitySet.returnLastID();
        assertNotEquals(firstID,secondID);
        assertEquals(secondID,test.getId());
    }
    @Test
    void checkIfRemoveMethodReallyRemovesEntities(){
        EntitySet.initializeExamples();
        Entity test = new BadPlant(new XY(3, -3));
        int removedID = test.getId();
        EntitySet.addEntity(test);
        assertEquals(EntitySet.returnLastID(),test.getId());
        EntitySet.removeEntity(test);
        assertNotEquals(removedID,EntitySet.returnLastID());
    }
    @Test
    void checkIfRemoveOnlyRemovesWhatNeedsToBeRemoved(){
        EntitySet.initializeExamples();
        int itemsInListStart = EntitySet.countItems();
        Entity test = new BadPlant(new XY(3, -3));
        EntitySet.addEntity(test);
        int itemsInListAfterAdd = EntitySet.countItems();
        assertNotEquals(itemsInListStart,itemsInListAfterAdd);
        EntitySet.removeEntity(test);
        int itemsInListAfterRemove = EntitySet.countItems();
        assertEquals(itemsInListStart,itemsInListAfterRemove);
    }
    @Test
    void throwExceptionIfEntityInContainer(){
        boolean thrown = false;
        Entity test = new BadPlant(new XY(3, -3));
        try {
            EntitySet.initializeExamples();
            EntitySet.addEntity(test);
            EntitySet.addEntity(test);
        } catch (IllegalStateException ise) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    void throwExceptionIfTryingToRemoveNonExistentEntity(){
        boolean thrown = false;
        Entity test = new BadPlant(new XY(3, -3));
        try {
            EntitySet.initializeExamples();
            EntitySet.removeEntity(test);
        } catch (IllegalStateException ise) {
            thrown = true;
        }
        assertTrue(thrown);
    }
    @Test
    void doesTheNextStepMethodActuallyCallTheNextStepMethodOfEntity(){
        EntitySet.initializeExamples();
        new State(new Board());
        Entity test = new TestEntity(new XY(2,5));
        EntitySet.addEntity(test);
        EntitySet.nextStep();
        assertTrue(TestEntity.getStatus());
    }

}
