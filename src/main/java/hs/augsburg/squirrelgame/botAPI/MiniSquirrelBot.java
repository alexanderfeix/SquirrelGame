package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.board.BoardConfig;
import hs.augsburg.squirrelgame.botAPI.exception.ImpactRadiusOutOfBoundsException;
import hs.augsburg.squirrelgame.botAPI.exception.OutOfViewException;
import hs.augsburg.squirrelgame.botAPI.exception.SpawnException;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

public class MiniSquirrelBot extends MiniSquirrel{


    private final Class<? extends BotControllerFactory> botControllerFactory;
    private final String name;

    public MiniSquirrelBot(XY position, Class<? extends BotControllerFactory> factoryImpl, String name, int energy) {
        super(position, energy);
        this.botControllerFactory = factoryImpl;
        this.name = name;
    }


    public void nextStep(EntityContext entityContext) {
        ControllerContext controllerContext = new MiniSquirrelBot.ControllerContextImpl(entityContext);
        try {
            Object object = botControllerFactory.getDeclaredConstructor().newInstance();
            Method method = botControllerFactory.getMethod("createMiniBotController");
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
        private final int impactRadius = 6;

        public ControllerContextImpl(EntityContext entityContext){
            this.entityContext = entityContext;
        }

        @Override
        public hs.augsburg.squirrelgame.util.XY getViewLowerLeft() {
            int y = getPosition().getY() + 10;
            int x = getPosition().getX() - 10;
            return new XY(x, y);
        }

        @Override
        public hs.augsburg.squirrelgame.util.XY getViewUpperRight() {
            int y = getPosition().getY() - 10;
            int x = getPosition().getX() + 10;
            return new XY(x, y);
        }

        @Override
        public EntityType getEntityAt(hs.augsburg.squirrelgame.util.XY xy) throws OutOfViewException{
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
            entityContext.move(MiniSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(hs.augsburg.squirrelgame.util.XY position, int energy) throws SpawnException {
            //TODO: NOTHING TO DO HERE!
        }

        @Override
        public hs.augsburg.squirrelgame.util.XY locate() {
            XY position = MiniSquirrelBot.this.getPosition();
            return new XY(position.getX(), position.getY());
        }

        @Override
        public void implode(int impactRadius){
            if(!(impactRadius >= 2 && impactRadius <= 10)){
                throw new ImpactRadiusOutOfBoundsException("[2; 10]");
            }
            for(int col = getPosition().getX() - impactRadius; col < getPosition().getX() + impactRadius; col++){
                for(int row = getPosition().getY() - impactRadius; row < getPosition().getY() + impactRadius; row++){
                    try {
                        XY position = new XY(col, row);
                        Entity entity = entityContext.getEntity(position);
                        switch (entity.getEntityType()) {
                            case MINI_SQUIRREL -> {
                                MiniSquirrel miniSquirrel = (MiniSquirrel) entity;
                                if (miniSquirrel.getMasterSquirrelId() != getMasterSquirrelId()) {
                                    implodeHandling(miniSquirrel);
                                }
                            }
                            case MASTER_SQUIRREL -> {
                                MasterSquirrel masterSquirrel = (MasterSquirrel) entity;
                                if (masterSquirrel.getId() != getMasterSquirrelId()) {
                                    implodeHandling(masterSquirrel);
                                }
                            }
                            case GOOD_PLANT, GOOD_BEAST -> implodeHandling(entity);
                            case BAD_BEAST, BAD_PLANT -> {
                                int energyLoss = MathUtils.getEnergyLoss(entity, MiniSquirrelBot.this, getImpactRadius());
                                if (entity.getEnergy() + energyLoss >= 0) {
                                    entity.setEnergy(0);
                                } else {
                                    entity.updateEnergy(energyLoss);
                                }
                            }
                        }
                    }catch (Exception ignored){}
                }
            }
            setEnergy(0);
        }

        @Override
        public Direction directionOfMaster() {
            MasterSquirrel masterSquirrel = getMasterSquirrel();
            XY masterSquirrelPosition = masterSquirrel.getPosition();
            if(masterSquirrelPosition.getX() > getPosition().getX() && masterSquirrelPosition.getY() == getPosition().getY()){
                return Direction.RIGHT;
            }else if(masterSquirrelPosition.getX() > getPosition().getX() && masterSquirrelPosition.getY() >= getPosition().getY()){
                return Direction.DOWN_RIGHT;
            }else if (masterSquirrelPosition.getX() > getPosition().getX() && masterSquirrelPosition.getY() <= getPosition().getY()){
                return Direction.UP_RIGHT;
            }else if(masterSquirrelPosition.getX() < getPosition().getX() && masterSquirrelPosition.getY() == getPosition().getY()){
                return Direction.LEFT;
            }else if(masterSquirrelPosition.getX() < getPosition().getX() && masterSquirrelPosition.getY() >= getPosition().getY()){
                return Direction.DOWN_LEFT;
            }else if (masterSquirrelPosition.getX() < getPosition().getX() && masterSquirrelPosition.getY() <= getPosition().getY()){
                return Direction.UP_LEFT;
            }else if(masterSquirrelPosition.getX() == getPosition().getX() && masterSquirrelPosition.getY() >= getPosition().getY()){
                return Direction.DOWN;
            }else if(masterSquirrelPosition.getX() == getPosition().getX() && masterSquirrelPosition.getY() <= getPosition().getY()) {
                return Direction.UP;
            }
            //Position of MasterSquirrel is equal to position of MiniSquirrel
            return null;
        }


        @Override
        public boolean isMine(hs.augsburg.squirrelgame.util.XY xy) throws OutOfViewException{
            hs.augsburg.squirrelgame.util.XY viewUpperRight = getViewUpperRight();
            hs.augsburg.squirrelgame.util.XY viewLowerLeft = getViewLowerLeft();
            if(xy.getX() > viewUpperRight.getX() || xy.getX() < viewLowerLeft.getX()){
                throw new OutOfViewException();
            }else if (xy.getY() > viewLowerLeft.getY() || xy.getY() < viewUpperRight.getY()){
                throw new OutOfViewException();
            }
            if(getEntityAt(xy) == EntityType.MINI_SQUIRREL){
                MiniSquirrel miniSquirrel = (MiniSquirrel) entityContext.getEntity(xy);
                return miniSquirrel.getMasterSquirrel() == getMasterSquirrel();
            }else if (getEntityAt(xy) == EntityType.MASTER_SQUIRREL){
                MasterSquirrel masterSquirrel = (MasterSquirrel) entityContext.getEntity(xy);
                return masterSquirrel.getId() == getMasterSquirrelId();
            }
            return false;
        }

        @Override
        public int getEnergy() {
            return MiniSquirrelBot.this.getEnergy();
        }

        @Override
        public long getRemainingSteps() {
            return BoardConfig.REMAINING_STEPS;
        }

        private void implodeHandling(Entity entity) {
                int energyLoss = MathUtils.getEnergyLoss(entity, MiniSquirrelBot.this, getImpactRadius());
                if(entity.getEnergy() < energyLoss){
                    entity.setEnergy(0);
                }else{
                    entity.updateEnergy(-energyLoss);
                }
                getMasterSquirrel().updateEnergy(energyLoss);
        }

        public int getImpactRadius() {
            return impactRadius;
        }
    }
}
