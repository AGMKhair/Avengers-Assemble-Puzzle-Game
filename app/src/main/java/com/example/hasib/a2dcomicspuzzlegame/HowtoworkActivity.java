package com.example.securesoftbd.avengersassemble;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import com.MyBounceInterpolator;
import com.example.hasib.a2dcomicspuzzlegame.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.example.securesoftbd.avengersassemble.Variable.*;


public class HowtoworkActivity extends AppCompatActivity {
   // Button back_play_button, go_play_to_game_button;



    @Override    protected void onUserLeaveHint() {
        super.onUserLeaveHint();

        if ( Music_On_Of == 1) { }
        else
        {
            if(mediaPlayer.isPlaying())
            {
                mediaPlayer.pause();


            }
        }

        Music_On_Of = 0;
     /*   if(sp.getBoolean("music",true))
        {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            //Common.isPlaying=true;
        }
        else if(sp.getBoolean("music",false))
        {
            mediaPlayer.pause();
        }
*/
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_howtowork);
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stencilbt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CardView cardView=findViewById(R.id.cardView);

       // ConstraintLayout layout=(ConstraintLayout) findViewById(R.id.ayout);


        final Animation myAnim = AnimationUtils.loadAnimation(HowtoworkActivity.this, R.anim.activity_animation);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(4, 4);

        myAnim.setInterpolator(interpolator);
        //overridePendingTransition(R.anim.enter, R.anim.exit);

        cardView.startAnimation(myAnim);

      //  layout.startAnimation(myAnim);



    }
}
