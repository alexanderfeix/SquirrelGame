package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.board.FlattenedBoard;

public class State {

    private int highScore = 0;
    private static Board board;
    private static FlattenedBoard flattenedBoard;

    public State(Board board){
        setBoard(board);
        setFlattenedBoard(getBoard().getFlattenedBoard());
    }

    public static FlattenedBoard getFlattenedBoard() {
        return flattenedBoard;
    }

    public static void setFlattenedBoard(FlattenedBoard flattenedBoard) {
        State.flattenedBoard = flattenedBoard;
    }

    public int getHighScore() {
        return highScore;
    }

    public static Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        State.board = board;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
