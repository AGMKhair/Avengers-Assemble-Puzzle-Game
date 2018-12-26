package com.example.hasib.a2dcomicspuzzlegame;

import android.content.SharedPreferences;
import android.media.MediaPlayer;

public class Variable {
    static SharedPreferences sp;
    static MediaPlayer mediaPlayer;
    static int Music_On_Of;

    static void chackHome(int i)
    {
        Music_On_Of = i;
    }

}
