package edu.up.cs301.fish;

import java.util.ArrayList;

import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.config.GameConfig;
import edu.up.cs301.game.config.GamePlayerType;

/**
 * Hey, That's My Fish MainActivity
 *
 * This class is the primary activity for
 * Hey That's My Fish.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/28/16
 */
public class FishMainActivity extends GameMainActivity {

    private int PORT_NUMBER = 5120;
    public GameConfig createDefaultConfig(){
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();

        // Pig has two player types:  human and computer
        playerTypes.add(new GamePlayerType("Local Human Player") {
            public GamePlayer createPlayer(String name) {
                return new FishHumanPlayer(name);
            }});

        playerTypes.add(new GamePlayerType("Dumb Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new FishDumbComputerPlayer(name);
            }});


        // Create a game configuration class for Pig:
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 4, "Hey, That's my Fish", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 1: a human player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);

        return defaultConfig;
    }

    /**
     * create a local game
     *
     * @return the local game, a Hey That's My Fish game
     *
     */
    @Override
    public LocalGame createLocalGame(){
        return new FishLocalGame(); //need to perform check later
    }

}
