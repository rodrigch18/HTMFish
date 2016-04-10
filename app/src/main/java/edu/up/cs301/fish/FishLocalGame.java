package edu.up.cs301.fish;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Hey, That's My Fish LocalGame
 *
 * This class controls the play of the game
 * amongst players who are all on the same device.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/28/16
 */
public class FishLocalGame extends LocalGame{

    FishGameState fishGameState;

    /**
     * Fish Local Game constructor that takes no parameters
     */
    public FishLocalGame(){
        fishGameState = new FishGameState();
    }

    /**
     * Sends the updated game state to a given player
     *
     * @param p - a player
     */
    protected void sendUpdatedStateTo(GamePlayer p){
        FishGameState temp = new FishGameState(fishGameState);
        p.sendInfo(temp);
    }

    /**
     * check if player, with given id, can take action
     *
     * @param playerIdx - specific player
     */
    protected boolean canMove(int playerIdx){

        if(playerIdx == fishGameState.getId()) {
            return true;
        }

        return false;
    }

    /**
     * check if the game is over
     * @return message to inform the users who has won or null
     *          if game is not over
     */
    protected String checkIfGameOver(){

        if (fishGameState.board == null) {

            int maxScore = fishGameState.player[0];
            int winner =0;
            for (int i = 1; i < fishGameState.player.length; i++) {
                if (fishGameState.getPlayerScore(i) > maxScore) {
                    maxScore = fishGameState.getPlayerScore(i);
                    winner = i;
                }
            }
            return playerNames[winner] + " wins with a score of " + maxScore;
        }
        return null;

    }

    /**
     * check if the action is taken or is invalid
     *
     * @param action - the action a player takes
     */
    protected boolean makeMove(GameAction action){
        if (fishGameState.getId() == 0) {
            return true;
        }
        return false;
    }

}
