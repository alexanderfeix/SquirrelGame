package hs.augsburg.squirrelgame.entity;

public abstract class MovableEntity extends Entity{

    public MovableEntity(hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(position, energy);
    }

    private void move(){
        updatePosition(getPosition().getRandomPosition());
        System.out.println("New position of: " + getId() + ": " + getPosition().getX() + ", " + getPosition().getY());
    }

    public void nextStep(){
        //Overrides method in Entity
        move();
    }
}
