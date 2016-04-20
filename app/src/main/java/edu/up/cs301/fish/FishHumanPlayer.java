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
 * <p/>
 * This class is a GUI for a human to
 * play Hey That's My Fish.
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 4/20/16
 */
public class FishHumanPlayer extends GameHumanPlayer implements View.OnTouchListener {

    private ArrayList<TextView> playerScoreTextView = new ArrayList<TextView>();
    private ArrayList<TextView> playerPlayerTextView = new ArrayList<TextView>();
    TextView whosTurn;
    private TextView turnTextView;
    private GameMainActivity myActivity;
    FishGameState newState;
    HexagonSurfaceView boardView = null;
    Paint p = new Paint();
    int pengsOwned = 0;
    boolean firstTouch = false;
    boolean onStart = true;
    boolean firstFalse = false;
    int k;
    ArrayList<Hex> listOfLegalMoves = new ArrayList<Hex>();

    /**
     * Fish Human Player constructor
     *
     * @param name - player name
     */
    public FishHumanPlayer(String name) {
        super(name);
    }

    private void setK(int k) {
        this.k = k;
    }

    private int getK() {
        return k;
    }

    /**
     * Returns the GUI's top view object
     */
    public View getTopView() {
        return myActivity.findViewById(R.id.viewBoard);
    }

    /**
     * callback method -- game's state has changed
     *
     * @param info - info from the game
     */
    public void receiveInfo(GameInfo info) {
        if (info instanceof FishGameState) {
            newState = (FishGameState) info;

            int deadPengs= 0;
            newState = (FishGameState) info;
            for(int p=0; p<newState.numPenguin; p++) {
                if(newState.pengA[this.playerNum][p].getIsDead())
                {
                    deadPengs++;
                }
            }
            if(deadPengs == newState.numPenguin){
                FishPassAction passAction = new FishPassAction(this);
                game.sendAction(passAction);
                return;
            }



            boardView.setOnTouchListener(this);

            switch (newState.numOfPlayers) {
                case 2:

                    for (int i = 0; i < 2; i++) {
                        playerScoreTextView.get(i).setText("" + newState.getPlayerScore(i));
                        playerPlayerTextView.get(i).setText("" + newState.playerName[i]);
                    }
                    playerScoreTextView.get(2).setText(" ");
                    playerScoreTextView.get(3).setText(" ");

                    playerPlayerTextView.get(2).setText(" ");
                    playerPlayerTextView.get(3).setText(" ");
                    break;

                case 3:

                    for (int i = 0; i < 3; i++) {
                        playerScoreTextView.get(i).setText("" + newState.getPlayerScore(i));
                        playerPlayerTextView.get(i).setText("" + newState.playerName[i]);
                    }

                    playerScoreTextView.get(3).setText(" ");
                    playerPlayerTextView.get(3).setText(" ");

                    break;

                case 4:

                    for (int i = 0; i < 4; i++) {
                        playerScoreTextView.get(i).setText("" + newState.getPlayerScore(i));
                        playerPlayerTextView.get(i).setText("" + newState.playerName[i]);
                    }

                    break;
            }


            boardView.setTheState(newState);
            boardView.setMyAct(myActivity);
            whosTurn.setText("Player: " + allPlayerNames[newState.getId()] + "'s Turn");

            this.boardView.invalidate();

        } else {
            flash(-65636, 33);
        }
    }


