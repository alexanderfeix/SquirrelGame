package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.util.XY;

public class BotControllerMini implements BotController{
    @Override
    public void nextStep(ControllerContext controllerContext) {
        XY position = controllerContext.locate();
        //TODO: Move MiniSquirrelBot with logic
        controllerContext.move(position.getRandomNearbyPosition());
    }
}
