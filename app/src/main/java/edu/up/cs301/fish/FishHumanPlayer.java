package edu.up.cs301.fish;

import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * Hey, That's My Fish HumanPlayer
 *
 * This class is a GUI for a human to
 * play Hey That's My Fish.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 3/28/16
 */
public class FishHumanPlayer extends GameHumanPlayer{

    private ArrayList<TextView> playerScoreTextView;
    private TextView turnTextView;
    private GameMainActivity myActivity;

    /**
     * Fish Human Player constructor
     *
     * @param name - player name
     */
    public FishHumanPlayer(String name){
        super(name);
    }

    /**
     * Returns the GUI's top view object
     */
    public View getTopView(){
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    /**
     * callback method -- game's state has changed
     *
     * @param info - info from the game
     */
    public void receiveInfo(GameInfo info){

    }

    /**
     * Check where the player touches/taps
     *
     * @param event - touch/tap
     */
    public boolean onTouchEvent(MotionEvent event){
        return true;
    }

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity - activity under which we are running
     */
    public void setAsGui(GameMainActivity activity){

    }

}