    /**
     * Check where the player touches/taps
     *
     * @param event - touch/tap
     */
    public boolean onTouch(View v, MotionEvent event) {

        if (event.getAction() == MotionEvent.ACTION_DOWN) {

            int xTouch = (int) event.getX();
            int yTouch = (int) event.getY();

            //coordinates of tapped spot
            outerloop:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (newState.board[i][j] != null) {
                        if ((!newState.board[i][j].getOccupied()) && (newState.board[i][j].getTileVal() == 1)) {
                            if (onStart) {
                                if ((xTouch - (newState.board[i][j].getX() + 65)) *
                                        (xTouch - (newState.board[i][j].getX() + 65)) +
                                        (yTouch - (newState.board[i][j].getY() + 65)) *
                                                (yTouch - (newState.board[i][j].getY() + 65)) <= 65 * 65) {


                                    FishSetPenguinAction setPenguinAction = new FishSetPenguinAction(this,
                                            newState.getPeng(this.playerNum, pengsOwned), newState.board[i][j].getX(), newState.board[i][j].getY(), this.playerNum, pengsOwned);
                                    pengsOwned++;

                                    newState.board[i][j].setOccupied(true);
                                    game.sendAction(setPenguinAction);

                                    if (newState.numOfPlayers == 2 && pengsOwned == 4) {
                                        onStart = false;
                                        firstFalse = true;
                                        break outerloop;
                                    } else if (newState.numOfPlayers == 3 && pengsOwned == 3) {
                                        onStart = false;
                                        firstFalse = true;
                                        break outerloop;
                                    } else if (newState.numOfPlayers == 4 && pengsOwned == 2) {
                                        onStart = false;
                                        firstFalse = true;
                                        break outerloop;
                                    }

                                }
                            }
                        }
                    }
                }
            }
            if(!firstFalse) {
                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 10; j++) {
                        if (newState.board[i][j] != null && !onStart) {

                            if (!firstTouch) {

                                if (newState.board[i][j].getOccupied()) {

                                    if ((xTouch - (newState.board[i][j].getX() + 65)) *
                                            (xTouch - (newState.board[i][j].getX() + 65)) +
                                            (yTouch - (newState.board[i][j].getY() + 65)) *
                                                    (yTouch - (newState.board[i][j].getY() + 65)) <= 65 * 65) {
                                        for (int k = 0; k < newState.pengA[this.playerNum].length; k++) {
                                            if (newState.pengA[this.playerNum][k].getCurrPosX() == newState.board[i][j].getX()
                                                    && newState.pengA[this.playerNum][k].getCurrPosY() == newState.board[i][j].getY()
                                                    && !newState.pengA[this.playerNum][k].getIsDead()) {

                                                this.k = k;
                                                firstTouch = true;
                                                return true;
                                            }
                                        }
                                    }
                                }
                            } else if (firstTouch) {
                                if (!(newState.board[i][j].getOccupied())) {
                                    if ((xTouch - (newState.board[i][j].getX() + 65)) *
                                            (xTouch - (newState.board[i][j].getX() + 65)) +
                                            (yTouch - (newState.board[i][j].getY() + 65)) *
                                                    (yTouch - (newState.board[i][j].getY() + 65)) <= 65 * 65) {
                                        int aX = 0;
                                        int aY = 0;
                                        for (int x = 0; x < 10; x++) {
                                            for (int y = 0; y < 10; y++) {
                                                if (newState.board[x][y] != null) {
                                                    if (newState.getPeng(this.playerNum, k).getCurrPosX() == newState.board[x][y].getX()
                                                            && newState.getPeng(this.playerNum, k).getCurrPosY() == newState.board[x][y].getY()) {

                                                        aX = x;
                                                        aY = y;

                                                    }
                                                }
                                            }
                                        }

                                        ArrayList<Hex> listOfLegalMoves = newState.checkIsLegalMove(aX, aY);

                                        if (listOfLegalMoves.contains(newState.board[i][j])) {

                                            firstTouch = false;


                                            FishMovePenguinAction movePenguinAction = new
                                                    FishMovePenguinAction(this, newState.getPeng(this.playerNum, k),
                                                    newState.board[i][j].getX(), newState.board[i][j].getY(), this.k, this.playerNum);

                                            newState.board[i][j].setOccupied(true);

                                            game.sendAction(movePenguinAction);
                                            return true;
                                        }
                                    }
                                }
                            }

                        }
                    }
                }
            }
            else{
                firstFalse = false;
            }
            boardView.invalidate();


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
    public void setAsGui(GameMainActivity activity) {
        // remember the activity
        myActivity = activity;

        // Load the layout resource for our GUI
        activity.setContentView(R.layout.htmfish_layout);

        boardView = (HexagonSurfaceView) activity.findViewById(R.id.viewBoard);

        this.whosTurn = (TextView) activity.findViewById(R.id.whoseTurn);

        //Initialize the widget reference member variables
        this.playerScoreTextView.add((TextView) activity.findViewById(R.id.viewScore1));
        this.playerScoreTextView.add((TextView) activity.findViewById(R.id.viewScore2));
        this.playerScoreTextView.add((TextView) activity.findViewById(R.id.viewScore3));
        this.playerScoreTextView.add((TextView) activity.findViewById(R.id.viewScore4));

        this.playerPlayerTextView.add((TextView) activity.findViewById(R.id.viewPlayer1));
        this.playerPlayerTextView.add((TextView) activity.findViewById(R.id.viewPlayer2));
        this.playerPlayerTextView.add((TextView) activity.findViewById(R.id.viewPlayer3));
        this.playerPlayerTextView.add((TextView) activity.findViewById(R.id.viewPlayer4));


    }

}
