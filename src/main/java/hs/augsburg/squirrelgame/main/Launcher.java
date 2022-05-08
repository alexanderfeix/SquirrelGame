package hs.augsburg.squirrelgame.main;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;
import hs.augsburg.squirrelgame.ui.FxUI;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
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
        VBox squirrelInfoBar = createSquirrelInfoBar();
        GridPane legendBar = createLegendBar();
        HBox statusBar = createStatusBar();
        MenuBar menuBar = createMenuBar();


        BorderPane root = new BorderPane();
        root.setTop(menuBar);
        root.setLeft(legendBar);
        root.setRight(squirrelInfoBar);
        root.setBottom(statusBar);
        root.setCenter(renderingPane);

        Scene scene = new Scene(root, 800, 700);
        stage.setTitle("Squirrel Game");
        stage.setScene(scene);
        stage.show();
    }

    private GridPane createLegendBar() {
        GridPane legendBar = new GridPane();
        Button pauseButton = new Button("pause");
        Button resumeButton = new Button("resume");
        Text controlTitle = new Text("Controls:");
            controlTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));


        legendBar.setHgap(10);
        legendBar.setVgap(0);
        legendBar.setPadding(new Insets(0, 10, 0, 10));

        //.add(Node, column, row, )
        legendBar.add(controlTitle,2, 5);
        legendBar.add(pauseButton, 2, 6);
        legendBar.add(resumeButton, 2, 7);

        return legendBar;
    }

    private MenuBar createMenuBar() {
        MenuItem pauseMenu = new MenuItem("pause");
            pauseMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        MenuItem quitMenu = new MenuItem("Quit");
            quitMenu.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
        MenuItem continueMenu = new MenuItem("continue");

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

    private VBox createSquirrelInfoBar() {
        VBox squirrelInfoBar = new VBox();
        squirrelInfoBar.setPadding(new Insets(10));
        squirrelInfoBar.setSpacing(8);

        Text title = new Text("Squirrel Energie:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        squirrelInfoBar.getChildren().add(title);

        return squirrelInfoBar;
    }

    private Pane createRenderingPane() {
        Pane renderingPane = new Pane();


        return renderingPane;
    }
}
