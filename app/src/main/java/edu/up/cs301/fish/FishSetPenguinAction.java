package edu.up.cs301.fish;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Created by gisellemarston on 4/11/16.
 */
public class FishSetPenguinAction extends GameAction {
    private int x;
    private int y;
    private Penguin penguin;
    /**
     * Move Penguin Action constructor
     *
     * @param gamePlayer - player
     */
    public FishSetPenguinAction(GamePlayer gamePlayer, Penguin penguin){

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
