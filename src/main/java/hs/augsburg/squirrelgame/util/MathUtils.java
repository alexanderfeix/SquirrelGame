package hs.augsburg.squirrelgame.util;

public class MathUtils {

    public static double getDistanceFromTwoPoints(int x1, int y1, int x2, int y2){
        return Math.sqrt(Math.pow((Math.abs(x2-x1)), 2) + Math.pow((Math.abs(y2-y1)), 2));
    }

}
