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
public class FishHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {

    private ArrayList<TextView> playerScoreTextView = new ArrayList<TextView>();
    private ArrayList<TextView> playerPlayerTextView = new ArrayList<TextView>();
    private TextView turnTextView;
    private GameMainActivity myActivity;
    FishGameState newState;
    HexagonSurfaceView boardView = null;
    Hex[][] humanBoard;
    Paint p = new Paint();
    int pengsOwned = 0;
    boolean firstTouch;

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
        return myActivity.findViewById(R.id.viewBoard);
    }

    /**
     * callback method -- game's state has changed
     *
     * @param info - info from the game
     */
    public void receiveInfo(GameInfo info){
        if (info instanceof FishGameState) {
            newState = (FishGameState) info;
            humanBoard = new Hex[10][10];

            if(newState.board != null) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if(newState.board[i][j] != null) {
                            humanBoard[i][j] = newState.board[i][j];
                        }
                        else{
                            humanBoard[i][j] = null;
                        }
                    }
                }
            }

            boardView.setTheState(newState);
            boardView.setMyAct(myActivity);

            boardView.setOnTouchListener(this);

            switch (newState.numOfPlayers){
                case 2:

                    for(int i=0; i<2; i++){
                        playerScoreTextView.get(i).setText("" + newState.getPlayerScore(i));
                        playerPlayerTextView.get(i).setText("" + newState.playerName[i]);
                    }
                    playerScoreTextView.get(2).setText(" ");
                    playerScoreTextView.get(3).setText(" ");

                    playerPlayerTextView.get(2).setText(" ");
                    playerPlayerTextView.get(3).setText(" ");
                    break;

                case 3:

                    for(int i=0; i<3; i++){
                        playerScoreTextView.get(i).setText("" + newState.getPlayerScore(i));
                        playerPlayerTextView.get(i).setText("" + newState.playerName[i]);
                    }

                    playerScoreTextView.get(3).setText(" ");
                    playerPlayerTextView.get(3).setText(" ");

                    break;

                case 4:

                    for(int i=0; i<4; i++){
                        playerScoreTextView.get(i).setText("" + newState.getPlayerScore(i));
                        playerPlayerTextView.get(i).setText("" + newState.playerName[i]);
                    }

                    break;
            }

            this.boardView.invalidate();

        }
        else {
            flash(-65636, 33);
        }
    }


    /**
     * Check where the player touches/taps
     *
     * @param event - touch/tap
     */
    public boolean onTouch(View v, MotionEvent event){

        if(event.getAction() == MotionEvent.ACTION_DOWN) {

            if(newState.numOfPlayers == 2 && pengsOwned == 4){
                return false;
            } else if(newState.numOfPlayers == 3 && pengsOwned == 3){
                return false;
            } else if(newState.numOfPlayers == 4 && pengsOwned == 2){
                return false;
            }

            int xTouch = (int) event.getX();
            int yTouch = (int) event.getY();

            Log.i("IS TOUCHED", xTouch + " " + yTouch);

            Log.i("peng", "" + pengsOwned);

            //coordinates of tapped spot
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (humanBoard[i][j] != null) {
                        if (humanBoard[i][j].getOccupied() == false) {
                            if ((xTouch - (humanBoard[i][j].x + 65)) *
                                    (xTouch - (humanBoard[i][j].x + 65)) +
                                    (yTouch - (humanBoard[i][j].y + 65)) *
                                            (yTouch - (humanBoard[i][j].y + 65)) <= 65 * 65) {
                                newState.setPeng(newState.getPeng(this.playerNum, pengsOwned), humanBoard[i][j].x, humanBoard[i][j].y, this.playerNum);
                                FishSetPenguinAction setPenguinAction = new FishSetPenguinAction(this,
                                        newState.getPeng(this.playerNum, pengsOwned));
                                pengsOwned++;
                                Log.i("TOUCH", humanBoard[i][j].x + " " + humanBoard[i][j].y);

                                humanBoard[i][j].occupied = true;
                                game.sendAction(setPenguinAction);
                            }
                        }
                        //to select peng
                        else {
                            if (firstTouch == false) {
                                if ((xTouch - (humanBoard[i][j].x + 65)) *
                                        (xTouch - (humanBoard[i][j].x + 65)) +
                                        (yTouch - (humanBoard[i][j].y + 65)) *
                                                (yTouch - (humanBoard[i][j].y + 65)) <= 65 * 65) {
                                    
                                    for (int p = 0; p < newState.pengA[this.playerNum].length; p++){
                                        if (newState.pengA[this.playerNum][p].getCurrPosX() == humanBoard[i][j].x
                                                && newState.pengA[this.playerNum][p].getCurrPosY() == humanBoard[i][j].y) {
                                            firstTouch = true;
                                            return true;
                                        }
                                    }
                                }
                                    newState.movePeng(this.playerNum, newState.pengA[this.playerNum][p], );
                                    //

                            }

                        }
                    }
                }
            }

            boardView.invalidate();

            //FishGameState gameState = new FishGameState();

//        if(newState.onStart = true){
//            newState.setPeng(new Penguin(X,Y),X,Y);
//        }


            return true;
        }
        return false;
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
