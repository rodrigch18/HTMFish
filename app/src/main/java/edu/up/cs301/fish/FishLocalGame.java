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
    int numPlayers;
    String[] playersNames;

    /**
     * Fish Local Game constructor that takes no parameters
     */
    public FishLocalGame(){
        //this.numPlayers = Integer.valueOf(this.players.length);
        //playersNames= new String[this.numPlayers];
        //fishGameState = new FishGameState(this.numPlayers, playersNames);


    }

    /**
     * Sends the updated game state to a given player
     *
     * @param p - a player
     */
    protected void sendUpdatedStateTo(GamePlayer p){
        if(fishGameState == null){
            this.numPlayers = Integer.valueOf(this.players.length);
            playersNames= new String[this.numPlayers];
            for(int i=0; i<this.numPlayers; i++){
                this.playersNames[i] = new String(this.playerNames[i]);
            }
            fishGameState = new FishGameState(numPlayers, playersNames);
        }
        FishGameState temp = new FishGameState(fishGameState, numPlayers, playersNames);
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
    protected boolean makeMove(GameAction action) {

        if (action instanceof FishMovePenguinAction){
            //if (fishGameState.getId() == 0) {

                //fishGameState.setPeng();
                //return true;
           // }
        }
        else if (action instanceof FishSetPenguinAction){

            fishGameState.setPeng(((FishSetPenguinAction) action).getPenguin(),
                    ((FishSetPenguinAction) action).getX(),((FishSetPenguinAction) action).getY(),
                    ((FishSetPenguinAction) action).getPenguin().player);
            int i = fishGameState.getId()+1;
            if(i==numPlayers) {
                fishGameState.setId(0);
            }
            else {
                fishGameState.setId(i);
            }
            return true;

        }
        else if( action instanceof FishSelectPenguinAction){



        }
        return false;

    }

}
