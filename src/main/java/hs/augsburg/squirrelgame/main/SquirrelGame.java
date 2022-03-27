package hs.augsburg.squirrelgame.main;

import hs.augsburg.squirrelgame.entity.EntitySet;

public class SquirrelGame {

    public static void main(String[] args) {
        EntitySet.initializeExamples();
        try{
            gameLoop();
        }catch (InterruptedException interruptedException){}
    }

    public static void gameLoop() throws InterruptedException {
        while (true){
            EntitySet.getEntityInformations();
            Thread.sleep(5000);
        }
    }

}
