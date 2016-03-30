package edu.up.cs301.fish;

import java.util.ArrayList;

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
public class FishMainActivity {

    private int PORT_NUMBER = 512018;
    public GameConfig createDefaultConfig(){
        ArrayList<GamePlayerType> playerTypes = new ArrayList<GamePlayerType>();
        GameConfig defaultConfig = new GameConfig(playerTypes, 1, 2, "Pig", PORT_NUMBER);
        return defaultConfig;
    }

    /**
     * create a local game
     *
     * @return the local game, a Hey That's My Fish game
     *
     */
    public LocalGame createLocalGame(){
        return new FishLocalGame();
    }

}
