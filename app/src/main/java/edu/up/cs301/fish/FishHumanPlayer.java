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
 * Created by gisellemarston on 3/28/16.
 */
public class FishHumanPlayer extends GameHumanPlayer{

    private ArrayList<TextView> playerScoreTextView;
    private TextView turnTextView;
    private GameMainActivity myActivity;

    public FishHumanPlayer(String name){
        super(name);
    }
    public View getTopView(){
        return myActivity.findViewById(R.id.top_gui_layout);
    }
    public void receiveInfo(GameInfo info){

    }
    public boolean onTouchEvent(MotionEvent event ){
        return true;
    }
    public void setAsGui(GameMainActivity activity){

    }

}
