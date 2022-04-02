package hs.augsburg.squirrelgame.ui;

import hs.augsburg.squirrelgame.util.Direction;

public interface UI {

    void render(BoardView view);
    Direction getNextDirection();

}
