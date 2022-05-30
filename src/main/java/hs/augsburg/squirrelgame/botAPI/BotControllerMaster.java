package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;
import hs.augsburg.squirrelgame.util.XYBot;

public class BotControllerMaster implements BotController{

    @Override
    public void nextStep(ControllerContext controllerContext) {
        XYBot position = controllerContext.locate();
        XYBot viewLowerLeft = controllerContext.getViewLowerLeft();
        XYBot viewUpperRight = controllerContext.getViewUpperRight();

        double shortestEnemyDistance = Double.MAX_VALUE;
        XYBot enemyPosition = null;
        double shortestFriendDistance = Double.MAX_VALUE;
        XYBot friendPosition = null;

        for(int col = viewLowerLeft.getX(); col < viewUpperRight.getX(); col++){
            for(int row = viewUpperRight.getY(); row < viewLowerLeft.getY(); row++){
                try {
                    XYBot currentPosition = new XYBot(col, row);
                    EntityType entityType = controllerContext.getEntityAt(currentPosition);
                    if(entityType == EntityType.BAD_BEAST || entityType == EntityType.BAD_PLANT || entityType == EntityType.WALL){
                        double distance = MathUtils.getDistanceFromTwoPoints(currentPosition.getX(), currentPosition.getY(), position.getX(), position.getY());
                        if(distance < shortestEnemyDistance){
                            shortestEnemyDistance = distance;
                            enemyPosition = currentPosition;
                        }
                    }else if(entityType == EntityType.GOOD_BEAST || entityType == EntityType.GOOD_PLANT){
                        double distance = MathUtils.getDistanceFromTwoPoints(currentPosition.getX(), currentPosition.getY(), position.getX(), position.getY());
                        if(distance < shortestFriendDistance){
                            shortestFriendDistance = distance;
                            friendPosition = currentPosition;
                        }
                    }
                }catch (Exception ignored){}
            }
        }
        if(shortestEnemyDistance < shortestFriendDistance){
            controllerContext.move(position.escapeFromEntity(enemyPosition));
        }else if (shortestEnemyDistance > shortestFriendDistance){
            controllerContext.move(position.chaseEntity(friendPosition));
        }else{
            controllerContext.move(position.getRandomNearbyPosition());
        }

    }


}
