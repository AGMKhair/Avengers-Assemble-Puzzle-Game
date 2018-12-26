package com.example.hasib.a2dcomicspuzzlegame;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import com.example.hasib.a2dcomicspuzzlegame.PuzzeAnimation.AnimationPuzze;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

import static com.example.hasib.a2dcomicspuzzlegame.Variable.*;


public class SoundActivity extends Activity {

    private ImageView sound_setup;
    SharedPreferences.Editor e;
    // SharedPreferences sp;
   // SharedPreferences sp;
   // SharedPreferences.Editor ed;
   // MediaPlayer m  ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sound);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);


        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stencilbt.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );




        sound_setup = findViewById(R.id.music_setup_imageview);

        AnimationPuzze.downtoup();
        AnimationPuzze.lefttoright();
        AnimationPuzze.righttoleft();
        AnimationPuzze.uptodown();

        CardView card=findViewById(R.id.cardView);
       // sound_setup = (ImageView) findViewById(R.id.music_setup_imageview);

        card.setAnimation(AnimationPuzze.downtoupanimation);

        sound_setup.setAnimation(AnimationPuzze.lefttorightanimation);
        // mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.music);
        //  m = MediaPlayer.create(getApplicationContext(), R.raw.music);




        sound_setup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){

               // sound();
           //    sp = getSharedPreferences("Music",MODE_PRIVATE);

                if( sp.getBoolean("music",true)) {
                    musicOff();
                    sound_setup.setImageResource(R.drawable.nosound);
                  //  sound_setup.setImageDrawable(getResources().getDrawable(R.drawable.nosound));
                }
                else if( !sp.getBoolean("music",true))
                {
                    musicOn();
                    sound_setup.setImageResource(R.drawable.mutebutton);
                    //sound_setup.setImageDrawable(getResources().getDrawable(R.drawable.mutebutton));
                }

            }
        });
    }


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

    }

    @Override
    protected void attachBaseContext(Context newBase)
    {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
    @Override
    protected void onRestart() {
        super.onRestart();

        if(sp.getBoolean("music",true))
        {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
              Common.isPlaying=true;
        }

    }

    private void musicOff()
    {
        e = sp.edit();
        e.putBoolean("music", false);
        Common.isPlaying=false;
        e.commit();
        mediaPlayer.pause();
    }

    private void musicOn()
    {
        e = sp.edit();
        e.putBoolean("music",true);
        e.commit();
        Common.isPlaying=true;
        mediaPlayer.start();
        mediaPlayer.setLooping(true);
    }
    private void sound()
    {
        if( sp.getBoolean("music",true) )
        {
          //  sound_setup.setImageDrawable(getResources().getDrawable(R.drawable.mutebutton));
            sound_setup.setImageResource(R.drawable.mutebutton);
        }
        else if(!sp.getBoolean("music",true))
        {
            //sound_setup.setImageDrawable(getResources().getDrawable(R.drawable.nosound));
            sound_setup.setImageResource(R.drawable.nosound);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        sound();
    }


}
