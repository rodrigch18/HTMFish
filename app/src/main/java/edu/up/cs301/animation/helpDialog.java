package edu.up.cs301.animation;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import edu.up.cs301.game.R;

/**
 * External Citation
 * Date: 24 April 2016
 * Needed help to create dialog box for rules.
 *
 * Resource:
 * http://stackoverflow.com/questions/13341560/how-to-create-a-custom-dialog-box-in-android
 */

/**
 * Hey, That's My Fish helpDialog
 *
 * creates a custom dialog box for the rules of the game
 *
 * @author Giselle Marston
 * @author Christian Rodriguez
 * @author Elias Paraiso
 * @author Elijah Fisher
 * @version 4/24/16
 */
public class helpDialog extends Dialog implements android.view.View.OnClickListener{

        public Activity c;
        public Dialog d;
        public Button ok;

        public helpDialog(Activity a) {
            super(a);
            this.c = a;
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            requestWindowFeature(Window.FEATURE_NO_TITLE);
            setContentView(R.layout.help);
            ok = (Button) findViewById(R.id.btn_ok);
            ok.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_ok:
                    dismiss();
                    break;
                default:
                    break;
            }
            dismiss();
        }
}
