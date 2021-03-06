package edu.up.cs301.fish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;

import java.io.Serializable;

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
 * @version 4/24/16
 */
public class Hex implements Serializable{

    private int tileVal;

    private int x;
    private int y;

    private boolean occupied;
    /**
     * Hex constructor that takes no parameters
     * It sets the tile values randomly (1,2, or 3)
     */
    public Hex(int xPos, int yPos) {
        //sets tileVal to either 1, 2 , or 3

        this.x = xPos;
        this.y = yPos;
        this.occupied = false;

        this.tileVal = (int)(Math.random()*3)+1;

    }

    public Hex(Hex aHex){
        this.tileVal = aHex.getTileVal();
        this.occupied = aHex.getOccupied();
        this.x = aHex.getX();
        this.y = aHex.getY();
    }

    //tile values
    public int getTileVal() {
        return this.tileVal;
    }

    public boolean getOccupied() { return this.occupied;}

    public void setOccupied(boolean occ) { this.occupied = occ;}

    public int getX() { return this.x;}

    public void setX(int x) { this.x = x;}

    public int getY() {
        return this.y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String toString(){ return (this.x + " " + this.y + " " + this.occupied);}



}

