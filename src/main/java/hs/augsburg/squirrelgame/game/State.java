package hs.augsburg.squirrelgame.game;

import hs.augsburg.squirrelgame.board.Board;

public class State {

    private int highScore = 0;
    private Board board;

    public int getHighScore() {
        return highScore;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }
}
