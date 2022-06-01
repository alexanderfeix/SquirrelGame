package hs.augsburg.squirrelgame.botimpls;

import hs.augsburg.squirrelgame.board.BoardConfig;
import hs.augsburg.squirrelgame.botAPI.BotController;
import hs.augsburg.squirrelgame.botAPI.ControllerContext;

import java.util.ArrayList;

public class Group1101BotControllerMaster implements BotController {

    /**
     * Explanation of the algorithm
     * 1. Search the 30x30 field for the nearest friendly entity(either GoodBeast or GoodPlant)
     * 2. Search the eight neighbor fields of bad entities
     * 3. If bad entity found: Escape from entity
     * 4. Else: Use the A* Algorithm to generate a path to the friendly entity
     * 5. Move the first step of the calculated path
     * 6. Repeat 1-5 every nextStep call
     */


    @Override
    public void nextStep(ControllerContext controllerContext) {
        controllerContext.move(controllerContext.locate().getUtils().getRandomNearbyPosition());

    }

}
