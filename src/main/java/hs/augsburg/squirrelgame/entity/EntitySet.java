package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.entity.beast.BadBeast;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.entity.util.Wall;
import hs.augsburg.squirrelgame.util.XY;

public class EntitySet {

    private static ListElement tail;

    public static void initializeExamples(){
        addEntity(new BadPlant(new XY(1, -3)));
        addEntity(new GoodPlant(new XY(2, -4)));
        addEntity(new BadBeast(new XY(0, 0)));
        addEntity(new GoodBeast(new XY(4, -6)));
        addEntity(new Wall(new XY(5, -7)));
        //MasterSquirrel masterSquirrel = new MasterSquirrel(new XY(6, -8));
        //addEntity(masterSquirrel);
        //Creating a mini-squirrel
        //addEntity(masterSquirrel.createMiniSquirrel(new XY(0, -1), 200));
    }

    public static void addEntity(Entity entity){
        ListElement newItem = new ListElement(entity);
        if(tail == null){
            tail = newItem;
        }else{
            ListElement prevTail = tail;
            tail.setNextItem(newItem);
            tail = newItem;
            tail.setPrevItem(prevTail);
        }
    }

    public static void removeEntity(Entity entity){
        ListElement tempTail = tail;
        if(tempTail == null){
            return;
        }
        if(!tempTail.hasPrev() && tempTail.getEntity() == entity){
            tail = null;
            return;
        }
        while(tempTail.hasPrev()){
            ListElement newTempTail = tempTail.getPrevItem();
            if(tempTail.getEntity() == entity){
                if(tail.getEntity() == tempTail.getEntity()){
                    tail = tempTail.getPrevItem();
                }
                tempTail.getPrevItem().setNextItem(null);
                tempTail.setNextItem(null);
                tempTail.setPrevItem(null);
                return;
            }
            if(tempTail.getPrevItem().getEntity() == entity){
                if(tempTail.getPrevItem().hasPrev()){
                    tempTail.getPrevItem().getPrevItem().setNextItem(tempTail);
                    tempTail.setPrevItem(tempTail.getPrevItem().getPrevItem());
                    tempTail.getPrevItem().setPrevItem(null);
                    tempTail.getPrevItem().setNextItem(null);
                }else{
                    tempTail.getPrevItem().setNextItem(null);
                    tempTail.setPrevItem(null);
                }
                return;
            }
            tempTail = newTempTail;
        }
    }

    public static void nextStep(){
        ListElement temptail = tail;
        temptail.getEntity().nextStep();
        while(temptail.hasPrev()){
            temptail.getPrevItem().getEntity().nextStep();
            temptail = temptail.getPrevItem();
        }
    }

    public static void getEntityInformations(){
        ListElement temptail = tail;
        if(temptail != null){
            if(temptail.getEntity().getEntityType() != EntityType.WALL){
                System.out.println("ID: " + temptail.getEntity().getId() + ", Energy: " + temptail.getEntity().getEnergy() + ", Position: " + temptail.getEntity().getPosition().getX() + ", " + temptail.getEntity().getPosition().getY() + " | " + temptail.getEntity().getEntityType());
            }
        }
        while(temptail != null && temptail.hasPrev()){
            if(temptail.getPrevItem().getEntity().getEntityType() != EntityType.WALL){
                System.out.println("ID: " + temptail.getPrevItem().getEntity().getId() + ", Energy: " + temptail.getPrevItem().getEntity().getEnergy() + ", Position: " + temptail.getPrevItem().getEntity().getPosition().getX() + ", " + temptail.getPrevItem().getEntity().getPosition().getY() + " | " + temptail.getPrevItem().getEntity().getEntityType());
            }
            temptail = temptail.getPrevItem();
        }
        System.out.println("\n----------\n");
    }

    public static ListElement getTail() {
        return tail;
    }
}
