package hs.augsburg.squirrelgame.main;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;
import hs.augsburg.squirrelgame.ui.FxUI;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Launcher extends Application {

    public static void main(String[] args) {
        launch(args);
        FxUI fxUI = new FxUI();
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
        Group root = new Group();
        Scene scene = new Scene(root, 800, 700);
        stage.setTitle("Squirrel Game");
        stage.setScene(scene);
        stage.show();
    }
}
