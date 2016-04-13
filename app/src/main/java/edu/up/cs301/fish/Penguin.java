package edu.up.cs301.fish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import edu.up.cs301.game.R;

/**
 * Hey, That's My Fish Penguin
 *
 * This class keeps track of which penguins belong to
 * certain players as well as their positions on the
 * board.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/28/16
 */

public class Penguin {
    // player that owns given penguin
    protected int player;
    // x,y coordinates of given penguin
    private int currPosX;
    private int currPosY;
    private Penguin aPeng;

    public Penguin(){

    }

    public Penguin(int x, int y){
        this.currPosX = x;
        this.currPosY = y;
    }

    /**
     * Penguin Constructor that takes no parameters
     */
    public Penguin(int x, int y, Penguin peng){
        this.aPeng = peng;
        this.currPosX = x;
        this.currPosY = y;
    }

    /**
     * Deep Copy Penguin Constructor
     *
     * @param p - a given penguin on the board
     */
    public Penguin(Penguin p){
        this.currPosX = p.getCurrPosX();
        this.currPosY = p.getCurrPosY();
    }

    //position of a player's penguin
    public int getCurrPosX() {
        return currPosX;
    }

    public void setCurrPosX(int newCurrPosX) {
        this.currPosX = newCurrPosX;
    }

    public int getCurrPosY() {
        return currPosY;
    }

    public void setCurrPosY(int newCurrPosY) {
        this.currPosY = newCurrPosY;
    }

}
