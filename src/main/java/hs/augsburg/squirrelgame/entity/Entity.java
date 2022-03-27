package hs.augsburg.squirrelgame.entity;

import hs.augsburg.squirrelgame.util.Position;

public abstract class Entity {

    public static int idCount = 0;

    private int id;
    private int energy;
    private Position position;


    public Entity(Position position){
        this.position = position;
        this.id = idCount;
        idCount++;
    }

    public void updateEnergy(int energy){
        this.energy += energy;
    }

    public void updatePosition(Position position){
        this.position = position;
    }

    public void nextStep(){

    }

    public int getId() {
        return id;
    }

    public int getEnergy() {
        return energy;
    }

    public Position getPosition() {
        return position;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }
}
