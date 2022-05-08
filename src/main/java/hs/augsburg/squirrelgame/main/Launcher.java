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
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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

        Pane renderingPane = createRenderingPane();
        VBox vBox = createVBox();
        HBox statusBar = createStatusBar();
        MenuBar menuBar = createMenuBar();


        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(vBox);
        root.setBottom(statusBar);
        root.setCenter(renderingPane);

        Scene scene = new Scene(root, 800, 700);
        stage.setTitle("Squirrel Game");
        stage.setScene(scene);
        stage.show();
    }

    private MenuBar createMenuBar() {
        MenuItem pauseMenu = new Menu("pause");
        pauseMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));

        MenuItem continueMenu = new Menu("continue");

        MenuItem quitMenu = new Menu("Quit");
        quitMenu.setAccelerator(KeyCombination.keyCombination("Alt+F4"));

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(pauseMenu, continueMenu, new SeparatorMenuItem(), quitMenu);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        return menuBar;
    }

    private HBox createStatusBar() {
        HBox statusBar = new HBox();
        Label statusLabel = new Label("StatusBar funktioniert!");
        statusBar.getChildren().add(statusLabel);
        return statusBar;
    }

    private VBox createVBox() {
        VBox vBox = new VBox();
        return vBox;
    }

    private Pane createRenderingPane() {
        Pane renderingPane = new Pane();


        return renderingPane;
    }
}
