package edu.up.cs301.fish;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.view.MotionEvent;
import android.view.View;
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
    private TextView turnTextView;
    private GameMainActivity myActivity;
    HexagonSurfaceView boardView = null;
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



            for(int i=0; i<newState.player.length; i++) {
                playerScoreTextView.get(i).setText("" + newState.getPlayerScore(i));
            }

            this.boardView.invalidate();

        }
        else {
            flash(0xFFFF0000, 33);
        }
    }

//    public void makeBoard(Hex[][] aBoard, Canvas canvas, GameMainActivity activity){
//
//        for(int i = 0; i < 10; i++) {
//            for(int j = 0; j < 10; j++) {
//
//                Bitmap myBitmap = BitmapFactory.decodeResource(myActivity.getResources(), R.mipmap.one_fish);
//                Bitmap resizedBitmap;
//
//                switch (aBoard[i][j].getTileVal()) {
//                    case 1:
//                        myBitmap = BitmapFactory.decodeResource(myActivity.getResources(), R.mipmap.one_fish); //decode bitmap in constructor
//                        break;
//                    case 2:
//                        myBitmap = BitmapFactory.decodeResource(myActivity.getResources(), R.mipmap.two_fish); //decode bitmap in constructor
//                        break;
//                    case 3:
//                        myBitmap = BitmapFactory.decodeResource(myActivity.getResources(), R.mipmap.three_fish); //decode bitmap in constructor
//                        break;
//                }
//
//                resizedBitmap = Bitmap.createScaledBitmap(myBitmap, 400, 400, false);
//
//                drawHex(aBoard[i][j], canvas, resizedBitmap);
//            }
//        }
//    }
//
//    public void drawHex(Hex aHex, Canvas canvas, Bitmap resizedBitmap){
//
//        canvas.drawBitmap(resizedBitmap,aHex.x-125,aHex.y-125,p);
//    }

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



    }

}
