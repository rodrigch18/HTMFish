package edu.up.cs301.fish;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by gisellemarston on 3/28/16.
 */
public class FishLocalGame extends LocalGame{

    FishGameState fishGameState;

    public FishLocalGame(){

    }
    protected void sendUpdatedStateTo(GamePlayer p){

    }
    protected boolean canMove(int playerIdx){
        return true;
    }
    protected String checkIfGameOver(){
        return "";
    }
    protected boolean makeMove(GameAction action){
        return true;
    }

}
