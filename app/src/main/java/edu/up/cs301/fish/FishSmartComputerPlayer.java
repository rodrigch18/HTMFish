package edu.up.cs301.fish;

import java.io.Serializable;
import java.util.ArrayList;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Hey, That's My Fish SmartComputerPlayer
 *
 * This class implements our 'smart' AI. It is the cpu player
 * that repeatedly picks the most advantageous move to win.
 * Advantageous meaning that it calculates the move that has the
 * highest value. The polar opposite of the DumbComputerPlayer
 * class that implements our 'not so smart' AI.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 4/24/16
 */
public class FishSmartComputerPlayer extends GameComputerPlayer implements Serializable {

    int pengsOwned = 0;
    FishGameState newState;
    int xBoard;
    int yBoard;

    public boolean onStart= true;
    /**
     * Smart Computer Player constructor
     *
     * @param name - player name
     */
    public FishSmartComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method -- game's state has changed
     *
     * @param info - info from the game
     */
    protected void receiveInfo(GameInfo info)
    {
        if (info instanceof FishGameState) {
            int deadPengs= 0;
            newState = (FishGameState) info;
            for(int p=0; p<newState.numPenguin; p++) {
                if(newState.pengA[this.playerNum][p].getIsDead())
                {
                    deadPengs++;
                }
            }
            //if all penguins of given player are dead, then pass their turn
            if(deadPengs == newState.numPenguin){
                FishPassAction passAction = new FishPassAction(this);
                game.sendAction(passAction);
                return;
            }


            //sleep(50);
            if (newState.getId() != this.playerNum) {
                return;
            } else {

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
                    //Log.i("peng Owned by Comp", pengsOwned + " " + newState.numPenguin);
                    if (pengsOwned == newState.numPenguin) {
                        this.onStart=false;
                        // Log.i("On Start", "" + onStart);

                    }
                }
                //penguins are allowed to be moved once the game has started and they're set
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

                    int randI = 0;
                    int max = listOfLegal.get(0).getTileVal();
                    //checks to see which legal move has the highest value
                    for(int i = 0; i<listOfLegal.size(); i++){
                        if(max == 3){
                            randI = 0;
                        }
                        if(listOfLegal.get(i).getTileVal() > max){
                            randI = i;
                        }
                    }



                    FishMovePenguinAction movePenguinAction = new FishMovePenguinAction(this,
                            newState.getPeng(this.playerNum, randPeng), listOfLegal.get(randI).getX(),
                            listOfLegal.get(randI).getY(),randPeng, this.playerNum);


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
