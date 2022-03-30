package hs.augsburg.squirrelgame.main;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.squirrel.HandOperatedMasterSquirrel;

import javax.swing.*;

public class SquirrelGame {

    public static void main(String[] args) {
        initializeJFrame();
        EntitySet.initializeExamples();
        try {
            gameLoop();
        } catch (InterruptedException e) {}
    }

   public static void gameLoop() throws InterruptedException {
        while(true){
            //Loop will run until the JFrame window gets closed
            EntitySet.getEntityInformations();
            System.out.println("-----");
            Thread.sleep(5000);
        }
    }

    private static void initializeJFrame(){
        HandOperatedMasterSquirrel ho = new HandOperatedMasterSquirrel();
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
        frame.addKeyListener(ho);
    }
}
