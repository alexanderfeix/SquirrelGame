package hs.augsburg.squirrelgame.main;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.FxUI;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class Launcher extends Application {

    private static FxUI fxUI;
    private static BorderPane rootPane;
    private static GameImpl controller;
    private static Thread gameThread;

    public static void main(String[] args) {
        State state = new State(new Board());
        setupGUI(state, args);
    }

    private static void setupGUI(State state, String[] args){
        fxUI = new FxUI();
        controller = new GameImpl(state, fxUI);
        fxUI.setController(controller);
        startGame(controller);
        launch(args);
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

        Scene scene = new Scene(getRootPane(), 800, 700);
        stage.setTitle("Squirrel Game");
        stage.setScene(scene);
        stage.show();
    }

    public FxUI getFxUI() {
        return fxUI;
    }

    public static BorderPane getRootPane() {
        return rootPane;
    }

    public static GameImpl getController() {
        return controller;
    }

    public static Thread getGameThread() {
        return gameThread;
    }

    public static void setGameThread(Thread gameThread) {
        Launcher.gameThread = gameThread;
    }
}
