package edu.up.cs301.fish;

import android.widget.Toast;

import java.util.ArrayList;

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
    private int playerScore[];

    //amount of players possible
    protected int[] player = new int[4];

    //number of penguins each player has
    protected int numPenguin;

    //current positions of penguins of a given player
    //assigns first few to player 0, second few to p1, etc
    private int currPosX[];
    private int currPosY[];

    //make sure the player move is legal
    protected boolean isLegalMove;
    //make sure the selected tile is legal
    protected boolean isLegalSelection;

    protected Hex[][] board;

    //penguin array that holds all the penguins in the game
    protected Penguin[] penguin;

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
        for(int p=0; p<player.length; p++) {    //TEST
            for (int peng=0; peng<numPenguin; peng++) {
                this.currPosX[p * numPenguin + peng] = fishGameState.penguin[p].getCurrPosX();
                this.currPosY[p * numPenguin + peng] = fishGameState.penguin[p].getCurrPosY();
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


    /**
     * Checks if a tapped position is the player's penguin whose turn it is
     * is illegal if it is another player's penguin or an empty tile
     *
     * @param id - player id
     * @param tappedPosX -x corrdinate of tapped spot
     * @param tappedPosX -x corrdinate of tapped spot
     */
    public void checkSelectedPeng(int id, int tappedPosX, int tappedPosY) {
        for(int i=0; i<penguin.length; i++)
        {
            if (tappedPosX == penguin[i].getCurrPosX() && tappedPosY == penguin[i].getCurrPosX() )
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
     * @param p Penguin selected
     * @param newPosX new x position that penguin will move to
     * @param newPosY new y position that penguin will move to
     */
    public void movePeng(int id, Penguin p, int newPosX, int newPosY) {

        //gets value of current tile
        if(board[p.getCurrPosX()][p.getCurrPosY()]!=null) {
            int val = board[p.getCurrPosX()][p.getCurrPosY()].getTileVal();
            //add value of curr tile to score of right player
            setPlayerScore(id, getPlayerScore(id) + val);
        }

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
