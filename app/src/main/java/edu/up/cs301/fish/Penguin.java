package edu.up.cs301.fish;

/**
 * Created by gisellemarston on 3/28/16.
 */
public class Penguin {

    protected int player;
    protected int currPosX;
    protected int currPosY;

    public Penguin(){

    }
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
