package hs.augsburg.squirrelgame.entity;

import java.util.Enumeration;
import java.util.HashSet;
import java.util.NoSuchElementException;
import java.util.Random;

public class EntitySet {
    /**
     * This class is used to manage the entities. Every instance of EntitySet could contain a different set of entities.
     */


    private ListElement tail;
    private ListElement head;
    /**
     * Counts the entities in the EntitySet. Do not confuse with the idCounter in Entity. The id in Entity-Class
     * is a real identifier for every entity created. The entityCounter in EntitySet identifies only counts the entities
     * in this EntitySet. So, the actual number of entities created can vary from the integer value below.
     */
    private int entityCounter = 0;

    /**
     * Adds an entity to the EntitySet.
     *
     * @param entity
     */
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

    /**
     * Enumerates the set of entities from head to tail.
     *
     * @return
     */
    public Enumeration enumerateForward() {
        return new Enumeration() {
            ListElement temphead = head;

            @Override
            public boolean hasMoreElements() {
                return temphead != null;
            }

            @Override
            public Entity nextElement() {
                if (temphead == null) {
                    throw new NoSuchElementException("No elements in list!");
                }
                if (!temphead.hasNext()) {
                    return temphead.getEntity();
                }
                ListElement newTempHead = temphead.getNextItem();
                ListElement output = temphead;
                temphead = newTempHead;
                return output.getEntity();
            }
        };
    }

    /**
     * Enumerates the set of entities from tail to head.
     *
     * @return
     */
    public Enumeration enumerateBackwards() {
        class E implements Enumeration {
            ListElement tempTail = tail;

            @Override
            public boolean hasMoreElements() {
                return tempTail != null;
            }

            @Override
            public Entity nextElement() {
                if (tempTail == null) {
                    throw new NoSuchElementException("No elements in list!");
                }
                if (!tempTail.hasPrev()) {
                    ListElement output = tempTail;
                    tempTail = null;
                    return output.getEntity();
                }
                ListElement newTempTail = tempTail.getPrevItem();
                ListElement output = tempTail;
                tempTail = newTempTail;
                return output.getEntity();
            }
        }
        return new E();
    }

    /**
     * Enumerates the set of entities randomly.
     * The method is working with indices and checks that no index can show up a second time.
     * Maybe check this method for cleaner code.
     *
     * @return
     */
    public Enumeration enumerateRandom() {
        class EnumerateRandomClass implements java.util.Enumeration {
            final HashSet<Integer> usedIndex = new HashSet<>();
            boolean checkUsedIndex = false;
            ListElement currentElement;
            final ListElement[] arrayTemp = new ListElement[entityCounter];

            public EnumerateRandomClass() {
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
            public Entity nextElement() { //maybe remove "dead" Entities in arraylist. Or check living entities.
                int randomIndex = 0;
                Random indexGenerator = new Random();
                if (this.hasMoreElements()) {
                    while (!checkUsedIndex) {
                        randomIndex = indexGenerator.nextInt(entityCounter);
                        checkUsedIndex = usedIndex.add(randomIndex);
                    }
                    checkUsedIndex = false;
                    currentElement = arrayTemp[randomIndex];
                    return currentElement.getEntity();
                } else {
                    throw new NoSuchElementException("No more elements in list");
                }
            }
        }
        return new EnumerateRandomClass();
    }

    /**
     * Checks if the set of entities contains a specific entity
     *
     * @param entity
     * @return
     */
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
        Enumeration enumeration = enumerateRandom();
        while (enumeration.hasMoreElements()) {
            Entity current = (Entity) enumeration.nextElement();
            if (current.isAlive()) {
                current.nextStep(entityContext);
            }
        }
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
     * @param allEntities return all entities or only alive entities?
     * @return the amount of items in the list
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

    /**
     * This class must be private static!
     */
    private static class ListElement {
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

        public boolean hasNext() {
            return this.nextItem != null;
        }
    }

}
