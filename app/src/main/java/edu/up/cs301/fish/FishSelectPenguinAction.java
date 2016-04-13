package edu.up.cs301.fish;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by christian on 4/12/16.
 */
public class FishSelectPenguinAction extends GameAction{

    private int x;
    private int y;
    private Penguin penguin;

    public FishSelectPenguinAction(GamePlayer gamePlayer, Penguin penguin){

        super(gamePlayer);
        x = penguin.getCurrPosX();
        y = penguin.getCurrPosY();
        this.penguin=penguin;

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Penguin getPenguin(){
        return penguin;
    }
}
