package hs.augsburg.squirrelgame.main;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.GameMode;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;
import hs.augsburg.squirrelgame.ui.FxUI;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class Launcher extends Application {

    private static FxUI fxUI;
    private static ConsoleUI consoleUI;
    private static BorderPane rootPane;
    private static GameImpl controller;
    private static Thread gameThread;

    public static void main(String[] args) {
        State state = new State(new Board());
        setupGame(state, args);
    }

    private static void setupGame(State state, String[] args){
        if(args.length > 0){
            if (args[0].equalsIgnoreCase(GameMode.SINGLEPLAYER_GUI.toString())) {
                setupSingleplayerGUI(state, args);
            }else if(args[0].equalsIgnoreCase(GameMode.SINGLEPLAYER_CONSOLE.toString())){
                setupConsole(state);
            }else if (args[0].equalsIgnoreCase(GameMode.BOT_GUI.toString())){
                setupBotGUI(state, args);
            }else{
                throw new RuntimeException("Can not interpret gameMode. Please correct your arguments!");
            }
        }else{
            throw new RuntimeException("Please correct your arguments in the run configuration to select a gamemode.");
        }
    }

    private static void setupSingleplayerGUI(State state, String[] args){
        fxUI = new FxUI();
        Game.setGameMode(GameMode.SINGLEPLAYER_GUI);
        controller = new GameImpl(state, fxUI);
        fxUI.setController(controller);
        startGame(controller);
        launch(args);
    }

    private static void setupBotGUI(State state, String[] args){
        fxUI = new FxUI();
        Game.setGameMode(GameMode.BOT_GUI);
        controller = new GameImpl(state, fxUI);
        fxUI.setController(controller);
        startGame(controller);
        launch(args);
    }

    private static void setupConsole(State state){
        consoleUI = new ConsoleUI();
        Game.setGameMode(GameMode.SINGLEPLAYER_CONSOLE);
        controller = new GameImpl(state, consoleUI);
        startGame(controller);
    }

    private static void startGame(Game game){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gameThread = new Thread(game::run);
        gameThread.start();
    }

    @Override
    public void start(Stage stage) throws Exception {
        rootPane = new BorderPane();
        VBox squirrelInfoBar = getFxUI().createSquirrelInfoBar();
        VBox legendBar = getFxUI().createLegendBar();
        HBox statusBar = getFxUI().createStatusBar();
        MenuBar menuBar = getFxUI().createMenuBar();
        GridPane gameBoard = getFxUI().getGameBoardPane();

        getRootPane().setTop(menuBar);
        getRootPane().setLeft(legendBar);
        getRootPane().setRight(squirrelInfoBar);
        getRootPane().setCenter(gameBoard);
        getRootPane().setBottom(statusBar);

        Scene scene = new Scene(getRootPane(), 1000, 800);
        stage.setTitle("Squirrel Game");
        stage.setScene(scene);
        stage.show();
        stage.setOnCloseRequest(we -> System.exit(0));

    }

    public FxUI getFxUI() {
        return fxUI;
    }

    public static BorderPane getRootPane() {
        return rootPane;
    }

    public static Thread getGameThread() {
        return gameThread;
    }

    public static void setGameThread(Thread gameThread) {
        Launcher.gameThread = gameThread;
    }
}
