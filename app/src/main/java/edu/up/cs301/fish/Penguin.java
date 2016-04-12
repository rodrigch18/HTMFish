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
    protected int currPosX;
    protected int currPosY;
    //bitmap for penguins
    protected Bitmap pengBitmap;
    //resized bitmap to fit the screen
    protected Bitmap resizedBitmap;

    /**
     * Penguin Constructor that takes no parameters
     */
    public Penguin(int x, int y){


    }

    /**
     * Deep Copy Penguin Constructor
     *
     * @param p - a given penguin on the board
     */
    public Penguin(Penguin p){

    }

    //position of a player's penguin
    public int getCurrPosX() {
        return currPosX;
    }

    public void setCurrPosX(int newCurrPosX) {
        currPosX = newCurrPosX;
    }

    public int getCurrPosY() {
        return currPosY;
    }

    public void setCurrPosY(int newCurrPosY) {
        currPosY = newCurrPosY;
    }

}
