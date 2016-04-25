package edu.up.cs301.fish;

import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;

import edu.up.cs301.animation.helpDialog;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.R;
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
 * @version 4/24/16
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

        playerTypes.add(new GamePlayerType("Smart Computer Player") {
            public GamePlayer createPlayer(String name) {
                return new FishSmartComputerPlayer(name);
            }});



        // Create a game configuration class for Pig:
        GameConfig defaultConfig = new GameConfig(playerTypes, 2, 4, "Hey, That's my Fish", PORT_NUMBER);
        defaultConfig.addPlayer("Human", 0); // player 1: a human player
        defaultConfig.addPlayer("Computer", 1); // player 1: a human player
        defaultConfig.setRemoteData("Remote Human Player", "", 0);


        return defaultConfig;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.game_main, menu);
        return true;
    }

    // Start your settings activity when a menu item is selected
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.menu_help) {
          //  Intent settingsIntent = new Intent(this, PrefsActivity.class);
          //  startActivity(settingsIntent);
            helpDialog cdd=new helpDialog(this);
            cdd.show();
        }
        return super.onOptionsItemSelected(item);
    }


    /**
     * create a local game
     *
     * @return the local game, a Hey That's My Fish game
     *
     */
    @Override
    public LocalGame createLocalGame(){
        helpDialog cdd=new helpDialog(this);
        cdd.show();
        return new FishLocalGame(); //need to perform check later
    }

}
