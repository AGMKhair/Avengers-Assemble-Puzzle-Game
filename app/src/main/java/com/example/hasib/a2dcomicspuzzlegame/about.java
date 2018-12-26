package com.example.securesoftbd.avengersassemble;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.MyBounceInterpolator;
import com.example.hasib.a2dcomicspuzzlegame.R;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

import static com.example.securesoftbd.avengersassemble.Variable.*;


public class about extends Activity {
  //  Button back_about_button, go_to_game_button;


    @Override
    protected void onUserLeaveHint() {
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
        setContentView(R.layout.about);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stencilbt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CardView cardView=findViewById(R.id.cardView);


        final Animation myAnim = AnimationUtils.loadAnimation(about.this, R.anim.activity_animation);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(4, 4);

        myAnim.setInterpolator(interpolator);
        //overridePendingTransition(R.anim.enter, R.anim.exit);

        cardView.startAnimation(myAnim);


    /*    back_about_button = findViewById(R.id.back_about);
        go_to_game_button = findViewById(R.id.go_game_about);*/


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //this.finish();
      //  finish();
        //startActivity(new Intent(about.this,MainActivity.class));
        overridePendingTransition(R.anim.slide_out_right, R.anim.slide_in_left);
    }
}
