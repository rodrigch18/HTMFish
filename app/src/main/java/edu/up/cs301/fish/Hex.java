package edu.up.cs301.fish;

/**
 * Class for Hex, each tile on the board is a hex
 * @author gisellemarston
 */
public class Hex {

    protected int tileVal;

    public Hex() {
        //sets tileVal to either 1, 2 , or 3
        tileVal = (int)(Math.random()*3+1);
    }

    public int getTileVal() {
        return tileVal;
    }

}
