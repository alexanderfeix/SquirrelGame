package hs.augsburg.squirrelgame.test;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.botAPI.MiniSquirrelBot;
import hs.augsburg.squirrelgame.entity.beast.BadBeast;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.game.Game;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;
import hs.augsburg.squirrelgame.ui.ConsoleUI;
import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MiniSquirrelBotTest {

    @Test
    void checkImplodeMethod(){
        State state = new State(new Board());
        Game game = new GameImpl(state, new ConsoleUI());
        MasterSquirrel masterSquirrel = new MasterSquirrel(new XY(5,5));
        MiniSquirrelBot miniSquirrel = (MiniSquirrelBot) masterSquirrel.createMiniSquirrel(new XY(20,20), 200);

        GoodBeast goodBeast = new GoodBeast(new XY(18,17));
        GoodBeast goodBeast2 = new GoodBeast(new XY(18,15));
        GoodPlant goodPlant = new GoodPlant(new XY(20, 17));
        GoodPlant goodPlant2 = new GoodPlant(new XY(20, 15));
        BadBeast badBeast = new BadBeast(new XY(22,17));
        BadBeast badBeast2 = new BadBeast(new XY(22,15));
        BadPlant badPlant = new BadPlant(new XY(24, 17));
        BadPlant badPlant2 = new BadPlant(new XY(24, 15));
        game.getState().getBoard().getEntitySet().addEntity(miniSquirrel);
        game.getState().getBoard().getEntitySet().addEntity(masterSquirrel);
        game.getState().getBoard().getEntitySet().addEntity(goodBeast);
        game.getState().getBoard().getEntitySet().addEntity(goodBeast2);
        game.getState().getBoard().getEntitySet().addEntity(goodPlant);
        game.getState().getBoard().getEntitySet().addEntity(goodPlant2);
        game.getState().getBoard().getEntitySet().addEntity(badBeast);
        game.getState().getBoard().getEntitySet().addEntity(badBeast2);
        game.getState().getBoard().getEntitySet().addEntity(badPlant);
        game.getState().getBoard().getEntitySet().addEntity(badPlant2);

        int latestEnergie_MasterSquirrel = masterSquirrel.getEnergy();

        int latestEnergie_GoodBeast = goodBeast.getEnergy();
        int latestEnergie_GoodBeast2 = goodBeast2.getEnergy();
        int latestEnergie_GoodPlant = goodPlant.getEnergy();
        int latestEnergie_GoodPlant2 = goodPlant2.getEnergy();
        int latestEnergie_BadBeast = badBeast.getEnergy();
        int latestEnergie_BadBeast2 = badBeast2.getEnergy();
        int latestEnergie_BadPlant = badPlant.getEnergy();
        int latestEnergie_BadPlant2 = badPlant2.getEnergy();


        miniSquirrel.getControllerContext().implode(6);

        //Energie-Abzug erfolgreich durgeführt?
        assertEquals(latestEnergie_GoodBeast - MathUtils.getEnergyLoss(goodBeast, miniSquirrel, 6), goodBeast.getEnergy());
        assertEquals(latestEnergie_GoodBeast2 - MathUtils.getEnergyLoss(goodBeast2, miniSquirrel, 6), goodBeast2.getEnergy());
        assertEquals(latestEnergie_GoodPlant - MathUtils.getEnergyLoss(goodPlant, miniSquirrel, 6), goodPlant.getEnergy());
        assertEquals(latestEnergie_GoodPlant2 - MathUtils.getEnergyLoss(goodPlant2, miniSquirrel, 6), goodPlant2.getEnergy());
        assertEquals(latestEnergie_BadBeast - MathUtils.getEnergyLoss(badBeast, miniSquirrel, 6), badBeast.getEnergy());
        assertEquals(latestEnergie_BadBeast2 - MathUtils.getEnergyLoss(badBeast2, miniSquirrel, 6), badBeast2.getEnergy());
        assertEquals(latestEnergie_BadPlant - MathUtils.getEnergyLoss(badPlant, miniSquirrel, 6), badPlant.getEnergy());
        assertEquals(latestEnergie_BadPlant2 - MathUtils.getEnergyLoss(badPlant2, miniSquirrel, 6), badPlant2.getEnergy());

        //Energie-Gutschrift auf MasterSquirrel erfolgreich durchgeführt?
    }

}