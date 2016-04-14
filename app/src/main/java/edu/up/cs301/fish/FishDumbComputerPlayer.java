package edu.up.cs301.fish;

import android.util.Log;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import edu.up.cs301.animation.HexagonSurfaceView;
import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Hey, That's My Fish DumbComputerPlayer
 *
 * This class implements our 'not so smart' AI.
 * It is the cpu player that picks random spots for a random
 * penguin to go to. It has neither the knowledge of the most
 * advantageous move to win nor does it really care. The opposite
 * of the SmartComputerPlayer class that implements our
 * 'smart' AI.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/31/16
 */

public class FishDumbComputerPlayer extends GameComputerPlayer implements Serializable {

    int pengsOwned = 0;
    FishGameState newState;
    int xBoard;
    int yBoard;

    public boolean onStart= true;
    /**
     * Dumb Computer Player constructor
     *
     * @param name - player name
     */
    public FishDumbComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method -- game's state has changed
     *
     * @param info - info from the game
     */
    protected void receiveInfo(GameInfo info)
    {
      //  Log.i("CPU recieve info", "info");
        if (info instanceof FishGameState) {
         //   Log.i("CPU FishGameState", "info");

            newState = (FishGameState) info;

            //Log.i("CPU PLAYER ID", "" + newState.getId());
//            newState.board = new Hex[10][10];
//
//            if (newState.board != null) {
//                for (int i = 0; i < 10; i++) {
//                    for (int j = 0; j < 10; j++) {
//                        if (newState.board[i][j] != null) {
//                            newState.board[i][j] = newState.board[i][j];
//                        } else {
//                            newState.board[i][j] = null;
//                        }
//                    }
//                }
//            }


            sleep(1000);
            //Log.i("CPU WOKE", newState.getId() + " " + this.playerNum);
            if (newState.getId() != this.playerNum) {
                return;
            } else {

//                if(newState.numOfPlayers == 2 && pengsOwned == 4){
//                    return;
//                } else if(newState.numOfPlayers == 3 && pengsOwned == 3){
//                    return;
//                } else if(newState.numOfPlayers == 4 && pengsOwned == 2){
//                    return;
//                }


                //creates pengs -giselle thinks
                if(this.onStart) {

//                    int x;
//                    int y;
//
//                    boolean inHex = false;
//                    do {
//                        x = (int) (Math.random() * 1000) + 335;
//                        y = (int) (Math.random() * 1000) + 150;
//
//                        inHex =checkIfInHex(x,y);
//                     //   Log.i("CHeckInHEx", x+" "+y+" "+ checkIfInHex(x,y));
//                    }
//                    while(inHex != true);

                    ArrayList<Hex> listOfTiles = new ArrayList<Hex>();
                    for (int i = 0; i< 10; i++){
                        for (int j = 0; j< 10; j++){
                            if(newState.board[i][j] != null && newState.board[i][j].getOccupied() == false &&
                                    newState.board[i][j].getTileVal() == 1){
                                listOfTiles.add(newState.board[i][j]);
                            }
                        }
                    }

                    int randI = (int) (Math.random() * listOfTiles.size());

                    FishSetPenguinAction setPenguinAction = new FishSetPenguinAction(this,
                            newState.getPeng(this.playerNum, pengsOwned) ,  listOfTiles.get(randI).x,
                            listOfTiles.get(randI).y, this.playerNum);


                    //Log.i("CPU set", x + " " + y);
                    game.sendAction(setPenguinAction);

                    pengsOwned++;
                    //Log.i("peng Owned by Comp", pengsOwned + " " + newState.numPenguin);
                    if (pengsOwned == newState.numPenguin) {
                        this.onStart=false;
                       // Log.i("On Start", "" + onStart);

                    }
                }
                else if (!this.onStart)
                {
                    int randPeng = (int) (Math.random() * pengsOwned );

//                    Log.i("CPU MOVE", getXboard() + " " + getYboard() + " " + randPeng);
//                    do {
//                        x = (int) (Math.random() * 1000) + 335;
//                        y = (int) (Math.random() * 1000) + 150;
//
//                        inHex = checkIfInHex(x,y);
//                    }
//                    while(inHex != true);

                    ArrayList<Hex> listOfTiles = new ArrayList<Hex>();
                    for (int i = 0; i< 10; i++){
                        for (int j = 0; j< 10; j++){
                            if(newState.board[i][j] != null && newState.board[i][j].getOccupied() == false){
                                listOfTiles.add(newState.board[i][j]);
                            }
                        }
                    }

                    int randI = (int) (Math.random() * listOfTiles.size());



                    FishMovePenguinAction movePenguinAction = new FishMovePenguinAction(this,
                            newState.getPeng(this.playerNum, randPeng), listOfTiles.get(randI).x, listOfTiles.get(randI).y,randPeng);


                    game.sendAction(movePenguinAction);
                }

            }
        }
    }

    public int getXboard(){
        return xBoard;
    }
    public void setXboard(int newX){
        xBoard = newX;
    }
    public int getYboard(){
        return yBoard;
    }
    public void setYboard(int newY){
        yBoard = newY;
    }
    public boolean getOnStart(){
        return this.onStart;
    }
    public void setOnStart(boolean onStartNow){
        onStart = onStartNow;
    }

    protected boolean checkIfInHex(int x, int y){

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (newState.board[i][j] != null) {

                    if (((x - (newState.board[i][j].x + 65)) * (x - (newState.board[i][j].x + 65)) +
                            (y - (newState.board[i][j].y + 65)) * (y - (newState.board[i][j].y + 65))
                            <= 65 * 65)) {
                        if(!newState.board[i][j].occupied) {
                            if(this.onStart==true){
                                if(newState.board[i][j].getTileVal()==1) {
                                    setXboard(newState.board[i][j].x);
                                    setYboard(newState.board[i][j].y);
                                    newState.board[i][j].occupied = true;
                                    return true;
                                }
                            }
                            else {
                                    setXboard(newState.board[i][j].x);
                                    setYboard(newState.board[i][j].y);
                                    newState.board[i][j].occupied = true;
                                    return true;
                            }

                        }
                    }
                }
            }
        }
        return false;
    }
}
