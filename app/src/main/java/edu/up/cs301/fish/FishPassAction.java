package edu.up.cs301.fish;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * @version 4/20/16
 */
public class FishPassAction extends GameAction implements Serializable {
    public FishPassAction(GamePlayer gamePlayer){
        super(gamePlayer);
    }
}
