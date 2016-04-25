package edu.up.cs301.fish;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Hey, That's My Fish MovePenguinAction
 *
 * This class implements the action of passing the
 * current player's turn.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 4/24/16
 */
public class FishPassAction extends GameAction implements Serializable {
    public FishPassAction(GamePlayer gamePlayer){
        super(gamePlayer);
    }
}
