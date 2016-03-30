package edu.up.cs301.fish;

import android.graphics.Canvas;

/**
 * Hey, That's My Fish Hex
 *
 * This is the class for Hex. Each tile on the board is a
 * hexagon. It draws the board, and sets the values of each
 * tile randomly as either 1, 2, or 3 fish.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/28/16
 */
public class Hex {

    protected int tileVal;

    /**
     * Hex constructor that takes no parameters
     * It sets the tile values randomly (1,2, or 3)
     */
    public Hex() {
        //sets tileVal to either 1, 2 , or 3
        tileVal = (int)(Math.random()*3+1);
    }

    // tile values
    public int getTileVal() {
        return tileVal;
    }


    /**
     * draws the hexagonal board, 1 at a time
     *
     * @param canvas - canvas for surfaceView
     */
    public void drawHex(Canvas canvas){

    }

}

