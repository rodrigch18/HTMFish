package edu.up.cs301.fish;

import java.io.Serializable;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * Hey, That's My Fish SetPenguinAction
 *
 * This class implements the action of setting the
 * penguin on the hexagonal grid. It can only be set
 * on '1 fish' tiles.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 4/24/16
 */
public class FishSetPenguinAction extends GameAction implements Serializable {
    private int x;
    private int y;
    private Penguin penguin;
    private int aPlayersNum;
    private int aPengIndex;
    /**
     * Move Penguin Action constructor
     *
     * @param gamePlayer - player
     */
    public FishSetPenguinAction(GamePlayer gamePlayer, Penguin penguin,int newX, int newY, int aPlayersNum, int aPengIndex){

        super(gamePlayer);

        this.aPlayersNum=aPlayersNum;
        this.x = newX;
        this.y = newY;
        this.penguin=penguin;
        this.aPengIndex = aPengIndex;

    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public Penguin getPenguin(){
        return this.penguin;
    }

    public int getaPlayersNum(){
        return this.aPlayersNum;
    }

    public int getaPengIndex() {
        return this.aPengIndex;
    }
}
