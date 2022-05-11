package hs.augsburg.squirrelgame.ui;

import hs.augsburg.squirrelgame.util.Direction;
import javafx.event.EventHandler;
import javafx.scene.input.KeyEvent;

public class GUIKeyListener extends FxUI implements EventHandler<KeyEvent> {
    @Override
    public void handle(KeyEvent keyEvent) {
        if(keyEvent.getEventType() == KeyEvent.KEY_TYPED){
            String keyTyped = keyEvent.getCharacter().toUpperCase();
            System.out.println(keyTyped);
            switch (keyTyped) {
                case "W", "UP" -> setNextDirection(Direction.UP);
                case "A", "LEFT" -> setNextDirection(Direction.LEFT);
                case "S", "DOWN" -> setNextDirection(Direction.DOWN);
                case "D", "RIGHT" -> setNextDirection(Direction.RIGHT);
            }
        }
    }
}
