package hs.augsburg.squirrelgame.test;

import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.util.XY;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EnumerationTest {

    @Test
    public void checkIfEnumerateForwardEnumeratesForward (){
        EntitySet entitySet = new EntitySet();
        int counter = 0;
        entitySet.addEntity(new BadPlant(new XY(3, 3)) );
        entitySet.addEntity(new GoodPlant(new XY(2, 5)) );
        entitySet.addEntity(new GoodBeast(new XY(8, 9)) );
        for(int i = 0; i < 3 ; i++){
            assertEquals(counter, entitySet.enumerateForward());
        }




    }
    @Test
    public void checkIfEnumerateBackwardsEnumeratesBackwards (){

    }
}
