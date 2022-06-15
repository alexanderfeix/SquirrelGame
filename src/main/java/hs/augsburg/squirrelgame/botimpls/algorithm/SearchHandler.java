package hs.augsburg.squirrelgame.botimpls.algorithm;

import hs.augsburg.squirrelgame.botAPI.ControllerContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;

import java.util.ArrayList;
import java.util.List;

public class SearchHandler {

    /**
     * This class is used to search the view-area of the squirrel for friends and enemies.
     */

    private final List<XY> enemyPositions = new ArrayList<>();
    private final List<XY> friendPositions = new ArrayList<>();

    public void search(ControllerContext context){
        for(int col = context.getViewLowerLeft().getX(); col <= context.getViewUpperRight().getX(); col++){
            for(int row = context.getViewUpperRight().getY(); row <= context.getViewLowerLeft().getY(); row++){
                try {
                    XY currentPosition = new XY(col, row);
                    EntityType entityType = context.getEntityAt(currentPosition);
                    if(entityType.equals(EntityType.BAD_BEAST) || entityType.equals(EntityType.BAD_PLANT) || entityType.equals(EntityType.WALL)){
                        enemyPositions.add(currentPosition);
                    }else if (entityType.equals(EntityType.GOOD_BEAST) || entityType.equals(EntityType.GOOD_PLANT)){
                        friendPositions.add(currentPosition);
                    }
                }catch (Exception ignored){}
            }
        }
    }

    public XY getNearestFriendPosition(ControllerContext context){
        double nearestDistance = Double.MAX_VALUE;
        XY nearestPosition = null;
        for(XY position : friendPositions){
            double distance = MathUtils.getDistanceFromTwoPoints(context.locate(), position);
            if(distance < nearestDistance){
                nearestDistance = distance;
                nearestPosition = position;
            }
        }
        return nearestPosition;
    }

    public XY getNearestEnemyPosition(ControllerContext context){
        double nearestDistance = Double.MAX_VALUE;
        XY nearestPosition = null;
        for(XY position : enemyPositions){
            double distance = MathUtils.getDistanceFromTwoPoints(context.locate(), position);
            if(distance < nearestDistance){
                nearestDistance = distance;
                nearestPosition = position;
            }
        }
        return nearestPosition;
    }

    public List<XY> getEnemyPositions() {
        return enemyPositions;
    }

    public List<XY> getFriendPositions() {
        return friendPositions;
    }
}
