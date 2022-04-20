package hs.augsburg.squirrelgame.entity;

import java.util.ArrayList;
import java.util.Enumeration;

public class EntitySet {

    private static class ListElement{
        private final Entity entity;
        private ListElement prevItem;
        private ListElement nextItem;

        public ListElement(Entity entity) {
            this.entity = entity;
            nextItem = null;
        }

        public Entity getEntity() {
            return entity;
        }

        public ListElement getPrevItem() {
            return prevItem;
        }

        public void setPrevItem(ListElement prevItem) {
            this.prevItem = prevItem;
        }

        public ListElement getNextItem() {
            return nextItem;
        }

        public void setNextItem(ListElement nextItem) {
            this.nextItem = nextItem;
        }

        public boolean hasPrev() {
            return this.prevItem != null;
        }

        public boolean hasNext() { return this.nextItem != null;}
    }


    private ListElement tail;
    private ListElement head;

    public void addEntity(Entity entity) {
        if (entityExists(entity)) {
            throw new IllegalStateException("An entity with this id already exists!");
        } else {
            ListElement newItem = new ListElement(entity);
            if (tail == null) {
                head = newItem;
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
            head = null;
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

    public Enumeration enumerateForward() {
        return new Enumeration() {
            ListElement temphead = head;

            @Override
            public boolean hasMoreElements() {
                return temphead != null;
            }

            @Override
            public Object nextElement() {
                if (!temphead.hasNext()) {
                    return temphead.getEntity();
                }
                while (temphead.hasNext()) {
                    if (temphead.getEntity() != null) {
                        return temphead.getEntity();
                    }
                    temphead = temphead.getNextItem();
                }
                return temphead.getEntity();
            }
        };

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
        while (temptail.hasPrev()){
            if(temptail.getPrevItem().getEntity().isAlive()){
                temptail.getPrevItem().getEntity().nextStep(entityContext);
            }
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
            if(tempTail.getPrevItem().getEntity().isAlive()){
                entities.add(tempTail.getPrevItem().getEntity());
            }
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
     * @param allEntities return all entities or only alive entities?
     */
    public int countItems(boolean allEntities) {
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
