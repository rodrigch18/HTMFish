package edu.up.cs301.fish;

import junit.framework.Assert;

import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Hey, That's My Fish FishGameStateTest
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/16/16
 */
public class FishGameStateTest {

//    Checks if a tapped position is the player's penguin whose turn it is
//      is illegal if it is another player's penguin or an empty tile
    @Test
    public void testCheckSelectedPeng() throws Exception {
        FishGameState game = new FishGameState();
        game.numPenguin= 2;
        game.checkSelectedPeng(0,1,2);
        assertEquals(game.isLegalSelection, false);
    }

//    tests if the two tiles are adjacent
    @Test
    public void testFindAdjInDir() throws Exception {
        FishGameState game = new FishGameState();

        ArrayList<Hex> left = new ArrayList<Hex>();
        ArrayList<Hex> right = new ArrayList<Hex>();
        left = game.findAdjInDir(2, 2, 1,0 , left);
        right = game.findAdjInDir(3, 2, -1,0 , right);
        assertEquals(left, right);
    }

    //tests if the move is legal
    @Test
    public void testCheckIsLegalMove() throws Exception {
        FishGameState game = new FishGameState();


        game.checkIsLegalMove(1,1,4,4);

        assertEquals(game.isLegalMove, false);
    }

    //tests if the penguin is moved to the tapped position
    @Test
    public void testMovePeng() throws Exception {
        FishGameState game = new FishGameState();
        game.movePeng(0, 0, 2, 3, 2, 2);
        assertEquals(game.getCurrPosX(0,0),2);
        assertEquals(game.getCurrPosY(0, 0),2);
    }

    //tests if a lone tile is set to null automatically
    @Test
    public void testRemoveIsland() throws Exception {
        FishGameState game = new FishGameState();
        Hex[][] currBoard = new Hex[10][10];
        int x=5;
        int y=5;
        currBoard[x+1][y] = null;
        currBoard[x+1][y+1] = null;
        currBoard[x+1][y-1] = null;
        currBoard[x][y-1] = null;
        currBoard[x][y+1] = null;
        currBoard[x-1][y] = null;
        game.removeIsland(currBoard);
        assertEquals(currBoard[5][5],null);
    }
}