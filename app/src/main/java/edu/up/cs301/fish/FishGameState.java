package edu.up.cs301.fish;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;

import edu.up.cs301.animation.HexagonSurfaceView;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameState;

/**
 * Hey, That's My Fish GameState
 *
 * The GameState class is where the magic happens! It checks
 * to make sure that the move being attempted is valid or invalid.
 * An invalid move is either moving to or through an already
 * occupied spot, moving to a spot that no longer exists
 * because it has liquid water instead of solid ice. It also
 * does not allow for a player to move in a direction that is not
 * in a straight line from the hexagon. It also keeps the players'
 * scores updated. It even takes care of the situation when an
 * island is created, where the tile values are added to the player's
 * score and the penguin is no longer in play.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/28/16
 */
public class FishGameState extends GameState{



    //player id
    private int id;
    //score's for each player


    int numOfPlayers;
    String[] playerName;

    //amount of players possible
    public int[] player = new int[numOfPlayers];
    private int[] playerScore;

    //number of penguins each player has

    public int numPenguin;

    //current positions of penguins of a given player
    //assigns first few to player 0, second few to p1, etc
    private int currPosX[];
    private int currPosY[];

    //make sure the player move is legal
    protected boolean isLegalMove;
    //make sure the selected tile is legal
    protected boolean isLegalSelection;

    public boolean onStart;

    public Hex[][] board;

    //penguin array that holds all the penguins in the game
    public Penguin[][] pengA;

