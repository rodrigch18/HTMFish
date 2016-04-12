package edu.up.cs301.fish;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

import edu.up.cs301.animation.HexagonSurfaceView;
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

    private ArrayList<TextView> playerScoreTextView = new ArrayList<TextView>();
    private ArrayList<TextView> playerPlayerTextView = new ArrayList<TextView>();
    private TextView turnTextView;
    private GameMainActivity myActivity;
    HexagonSurfaceView boardView = null;
    Hex[][] humanBoard = new Hex[10][10];
    Paint p = new Paint();


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
        if (info instanceof FishGameState) {
            FishGameState newState = (FishGameState) info;
            boardView.setTheState(newState);
            boardView.setMyAct(myActivity);

            Log.i("Number of Players","" + newState.numOfPlayers);

            switch (newState.numOfPlayers){
                case 2:
                    playerScoreTextView.get(0).setText("" + newState.getPlayerScore(0));
                    playerScoreTextView.get(1).setText("" + newState.getPlayerScore(1));
                    playerScoreTextView.get(2).setText(" ");
                    playerScoreTextView.get(3).setText(" ");

                    playerPlayerTextView.get(0).setText("" + newState.playerName[0]);
                    playerPlayerTextView.get(1).setText("" + newState.playerName[1]);
                    playerPlayerTextView.get(2).setText(" ");
                    playerPlayerTextView.get(3).setText(" ");
                    break;
                case 3:
                    playerScoreTextView.get(0).setText("" + newState.getPlayerScore(0));
                    playerScoreTextView.get(1).setText("" + newState.getPlayerScore(1));
                    playerScoreTextView.get(2).setText("" + newState.getPlayerScore(2));
                    playerScoreTextView.get(3).setText(" ");

                    playerPlayerTextView.get(0).setText("" + newState.playerName[0]);
                    playerPlayerTextView.get(1).setText("" + newState.playerName[1]);
                    playerPlayerTextView.get(2).setText("" + newState.playerName[2]);
                    playerPlayerTextView.get(3).setText(" ");
                    break;
                case 4:
                    playerScoreTextView.get(0).setText("" + newState.getPlayerScore(0));
                    playerScoreTextView.get(1).setText("" + newState.getPlayerScore(1));
                    playerScoreTextView.get(2).setText("" + newState.getPlayerScore(2));
                    playerScoreTextView.get(3).setText("" + newState.getPlayerScore(3));

                    playerPlayerTextView.get(0).setText("" + newState.playerName[0]);
                    playerPlayerTextView.get(1).setText("" + newState.playerName[1]);
                    playerPlayerTextView.get(2).setText("" + newState.playerName[2]);
                    playerPlayerTextView.get(3).setText("" + newState.playerName[3]);
                    break;
            }

            this.boardView.invalidate();

        }
        else {
            flash(0xFFFF0000, 33);
        }
    }


    /**
     * Check where the player touches/taps
     *
     * @param event - touch/tap
     */
    public boolean onTouchEvent(MotionEvent event){
        int ea = event.getAction();
        //coordinates of tapped spot
        int X = (int) event.getX();
        int Y = (int) event.getY();
        return true;
    }

    /**
     * callback method--our game has been chosen/rechosen to be the GUI,
     * called from the GUI thread
     *
     * @param activity - activity under which we are running
     */
    public void setAsGui(GameMainActivity activity){
        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.htmfish_layout);

        boardView = (HexagonSurfaceView)activity.findViewById(R.id.viewBoard);

        //Initialize the widget reference member variables
        this.playerScoreTextView.add((TextView)activity.findViewById(R.id.viewScore1));
        this.playerScoreTextView.add((TextView)activity.findViewById(R.id.viewScore2));
        this.playerScoreTextView.add((TextView)activity.findViewById(R.id.viewScore3));
        this.playerScoreTextView.add((TextView)activity.findViewById(R.id.viewScore4));

        this.playerPlayerTextView.add((TextView)activity.findViewById(R.id.viewPlayer1));
        this.playerPlayerTextView.add((TextView)activity.findViewById(R.id.viewPlayer2));
        this.playerPlayerTextView.add((TextView)activity.findViewById(R.id.viewPlayer3));
        this.playerPlayerTextView.add((TextView)activity.findViewById(R.id.viewPlayer4));



    }

}
