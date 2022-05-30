package hs.augsburg.squirrelgame.ui;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.command.CommandScanner;
import hs.augsburg.squirrelgame.command.GameCommandType;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.main.Launcher;
import hs.augsburg.squirrelgame.util.Direction;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FxUI implements UI{

    private GridPane gameBoardPane;
    private VBox squirrelInfoBar;
    private GameImpl controller;
    Label statusLabel = new Label("Game is running!");
    private static Direction nextDirection;


    MenuItem resumeMenu = new MenuItem("Resume");
    MenuItem pauseMenu = new MenuItem("Pause");
    Button pauseButton = new Button("Pause");
    Button resumeButton = new Button("Resume");
    Button helpButton = new Button("Help");
    Button spawnMiniSquirrelButton = new Button("Spawn MiniSquirrel");

    private boolean checkedHandOperatedSquirrelDeath = false;
    private final BufferedReader inputReader = new BufferedReader(new InputStreamReader(System.in));
    private final CommandScanner commandScanner = new CommandScanner(GameCommandType.values(), inputReader);


    public void setController(GameImpl game){
        this.controller = game;
    }

    @Override
    public void render(BoardView view) {
        Platform.runLater(() -> {
            handleSquirrelDead();
            refreshGameBoard(view);
            refreshSquirrelInfoBar(view);
            setNextDirection(null);
        });
    }

    @Override
    public Direction getNextDirection() {
        return nextDirection;
    }

    @Override
    public void setNextDirection(Direction direction) {
        nextDirection = direction;
    }

    @Override
    public Command getCommand() {
        return null;
    }

    @Override
    public void handleSquirrelDead() {
        if(!checkedHandOperatedSquirrelDeath){
            if(!getController().getHandOperatedMasterSquirrel().isAlive()){
                switchPauseItems();
                getController().setPause(true);
                setAllDisabledOnSquirrelDeath();
                checkedHandOperatedSquirrelDeath = true;
            }
        }
    }


    public void refreshGameBoard(BoardView view){
        gameBoardPane = getGridPane(view);
    }

    public GridPane getGridPane(BoardView view){
        if(gameBoardPane != null){
            gameBoardPane.getChildren().clear();
        }
        for(int col = 0; col < view.getGameBoard().length; col++){
            for(int row = 0; row < view.getGameBoard()[col].length; row++){
                try {
                    Entity entity = view.getEntity(col, row);
                    Shape shape = getRenderedEntityItem(entity.getEntityType());
                    gameBoardPane.add(shape, col, row);
                }catch (Exception ignored){}
            }
        }
        return gameBoardPane;
    }

    private Shape getRenderedEntityItem(EntityType entityType){
        switch (entityType) {
            case MASTER_SQUIRREL -> {
                return createRectangle(Color.BLACK);
            }
            case MINI_SQUIRREL -> {
                return createRectangle(Color.AQUA);
            }
            case BAD_BEAST -> {
                return createRectangle(Color.RED);
            }
            case GOOD_BEAST -> {
                return createRectangle(Color.GREEN);
            }
            case GOOD_PLANT -> {
                return createCircle(Color.GREEN);
            }
            case BAD_PLANT -> {
                return createCircle(Color.RED);
            }
            case WALL -> {
                return createRectangle(Color.ORANGE);
            }
        }
        return null;
    }


    public GridPane getGameBoardPane() {
        if(gameBoardPane == null){
            gameBoardPane = new GridPane();
            Launcher.getRootPane().setOnKeyPressed(new GUIKeyPressedListener());
            styleGameBoardPane();
        }
        return gameBoardPane;
    }

    private void styleGameBoardPane(){
        gameBoardPane.setAlignment(Pos.CENTER);
    }

    public VBox createLegendBar() {
        VBox legendBar = new VBox();
        GridPane gridPane = new GridPane();
        //Buttons
        resumeButton.setDisable(true);
            pauseButton.setOnAction(e -> {
                switchPauseItems();
                getController().setPause(true);
            });
            resumeButton.setOnAction(e -> {
                switchPauseItems();
                getController().setPause(false);
            });
            helpButton.setOnAction(e -> {
                if(!getController().isPause()){
                    switchPauseItems();
                    getController().setPause(true);
                }
                showHelpMenu();
            });
            spawnMiniSquirrelButton.setOnAction(e -> {
                if(!getController().isPause()){
                    switchPauseItems();
                    getController().setPause(true);
                }
                showSpawnMiniSquirrelMenu();
            });
        //Titles
        Text controlTitle = new Text("Controls");
        controlTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text legendTitle = new Text("Legend");
        legendTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        //Forms/Plants
        Text badPlantText = new Text("BadPlant");
        Circle badPlantCircle = createCircle(Color.RED);

        Text goodPlantText = new Text("GoodPlant");
        Circle goodPlantCircle = createCircle(Color.GREEN);

        //Forms/Beasts
        Text badBeastText = new Text("BadBeast");
        Rectangle badBeastRectangle = createRectangle(Color.RED);

        Text goodBeastText = new Text("GoodBeast");
        Rectangle goodBeastRectangle = createRectangle(Color.GREEN);

        //Forms/Squirrels
        Text masterSquirrelText = new Text("MasterSquirrel");
        Rectangle masterSquirrelRectangle = createRectangle(Color.BLACK);

        Text miniSquirrelText = new Text("MiniSquirrel");
        Rectangle miniSquirrelRectangle = createRectangle(Color.LIGHTBLUE);

        Text handOperatedSquirrelText = new Text("HandOperatedSquirrel");
        Rectangle handOperatedMasterSquirrelRectangle = createRectangle(Color.BLUE);

        //Forms/Structure
        Text wallText = new Text("Wall");
        Rectangle wallRectangle = createRectangle(Color.ORANGE);

        //alignments
        legendBar.setAlignment(Pos.TOP_LEFT);
        legendBar.setPadding(new Insets(25,0,50,25)); //TOP,RIGHT,BOTTOM,LEFT @param
        legendBar.setMargin(legendTitle, new Insets(100,0,10,0)); //TOP,RIGHT,BOTTOM,LEFT @param
        legendBar.setSpacing(5);
        gridPane.setHgap(5);
        gridPane.setVgap(3);

        setIndices(badPlantCircle, badPlantText, 0, 0);
        setIndices(badBeastRectangle, badBeastText, 0, 1);
        setIndices(goodPlantCircle, goodPlantText, 0, 2);
        setIndices(goodBeastRectangle, goodBeastText, 0, 3);
        setIndices(masterSquirrelRectangle, masterSquirrelText, 0, 4);
        setIndices(miniSquirrelRectangle, miniSquirrelText, 0, 5);
        setIndices(wallRectangle, wallText, 0, 6);
        setIndices(handOperatedMasterSquirrelRectangle, handOperatedSquirrelText, 0, 7);

        gridPane.getChildren().addAll(badPlantText, badBeastText, goodPlantText, goodBeastText, masterSquirrelText, miniSquirrelText, wallText, handOperatedSquirrelText);
        gridPane.getChildren().addAll(badPlantCircle, badBeastRectangle, goodPlantCircle, goodBeastRectangle, masterSquirrelRectangle, miniSquirrelRectangle, wallRectangle, handOperatedMasterSquirrelRectangle);

        legendBar.getChildren().addAll(controlTitle, pauseButton, resumeButton, helpButton, spawnMiniSquirrelButton, legendTitle, gridPane);

        return legendBar;
    }

    public MenuBar createMenuBar() {
        pauseMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
        resumeMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+R"));
        resumeMenu.setDisable(true);
            pauseMenu.setOnAction(e -> {
                switchPauseItems();
                getController().setPause(true);
            });
            resumeMenu.setOnAction(e -> {
                switchPauseItems();
                getController().setPause(false);
            });

        MenuItem quitMenu = new MenuItem("Quit");
        quitMenu.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
            quitMenu.setOnAction(e-> {
                System.exit(0);
            });

        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(pauseMenu, resumeMenu, new SeparatorMenuItem(), quitMenu);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        return menuBar;
    }

    public HBox createStatusBar() {
        HBox statusBar = new HBox();
        statusBar.getChildren().add(statusLabel);
        return statusBar;
    }

    public VBox createSquirrelInfoBar() {
        VBox squirrelInfoBar = new VBox();
        squirrelInfoBar.setPadding(new Insets(10));
        squirrelInfoBar.setSpacing(8);
        setSquirrelInfoBar(squirrelInfoBar);
        return squirrelInfoBar;
    }

    private void refreshSquirrelInfoBar(BoardView view){
        if(getSquirrelInfoBar() != null){
            getSquirrelInfoBar().getChildren().clear();
            for(int col = 0; col < view.getGameBoard().length; col++){
                for(int row = 0; row < view.getGameBoard()[col].length; row++){
                    try {
                        Entity entity = view.getEntity(col, row);
                        if(entity.getEntityType() == EntityType.MASTER_SQUIRREL || entity.getEntityType() == EntityType.MINI_SQUIRREL){
                            Text text = new Text(entity.getEntityType().toString() + ": " + entity.getEnergy());
                            text.setFont(Font.font("Arial", FontWeight.NORMAL, 12));
                            getSquirrelInfoBar().getChildren().add(text);
                        }
                    }catch (Exception ignored){}
                }
            }
        }
    }

    private void showHelpMenu(){
        VBox helpVbox = new VBox();
        helpVbox.setPadding(new Insets(20, 20, 20, 20));
        helpVbox.setAlignment(Pos.CENTER_LEFT);
        Stage stage = new Stage();
        Scene scene = new Scene(helpVbox, 400, 200);
        stage.setTitle("Help Menu");
        stage.setScene(scene);
        stage.setResizable(false);
        createCommandButtons(helpVbox);
        stage.show();
    }

    private void showSpawnMiniSquirrelMenu(){
        VBox helpVbox = new VBox();
        helpVbox.setAlignment(Pos.CENTER_LEFT);
        Stage stage = new Stage();
        Scene scene = new Scene(helpVbox, 400, 150);
        stage.setTitle("SpawnMiniSquirrel");
        stage.setScene(scene);
        stage.setResizable(false);
        styleSpawnMiniSquirrelMenu(stage, helpVbox);
        stage.show();
    }

    private void createCommandButtons(Pane pane){
        for(GameCommandType gameCommandType : getController().getGameCommandTypes()){
            Text text = new Text(gameCommandType.getName() + ": " + gameCommandType.getHelpText());
            pane.getChildren().addAll(text);
        }
    }

    private void styleSpawnMiniSquirrelMenu(Stage stage, Pane pane){
        Text text = new Text("Please type in the spawn-energy");
        TextField textField = new TextField("250");
        pane.getChildren().addAll(text, textField);
        pane.setPadding(new Insets(20, 20, 20, 20));
        textField.setOnAction(e -> {
            try {
                int energy = Integer.parseInt(textField.getText());
                getController().getState().getFlattenedBoard().createStandardMiniSquirrel(getController().getHandOperatedMasterSquirrel(), energy);
                stage.close();
                switchPauseItems();
                getController().setPause(false);
            }catch (Exception ignored){}
        });
    }

    //Helper methods

    private void setIndices(Shape shape, Text text, int colIndex, int rowIndex){
        GridPane.setRowIndex(shape, rowIndex);
        GridPane.setColumnIndex(shape, colIndex);
        GridPane.setRowIndex(text, rowIndex);
        GridPane.setColumnIndex(text, colIndex + 1);
    }

    private Rectangle createRectangle(Color color){
        Rectangle rectangle = new Rectangle(10, 10, 10, 10);
        rectangle.setStroke(color);
        rectangle.setFill(color);
        return rectangle;
    }

    private Circle createCircle(Color color){
        Circle circle = new Circle(10, 10, 5);
        circle.setStroke(color);
        circle.setFill(color);
        return circle;
    }

    private void switchPauseItems(){
        String statusText;
        if(getController().isPause()){
            pauseButton.setDisable(false);
            pauseMenu.setDisable(false);
            resumeButton.setDisable(true);
            resumeMenu.setDisable(true);
            helpButton.setDisable(false);
            spawnMiniSquirrelButton.setDisable(false);
            statusText = "Game resumed!";
        }else{
            pauseButton.setDisable(true);
            pauseMenu.setDisable(true);
            resumeButton.setDisable(false);
            resumeMenu.setDisable(false);
            helpButton.setDisable(false);
            spawnMiniSquirrelButton.setDisable(false);
            statusText = "Game paused!";
        }
        statusLabel.setText(statusText);
    }

    private void setAllDisabledOnSquirrelDeath(){
        pauseMenu.setDisable(true);
        pauseButton.setDisable(true);
        resumeMenu.setDisable(true);
        resumeButton.setDisable(true);
        spawnMiniSquirrelButton.setDisable(true);
        helpButton.setDisable(true);
        statusLabel.setText("Oh no, your squirrel has died. Game Over!");
    }

    public GameImpl getController() {
        return controller;
    }

    public VBox getSquirrelInfoBar() {
        return squirrelInfoBar;
    }

    public void setSquirrelInfoBar(VBox squirrelInfoBar) {
        this.squirrelInfoBar = squirrelInfoBar;
    }
}
