package hs.augsburg.squirrelgame.entity.beast;

import hs.augsburg.squirrelgame.board.Board;
import hs.augsburg.squirrelgame.entity.*;

public class BadBeast extends MovableEntity {

    private static final int startEnergy = -150;

    public BadBeast(hs.augsburg.squirrelgame.util.XY position) {
        super(EntityType.BAD_BEAST, position, startEnergy);
        setEntity(this);
    }

    public void nextStep(EntityContext entityContext){
        if(getMoveCounter() != 0){
            setMoveCounter(getMoveCounter()-1);
            System.out.println("Current MoveCounter Bad Beast: " + getMoveCounter());
            return;
        }
        entityContext.move(getEntity(), getPosition().getRandomPosition());
        setMoveCounter(4);
    }

    public void onCollision(Entity enemy, Board board){

    }

    @Override
    public void move(Entity entity, hs.augsburg.squirrelgame.util.XY randomPosition) {

    }
}
