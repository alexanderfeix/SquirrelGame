package hs.augsburg.squirrelgame.ui;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.util.Direction;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class FxUI implements UI{



    @Override
    public void render(BoardView view) {
        //Print pane in the center of the view here

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

    private GridPane getGridPane(BoardView view){
        GridPane gridPane = new GridPane();
        for(int col = 0; col < view.getGameBoard().length; col++){
            for(int row = 0; row < view.getGameBoard()[col].length; row++){
                Entity entity = view.getEntity(col, row);
                Shape shape = getRenderedEntityItem(entity.getEntityType());
                gridPane.add(shape, col, row);
            }
        }
        return gridPane;
    }

    private Shape getRenderedEntityItem(EntityType entityType){
        switch (entityType){
            case MASTER_SQUIRREL:
                Rectangle masterSquirrel = new Rectangle(10, 10, 10, 10);
                masterSquirrel.setStroke(Color.BLACK);
                masterSquirrel.setFill(Color.BLACK);
                return masterSquirrel;
            case MINI_SQUIRREL:
                Rectangle miniSquirrel = new Rectangle(10, 10, 10, 10);
                miniSquirrel.setStroke(Color.AQUA);
                miniSquirrel.setFill(Color.AQUA);
                return miniSquirrel;
            case BAD_BEAST:
                Rectangle badBeast = new Rectangle(10, 10, 10, 10);
                badBeast.setStroke(Color.RED);
                badBeast.setFill(Color.RED);
                return badBeast;
            case GOOD_BEAST:
                Rectangle goodBeast = new Rectangle(10, 10, 10, 10);
                goodBeast.setStroke(Color.GREEN);
                goodBeast.setFill(Color.GREEN);
                return goodBeast;
            case GOOD_PLANT:
                Circle goodPlant = new Circle(10, 10, 5);
                goodPlant.setStroke(Color.GREEN);
                goodPlant.setFill(Color.GREEN);
                return goodPlant;
            case BAD_PLANT:
                Circle badPlant = new Circle(10, 10, 5);
                badPlant.setStroke(Color.RED);
                badPlant.setFill(Color.RED);
                return badPlant;
            case WALL:
                Rectangle wall = new Rectangle(10, 10, 10, 10);
                wall.setStroke(Color.ORANGE);
                wall.setFill(Color.ORANGE);
                return wall;
            default:
                break;
        }
        Rectangle empty = new Rectangle(10, 10, 10, 10);
        empty.setStroke(Color.WHITE);
        empty.setFill(Color.WHITE);
        return empty;
    }
}
