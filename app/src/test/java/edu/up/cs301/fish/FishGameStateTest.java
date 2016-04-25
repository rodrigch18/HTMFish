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
    public void testGetPenguin() throws Exception {
        String[] names = {"me", "you"};
        FishGameState game = new FishGameState(2,names);


        assertEquals(game.getPeng(0,1) , game.pengA[0][1]);
    }

//    tests if the two tiles are adjacent
    @Test
    public void testFindAdjInDir() throws Exception {
        String[] names = {"me", "you"};
        FishGameState game = new FishGameState(2, names);

        ArrayList<Hex> left = new ArrayList<Hex>();
        ArrayList<Hex> right = new ArrayList<Hex>();
        left = game.findAdjInDir(1, 2, 1,0 , left);
        right = game.findAdjInDir(2, 2, 1,0 , right);

        assertEquals(left.size()-1, right.size());
    }

    //tests if the move is legal
    @Test
    public void testCheckIsLegalMove() throws Exception {

        String[] names = {"me", "you"};
        FishGameState game = new FishGameState(2, names);


        assertEquals(game.checkIsLegalMove(2,2).size(), 17);
    }

    //tests if the penguin is moved to the tapped position
    @Test
    public void testMovePeng() throws Exception {

        String[] names = {"me", "you"};
        FishGameState game = new FishGameState(2, names);

        game.movePeng(0,game.pengA[0][0],1,1,0,0);
        assertEquals(game.pengA[0][0].getCurrPosX(),1);
        assertEquals(game.pengA[0][0].getCurrPosY(),1);

    }

}