package hs.augsburg.squirrelgame.botimpls;

import hs.augsburg.squirrelgame.board.BoardConfig;
import hs.augsburg.squirrelgame.botAPI.BotController;
import hs.augsburg.squirrelgame.botAPI.ControllerContext;

import java.util.ArrayList;

public class Group1101BotControllerMaster implements BotController {

    @Override
    public void nextStep(ControllerContext controllerContext) {
        controllerContext.move(controllerContext.locate().getUtils().getRandomNearbyPosition());

    }

}
