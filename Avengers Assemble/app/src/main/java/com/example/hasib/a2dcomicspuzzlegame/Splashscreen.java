package com.example.hasib.a2dcomicspuzzlegame;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.transition.Fade;
import android.util.Pair;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import static com.example.hasib.a2dcomicspuzzlegame.Variable.*;
//import static com.example.hasib.a2dcomicspuzzlegame.Variable.sp;


public class Splashscreen extends AppCompatActivity {



   // int r=0;

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.slash_activity);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        final ImageView image=findViewById(R.id.slash_screen1);


        mediaPlayer = MediaPlayer.create(getApplicationContext(), R.raw.background_song);
        sp = getSharedPreferences("Music",MODE_PRIVATE);
        if(sp.getBoolean("music",true))
        {
            mediaPlayer.start();
            mediaPlayer.setLooping(true);
            //Common.isPlaying=true;
        }
        else if(sp.getBoolean("music",false))
        {
            mediaPlayer.pause();
        }
        new Handler().postDelayed(new Runnable() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void run() {

                Intent intent = new Intent(Splashscreen.this, MainActivity.class);
                startActivity(intent);
                finish();

            }



        },1500);

    }

}
