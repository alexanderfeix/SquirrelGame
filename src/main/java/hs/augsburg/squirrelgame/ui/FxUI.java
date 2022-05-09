package hs.augsburg.squirrelgame.ui;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.util.Direction;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class FxUI implements UI{

    private GridPane gameBoardPane;
    private final GameImpl controller;

    public FxUI(GameImpl controller){
        this.controller = controller;
    }

    @Override
    public void render(BoardView view) {
        //Print pane in the center of the view here
        gameBoardPane = getGridPane(view);
    }

    @Override
    public Direction getNextDirection() {
        return null;
    }

    @Override
    public void setNextDirection(Direction direction) {

    }

    @Override
    public Command getCommand() {
        return null;
    }

    public GridPane getGridPane(BoardView view){
        gameBoardPane = new GridPane();
        for(int col = 0; col < view.getGameBoard().length; col++){
            for(int row = 0; row < view.getGameBoard()[col].length; row++){
                Entity entity = view.getEntity(col, row);
                Shape shape = getRenderedEntityItem(entity.getEntityType());
                gameBoardPane.add(shape, col, row);
            }
        }
        return gameBoardPane;
    }

    private Shape getRenderedEntityItem(EntityType entityType){
        switch (entityType) {
            case MASTER_SQUIRREL -> {
                Rectangle masterSquirrel = new Rectangle(10, 10, 10, 10);
                masterSquirrel.setStroke(Color.BLACK);
                masterSquirrel.setFill(Color.BLACK);
                return masterSquirrel;
            }
            case MINI_SQUIRREL -> {
                Rectangle miniSquirrel = new Rectangle(10, 10, 10, 10);
                miniSquirrel.setStroke(Color.AQUA);
                miniSquirrel.setFill(Color.AQUA);
                return miniSquirrel;
            }
            case BAD_BEAST -> {
                Rectangle badBeast = new Rectangle(10, 10, 10, 10);
                badBeast.setStroke(Color.RED);
                badBeast.setFill(Color.RED);
                return badBeast;
            }
            case GOOD_BEAST -> {
                Rectangle goodBeast = new Rectangle(10, 10, 10, 10);
                goodBeast.setStroke(Color.GREEN);
                goodBeast.setFill(Color.GREEN);
                return goodBeast;
            }
            case GOOD_PLANT -> {
                Circle goodPlant = new Circle(10, 10, 5);
                goodPlant.setStroke(Color.GREEN);
                goodPlant.setFill(Color.GREEN);
                return goodPlant;
            }
            case BAD_PLANT -> {
                Circle badPlant = new Circle(10, 10, 5);
                badPlant.setStroke(Color.RED);
                badPlant.setFill(Color.RED);
                return badPlant;
            }
            case WALL -> {
                Rectangle wall = new Rectangle(10, 10, 10, 10);
                wall.setStroke(Color.ORANGE);
                wall.setFill(Color.ORANGE);
                return wall;
            }
            default -> {
            }
        }
        Rectangle empty = new Rectangle(10, 10, 10, 10);
        empty.setStroke(Color.WHITE);
        empty.setFill(Color.WHITE);
        return empty;
    }

    public GridPane getGameBoardPane() {
        return gameBoardPane;
    }

    public VBox createLegendBar() {
        VBox legendBar = new VBox();
        GridPane gridPane = new GridPane();
        //Buttons
        Button resumeButton = new Button("Resume");
        Button pauseButton = new Button("Pause");
            pauseButton.setOnAction((ActionEvent event) ->{
                getController().setPause(true);
                pauseButton.setDisable(true);
                resumeButton.setDisable(false);

            });
            resumeButton.setOnAction((ActionEvent event) ->{
                getController().setPause(false);
                resumeButton.setDisable(true);
                pauseButton.setDisable(false);
            });
        //Titles
        Text controlTitle = new Text("Controls");
        controlTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        Text legendTitle = new Text("Legend");
        legendTitle.setFont(Font.font("Arial", FontWeight.BOLD, 14));

        //Forms/Plants
        Text badPlantText = new Text("BadPlant");
        Circle c1BadPlant = new Circle(10,10,5);
        c1BadPlant.setStroke(Color.RED);
        c1BadPlant.setFill(Color.RED);

        Text goodPlantText = new Text("GoodPlant");
        Circle c2GoodPlant = new Circle(10,10,5);
        c2GoodPlant.setStroke(Color.GREEN);
        c2GoodPlant.setFill(Color.GREEN);

        //Forms/Beasts
        Text badBeastText = new Text("BadBeast");
        Rectangle r1BadBeast = new Rectangle(10,10,10,10);
        r1BadBeast.setStroke(Color.RED);
        r1BadBeast.setFill(Color.RED);

        Text goodBeastText = new Text("GoodBeast");
        Rectangle r2GoodBeast = new Rectangle(10,10,10,10);
        r2GoodBeast.setStroke(Color.GREEN);
        r2GoodBeast.setFill(Color.GREEN);

        //Forms/Squirrels
        Text masterSquirrelText = new Text("MasterSquirrel");
        Rectangle masterSquirrel = new Rectangle(10,10,10,10);
        masterSquirrel.setStroke(Color.BLACK);
        masterSquirrel.setFill(Color.BLACK);

        Text miniSquirrelText = new Text("MiniSquirrel");
        Rectangle miniSquirrel = new Rectangle(10,10,10,10);
        miniSquirrel.setStroke(Color.LIGHTSKYBLUE);
        miniSquirrel.setFill(Color.LIGHTSKYBLUE);

        Text handOperatedSquirrelText = new Text("HandOperatedSquirrel");
        Rectangle handOperatedSquirrel = new Rectangle(10,10,10,10);
        handOperatedSquirrel.setStroke(Color.BLUE);
        handOperatedSquirrel.setFill(Color.BLUE);

        //Forms/Structure
        Text wallText = new Text("Wall");
        Rectangle wall = new Rectangle(10,10,10,10);
        wall.setStroke(Color.ORANGE);
        wall.setFill(Color.ORANGE);

        //alignments
        legendBar.setAlignment(Pos.TOP_LEFT);
        legendBar.setPadding(new Insets(25,0,50,25)); //TOP,RIGHT,BOTTOM,LEFT @param
        legendBar.setMargin(legendTitle, new Insets(100,0,10,0)); //TOP,RIGHT,BOTTOM,LEFT @param
        legendBar.setSpacing(5);
        gridPane.setHgap(5);
        gridPane.setVgap(3);

        GridPane.setRowIndex(c1BadPlant, 0);
        GridPane.setColumnIndex(c1BadPlant,0);
        GridPane.setRowIndex(badPlantText,0);
        GridPane.setColumnIndex(badPlantText, 1);

        GridPane.setRowIndex(r1BadBeast, 1);
        GridPane.setColumnIndex(r1BadBeast,0);
        GridPane.setRowIndex(badBeastText, 1);
        GridPane.setColumnIndex(badBeastText,1);

        GridPane.setRowIndex(c2GoodPlant, 2);
        GridPane.setColumnIndex(c2GoodPlant,0);
        GridPane.setRowIndex(goodPlantText, 2);
        GridPane.setColumnIndex(goodPlantText,1);

        GridPane.setRowIndex(r2GoodBeast, 3);
        GridPane.setColumnIndex(r2GoodBeast,0);
        GridPane.setRowIndex(goodBeastText, 3);
        GridPane.setColumnIndex(goodBeastText,1);

        GridPane.setRowIndex(masterSquirrel, 4);
        GridPane.setColumnIndex(masterSquirrel,0);
        GridPane.setRowIndex(masterSquirrelText, 4);
        GridPane.setColumnIndex(masterSquirrelText,1);

        GridPane.setRowIndex(miniSquirrel, 5);
        GridPane.setColumnIndex(miniSquirrel,0);
        GridPane.setRowIndex(miniSquirrelText,5);
        GridPane.setColumnIndex(miniSquirrelText, 1);

        GridPane.setRowIndex(wall, 6);
        GridPane.setColumnIndex(wall,0);
        GridPane.setRowIndex(wallText, 6);
        GridPane.setColumnIndex(wallText,1);

        GridPane.setRowIndex(handOperatedSquirrel, 7);
        GridPane.setColumnIndex(handOperatedSquirrel,0);
        GridPane.setRowIndex(handOperatedSquirrelText,7);
        GridPane.setColumnIndex(handOperatedSquirrelText, 1);



        gridPane.getChildren().addAll(c1BadPlant, badPlantText, r1BadBeast, badBeastText, c2GoodPlant, goodPlantText, r2GoodBeast, goodBeastText,
                masterSquirrel, masterSquirrelText, miniSquirrel, miniSquirrelText,wall, wallText, handOperatedSquirrel, handOperatedSquirrelText);

        legendBar.getChildren().addAll(controlTitle, pauseButton, resumeButton, legendTitle, gridPane);

        return legendBar;
    }

    public MenuBar createMenuBar() {
        MenuItem pauseMenu = new MenuItem("Pause");
        pauseMenu.setAccelerator(KeyCombination.keyCombination("Ctrl+P"));
            pauseMenu.setOnAction((ActionEvent event) ->{
                getController().setPause(true);

            });
        MenuItem quitMenu = new MenuItem("Quit");
        quitMenu.setAccelerator(KeyCombination.keyCombination("Alt+F4"));
            quitMenu.setOnAction((ActionEvent event) ->{
                System.exit(0);
            });
        MenuItem continueMenu = new MenuItem("Continue");
            continueMenu.setOnAction((ActionEvent event) ->{
                getController().setPause(false);
            });


        Menu fileMenu = new Menu("File");
        fileMenu.getItems().addAll(pauseMenu, continueMenu, new SeparatorMenuItem(), quitMenu);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(fileMenu);
        return menuBar;
    }

    public HBox createStatusBar() {
        HBox statusBar = new HBox();
        Label statusLabel = new Label("StatusBar funktioniert!");
        statusBar.getChildren().add(statusLabel);
        return statusBar;
    }

    public VBox createSquirrelInfoBar() {
        VBox squirrelInfoBar = new VBox();
        squirrelInfoBar.setPadding(new Insets(10));
        squirrelInfoBar.setSpacing(8);

        Text title = new Text("Squirrel Energie:");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        squirrelInfoBar.getChildren().add(title);

        return squirrelInfoBar;
    }

    public GameImpl getController() {
        return controller;
    }
}
