package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.util.XY;

public abstract class Entity implements EntityInterface {

    public static int idCount = 0;

    private final int id;
    private final EntityType entityType;
    private int energy;
    private XY XY;
    private Entity entity;


    public Entity(EntityType entityType, XY position, int energy) {
        this.XY = position;
        this.energy = energy;
        this.entityType = entityType;
        this.id = idCount;
        idCount++;
    }

    public void updateEnergy(int energy) {
        this.energy += energy;
    }

    public void updatePosition(XY position) {
        this.XY = position;
    }

    public void nextStep() {
    }

    public boolean equals(Entity entity) {
        return getId() == entity.getId();
    }

    public int getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public XY getPosition() {
        return XY;
    }

    public Entity getEntity() {
        return entity;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public EntityType getEntityType() {
        return entityType;
    }
}
