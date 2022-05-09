package hs.augsburg.squirrelgame.main;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;
import hs.augsburg.squirrelgame.ui.FxUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Launcher extends Application {

    private final FxUI fxUI = new FxUI();

    public static void main(String[] args) {
        launch(args);
        State state = new State(new Board());
        Game game = new GameImpl(state, new ConsoleUI());
        startGame(game);
    }

    private static void startGame(Game game){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        game.run();
    }

    @Override
    public void start(Stage stage) throws Exception {
        VBox squirrelInfoBar = getFxUI().createSquirrelInfoBar();
        VBox legendBar = getFxUI().createLegendBar();
        HBox statusBar = getFxUI().createStatusBar();
        MenuBar menuBar = getFxUI().createMenuBar();
        GridPane gameBoard = new FxUI().getGameBoardPane();

        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(legendBar);
        root.setRight(squirrelInfoBar);
        root.setCenter(gameBoard);
        root.setBottom(statusBar);

        Scene scene = new Scene(root, 800, 700);
        stage.setTitle("Squirrel Game");
        stage.setScene(scene);
        stage.show();
    }

    public FxUI getFxUI() {
        return fxUI;
    }
}
