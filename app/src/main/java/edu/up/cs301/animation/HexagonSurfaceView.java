package edu.up.cs301.animation;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;

import edu.up.cs301.fish.FishGameState;
import edu.up.cs301.fish.FishMainActivity;
import edu.up.cs301.fish.Hex;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;

/**
 * Created by rodrigch18 on 3/31/2016.
 */
public class HexagonSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

    FishGameState theState = null;
    GameMainActivity myAct = null;

    Paint paint = new Paint();



    /**
     * Constructor of Hexagon Game Board SurfaceView
     *
     * @param context implied context that the surface view is drawn on
     */
    public HexagonSurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setFocusable(true);
        setWillNotDraw(false);
    }

    /**
     * Constructor of Hexagon Game Board SurfaceView
     *
     * @param context implied context that the surface view is drawn on
     * @param attrs attributes of possible construction
     */
    public HexagonSurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setFocusable(true);
        setWillNotDraw(false);
    }

    /**
     *  Constructor of Hexagon Game Board SurfaceView
     *
     * @param context implied context that the surface view is drawn on
     * @param attrs  attributes of possible construction
     * @param defStyleAttr style definition of attributes of possible construction
     */
    public HexagonSurfaceView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        setFocusable(true);
        setWillNotDraw(false);
    }

    public void setTheState(FishGameState newState) { this.theState = newState; }

    public void setMyAct(GameMainActivity newAct){this.myAct = newAct; }

    /**
     * Surface Holder Changes
     *
     * Change in surface dimensions caught by holder
     *
     * @param holder holder of canvas
     * @param format format of canvas
     * @param width width of canvas
     * @param height height of canvas
     */

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // TODO Auto-generated method stub
    }

    /**
     * Surface Holder Creates
     *
     * Creation of a new surface called by the surface holder
     *
     * @param holder holder of canvas
     */

    public void surfaceCreated(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    /**
     * Surface Holder Destroys
     *
     * Destruction of the already held surface by the holder
     *
     * @param holder holder of canvas
     */

    public void surfaceDestroyed(SurfaceHolder holder) {
        // TODO Auto-generated method stub
    }

    /**
     * onDraw of Hexagon Board
     *
     * draws the hexagon board and the circle objects
     *
     * @param canvas the parameter of the canvas created at app run
     */
    @Override
    public void onDraw(Canvas canvas) {

        Hex[][] hexB = new Hex[10][10];

        if (theState == null) {
            // TODO
            return;
        }
        if (myAct == null) {
            // TODO
            return;
        }
        if (theState.board == null) {
            // TODO
            return;
        }

        for(int i = 0; i < 10; i++){
            for(int j = 0; j < 10; j++){
                if(theState.board[i][j] != null){
                    hexB[i][j] = theState.board[i][j];
                }
                else{
                    hexB[i][j] = null;
                }

            }
        }


        makeBoard(hexB,canvas);

    }

    public void makeBoard(Hex[][] aBoard, Canvas canvas){

        for(int i = 0; i < 10; i++) {
            for(int j = 0; j < 10; j++) {

                if(aBoard[i][j] != null) {

                    BitmapFactory.Options options = new BitmapFactory.Options();
                    options.inScaled = false;

                    Bitmap myBitmap = BitmapFactory.decodeResource(myAct.getResources(), R.drawable.one_fish, options);
                    Bitmap resizedBitmap;


                    switch (aBoard[i][j].getTileVal()) {
                        case 1:
                            myBitmap = BitmapFactory.decodeResource(myAct.getResources(), R.drawable.one_fish, options); //decode bitmap in constructor
                            break;
                        case 2:
                            myBitmap = BitmapFactory.decodeResource(myAct.getResources(), R.drawable.two_fish, options); //decode bitmap in constructor
                            break;
                        case 3:
                            myBitmap = BitmapFactory.decodeResource(myAct.getResources(), R.drawable.three_fish, options); //decode bitmap in constructor
                            break;
                    }

                    resizedBitmap = Bitmap.createScaledBitmap(myBitmap, 400, 400, true);

                    canvas.drawBitmap(resizedBitmap, aBoard[i][j].x - 125, aBoard[i][j].y - 125, paint);
                }
                else {
                    aBoard[i][j] = null;
                }
            }
        }
    }


}

