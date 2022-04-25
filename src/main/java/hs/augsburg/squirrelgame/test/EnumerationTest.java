package hs.augsburg.squirrelgame.test;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.util.XY;
import org.junit.jupiter.api.Test;

import java.util.Enumeration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class EnumerationTest {

    @Test
    public void checkIfEnumerateForwardEnumeratesForward (){
        EntitySet entitySet = new EntitySet();
        entitySet.addEntity(new BadPlant(new XY(3, 3)) );
        entitySet.addEntity(new GoodPlant(new XY(2, 5)) );
        entitySet.addEntity(new GoodBeast(new XY(8, 9)) );
        Enumeration enu = entitySet.enumerateForward();
        for(int i = 0; i < 3 ; i++){
            assertEquals(i, ((Entity) enu.nextElement()).getId());
        }
    }
    @Test
    public void checkIfEnumerateBackwardsEnumeratesBackwards (){
        EntitySet entitySet = new EntitySet();
        entitySet.addEntity(new BadPlant(new XY(4, 3)) );
        entitySet.addEntity(new GoodPlant(new XY(3, 5)) );
        GoodBeast goodBeast = new GoodBeast(new XY(9, 9));
        entitySet.addEntity(goodBeast);
        int lastEntityIndex = goodBeast.getId();
        Enumeration enu = entitySet.enumerateBackwards();
        for(int i = lastEntityIndex; i >= lastEntityIndex-2 ; i--) {
            int nextElementId = ((Entity) enu.nextElement()).getId();
            assertEquals(i, nextElementId);
        }
    }

    @Test
    public void checkIfEnumerateRandomEnumeratesRandom (){
        EntitySet entitySet = new EntitySet();
        entitySet.addEntity(new BadPlant(new XY(8, 3)) );
        entitySet.addEntity(new GoodPlant(new XY(8, 5)) );
        entitySet.addEntity(new GoodBeast(new XY(8, 9)) );
        entitySet.addEntity(new GoodBeast(new XY(8, 7)) );
        entitySet.addEntity(new GoodBeast(new XY(8, 10)) );
        Enumeration enu = entitySet.enumerateRandom();
        int equalsCount1 = 0;
        for(int i = 0; i < 5 ; i++) {
            if(i == ((Entity) enu.nextElement()).getId()){
                equalsCount1++;
            }
        }
        assertNotEquals(equalsCount1, 5);
        Enumeration enuZwei = entitySet.enumerateRandom();
        int equalsCount2 = 0;
        for(int i = 4; i >= 0 ; i--) {
            if(i == ((Entity) enuZwei.nextElement()).getId()){
                equalsCount2++;
            }
        }
        assertNotEquals(equalsCount2, 5);
    }
}
