package edu.up.cs301.fish;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Hey, That's My Fish DumbComputerPlayer
 *
 * This class implements our 'not so smart' AI.
 * It is the cpu player that picks random spots for a random
 * penguin to go to. It has neither the knowledge of the most
 * advantageous move to win nor does it really care. The opposite
 * of the SmartComputerPlayer class that implements our
 * 'smart' AI.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/31/16
 */

public class FishDumbComputerPlayer extends GameComputerPlayer {

    /**
     * Dumb Computer Player constructor
     *
     * @param name - player name
     */
    public FishDumbComputerPlayer(String name){
        super(name);
    }

    /**
     * callback method -- game's state has changed
     *
     * @param info - info from the game
     */
    protected void receiveInfo(GameInfo info){
        if(info instanceof FishGameState) {
            FishGameState newState = (FishGameState) info;
            sleep(1000);
            if (newState.getId() != this.playerNum)
                return;
            else {
                FishMovePenguinAction move = new FishMovePenguinAction(this);
                game.sendAction(move);
            }
        }
    }
}
