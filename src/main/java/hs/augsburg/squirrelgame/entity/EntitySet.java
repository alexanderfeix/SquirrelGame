package hs.augsburg.squirrelgame.entity;

import java.util.ArrayList;

public class EntitySet {

    private ListElement tail;

    public void addEntity(Entity entity) {
        if (entityExists(entity)) {
            throw new IllegalStateException("An entity with this id already exists!");
        } else {
            ListElement newItem = new ListElement(entity);
            if (tail == null) {
                tail = newItem;
            } else {
                ListElement prevTail = tail;
                tail.setNextItem(newItem);
                tail = newItem;
                tail.setPrevItem(prevTail);
            }
        }
    }


    public void removeEntity(Entity entity) {
        if (!entityExists(entity)) throw new IllegalStateException("The entity doesn't exists!");

        ListElement tempTail = tail;
        if (tempTail == null) {
            return;
        }
        if (!tempTail.hasPrev() && tempTail.getEntity() == entity) {
            tail = null;
            return;
        }
        while (tempTail.hasPrev()) {
            ListElement newTempTail = tempTail.getPrevItem();
            if (tempTail.getEntity() == entity) {
                if (tail.getEntity() == tempTail.getEntity()) {
                    tail = tempTail.getPrevItem();
                }
                tempTail.getPrevItem().setNextItem(null);
                tempTail.setNextItem(null);
                tempTail.setPrevItem(null);
                return;
            }
            if (tempTail.getPrevItem().getEntity() == entity) {
                if (tempTail.getPrevItem().hasPrev()) {
                    tempTail.getPrevItem().getPrevItem().setNextItem(tempTail);
                    tempTail.setPrevItem(tempTail.getPrevItem().getPrevItem());
                    tempTail.getPrevItem().setPrevItem(null);
                    tempTail.getPrevItem().setNextItem(null);
                } else {
                    tempTail.getPrevItem().setNextItem(null);
                    tempTail.setPrevItem(null);
                }
                return;
            }
            tempTail = newTempTail;
        }

    }

    public boolean entityExists(Entity entity) {
        ListElement tempTail = tail;
        if (tempTail == null) {
            return false;
        }
        if (!tempTail.hasPrev() && tempTail.getEntity().equals(entity)) {
            return true;
        }
        while (tempTail.hasPrev()) {
            ListElement newTempTail = tempTail.getPrevItem();
            if (tempTail.getEntity().equals(entity)) {
                return true;
            }
            tempTail = newTempTail;
        }
        return tempTail.getEntity().equals(entity);
    }

    /**
     * Calls the nextStep() method on all entities
     */
    public void nextStep(EntityContext entityContext) {
        ListElement temptail = tail;
        temptail.getEntity().nextStep(entityContext);
        while (temptail.hasPrev()) {
            temptail.getPrevItem().getEntity().nextStep(entityContext);
            temptail = temptail.getPrevItem();
            }
        }


    /**
     * @return ArrayList with all entities in the linked list
     * commonly used to get all entities that are currently on the board
     */
    public ArrayList<Entity> getEntities() {
        ArrayList<Entity> entities = new ArrayList<>();
        if (getTail() == null) {
            return entities;
        }
        ListElement tempTail = getTail();
        entities.add(tempTail.getEntity());
        while (tempTail.hasPrev()) {
            entities.add(tempTail.getPrevItem().getEntity());
            tempTail = tempTail.getPrevItem();
        }
        return entities;
    }

    /**
     * @return the id of the tail. When tail == null, then return -1 flag.
     */
    public int returnLastID() {
        if (tail == null) {
            return -1;
        } else {
            return tail.getEntity().getId();
        }
    }

    /**
     * @return the amount of items in the list
     */
    public int countItems() {
        ListElement temptail = tail;
        int counter = 0;
        if (temptail == null) {
            return counter;
        } else {
            while (temptail != null) {
                counter++;
                temptail = temptail.getPrevItem();
            }
        }
        return counter;
    }

    public ListElement getTail() {
        return tail;
    }
}
