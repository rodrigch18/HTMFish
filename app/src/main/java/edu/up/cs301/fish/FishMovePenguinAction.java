package edu.up.cs301.fish;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Hey, That's My Fish MovePenguinAction
 *
 * This class implements the action of moving the
 * penguin across the hexagonal grid.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 4/20/16
 */
public class FishMovePenguinAction extends GameAction implements Serializable{

    private int x;
    private int y;
    private int pengIndex;
    private Penguin penguin;
    private int playerIndex;

    /**
     * Move Penguin Action constructor
     *
     * @param gamePlayer - player
     */
    public FishMovePenguinAction(GamePlayer gamePlayer, Penguin penguin, int newX, int newY, int pengIndex, int playerIndex){

        super(gamePlayer);
        this.pengIndex = pengIndex;
        this.x = newX;
        this.y = newY;
        this.penguin=penguin;
        this.playerIndex = playerIndex;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public int getPengIndex(){
        return this.pengIndex;
    }


    public void setX(int newX){
         x= newX;
    }

    public void setY(int newY){ y = newY; }

    public Penguin getPenguin(){
        return this.penguin;
    }

    public int getPlayerIndex() {
        return this.playerIndex;
    }

}
