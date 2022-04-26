package hs.augsburg.squirrelgame.command.command;

import hs.augsburg.squirrelgame.command.Command;
import hs.augsburg.squirrelgame.command.CommandTypeInfo;
import hs.augsburg.squirrelgame.entity.squirrel.HandOperatedMasterSquirrel;
import hs.augsburg.squirrelgame.game.GameImpl;
import hs.augsburg.squirrelgame.game.State;

public class SpawnMiniCommand extends Command {
    public SpawnMiniCommand(CommandTypeInfo commandTypeInfo, Object[] params) {
        super(commandTypeInfo, params);
    }

    public void handle(GameImpl game) {
        game.getState().getFlattenedBoard().createStandardMiniSquirrel(game.getHandOperatedMasterSquirrel(), this);
    }
}
