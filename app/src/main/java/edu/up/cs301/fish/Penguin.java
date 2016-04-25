package edu.up.cs301.fish;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.Serializable;

import edu.up.cs301.game.R;

/**
 * Hey, That's My Fish Penguin
 *
 * This class has characteristics of a penguin.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 4/24/16
 */

public class Penguin implements Serializable{

    //x,y coordinates of given penguin
    private int currPosX;
    private int currPosY;
    private boolean isSelected;
    private boolean isDead;

    public Penguin(){
        isSelected = false;
        isDead = false;
    }

    public Penguin(int x, int y, boolean isSelected, boolean isDead){
        this.isSelected = isSelected;
        this.isDead = isDead;
        this.currPosX = x;
        this.currPosY = y;
    }

    public Penguin(Penguin aPeng){
        this.isSelected = aPeng.getIsSelected();
        this.isDead = aPeng.getIsDead();
        this.currPosX = aPeng.getCurrPosX();
        this.currPosY = aPeng.getCurrPosY();
    }

    //position of a player's penguin
    public int getCurrPosX() {
        return this.currPosX;
    }

    public void setCurrPosX(int newCurrPosX) {
        this.currPosX = newCurrPosX;
    }

    public int getCurrPosY() {
        return this.currPosY;
    }

    public void setCurrPosY(int newCurrPosY) {
        this.currPosY = newCurrPosY;
    }

    public boolean getIsSelected() {
        return this.isSelected;
    }

    public void setIsSelected(boolean newSelected) {
        this.isSelected = newSelected;
    }

    public boolean getIsDead() {
        return this.isDead;
    }

    public void setIsDead(boolean newDeath) {
        this.isDead = newDeath;
    }



}
