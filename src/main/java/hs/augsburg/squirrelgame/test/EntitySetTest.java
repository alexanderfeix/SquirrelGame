package hs.augsburg.squirrelgame.test;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.util.XY;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EntitySetTest {
    @Test
    void checkIfAddMethodReallyAddsEntities() {
        EntitySet entitySet = new EntitySet();
        int firstID = entitySet.returnLastID();
        Entity test = new BadPlant(new XY(3, 3));
        entitySet.addEntity(test);
        int secondID = entitySet.returnLastID();
        assertNotEquals(firstID, secondID);
        assertEquals(secondID, test.getId());
    }

    @Test
    void checkIfRemoveMethodReallyRemovesEntities() {
        EntitySet entitySet = new EntitySet();
        Entity test = new BadPlant(new XY(3, 3));
        int removedID = test.getId();
        entitySet.addEntity(test);
        assertEquals(entitySet.returnLastID(), test.getId());
        entitySet.removeEntity(test);
        assertNotEquals(removedID, entitySet.returnLastID());
    }

    @Test
    void checkIfRemoveOnlyRemovesWhatNeedsToBeRemoved() {
        EntitySet entitySet = new EntitySet();
        int itemsInListStart = entitySet.countItems(true);
        Entity test = new BadPlant(new XY(3, 3));
        entitySet.addEntity(test);
        int itemsInListAfterAdd = entitySet.countItems(true);
        assertNotEquals(itemsInListStart, itemsInListAfterAdd);
        entitySet.removeEntity(test);
        int itemsInListAfterRemove = entitySet.countItems(true);
        assertEquals(itemsInListStart, itemsInListAfterRemove);
    }

    @Test
    void throwExceptionIfEntityInContainer() {
        boolean thrown = false;
        EntitySet entitySet = new EntitySet();
        Entity test = new BadPlant(new XY(3, 3));
        try {
            entitySet.addEntity(test);
            entitySet.addEntity(test);
        } catch (IllegalStateException ise) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void throwExceptionIfTryingToRemoveNonExistentEntity() {
        boolean thrown = false;
        EntitySet entitySet = new EntitySet();
        Entity test = new BadPlant(new XY(3, 3));
        try {
            entitySet.removeEntity(test);
        } catch (IllegalStateException ise) {
            thrown = true;
        }
        assertTrue(thrown);
    }

    @Test
    void doesTheNextStepMethodActuallyCallTheNextStepMethodOfEntity() {
        State state = new State(new Board());
        EntitySet entitySet = new EntitySet();
        Entity test = new TestEntity(new XY(2, 5));
        entitySet.addEntity(test);
        entitySet.nextStep(state.getFlattenedBoard());
        assertTrue(TestEntity.getStatus());
    }

}
