package edu.up.cs301.fish;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Created by gisellemarston on 3/14/16.
 */
public class FishGameState extends GameState {

    private int id;
    private int p0score;
    private int p1score;
    private int p2score;
    private int p3score;

    private int numPenguin;

    private int currPos0;
    private int currPos1;
    private int currPos2;
    private int currPos3;

    private int currTileVal;

    private boolean isLegalMove;
    private Hexagon[] board;


    public FishGameState() {
        id = 0;
        p0score = 0;
        p1score = 0;
        p2score = 0;
        p3score = 0;
        currTileVal = 1;
        board = new Hexagon[60];
    }

    public FishGameState(FishGameState fishGameState) {
        this.id = fishGameState.getId();
        this.p0score = fishGameState.getP0score();
        this.p1score = fishGameState.getP1score();
        this.p2score = fishGameState.getP2score();
        this.p3score = fishGameState.getP3score();
        this.currPos0 = fishGameState.getCurrPos0();
        this.currPos1 = fishGameState.getCurrPos1();
        this.currPos2 = fishGameState.getCurrPos2();
        this.currPos3 = fishGameState.getCurrPos3();
        this.

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getP0score() {
        return p0score;
    }

    public void setP0score(int p0score) {
        this.p0score = p0score;
    }

    public int getP1score() {
        return p1score;
    }

    public void setP1score(int p1score) {
        this.p1score = p1score;
    }

    public int getP2score() {
        return p2score;
    }

    public void setP2score(int p2score) {
        this.p2score = p2score;
    }

    public int getP3score() {
        return p3score;
    }

    public void setP3score(int p3score) {
        this.p3score = p3score;
    }

    public int getCurrPos0() {
        return currPos0;
    }

    public void setCurrPos0(int currPos0) {
        this.currPos0 = currPos0;
    }

    public int getCurrPos1() {
        return currPos1;
    }

    public void setCurrPos1(int currPos1) {
        this.currPos1 = currPos1;
    }

    public int getCurrPos2() {
        return currPos2;
    }

    public void setCurrPos2(int currPos2) {
        this.currPos2 = currPos2;
    }

    public int getCurrPos3() {
        return currPos3;
    }

    public void setCurrPos3(int currPos3) {
        this.currPos3 = currPos3;
    }

    /**
     * need to check if it is player's penguin when tapped and highlight hex
     * if not flash red?
     */
    public void highlightSelectedPeng(int tappedPos) {
        if (tappedPos == currPos0 || tappedPos == currPos1 || tappedPos == currPos2
                || tappedPos == currPos3 ) {
            // highlight
        }
        else {
            // ERROR - Toast Message
        }
    }

    /**
     * on second tap, check if penguin can move there with boolean hasNeighbors?
     */
    public void checkIsLegalMove(int currP) {
        //if there is a straight path to tapped pos without jumps or penguins in the way
        if(board[currP].isTouching() == 1) {
            isLegalMove = true;
        }
    }

    /**
     * redraw penguin in new place and set new current position for this penguin
     * if isIllegalMove is true
     */
    public void movePenguin(int penguin, int newPos) {


        //check which player and value of cur pos and update score
        if(getId() == 0){

        }
        else if(getId() == 1){

        }
        else if(getId() == 2){

        }
        else{

        }

        if (penguin == 0) {
            int tileVal = board[getCurrPos0()].getCurrTileVal();
            setCurrPos0(newPos);
        } else if (penguin == 1) {
            setCurrPos1(newPos);
        } else if (penguin == 2) {
            setCurrPos2(newPos);
        } else if (penguin == 3) {
            setCurrPos3(newPos);
        }

    }

    /**
     * checks if all tiles have > 0 neighbors after every move
     * if not, delete specific hex
     */
    public void checkRemoveIsland(Hexagon[] curBoard) {
        for(int i=0; i < 60; i++){
            if (curBoard[i].numNeighbors==0){
                curBoard[i].disable();
            }
        }
    }

    public void


}
