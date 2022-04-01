package hs.augsburg.squirrelgame.main;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntitySet;
import hs.augsburg.squirrelgame.entity.MovableEntity;
import hs.augsburg.squirrelgame.entity.squirrel.HandOperatedMasterSquirrel;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.util.XY;

import javax.swing.*;

public class SquirrelGame {

    public static void main(String[] args) {
        //initializeJFrame();
        EntitySet.initializeExamples();
        try {
            gameLoop();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

   public static void gameLoop() throws InterruptedException {
        while(true){
            //Loop will run until the JFrame window gets closed

            //nextStep() calls all entities in the list to update (e.g. their positions)
            EntitySet.nextStep();
            EntitySet.getEntityInformations();
            System.out.println("-----");
            Thread.sleep(5000);
        }
    }

    private static void initializeJFrame(){
        HandOperatedMasterSquirrel ho = new HandOperatedMasterSquirrel(new XY(9, 9));
        JFrame frame = new JFrame("My First GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,300);
        frame.setVisible(true);
        frame.addKeyListener(ho);
    }
}
