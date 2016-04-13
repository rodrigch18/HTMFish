package edu.up.cs301.fish;

import android.util.Log;

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

public class FishDumbComputerPlayer extends GameComputerPlayer {

    int pengsOwned = 0;
    FishGameState newState;
    Hex[][] compBoard;
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
    protected void receiveInfo(GameInfo info) {
        if (info instanceof FishGameState) {
            newState = (FishGameState) info;
            compBoard = new Hex[10][10];

            if (newState.board != null) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (newState.board[i][j] != null) {
                            compBoard[i][j] = newState.board[i][j];
                        } else {
                            compBoard[i][j] = null;
                        }
                    }
                }
            }


            sleep(1000);
            if (newState.getId() != this.playerNum) {
                return;
            } else {

                if(newState.numOfPlayers == 2 && pengsOwned == 4){
                    return;
                } else if(newState.numOfPlayers == 3 && pengsOwned == 3){
                    return;
                } else if(newState.numOfPlayers == 4 && pengsOwned == 2){
                    return;
                }

                int x;
                int y;

                boolean inHex = false;
                do {
                    x = (int) (Math.random() * 1000) + 335;
                    y = (int) (Math.random() * 1000) + 150;

                    inHex =checkIfInHex(x,y);
                    Log.i("CHeckInHEx", x+" "+y+" "+ checkIfInHex(x,y));
                }
                while(inHex != true);

                //creates pengs -giselle thinks
                if(onStart) {
                    FishSetPenguinAction setPenguinAction = new FishSetPenguinAction(this,
                            new Penguin(getXboard(), getYboard()), this.playerNum);
                    newState.setPeng(newState.getPeng(this.playerNum, pengsOwned), getXboard(),
                            getYboard(), this.playerNum);
                    pengsOwned++;

                    if (pengsOwned == newState.numPenguin) {
                        this.onStart=false;
                        Log.i("On Start", "" + onStart);

                    }

                    Log.i("CPU set", x + " " + y);
                    game.sendAction(setPenguinAction);
                }
                if (!onStart)
                {
                    int randPeng = (int) (Math.random() * pengsOwned);
                    Log.i("CPU move", getXboard() + " " + getYboard());
                    do {
                        x = (int) (Math.random() * 1000) + 335;
                        y = (int) (Math.random() * 1000) + 150;

                        inHex = checkIfInHex(x,y);
                    }
                    while(inHex != true);


                    FishMovePenguinAction movePenguinAction = new FishMovePenguinAction(this,
                            newState.getPeng(this.playerNum, randPeng), getXboard(), getYboard(),randPeng);
                    newState.movePeng(this.playerNum, newState.getPeng(this.playerNum, randPeng),
                           getXboard(), getYboard(), randPeng);

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
        return onStart;
    }
    public void setOnStart(boolean onStartNow){
        onStart = onStartNow;
    }

    protected boolean checkIfInHex(int x, int y){

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                if (compBoard[i][j] != null) {

                    if (((x - (compBoard[i][j].x + 65)) * (x - (compBoard[i][j].x + 65)) +
                            (y - (compBoard[i][j].y + 65)) * (y - (compBoard[i][j].y + 65))
                            <= 65 * 65)) {
                        if(!compBoard[i][j].occupied) {
                            if(onStart==true){
                                if(compBoard[i][j].getTileVal()==1) {
                                    setXboard(compBoard[i][j].x);
                                    setYboard(compBoard[i][j].y);
                                    compBoard[i][j].occupied = true;
                                    return true;
                                }
                            }
                            else {
                                    setXboard(compBoard[i][j].x);
                                    setYboard(compBoard[i][j].y);
                                    compBoard[i][j].occupied = true;
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
