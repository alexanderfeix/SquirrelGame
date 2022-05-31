package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.board.BoardConfig;
import hs.augsburg.squirrelgame.botAPI.exception.OutOfViewException;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;
import hs.augsburg.squirrelgame.util.exception.NotEnoughEnergyException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MasterSquirrelBot extends MasterSquirrel{

    private final Class<? extends BotControllerFactory> botControllerFactory;
    private final String name;

    public MasterSquirrelBot(XY position, Class<? extends BotControllerFactory> factoryImpl, String name) {
        super(position);
        this.botControllerFactory = factoryImpl;
        this.name = name;
    }

    @Override
    public void nextStep(EntityContext entityContext) {
        ControllerContext controllerContext = new ControllerContextImpl(entityContext);
        try {
            Object object = botControllerFactory.getDeclaredConstructor().newInstance();
            Method method = botControllerFactory.getMethod("createMasterBotController");
            BotController botController = (BotController) method.invoke(object);
            botController.nextStep(controllerContext);
        } catch (NoSuchMethodException | InvocationTargetException | InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        if(controllerContext.getRemainingSteps() <= 0){
            ArrayList<Integer> highScores;
            if(BoardConfig.HIGHSCORES.get(name) == null){
                highScores = new ArrayList<>();
            }else{
                highScores = BoardConfig.HIGHSCORES.get(name);
            }
            highScores.add(controllerContext.getEnergy());
            BoardConfig.HIGHSCORES.put(name, highScores);
            System.out.println(highScores);
        }
    }

    private class ControllerContextImpl implements ControllerContext{

        private final EntityContext entityContext;

        public ControllerContextImpl(EntityContext entityContext){
            this.entityContext = entityContext;
        }

        @Override
        public hs.augsburg.squirrelgame.util.XY getViewLowerLeft() {
            int y = getPosition().getY() + 15;
            int x = getPosition().getX() - 15;
            return new XY(x, y);
        }

        @Override
        public hs.augsburg.squirrelgame.util.XY getViewUpperRight() {
            int y = getPosition().getY() - 15;
            int x = getPosition().getX() + 15;
            return new XY(x, y);
        }

        @Override
        public EntityType getEntityAt(hs.augsburg.squirrelgame.util.XY xy) {
            hs.augsburg.squirrelgame.util.XY viewUpperRight = getViewUpperRight();
            hs.augsburg.squirrelgame.util.XY viewLowerLeft = getViewLowerLeft();
            if(xy.getX() > viewUpperRight.getX() || xy.getX() < viewLowerLeft.getX()){
                throw new OutOfViewException();
            }else if (xy.getY() > viewLowerLeft.getY() || xy.getY() < viewUpperRight.getY()){
                throw new OutOfViewException();
            }
            return entityContext.getEntity(xy).getEntityType();
        }

        @Override
        public void move(hs.augsburg.squirrelgame.util.XY direction) {
            entityContext.move(MasterSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(hs.augsburg.squirrelgame.util.XY position, int energy) {
            if(energy < 100){
                throw new NotEnoughEnergyException();
            }
            MiniSquirrelBot miniSquirrelBot = (MiniSquirrelBot) createMiniSquirrel(position.getUtils().getRandomNearbyPosition(), energy);
            if(miniSquirrelBot != null){
                miniSquirrelBot.setMasterSquirrelId(getId());
                miniSquirrelBot.setMasterSquirrel(MasterSquirrelBot.this);
            }
        }

        @Override
        public hs.augsburg.squirrelgame.util.XY locate() {
            XY position =  MasterSquirrelBot.this.getPosition();
            return new XY(position.getX(), position.getY());
        }

        @Override
        public void implode(int implodeRadius) {
            //MasterSquirrel can not implode.
        }

        @Override
        public Direction directionOfMaster() {
            return null;
        }

        @Override
        public boolean isMine(hs.augsburg.squirrelgame.util.XY xy) {
            return false;
        }

        @Override
        public int getEnergy() {
            return MasterSquirrelBot.this.getEnergy();
        }

        @Override
        public long getRemainingSteps() {
            return BoardConfig.REMAINING_STEPS;
        }
    }
}
