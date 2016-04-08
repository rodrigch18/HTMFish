package edu.up.cs301.fish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

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

    Bitmap myBitmap;
    Canvas canvas;
    private Paint   mBitmapPaint;
    Paint p= new Paint();

    protected int tileVal;
    boolean fullTile;
    private int x;
    private int y;


    /**
     * Hex constructor that takes no parameters
     * It sets the tile values randomly (1,2, or 3)
     */
    public Hex(Context context, int xPos, int yPos) {
        //sets tileVal to either 1, 2 , or 3

        x = xPos;
        y = yPos;

        tileVal = (int)(Math.random()*3+1);

        switch (tileVal) {
            case 1:
                myBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.one_fish); //decode bitmap in constructor
            case 2:
                myBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.two_fish); //decode bitmap in constructor
            case 3:
                myBitmap = BitmapFactory.decodeResource(context.getResources(), R.mipmap.three_fish); //decode bitmap in constructor
        }

        myBitmap.createScaledBitmap(myBitmap, 50, 50, false);

    }

    public Hex(int tileVal){
        this.tileVal = tileVal;
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

        canvas.drawColor(Color.BLACK);
        canvas.drawBitmap(myBitmap, x,y, p);
    }

}

