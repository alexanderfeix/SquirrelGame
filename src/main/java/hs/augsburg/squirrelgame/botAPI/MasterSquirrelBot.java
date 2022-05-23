package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.entity.Entity;
import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.util.XY;

public class MasterSquirrelBot extends MasterSquirrel implements BotController {

    public MasterSquirrelBot(hs.augsburg.squirrelgame.util.XY position) {
        super(position);
    }

    private final ControllerContext controllerContext = new ControllerContextImpl();

    @Override
    public void nextStep(EntityContext entityContext) {
        nextStep(getControllerContext());
    }

    @Override
    public void nextStep(ControllerContext controllerContext) {
        //TODO: Actual nextStep method
    }

    public ControllerContext getControllerContext() {
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
