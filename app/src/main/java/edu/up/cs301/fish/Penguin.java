package edu.up.cs301.fish;

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

    protected int player; // player that owns given penguin
    // x,y coordinates of given penguin
    protected int currPosX;
    protected int currPosY;

    /**
     * Penguin Constructor that takes no parameters
     */
    public Penguin(){

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
