package hs.augsburg.squirrelgame.botimpls;

import hs.augsburg.squirrelgame.botAPI.BotController;
import hs.augsburg.squirrelgame.botAPI.ControllerContext;
import hs.augsburg.squirrelgame.botimpls.algorithm.AStarAlgorithmHandler;
import hs.augsburg.squirrelgame.botimpls.algorithm.SearchHandler;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.XY;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Group1101BotControllerMaster implements BotController {


    @Override
    public void nextStep(ControllerContext controllerContext) {
        SearchHandler searchHandler = new SearchHandler();
        searchHandler.search(controllerContext);
        XY nearestFriend = searchHandler.getNearestFriendPosition(controllerContext);
        if(nearestFriend != null){
            AStarAlgorithmHandler.fillGameBoard(searchHandler);
            AStarAlgorithmHandler.evaluate(controllerContext.locate(), nearestFriend);
            if(AStarAlgorithmHandler.getNextMove() != null){
                controllerContext.move(AStarAlgorithmHandler.getNextMove());
            }else{
                controllerContext.move(moveRandom(controllerContext.locate()));
            }
        }else{
            controllerContext.move(moveRandom(controllerContext.locate()));
        }
        AStarAlgorithmHandler.reset();
    }


    private XY moveRandom(XY position){
        Random random = new Random();
        int nextInt = random.nextInt(8);
        return switch (nextInt) {
            case 0 -> position.plus(XY.UP);
            case 1 -> position.plus(XY.RIGHT_UP);
            case 2 -> position.plus(XY.LEFT_UP);
            case 3 -> position.plus(XY.LEFT);
            case 4 -> position.plus(XY.LEFT_DOWN);
            case 5 -> position.plus(XY.DOWN);
            case 6 -> position.plus(XY.RIGHT_DOWN);
            case 7 -> position.plus(XY.RIGHT);
            default -> position;
        };
    }

}
