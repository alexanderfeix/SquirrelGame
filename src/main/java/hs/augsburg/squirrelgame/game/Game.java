package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.ui.UI;

public abstract class Game {

    private final State state;
    private final UI ui;

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
            try {
                Thread.sleep(1000);
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
}
