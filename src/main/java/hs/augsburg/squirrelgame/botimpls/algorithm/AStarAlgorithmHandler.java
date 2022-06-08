package hs.augsburg.squirrelgame.botimpls.algorithm;

import hs.augsburg.squirrelgame.util.XY;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class AStarAlgorithmHandler {

    /**
     * This is currently just a test handler class for the A* algorithm.
     * More information about A* algorithm: https://www.youtube.com/watch?v=-L-WgKMFuhE&t=618s
     */


    //The list of nodes to be evaluated
    private static List<AStarNode> openNodes = new ArrayList<>();
    //The list of nodes already evaluated
    private static List<AStarNode> closedNodes = new ArrayList<>();

    public static AStarNode[][] gameBoard = new AStarNode[100][100];


    public static void main(String[] args) {
        //You can change the XY positions for sure :)
        XY start = new XY(10, 10);
        XY target = new XY(15, 12);
        createObstacle();
        evaluate(start, target);
    }

    public static void evaluate(XY position, XY targetPosition){
        AStarNode startNode = new AStarNode(null, position, position, targetPosition);
        openNodes.add(startNode);
        int counter = 0;
        while(true){
            counter++;
            AStarNode currentNode = getNodeWithLowestFCost();
            openNodes.remove(currentNode);
            closedNodes.add(currentNode);
            if(currentNode.getPosition().toString().equalsIgnoreCase(targetPosition.toString())){
                //Path has been found
                System.out.println("Path found! Took " + counter + " runs.\nPath-List: ");
                printParents(currentNode);
                return;
            }
            for(int col = currentNode.getPosition().getX() - 1; col <= currentNode.getPosition().getX() + 1; col++){
                for(int row = currentNode.getPosition().getY() - 1; row <= currentNode.getPosition().getY() + 1; row++){
                    AStarNode neighbour = new AStarNode(currentNode, new XY(col, row), startNode.getPosition(), targetPosition);
                    if(gameBoard[col][row] != null || containsNode(closedNodes, neighbour)) {
                        continue;
                    }
                    if(!containsNode(openNodes, neighbour) || isPathShorter(neighbour)){
                        if(!containsNode(openNodes, neighbour)){
                            openNodes.add(neighbour);
                        }
                    }
                }
            }

        }
    }

    private static AStarNode getNodeWithLowestFCost(){
        AStarNode lowestFCostNode = null;
        int lowestFCost = Integer.MAX_VALUE;
        for(AStarNode aStarNode : openNodes){
            if(aStarNode.getFCost() < lowestFCost){
                lowestFCost = aStarNode.getFCost();
                lowestFCostNode = aStarNode;
            }
        }
        return lowestFCostNode;
    }

    private static boolean containsNode(List<AStarNode> list, AStarNode aStarNode){
        for(AStarNode aStarNodeInList : list){
            if(aStarNode.getPosition().toString().equalsIgnoreCase(aStarNodeInList.getPosition().toString())) return true;
        }
        return false;
    }

    private static boolean isPathShorter(AStarNode aStarNode){
        if(containsNode(openNodes, aStarNode)){
            AStarNode existingNode = getFromPosition(openNodes, aStarNode.getPosition());
            if(existingNode.getFCost() > aStarNode.getFCost()){
                return true;
            }
        }
        return false;
    }

    private static AStarNode getFromPosition(List<AStarNode> aStarNodeList, XY position){
        for(AStarNode aStarNode : aStarNodeList){
            if(aStarNode.getPosition().toString().equalsIgnoreCase(position.toString())){
                return aStarNode;
            }
        }
        return null;
    }

    /**
     * You can put some Obstacles on the board here to make the pathfinding a little bit more interesting :)
     */
    private static void createObstacle(){
        XY xy = new XY(1, 1);
        gameBoard[14][9] = new AStarNode(null, xy, xy, xy);
        gameBoard[14][10] = new AStarNode(null, xy, xy, xy);
        gameBoard[14][11] = new AStarNode(null, xy, xy, xy);
        gameBoard[15][11] = new AStarNode(null, xy, xy, xy);
    }

    private static AStarNode printParents(AStarNode aStarNode){
        System.out.println("PathNode:" + aStarNode.getPosition().toString());
        if(aStarNode.getParentNode() == null){
            return null;
        }
        return printParents(aStarNode.getParentNode());
    }

}
