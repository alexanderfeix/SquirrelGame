package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.ui.UI;

public abstract class Game {

    private final State state;
    private final UI ui;
    public static final int FPS = 10;
    public static final int DELAY_MULTIPLY_FACTOR_CONSOLE = 5;
    public static boolean FPS_MODE = true;
    public static boolean PAUSE_MODE;
    private static GameMode gameMode;


    public Game(State state, UI ui) {
        this.state = state;
        this.ui = ui;
    }

    /**
     * This method is the game loop
     */
    public void run() {
        while (true) {
            if(!PAUSE_MODE){
                render();
                sleep();
                processInput();
                update();
            }else{
                processInput();
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

    public static GameMode getGameMode() {
        return gameMode;
    }

    public static void setGameMode(GameMode gameMode) {
        Game.gameMode = gameMode;
    }

    private void sleep(){
        if(FPS_MODE){
            try {
                if(getGameMode().equals(GameMode.SINGLEPLAYER_GUI)){
                    Thread.sleep(1000/FPS);
                }else{
                    Thread.sleep(1000/FPS * DELAY_MULTIPLY_FACTOR_CONSOLE);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
