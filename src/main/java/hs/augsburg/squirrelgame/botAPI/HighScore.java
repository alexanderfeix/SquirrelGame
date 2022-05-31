package hs.augsburg.squirrelgame.botAPI;

public class HighScore {

    private final String name;
    private final int round;
    private final int highScore;

    public HighScore(String name, int round, int highScore){
        this.name = name;
        this.round = round;
        this.highScore = highScore;
    }

    public String getName() {
        return name;
    }

    public int getRound() {
        return round;
    }

    public int getHighScore() {
        return highScore;
    }
}
