package edu.up.cs301.fish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import edu.up.cs301.game.R;

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

    public int x;
    public int y;

    public boolean occupied;
    /**
     * Hex constructor that takes no parameters
     * It sets the tile values randomly (1,2, or 3)
     */
    public Hex(int xPos, int yPos) {
        //sets tileVal to either 1, 2 , or 3

        x = xPos;
        y = yPos;
        occupied = false;

        tileVal = (int)(Math.random()*3)+1;

    }

    public Hex(int tileVal, boolean occupied){
        this.tileVal = tileVal;
        this.occupied = occupied;
    }

    // tile values
    public int getTileVal() {
        return tileVal;
    }

    public boolean getOccupied() { return occupied;}

    public String toString(){
        return (this.x + " " + this.y + " " + this.occupied);
    }



}

