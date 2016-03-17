package edu.up.cs301.fish;

import android.widget.Toast;

import java.util.ArrayList;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * Hey, That's My Fish GameState
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/16/16
 */
public class FishGameState extends GameState{

    private int id;
    //score's for each player
    private int playerScore[];

    protected int[] player = new int[4];

    //number of penguins each player has
    protected int numPenguin;

    //current positions of penguins of a given player
    //assigns first few to player 0, second few to p1, etc
    private int currPosX[];
    private int currPosY[];


    protected boolean isLegalMove;
    protected boolean isLegalSelection;

    protected Hex[][] board;

    public FishGameState() {
        id = 0;
        //creates 10 by 10 game board
        board = new Hex[10][10];
        //creates array list equal to the number of players for the scores
        playerScore = new int[player.length];

        /**
         * Each player has:
         * 4 penguins if there are 2 players
         * 3 penguins if there are 3 players
         * 2 penguins if there are 4 players
         */
        if(player.length == 2){
            numPenguin = 4;
        } else if(player.length == 3){
            numPenguin = 3;
        } else if(player.length == 4){
            numPenguin = 2;
        }


        //arrays of all the positions of penguins in the game in order by player
        currPosX = new int[numPenguin * player.length];
        currPosY = new int[numPenguin * player.length];
    }

    public FishGameState(FishGameState fishGameState) {
        board = new Hex[10][10];
        this.id = fishGameState.getId();
        //gets all the currents scores of the players
        for (int i=0; i<player.length; i++) {
            this.playerScore[i] = fishGameState.getPlayerScore(i);
        }
        //same as before
        if(player.length == 2){
            numPenguin = 4;
        } else if(player.length == 3){
            numPenguin = 3;
        } else if(player.length == 4){
            numPenguin = 2;
        }

        //Sets the positions of the penguins
        for(int p=0; p<player.length; p++) {
            for (int peng=0; peng<numPenguin; peng++) {
                this.currPosX[p * numPenguin + peng] = fishGameState.getCurrPosX(p,peng);
                this.currPosY[p * numPenguin + peng] = fishGameState.getCurrPosY(p,peng);
            }
        }
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
        this.playerScore[id] = newScore;
    }

    //position of a player's penguin
    public int getCurrPosX(int id, int penguin) {
        int i = id * numPenguin;
        return currPosX[i+penguin];
    }

    public void setCurrPosX(int id, int penguin, int newCurrPosX) {
        int i = id * numPenguin;
        currPosX[i+penguin] = newCurrPosX;
    }

    public int getCurrPosY(int id, int penguin) {
        int i = id * numPenguin;
        return currPosY[i+penguin];
    }

    public void setCurrPosY(int id, int penguin, int newCurrPosY) {
        int i = id * numPenguin;
        currPosY[i+penguin] = newCurrPosY;
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
        for(int i=0; i<numPenguin; i++)
        {
            if (tappedPosX == currPosX[id * numPenguin +i] && tappedPosY == currPosY[id * numPenguin +i])
            {
                isLegalSelection=true;
                return;
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
        while(board[x][y] != null);

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



    /**
     * Set new current position for a given penguin of a given player and add old tile value to
     * score
     *
     * @param id player id
     * @param peng penguin number of a player
     * @param currPX current x coordinate of penguin
     * @param currPY current y coordinate of penguin
     * @param newPosX new x position that penguin will move to
     * @param newPosY new y position that penguin will move to
     */
    public void movePeng(int id, int peng, int currPX, int currPY, int newPosX, int newPosY) {

        //gets value of current tile
        if(board[currPX][currPY]!=null) {
            int val = board[currPX][currPY].getTileVal();
            //add value of curr tile to score of right player
            setPlayerScore(id, getPlayerScore(id) + val);
        }

        //sets new position of penguin --moves it
        setCurrPosX(id,peng,newPosX);
        setCurrPosY(id,peng,newPosY);

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
