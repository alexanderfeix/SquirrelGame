package hs.augsburg.squirrelgame.entity;

import java.util.*;

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
    public static int entityCounter = 0;

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
            entityCounter++;
        }
    }

    public Enumeration enumerateBackwards(){
        class E implements Enumeration{
            ListElement tempTail = tail;
            @Override
            public boolean hasMoreElements() {
                return tempTail != null;
            }

            @Override
            public ListElement nextElement() {
                if(tempTail == null){
                    throw new NoSuchElementException("No elements in list!");
                }
                if (!tempTail.hasPrev()) {
                    return tempTail;
                }
                ListElement newTempTail = tempTail.getPrevItem();
                ListElement output = tempTail;
                tempTail = newTempTail;
                return output;
            }
        }
        return new E();
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
            public ListElement nextElement() {
                if(temphead == null){
                    throw new NoSuchElementException("No elements in list!");
                }
                if (!temphead.hasNext()) {
                    return temphead;
                }
                ListElement newTempHead = temphead.getNextItem();
                ListElement output = temphead;
                temphead = newTempHead;
                return output;
            }
        };
    }
    public Enumeration enumerateRandom() {
        class EnumerateRandomClass implements java.util.Enumeration {
            HashSet<Integer> usedIndex = new HashSet<>();
            boolean checkUsedIndex = false;
            ListElement currentElement;
            ListElement[] arrayTemp = new ListElement[entityCounter];
            public EnumerateRandomClass(){
                currentElement = head;
                for (int j = 0; j < entityCounter; j++) {
                    arrayTemp[j] = currentElement;
                    currentElement = currentElement.getNextItem();
                }
            }
            @Override
            public boolean hasMoreElements() {
                return usedIndex.size() < entityCounter;
            }

            @Override
            public ListElement nextElement() { //maybe remove "dead" Entities in arraylist. Or check living entities.
                int randomIndex = 0;
                Random indexGenerator = new Random();
                if (this.hasMoreElements()) {
                    while (!checkUsedIndex) {
                        randomIndex = indexGenerator.nextInt(entityCounter);
                        checkUsedIndex = usedIndex.add(randomIndex);
                    }
                    checkUsedIndex = false;
                    currentElement = arrayTemp[randomIndex];
                    return currentElement;
                } else {
                    throw new NoSuchElementException("No more elements in list");
                }
            }
        }
        return new EnumerateRandomClass();
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
        while (enumerateRandom().hasMoreElements()){
            if(temptail.getPrevItem().getEntity().isAlive()){
                temptail.getPrevItem().getEntity().nextStep(entityContext);
            }
            temptail = enumerateRandom().nextElement();
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
