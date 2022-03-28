package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.entity.beast.BadBeast;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.entity.util.Wall;
import hs.augsburg.squirrelgame.util.XY;

public class EntitySet {

    private static ListElement tail;

    public static void initializeExamples(){
        addEntity(new BadPlant(new XY(1, 3)));
        addEntity(new GoodPlant(new XY(2, 4)));
        addEntity(new BadBeast(new XY(3, 5)));
        addEntity(new GoodBeast(new XY(4, 6)));
        addEntity(new Wall(new XY(5, 7)));
        MasterSquirrel masterSquirrel = new MasterSquirrel(new XY(6, 8));
        addEntity(masterSquirrel);
        addEntity(masterSquirrel.createMiniSquirrel(new XY(1, 1), 200));
 
    }

    public static void addEntity(Entity entity){
        ListElement newItem = new ListElement(entity);
        if(tail == null){
            tail = newItem;
        }else{
            tail.setNextItem(newItem);
            ListElement temptail = tail;
            tail = new ListElement(entity);
            tail.setPrevItem(temptail);
        }
    }

    public static void removeEntity(Entity entity){
        ListElement temptail = tail;
        while(temptail.hasPrev()){
            if(temptail.getPrevItem().getEntity() == entity){
                temptail.getPrevItem().setNextItem(null);
                temptail.getPrevItem().setPrevItem(null);
                temptail.setPrevItem(temptail.getPrevItem().getPrevItem());
                temptail.getPrevItem().getPrevItem().setNextItem(temptail);
                continue;
            }
            temptail = temptail.getPrevItem();
        }
    }

    public static void nextStep(Entity entity){
        entity.nextStep();
        ListElement temptail = tail;
        temptail.getEntity().nextStep();
        while(temptail.hasPrev()){
            temptail.getPrevItem().getEntity().nextStep();
            temptail = temptail.getPrevItem();
        }
    }

    public static void getEntityInformations(){
        ListElement temptail = tail;
        System.out.println("ID: " + temptail.getEntity().getId() + ", Energy: " + temptail.getEntity().getEnergy() + ", Position: " + temptail.getEntity().getPosition().getX() + ", " + temptail.getEntity().getPosition().getY());
        while(temptail.hasPrev()){
            System.out.println("ID: " + temptail.getPrevItem().getEntity().getId() + ", Energy: " + temptail.getPrevItem().getEntity().getEnergy() + ", Position: " + temptail.getEntity().getPosition().getX() + ", " + temptail.getEntity().getPosition().getY());
            temptail = temptail.getPrevItem();
        }
    }

}
class ListElement {

    private final Entity entity;
    private ListElement prevItem;
    private ListElement nextItem;

    public ListElement(Entity entity){
        this.entity = entity;
        nextItem = null;
    }

    public Entity getEntity() {
        return entity;
    }

    public ListElement getPrevItem() {
        return prevItem;
    }

    public ListElement getNextItem() {
        return nextItem;
    }

    public void setPrevItem(ListElement prevItem) {
        this.prevItem = prevItem;
    }

    public void setNextItem(ListElement nextItem) {
        this.nextItem = nextItem;
    }

    public boolean hasPrev(){
        return this.prevItem != null;
    }
}
