package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.botAPI.exception.ImpactRadiusOutOfBoundsException;
import hs.augsburg.squirrelgame.botAPI.exception.OutOfViewException;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;
import hs.augsburg.squirrelgame.util.XYBot;

public class MiniSquirrelBot extends MiniSquirrel{

    public MiniSquirrelBot(hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(position, energy);
    }
    private final BotControllerFactory botControllerFactory = new BotControllerFactoryImpl();
    private ControllerContext controllerContext;


    public void nextStep(EntityContext entityContext) {
        controllerContext = new ControllerContextImpl(entityContext);
        BotController botController = botControllerFactory.createMiniBotController();
        botController.nextStep(controllerContext);
    }

    public ControllerContext getControllerContext() {
        return controllerContext;
    }

    private class ControllerContextImpl implements ControllerContext{

        private final EntityContext entityContext;
        private final int impactRadius = 6;

        public ControllerContextImpl(EntityContext entityContext){
            this.entityContext = entityContext;
        }

        @Override
        public hs.augsburg.squirrelgame.util.XYBot getViewLowerLeft() {
            int y = getPosition().getY() + 10;
            int x = getPosition().getX() - 10;
            return new XYBot(x, y);
        }

        @Override
        public hs.augsburg.squirrelgame.util.XYBot getViewUpperRight() {
            int y = getPosition().getY() - 10;
            int x = getPosition().getX() + 10;
            return new XYBot(x, y);
        }

        @Override
        public EntityType getEntityAt(hs.augsburg.squirrelgame.util.XYBot xy) {
            XYBot viewUpperRight = getViewUpperRight();
            XYBot viewLowerLeft = getViewLowerLeft();
            if(xy.getX() > viewUpperRight.getX() || xy.getX() < viewLowerLeft.getX()){
                throw new OutOfViewException();
            }else if (xy.getY() > viewLowerLeft.getY() || xy.getY() < viewUpperRight.getY()){
                throw new OutOfViewException();
            }
            return entityContext.getEntity(xy).getEntityType();
        }

        @Override
        public void move(hs.augsburg.squirrelgame.util.XYBot direction) {
            entityContext.move(MiniSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(hs.augsburg.squirrelgame.util.XYBot position, int energy) {
            //TODO: NOTHING TO DO HERE!
        }

        @Override
        public hs.augsburg.squirrelgame.util.XYBot locate() {
            XY position = MiniSquirrelBot.this.getPosition();
            return new XYBot(position.getX(), position.getY());
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
        public Entity getEntity() {
            return MiniSquirrelBot.this;
        }

        @Override
        public boolean isMine(hs.augsburg.squirrelgame.util.XYBot xy) {
            XYBot viewUpperRight = getViewUpperRight();
            XYBot viewLowerLeft = getViewLowerLeft();
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
            return 0;
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
