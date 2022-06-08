package hs.augsburg.squirrelgame.botimpls.algorithm;

import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;

/*

    Copyright © 2022 Alexander Feix
    Mail: alexander.feix03@gmail.com
    Location: SquirrelGame/hs.augsburg.squirrelgame.botimpls.algorithm
    Date: 08.06.2022
    
*/
public class AStarNode {

    private XY position;
    private int gCost;
    private int hCost;
    private int fCost;
    private double distanceToTarget;
    private double distanceToStart;
    private AStarNode parentNode;

    public AStarNode(AStarNode parentNode, XY position, XY startPosition, XY targetPosition){
        this.position = position;
        this.parentNode = parentNode;
        setDistanceToTarget(targetPosition);
        setDistanceToStart(startPosition);
        gCost = calculateGCost();
        hCost = calculateHeuristicCost();
        fCost = gCost + hCost;
    }

    private void setDistanceToTarget(XY targetPosition){
        distanceToTarget = MathUtils.getDistanceFromTwoPoints(position, targetPosition);
    }

    private void setDistanceToStart(XY startPosition){
        distanceToStart = MathUtils.getDistanceFromTwoPoints(position, startPosition);
    }

    private int calculateGCost(){
        return 10 + (int) distanceToStart;
    }
    private int calculateHeuristicCost(){
        return 10 + (int) distanceToTarget;
    }

    public double getDistanceToStart() {
        return distanceToStart;
    }

    public double getDistanceToTarget() {
        return distanceToTarget;
    }

    public int getFCost() {
        return fCost;
    }

    public int getGCost() {
        return gCost;
    }

    public int getHCost() {
        return hCost;
    }

    public XY getPosition() {
        return position;
    }

    public AStarNode getParentNode() {
        return parentNode;
    }

    public void setParentNode(AStarNode parentNode) {
        this.parentNode = parentNode;
    }
}
