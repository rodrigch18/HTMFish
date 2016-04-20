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
 * @version 4/20/16
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
        Log.i("CPU recieve info", "info");
        if (info instanceof FishGameState) {
            Log.i("CPU FishGameState", "info");
            int deadPengs= 0;
            newState = (FishGameState) info;
            for(int p=0; p<newState.numPenguin; p++) {
                if(newState.pengA[this.playerNum][p].getIsDead())
                {
                    deadPengs++;
                }
            }
            if(deadPengs == newState.numPenguin){
                FishPassAction passAction = new FishPassAction(this);
                game.sendAction(passAction);
                return;
            }



            //sleep(50);
            //Log.i("CPU WOKE", newState.getId() + " " + this.playerNum);
            if (newState.getId() != this.playerNum) {
                return;
            } else {




                //creates pengs -giselle thinks
                if(this.onStart) {

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
                            newState.getPeng(this.playerNum, pengsOwned) ,  listOfTiles.get(randI).getX(),
                            listOfTiles.get(randI).getY(), this.playerNum, pengsOwned);


                    game.sendAction(setPenguinAction);

                    for (int i = 0; i< 10; i++){
                        for (int j = 0; j< 10; j++){
                            if(newState.board[i][j] != null){
                                if(listOfTiles.get(randI).getX() == newState.board[i][j].getX() &&
                                        listOfTiles.get(randI).getY() == newState.board[i][j].getY()){
                                    newState.board[i][j].setOccupied(true);
                                }
                            }
                        }
                    }

                    pengsOwned++;
                    if (pengsOwned == newState.numPenguin) {
                        this.onStart=false;

                    }
                }
                else if (!this.onStart)
                {
                    newState.checkPenguins();
                    int randPeng;
                    do {
                        randPeng = (int) (Math.random() * pengsOwned);
                    }
                    while(newState.getPeng(this.playerNum,randPeng).getIsDead());



                    int aI=0;
                    int aJ=0;

                    for (int i = 0; i< 10; i++){
                        for (int j = 0; j< 10; j++){
                            if(newState.board[i][j] != null){
                                if(newState.getPeng(this.playerNum, randPeng).getCurrPosX() == newState.board[i][j].getX()
                                        && newState.getPeng(this.playerNum, randPeng).getCurrPosY() == newState.board[i][j].getY()){

                                    aI = i;
                                    aJ = j;

                                }
                            }
                        }
                    }


                    ArrayList<Hex> listOfLegal = newState.checkIsLegalMove(aI, aJ);

                    int randI = (int) (Math.random() * listOfLegal.size());


                    FishMovePenguinAction movePenguinAction = new FishMovePenguinAction(this,
                            newState.getPeng(this.playerNum, randPeng), listOfLegal.get(randI).getX(), listOfLegal.get(randI).getY(),randPeng, this.playerNum);


                    game.sendAction(movePenguinAction);

                    for (int i = 0; i< 10; i++){
                        for (int j = 0; j< 10; j++){
                            if(newState.board[i][j] != null){
                                if(listOfLegal.get(randI).getX() == newState.board[i][j].getX() &&
                                        listOfLegal.get(randI).getY() == newState.board[i][j].getY()){
                                    newState.board[i][j].setOccupied(true);
                                }
                            }
                        }
                    }

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


}
