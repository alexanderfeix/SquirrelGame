package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.botAPI.exception.ImpactRadiusOutOfBoundsException;
import hs.augsburg.squirrelgame.botAPI.exception.OutOfViewException;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.beast.BadBeast;
import hs.augsburg.squirrelgame.entity.beast.GoodBeast;
import hs.augsburg.squirrelgame.entity.plant.BadPlant;
import hs.augsburg.squirrelgame.entity.plant.GoodPlant;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.MathUtils;
import hs.augsburg.squirrelgame.util.XY;

public class MiniSquirrelBot extends MiniSquirrel{

    public MiniSquirrelBot(hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(position, energy);
    }
    private final BotControllerFactory botControllerFactory = new BotControllerFactoryImpl();

    @Override
    public void nextStep(EntityContext entityContext) {
        ControllerContext controllerContext = new ControllerContextImpl(entityContext);
        BotController botController = botControllerFactory.createMasterBotController();
        botController.nextStep(controllerContext);
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
        public Entity getEntity(hs.augsburg.squirrelgame.util.XY xy) {
            XY viewUpperRight = getViewUpperRight();
            XY viewLowerLeft = getViewLowerLeft();
            if(xy.getX() > viewUpperRight.getX() || xy.getX() < viewLowerLeft.getX()){
                throw new OutOfViewException();
            }else if (xy.getY() > viewLowerLeft.getY() || xy.getY() < viewUpperRight.getY()){
                throw new OutOfViewException();
            }
            //TODO: check context.getEntityType()
            return entityContext.getEntity(xy);
        }

        @Override
        public void move(hs.augsburg.squirrelgame.util.XY direction) {
            entityContext.move(MiniSquirrelBot.this, direction);
        }

        @Override
        public void spawnMiniBot(hs.augsburg.squirrelgame.util.XY position, int energy) {
            //TODO: NOTHING TO DO HERE!
        }

        @Override
        public hs.augsburg.squirrelgame.util.XY locate() {
            return MiniSquirrelBot.this.getPosition();
        }

        @Override
        public void implode(int impactRadius){
            if(!(impactRadius >= 2 && impactRadius <= 10)){
                throw new ImpactRadiusOutOfBoundsException("Impact radius is out of bounds! Valid: [2; 10]");
            }
            for(int col = getPosition().getX() - impactRadius; col < getPosition().getX() + impactRadius; col++){
                for(int row = getPosition().getY() - impactRadius; row < getPosition().getY() + impactRadius; row++){
                    try {
                        XY position = new XY(col, row);
                        Entity entity = getEntity(position);
                        switch(entity.getEntityType()){
                            case MINI_SQUIRREL:
                                MiniSquirrel miniSquirrel = (MiniSquirrel) entity;
                                implodeHandling(miniSquirrel);
                                break;
                            case MASTER_SQUIRREL:
                                MasterSquirrel masterSquirrel = (MasterSquirrel) entity;
                                implodeHandling(masterSquirrel);
                                break;
                            case GOOD_PLANT, GOOD_BEAST:
                                Entity goodEntity = entity;
                                implodeHandling(goodEntity);
                                break;
                            case BAD_BEAST, BAD_PLANT:
                                Entity badEntity = entity;
                                int energyLoss = MathUtils.getEnergyLoss(badEntity, MiniSquirrelBot.this, getImpactRadius());
                                if(badEntity.getEnergy() + energyLoss >= 0){
                                    badEntity.setEnergy(0);
                                }else{
                                    badEntity.updateEnergy(energyLoss);
                                }
                                break;
                        }
                    }catch (Exception ignored){}
                }
            }
            setEnergy(0);
        }

        private void implodeHandling(Entity entity) {
            if(entity.getId() != getMasterSquirrelId()){
                int energyLoss = MathUtils.getEnergyLoss(entity, MiniSquirrelBot.this, getImpactRadius());
                if(entity.getEnergy() < energyLoss){
                    entity.setEnergy(0);
                }else{
                    entity.updateEnergy(-energyLoss);
                }
                getMasterSquirrel().updateEnergy(energyLoss);
            }
        }

        @Override
        public Direction getMasterSquirrelDirection(){
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

        public int getImpactRadius() {
            return impactRadius;
        }
    }
}
