package hs.augsburg.squirrelgame.botAPI;

import hs.augsburg.squirrelgame.entity.EntityContext;
import hs.augsburg.squirrelgame.entity.EntityType;
import hs.augsburg.squirrelgame.entity.squirrel.MasterSquirrel;
import hs.augsburg.squirrelgame.util.XY;

public class MasterSquirrelBot extends MasterSquirrel {

    public MasterSquirrelBot(hs.augsburg.squirrelgame.util.XY position) {
        super(position);
    }

    @Override
    public void nextStep(EntityContext entityContext) {
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
        public void move(hs.augsburg.squirrelgame.util.XY direction) {

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
