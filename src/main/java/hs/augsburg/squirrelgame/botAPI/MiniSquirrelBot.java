package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MiniSquirrel;
import hs.augsburg.squirrelgame.util.XY;

public class MiniSquirrelBot extends MiniSquirrel implements BotController{

    public MiniSquirrelBot(hs.augsburg.squirrelgame.util.XY position, int energy) {
        super(position, energy);
    }

    private ControllerContextImpl controllerContext = new ControllerContextImpl();

    @Override
    public void nextStep(EntityContext entityContext) {
        nextStep(getControllerContext());
    }

    @Override
    public void nextStep(ControllerContext controllerContext) {
        //TODO: actual nextStep() method
    }

    public ControllerContextImpl getControllerContext() {
        return controllerContext;
    }

    private class ControllerContextImpl implements ControllerContext{

        @Override
        public hs.augsburg.squirrelgame.util.XY getViewLowerLeft() {
            return null;
        }

        @Override
        public hs.augsburg.squirrelgame.util.XY getViewUpperRight() {
            return null;
        }

        @Override
        public EntityType getEntity(hs.augsburg.squirrelgame.util.XY xy) {
            return null;
        }

        @Override
        public void move(Entity entity, hs.augsburg.squirrelgame.util.XY direction) {

        }

        @Override
        public void spawnMiniBot(hs.augsburg.squirrelgame.util.XY direction, int energy) {

        }

        @Override
        public int getEnergy() {
            return 0;
        }
    }
}
