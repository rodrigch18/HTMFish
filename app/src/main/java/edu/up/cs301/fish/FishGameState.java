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
    private Hexagon[][] board;


    public FishGameState(){
        id = 0;
        p0score = 0;
        p1score = 0;
        p2score = 0;
        p3score = 0;
        board = new Hexagon[8][8];
    }

    public FishGameState(FishGameState fishGameState){
        this.id = fishGameState.getId();
        this.p0score = fishGameState.getP0score();
        this.p1score = fishGameState.getP1score();
        this.p2score = fishGameState.getP2score();
        this.p3score = fishGameState.getP3score();
        this.currPos0 = fishGameState.getCurrPos0();
        this.currPos1 = fishGameState.getCurrPos1();
        this.currPos2 = fishGameState.getCurrPos2();
        this.currPos3 = fishGameState.getCurrPos3();

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

    public void checkIllegalMove(int currP){

    }







}
