package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;

public class BotControllerMaster implements BotController{
    @Override
    public void nextStep(ControllerContext controllerContext) {
        XY position = controllerContext.locate();
        XY viewLowerLeft = controllerContext.getViewLowerLeft();
        XY viewUpperRight = controllerContext.getViewUpperRight();

        double shortestEnemyDistance = Double.MAX_VALUE;
        Entity shortestEnemy = null;
        double shortestFriendDistance = Double.MAX_VALUE;
        Entity shortestFriend = null;

        for(int col = viewLowerLeft.getX(); col < viewUpperRight.getX(); col++){
            for(int row = viewUpperRight.getY(); row < viewLowerLeft.getY(); row++){
                try {
                    XY currentPosition = new XY(col, row);
                    Entity entity = controllerContext.getEntity(currentPosition);
                    if(entity.getEntityType() == EntityType.BAD_BEAST || entity.getEntityType() == EntityType.BAD_PLANT){
                        double distance = MathUtils.getDistanceFromTwoPoints(entity.getPosition().getX(), entity.getPosition().getY(), position.getX(), position.getY());
                        if(distance < shortestEnemyDistance){
                            shortestEnemyDistance = distance;
                            shortestEnemy = entity;
                        }
                    }else if(entity.getEntityType() == EntityType.GOOD_BEAST || entity.getEntityType() == EntityType.GOOD_PLANT){
                        double distance = MathUtils.getDistanceFromTwoPoints(entity.getPosition().getX(), entity.getPosition().getY(), position.getX(), position.getY());
                        if(distance < shortestFriendDistance){
                            shortestFriendDistance = distance;
                            shortestFriend = entity;
                        }
                    }
                }catch (Exception ignored){}
            }
        }
        if(shortestEnemyDistance < shortestFriendDistance){
            //TODO: Get away from enemy
        }else if (shortestEnemyDistance > shortestFriendDistance){
            //TODO: Go to friendly entity
        }else{
            controllerContext.move(position.getRandomNearbyPosition());
        }
    }
}
