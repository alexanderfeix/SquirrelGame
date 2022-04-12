package hs.augsburg.squirrelgame.game;

public abstract class Game {

    private final State state;

    public Game(State state) {
        this.state = state;
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
                Thread.sleep(2000);
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
}
