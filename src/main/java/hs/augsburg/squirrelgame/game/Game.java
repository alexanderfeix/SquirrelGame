package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.entity.EntitySet;

public abstract class Game{

    public void run(){
        while (true){
            render();
            processInput();
            update();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Process user input
     */
    public abstract void processInput();

    /**
     * Updates the current state
     */
    public void update() {
        EntitySet.nextStep();
    }

    /**
     * Shows current state on output medium
     */
    public abstract void render();

}
