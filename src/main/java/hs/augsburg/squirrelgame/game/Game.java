package hs.augsburg.squirrelgame.game;

public abstract class Game {

    public void run(){
        while (true){
            render();
            processInput();
            update();
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
    }

    /**
     * Shows current state on output medium
     */
    public abstract void render();

}
