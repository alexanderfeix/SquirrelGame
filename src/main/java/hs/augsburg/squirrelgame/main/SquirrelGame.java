package hs.augsburg.squirrelgame.main;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.squirrel.HandOperatedMasterSquirrel;

import javax.swing.*;

public class SquirrelGame {

    public static void main(String[] args) {
        //Ab hier GUI Implementierung
        HandOperatedMasterSquirrel ho = new HandOperatedMasterSquirrel();
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
        frame.addKeyListener(ho);
        //Ab hier GUI Implementierung vorbei
        EntitySet.initializeExamples();
        try{
            gameLoop();
        }catch (InterruptedException interruptedException){}


    }

   public static void gameLoop() throws InterruptedException {

            EntitySet.getEntityInformations();
            Thread.sleep(5000);

    }


}
