package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.ui.UI;

public abstract class Game {

    private final State state;
    private final UI ui;
    public static final int FPS = 1000;
    public static boolean FPS_MODE = true;

    public Game(State state, UI ui) {
        this.state = state;
        this.ui = ui;
    }

    /**
     * This method is the game loop
     */
    public void run() {
        while (true) {
            render();
            processInput();
            update();
            sleep();
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
        getState().getBoard().getEntitySet().nextStep(getState().getBoard().getFlattenedBoard());
    }

    /**
     * Shows current state on output medium
     */
    public abstract void render();

    public State getState() {
        return state;
    }

    public UI getUi() {
        return ui;
    }

    private void sleep(){
        if(FPS_MODE){
            try {
                Thread.sleep(Game.FPS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
