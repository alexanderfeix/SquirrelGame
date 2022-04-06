package hs.augsburg.squirrelgame.entity;

public class ListElement {

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
}