    public FishGameState(int numPlayers, String[] playersNames) {

        numOfPlayers = numPlayers;
        playerName = playersNames;
       // Log.i("Number of Players","" + numOfPlayers);
        id = 0;


        //creates 10 by 10 game board
        board = new Hex[10][10];

        for(int i = 0; i < 10; i++) {  //i represents the columns of the tiles
            for(int j = 0; j < 10; j++) {//j represents the rows
                if (i==0||i==9||j==0||j==9||i==1 && j%2==1) { // does not draw the first tile of the odd rows
                    board[i][j] = null;
                }
                else if (j%2==0){ // shifts and draws the even rows by the radius
                    board[i][j] = new Hex((i*135)+70, (j*130));
                }
                else{ //draws the odd rows
                    board[i][j] = new Hex((i*135), (j*130));

                }
            }
        }

        switch (numOfPlayers){
            case 2:
                pengA = new Penguin[2][4];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 4; j++) {
                        pengA[i][j]= new Penguin();
                    }
                }
                break;
            case 3:
                pengA = new Penguin[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        pengA[i][j]= new Penguin();
                    }
                }
                break;
            case 4:
                pengA = new Penguin[4][2];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        pengA[i][j]= new Penguin();
                    }
                }
                break;
        }



        //creates array list equal to the number of players for the scores
        playerScore = new int[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++){
            playerScore[i] = 0;
        }

        /**
         * Each player has:
         * 4 penguins if there are 2 players
         * 3 penguins if there are 3 players
         * 2 penguins if there are 4 players
         */
        if(numOfPlayers == 2){
            numPenguin = 4;
        } else if(numOfPlayers == 3){
            numPenguin = 3;
        } else if(numOfPlayers == 4){
            numPenguin = 2;
        }

        player = new int[numOfPlayers];


        //arrays of all the positions of penguins in the game in order by player
        currPosX = new int[numPenguin * numOfPlayers];
        currPosY = new int[numPenguin * numOfPlayers];
    }

    public FishGameState(FishGameState fishGameState, int numPlayers, String[] playersNames) {
        numOfPlayers = numPlayers;
        playerName = playersNames;

        board = new Hex[10][10];

        if(fishGameState.board != null) {
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if(fishGameState.board[i][j] != null) {
                        board[i][j] = fishGameState.board[i][j];
                    }
                    else{
                        board[i][j] = null;
                    }
                }
            }
        }

        switch (numOfPlayers){
            case 2:
                pengA = new Penguin[2][4];
                for (int i = 0; i < 2; i++) {
                    for (int j = 0; j < 4; j++) {
                        if(fishGameState.pengA[i][j] != null) {
                            pengA[i][j] = fishGameState.pengA[i][j];
                        }
                        else{
                            pengA[i][j] = null;
                        }
                    }
                }
                break;
            case 3:
                pengA = new Penguin[3][3];
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        if(fishGameState.pengA[i][j] != null) {
                            pengA[i][j] = fishGameState.pengA[i][j];
                        }
                        else{
                            pengA[i][j] = null;
                        }
                    }
                }
                break;
            case 4:
                pengA = new Penguin[4][2];
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 2; j++) {
                        if(fishGameState.pengA[i][j] != null) {
                            pengA[i][j] = fishGameState.pengA[i][j];
                        }
                        else{
                            pengA[i][j] = null;
                        }
                    }
                }
                break;
        }

        this.id = fishGameState.getId();
        //gets all the currents scores of the players
     //   for (int i=0; i<numOfPlayers; i++) {
     //       this.playerScore[i] = fishGameState.getPlayerScore(i);
     //   }
        //same as before
        if(numOfPlayers == 2){
            numPenguin = 4;
        } else if(numOfPlayers == 3){
            numPenguin = 3;
        } else if(numOfPlayers == 4){
            numPenguin = 2;
        }

        player = new int[numOfPlayers];

        playerScore = new int[numOfPlayers];
        for(int i = 0; i < numOfPlayers; i++){
            playerScore[i] = fishGameState.getPlayerScore(i);
        }

        //Sets the positions of the penguins
   //     for(int p=0; p<numOfPlayers; p++) {    //TEST
   //         for (int peng=0; peng<numPenguin; peng++) {
    //            this.currPosX[p * numPenguin + peng] = fishGameState.penguin[p].getCurrPosX();
    //            this.currPosY[p * numPenguin + peng] = fishGameState.penguin[p].getCurrPosY();
     //       }
     //   }
    }

    //player ID
    public int getId() {
        return id;
    }

    public void setId(int i) {
        this.id = i;
    }

    //player score dependent on ID
    public int getPlayerScore(int id) {
        return playerScore[id];
    }


    public void setPlayerScore(int id, int newScore) {
        this.playerScore[id] = this.playerScore[id] + newScore;
    }


    /**
     * Checks if a tapped position is the player's penguin whose turn it is
     * is illegal if it is another player's penguin or an empty tile
     *
     * @param id - player id
     * @param tappedPosX -x corrdinate of tapped spot
     * @param tappedPosX -x corrdinate of tapped spot
     */
    public void checkSelectedPeng(int id, int tappedPosX, int tappedPosY) {
        for(int i=0; i<numOfPlayers; i++) {
            for (int j = 0; j < pengA[0].length; j++) {
                if (tappedPosX == pengA[i][j].getCurrPosX() && tappedPosY == pengA[i][j].getCurrPosX()) {
                    isLegalSelection = true;
                    return;
                }
            }
        }
        isLegalSelection=false;
    }


    /**
     * External Citation
     * Date: 16 March 2016
     * Needed help to check possible paths that penguin can move to.
     * Resource:
     * Dr. Andrew Nuxoll
     */

    /**
     * Create list of tiles adjacent to current tile in defined direction
     *
     * @param x- current x coordinate
     * @param y- current x coordinate
     * @param xDelta - change in x coordinates
     * @param yDelta - change in y coordinates
     * @param results - list of positions on board that are valid places to move to
     * @return
     */
    public ArrayList<Hex> findAdjInDir(int x, int y,  int xDelta, int yDelta, ArrayList<Hex> results)
    {
        do
        {
            x = x + xDelta;
            y = y + yDelta;
            if (board[x][y] != null)
            {
                results.add(board[x][y]);
            }
        }
        while(board[x][y] != null && board[x][y].getOccupied() == false);

        return results;
    }

    /**
     * Checks if the tapped spot is a valid move from current position using findAdjInDir,
     * which is used to make lists of valid moves in all 6 directions from hex
     *
     * @param currPX
     * @param currPY
     * @param tappedPosX
     * @param tappedPosY
     */
    public void checkIsLegalMove(int currPX,int currPY, int tappedPosX, int tappedPosY) {

        ArrayList<Hex> results = new ArrayList<Hex>();

        findAdjInDir(currPX, currPY, 1, 0, results);
        findAdjInDir(currPX, currPY, -1, 0, results);
        findAdjInDir(currPX, currPY, 1, 1, results);
        findAdjInDir(currPX, currPY, 0, 1, results);
        findAdjInDir(currPX, currPY, 1, -1, results);
        findAdjInDir(currPX, currPY, 0, -1, results);

        if(results.contains(board[tappedPosX][tappedPosY])) {
            isLegalMove = true;
        }
        else {
            isLegalMove = false;
        }
    }


    public void setPeng(Penguin p, int newPosX, int newPosY, int playerIndex) {


        p.setCurrPosX(newPosX);
        p.setCurrPosY(newPosY);

        for(int i = 0; i < pengA[0].length; i++) {
            if(pengA[playerIndex][i] == null){
                pengA[playerIndex][i] = p;
            }
        }

    }

    public Penguin getPeng(int id, int pengidx){
        return pengA[id][pengidx];
    }

    public boolean checkIfOccupied(Hex selectedHex){
        return selectedHex.occupied;
    }



    /**
     * Set new current position for a given penguin of a given player and add old tile value to
     * score
     *
     * @param id player id
     * @param p Penguin selected
     * @param newPosX new x position that penguin will move to
     * @param newPosY new y position that penguin will move to
     */
    public void movePeng(int id, Penguin p, int newPosX, int newPosY, int pengIndex) {

       // Log.i("MOVING", "THE PENGUIN");
        int val = 0;

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(board[i][j] != null){
                    if(p.getCurrPosX() == board[i][j].x && p.getCurrPosY() == board[i][j].y) {
                        val = board[i][j].getTileVal();
                        board[i][j]=null;
                    }
                }
            }
        }

        //add value of curr tile to score of right player
       // setPlayerScore(id, getPlayerScore(id) + val);
        setPlayerScore(id, val);

        pengA[id][pengIndex] = new Penguin(newPosX,newPosY,false
                ,pengA[id][pengIndex].getIsDead());

        //sets new position of penguin --moves it
       p.setCurrPosX(newPosX);
       p.setCurrPosY(newPosY);

    }


    /**
     * Makes one-tile islands null if there are all adjacent tiles are null
     *
     * @param currBoard - current board with any gaps
     * @return new board when islands are taken out
     */
    public Hex[][] removeIsland(Hex[][] currBoard) {

        for(int x=1; x < 9; x++){
            for(int y=1; y<9; y++){
                if (currBoard[x+1][y] == null && currBoard[x+1][y+1] == null
                        && currBoard[x+1][y-1] == null && currBoard[x][y-1] == null
                        && currBoard[x][y+1] == null && currBoard[x-1][y] == null){

                    currBoard[x][y] = null;
                }
            }
        }
        return currBoard;
    }

}
