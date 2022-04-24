package hs.augsburg.squirrelgame.test;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.util.XY;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumerationTest {

    @Test
    public void checkIfEnumerateForwardEnumeratesForward (){
        EntitySet entitySet = new EntitySet();
        entitySet.addEntity(new BadPlant(new XY(3, 3)) );
        entitySet.addEntity(new GoodPlant(new XY(2, 5)) );
        entitySet.addEntity(new GoodBeast(new XY(8, 9)) );
        Enumeration enu = entitySet.enumerateForward();
        for(int i = 0; i < 3 ; i++){
            assertEquals(i, ((EntitySet.ListElement) enu.nextElement()).getEntity().getId());

        }







    }
    @Test
    public void checkIfEnumerateBackwardsEnumeratesBackwards (){
        EntitySet entitySet = new EntitySet();
        entitySet.addEntity(new BadPlant(new XY(3, 3)) );
        entitySet.addEntity(new GoodPlant(new XY(2, 5)) );
        entitySet.addEntity(new GoodBeast(new XY(8, 9)) );
        Enumeration enu = entitySet.enumerateBackwards();
        for(int i = 2; i >= 0 ; i--) {
            assertEquals(i, ((EntitySet.ListElement) enu.nextElement()).getEntity().getId());
        }
    }

    public void checkIfEnumerateRandomEnumeratesRandom (){
        EntitySet entitySet = new EntitySet();
        entitySet.addEntity(new BadPlant(new XY(3, 3)) );
        entitySet.addEntity(new GoodPlant(new XY(2, 5)) );
        entitySet.addEntity(new GoodBeast(new XY(8, 9)) );
        entitySet.addEntity(new GoodBeast(new XY(10, 7)) );
        entitySet.addEntity(new GoodBeast(new XY(9, 10)) );

        for(int i = 2; i >= 0 ; i--) {
            assertEquals(i, ((EntitySet.ListElement) enu.nextElement()).getEntity().getId());
        }
    }
}
