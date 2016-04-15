package edu.up.cs301.fish;

import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import java.io.Serializable;
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
public class FishHumanPlayer extends GameHumanPlayer implements View.OnTouchListener, Serializable {

    private ArrayList<TextView> playerScoreTextView = new ArrayList<TextView>();
    private ArrayList<TextView> playerPlayerTextView = new ArrayList<TextView>();
    TextView whosTurn;
    private TextView turnTextView;
    private GameMainActivity myActivity;
    FishGameState newState;
    HexagonSurfaceView boardView = null;
    Paint p = new Paint();
    int pengsOwned = 0;
    boolean firstTouch =false;
    boolean onStart = true;
//    int i;
//    int j;
    int k;

    /**
     * Fish Human Player constructor
     *
     * @param name - player name
     */
    public FishHumanPlayer(String name){
        super(name);
    }

//    private void setI(int i){
//        this.i =i;
//    }
//
//    private int getI(){
//        return i;
//    }
//
//    private void setJ(int k){
//        this.j =j;
//    }
//
//    private int getJ(){
//        return j;
//    }

    private void setK(int k){
        this.k =k;
    }

    private int getK(){
        return this.k;
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
       // Log.i("HUMAN Recieving", " Info");
        if (info instanceof FishGameState) {
         //   Log.i("HUMAN Recieving", " FishGameState");
            newState = (FishGameState) info;



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



            boardView.setTheState(newState);
            boardView.setMyAct(myActivity);
            whosTurn.setText("Player: "+allPlayerNames[newState.getId()]+"'s Turn");

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

            int xTouch = (int) event.getX();
            int yTouch = (int) event.getY();

           // Log.i("IS TOUCHED", xTouch + " " + yTouch);

           // Log.i("peng", "" + pengsOwned);

            //coordinates of tapped spot
            outerloop:
            for (int i = 0; i < 10; i++) {
                for (int j = 0; j < 10; j++) {
                    if (newState.board[i][j] != null) {
                        if ((!newState.board[i][j].getOccupied()) && (newState.board[i][j].getTileVal() == 1)) {
                            if(onStart) {
                                if ((xTouch - (newState.board[i][j].getX() + 65)) *
                                        (xTouch - (newState.board[i][j].getX() + 65)) +
                                        (yTouch - (newState.board[i][j].getY() + 65)) *
                                                (yTouch - (newState.board[i][j].getY() + 65)) <= 65 * 65) {

//
                                    FishSetPenguinAction setPenguinAction = new FishSetPenguinAction(this,
                                            newState.getPeng(this.playerNum, pengsOwned),newState.board[i][j].getX(), newState.board[i][j].getY(), this.playerNum);
                                    pengsOwned++;
                                    //Log.i("TOUCH", humanBoard[i][j].x + " " + humanBoard[i][j].y);

                                    newState.board[i][j].setOccupied(true);
                                    game.sendAction(setPenguinAction);

                                    if (newState.numOfPlayers == 2 && pengsOwned == 4) {
                                        onStart =false;
                                        break outerloop;
                                    } else if (newState.numOfPlayers == 3 && pengsOwned == 3) {
                                        onStart =false;
                                        break outerloop;
                                    } else if (newState.numOfPlayers == 4 && pengsOwned == 2) {
                                        onStart =false;
                                        break outerloop;
                                    }

                                }
                            }
                        }
                    }
                }
            }

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
                                        //if (newState.pengA[this.playerNum][k].getCurrPosX() == newState.board[i][j].getX()
                                                //&& newState.pengA[this.playerNum][k].getCurrPosY() == newState.board[i][j].getY()) {
                                            //this.i = i;
                                            //this.j = j;
                                            this.k = k;
                                            firstTouch = true;
                                           // Log.i("Penguin Selected", "stuff");
                                            return true;
                                       // }
                                    }
                                }
                            }
                        }
                        else if (firstTouch) {
                            if (!(newState.board[i][j].getOccupied())) {
                                if ((xTouch - (newState.board[i][j].getX() + 65)) *
                                        (xTouch - (newState.board[i][j].getX() + 65)) +
                                        (yTouch - (newState.board[i][j].getY() + 65)) *
                                                (yTouch - (newState.board[i][j].getY() + 65)) <= 65 * 65) {


                                    firstTouch = false;


                                    FishMovePenguinAction movePenguinAction = new
                                            FishMovePenguinAction(this, newState.getPeng(this.playerNum, k),
                                            newState.board[i][j].getX(), newState.board[i][j].getY(), this.k);
                                    newState.board[i][j].setOccupied(true);
                                  //  Log.i("SECOND TOUCH", "stuff");
                                  //  Log.i("SECOND TOUCH", "Set Happens");

                                   // Log.i(" SECOND TOUCH", "" + newState.board[i][j].toString());

                                    game.sendAction(movePenguinAction);
                                    //boardView.invalidate();
                                    return true;
                                }
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

        this.whosTurn = (TextView)activity.findViewById(R.id.whoseTurn);

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
