package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.botAPI.exception.OutOfViewException;
import hs.augsburg.squirrelgame.botAPI.exception.SpawnException;
import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.util.Direction;
import hs.augsburg.squirrelgame.util.XY;
import hs.augsburg.squirrelgame.util.XYBot;
import hs.augsburg.squirrelgame.util.exception.NotEnoughEnergyException;

public class MasterSquirrelBot extends MasterSquirrel{

    private final BotControllerFactory botControllerFactory;

    public MasterSquirrelBot(hs.augsburg.squirrelgame.util.XY position) {
        super(position);
        this.botControllerFactory = new BotControllerFactoryImpl();

    }

    @Override
    public void nextStep(EntityContext entityContext) {
        ControllerContext controllerContext = new ControllerContextImpl(entityContext);
        BotController botController = botControllerFactory.createMasterBotController();
        botController.nextStep(controllerContext);
    }

    private class ControllerContextImpl implements ControllerContext{

        private final EntityContext entityContext;

        public ControllerContextImpl(EntityContext entityContext){
            this.entityContext = entityContext;
        }

        @Override
        public hs.augsburg.squirrelgame.util.XYBot getViewLowerLeft() {
            int y = getPosition().getY() + 15;
            int x = getPosition().getX() - 15;
            return new XYBot(x, y);
        }

        @Override
        public hs.augsburg.squirrelgame.util.XYBot getViewUpperRight() {
            int y = getPosition().getY() - 15;
            int x = getPosition().getX() + 15;
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
            entityContext.move(MasterSquirrelBot.this, direction);
        }

        @Override

        public void spawnMiniBot(hs.augsburg.squirrelgame.util.XYBot position, int energy) {
            MiniSquirrelBot miniSquirrelBot = (MiniSquirrelBot) createMiniSquirrel(position.getRandomNearbyPosition(), energy);
            if(miniSquirrelBot != null){
                if(energy < 100 || position == null || MasterSquirrelBot.this.getPosition() == miniSquirrelBot.getPosition()){
                    throw new SpawnException();
                }
                miniSquirrelBot.setMasterSquirrelId(getId());
                miniSquirrelBot.setMasterSquirrel(MasterSquirrelBot.this);
            }
        }

        @Override
        public hs.augsburg.squirrelgame.util.XYBot locate() {
            XY position =  MasterSquirrelBot.this.getPosition();
            return new XYBot(position.getX(), position.getY());
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
        public Entity getEntity() {
            return MasterSquirrelBot.this;
        }

        @Override
        public boolean isMine(hs.augsburg.squirrelgame.util.XYBot xy) {
            return false;
        }

        @Override
        public int getEnergy() {
            return MasterSquirrelBot.this.getEnergy();
        }

        @Override
        public long getRemainingSteps() {
            return 0;
        }
    }
}
