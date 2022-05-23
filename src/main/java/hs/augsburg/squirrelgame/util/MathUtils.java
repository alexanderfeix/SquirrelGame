package hs.augsburg.squirrelgame.util;

import hs.augsburg.squirrelgame.entity.Entity;

public class MathUtils {

    public static double getDistanceFromTwoPoints(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow((Math.abs(x2-x1)), 2) + Math.pow((Math.abs(y2-y1)), 2));
    }

    public static int getEnergyLoss(Entity affectedEntity, Entity implodeEntity, int impactRadius){
        double distance = getDistanceFromTwoPoints(affectedEntity.getPosition().getX(), affectedEntity.getPosition().getY(), implodeEntity.getPosition().getX(), implodeEntity.getEntity().getPosition().getY());
        int impactArea = (int) (Math.pow(impactRadius, 2) * Math.PI);
        return  200 * (affectedEntity.getEnergy()/impactArea) * (1 - (int)distance/impactRadius);
    }

}
