package com.example.hasib.a2dcomicspuzzlegame;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.example.securesoftbd.avengersassemble.Adepter.ScoreAdepter;
import com.example.securesoftbd.avengersassemble.DatabaseBoos.GameDatabase;
import com.example.securesoftbd.avengersassemble.Model.Score;
import com.example.securesoftbd.avengersassemble.PuzzeAnimation.AnimationPuzze;

import java.util.ArrayList;
import java.util.List;

import uk.co.chrisjenx.calligraphy.CalligraphyConfig;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;
import static com.example.securesoftbd.avengersassemble.Variable.*;


public class ScoreActivity extends AppCompatActivity {
    GameDatabase gameDatabase;

    public  String gameName;

    String[] modeArray={"Easy","Medium","Hard"};

    List<Score> scores_list=new ArrayList<>();
    // List<ScoreMedium> score_list_medium=new ArrayList<>();
    // List<ScoreHard>score_list_hard=new ArrayList<>();

    TextView EasyName,EasyImageName,EasyTime,MediumName,MediumImageName,MediumTime,HardName,HardImageName,HardTime;

    ImageView EasyImage,MediumImage,HardImage;

    LinearLayout easy_layout;
    LinearLayout medium_layout;
    LinearLayout hard_layout;



    List<String> bestEasy=new ArrayList<>();
    List<String> bestMedium=new ArrayList<>();
    List<String> bestHard=new ArrayList<>();

    String imageNmae;
    String time;
    String mode;
    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(com.example.hasib.avengersassemble.R.layout.result_activity);
        gameDatabase=new GameDatabase(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/stencilbt.ttf")
                .setFontAttrId(com.example.hasib.avengersassemble.R.attr.fontPath)
                .build()
        );
        AnimationPuzze.uptodown();
        AnimationPuzze.righttoleft();
        AnimationPuzze.lefttoright();
        AnimationPuzze.downtoup();


        CardView layout1=findViewById(com.example.hasib.avengersassemble.R.id.card1);
        CardView layout2=findViewById(com.example.hasib.avengersassemble.R.id.card2);
        CardView layout3=findViewById(com.example.hasib.avengersassemble.R.id.card3);


        // setUpEasyData();
        getbesteasy();
        initializeValue();
        setUpView();
        layout_click_listener();

        easy_layout.setAnimation(AnimationPuzze.lefttorightanimation);
       medium_layout.setAnimation(AnimationPuzze.righttoleft);
       hard_layout.setAnimation(AnimationPuzze.downtoupanimation);


        /// Toast.makeText(getApplicationContext(),,Toast.LENGTH_SHORT).show();
    }

    private void layout_click_listener() {
        easy_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allscoreDatadialog("Easy");
            }
        });
        medium_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allscoreDatadialog("Medium");
            }
        });
        hard_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                allscoreDatadialog("Hard");
            }
        });
    }

    private void allscoreDatadialog(String mode) {

        scores_list.clear();
        Cursor cursor=gameDatabase.retiveAllData(mode);
        if (cursor!=null){
            while (cursor.moveToNext()){
                String modename=cursor.getString(cursor.getColumnIndex(GameDatabase.MODE));
                String name=cursor.getString(cursor.getColumnIndex(GameDatabase.NAME));
                String time=cursor.getString(cursor.getColumnIndex(GameDatabase.TIME));
                String imgname=cursor.getString(cursor.getColumnIndex(GameDatabase.IMAGENAME));

               // Toast.makeText(getApplicationContext(),"Image Name=  " +  imgname,Toast.LENGTH_SHORT).show();
                int image=imageresource(imgname);

                Score score=new Score(modename,gameName,name,time,image);
                gameName=null;

                scores_list.add(score);


            }

            showDialog();
        }


    }

    private void showDialog() {
        @SuppressLint("ResourceType") AlertDialog.Builder alrt=new AlertDialog.Builder(this, com.example.hasib.avengersassemble.R.style.MyDialogThem);
        View v= LayoutInflater.from(this).inflate(com.example.hasib.avengersassemble.R.layout.score_dialog,null);
        alrt.setView(v);


        RecyclerView recyclerView=v.findViewById(com.example.hasib.avengersassemble.R.id.score_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ScoreAdepter scoreAdepter=new ScoreAdepter(scores_list,this);
        recyclerView.setAdapter(scoreAdepter);
        AlertDialog dialog=alrt.create();




        alrt.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                dialogInterface.dismiss();
            }
        });


        alrt.show();


    }


    private void setUpView() {

        if (!bestEasy.isEmpty()) {

            easy_layout.setVisibility(View.VISIBLE);

            EasyName.setText(bestEasy.get(0));
            EasyTime.setText(bestEasy.get(1).toString()+"s");
            String imageName = bestEasy.get(2);
            if (imageName.equals("halk")){
                gameName="Halk";
                EasyImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.halk);
            }else if (imageName.equals("hawkey")){
                gameName="Hawkey";
                EasyImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.hawkeye);
            }else if (imageName.equals("captainAmrica")){
                gameName="CaptainAmrica";
                EasyImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.captainamrica);
            }else if (imageName.equals("IronMan")){
                gameName="IronMan";
                EasyImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.ironman);

            }else if (imageName.equals("thor")){
                gameName="Thor";
                EasyImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.thor);

            }else if (imageName.equals("blackWidow")){
                gameName="Black Widow";
                EasyImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.black_widow);

            }

            EasyImageName.setText(gameName);
            gameName=null;

        }else {
            easy_layout.setVisibility(View.GONE);
        }

        if (bestMedium.size()!=0) {

            medium_layout.setVisibility(View.VISIBLE);

            MediumName.setText(bestMedium.get(0));
            MediumTime.setText(bestMedium.get(1));


            String imageName=bestMedium.get(2);
            if (imageName.equals("antMan")){
                gameName="AntMan";
                MediumImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.antman);
            }else if (imageName.equals("doctoreStrane")){
                gameName="DoctoreStrane";
                MediumImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.doctorstrange);
            }else if (imageName.equals("falon")){
                gameName="Falon";
                MediumImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.falson);
            }else if (imageName.equals("vison")){
                gameName="Vison";
                MediumImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.vison);

            }else if (imageName.equals("warmansing")){
                gameName="Warmansing";
                MediumImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.warmasigin);

            }else if (imageName.equals("Winter Soldier")){
                gameName="Winter Soldier";
                MediumImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.winter_soldier);

            }

            MediumImageName.setText(gameName);
            gameName=null;

        }else {
            medium_layout.setVisibility(View.GONE);
        }

        if (bestHard.size()!=0) {
            hard_layout.setVisibility(View.VISIBLE);

            HardName.setText(bestHard.get(0));
            HardTime.setText(bestHard.get(1)+"s");
            String imageName=bestHard.get(2);
            if (imageName.equals("supperMan")){
                gameName="SupperMan";
                HardImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.supperman);
            }else if (imageName.equals("blackPanther")){
                gameName="BlackPanther";
                HardImage  .setImageResource(com.example.hasib.avengersassemble.R.drawable.blackpanther);
            }else if (imageName.equals("quickSilver")){

                gameName="QuickSilver";
                HardImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.quicksilver);
            }else if (imageName.equals("scarl")){
                gameName="Scarl";
                HardImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.scarlterwitch);

            }else if (imageName.equals("wasp")){
                gameName="Wasp";
                HardImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.wasp);

            }else if (imageName.equals("Captain Marvel")){
                gameName="Captain Marvel";
                HardImage.setImageResource(com.example.hasib.avengersassemble.R.drawable.captin_marvel);

            }

            HardImageName.setText(gameName);
            gameName=null;
        }else {
            hard_layout.setVisibility(View.GONE);
        }
    }

    private void initializeValue() {
        EasyName=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.first_playername);
        EasyImage=(ImageView) findViewById(com.example.hasib.avengersassemble.R.id.first_Image);
        EasyTime=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.first_score_time);
        EasyImageName=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.first_imagename);


        MediumName=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.second_player_name);
        MediumImage=(ImageView) findViewById(com.example.hasib.avengersassemble.R.id.second_Image);
        MediumTime=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.second_Time);
        MediumImageName=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.second_imagename);

        HardName=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.thirt_player_name);
        HardImage=(ImageView) findViewById(com.example.hasib.avengersassemble.R.id.thirt_Image);
        HardTime=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.thirt_time);
        HardImageName=(TextView) findViewById(com.example.hasib.avengersassemble.R.id.thirt_imagename);

        easy_layout=(LinearLayout) findViewById(com.example.hasib.avengersassemble.R.id.easy_Layout);
        medium_layout=(LinearLayout) findViewById(com.example.hasib.avengersassemble.R.id.medium_Layout);
        hard_layout=(LinearLayout) findViewById(com.example.hasib.avengersassemble.R.id.hard_Layout);

    }

    private void getbesteasy() {

        for (int i=0;i<modeArray.length;i++) {
            Cursor cursor = gameDatabase.mintime(modeArray[i]);

            if (cursor != null) {
                while (cursor.moveToNext()) {
                    mode = cursor.getString(cursor.getColumnIndex(GameDatabase.MODE));
                    time = cursor.getString(cursor.getColumnIndex(GameDatabase.TIME));
                    imageNmae = cursor.getString(cursor.getColumnIndex(GameDatabase.IMAGENAME));


                    if (mode.equals("Easy")) {
                        bestEasy.add(0, mode);
                        bestEasy.add(1, time);
                        bestEasy.add(2, imageNmae);
                    }else if (mode.equals("Medium")){
                        bestMedium.add(0, mode);
                        bestMedium.add(1, time);
                        bestMedium.add(2, imageNmae);

                    }else if (mode.equals("Hard")){
                        bestHard.add(0, mode);
                        bestHard.add(1, time);
                        bestHard.add(2, imageNmae);
                    }
                }



            } else {
             //   Toast.makeText(getApplicationContext(), "Data not found", Toast.LENGTH_SHORT).show();

            }

        }


    }

    public  int imageresource(String imageName){
        int imageResure = 0;
        if (imageName.equals("halk")){
            gameName="Halk";
            imageResure= com.example.hasib.avengersassemble.R.drawable.halk;
        }else if (imageName.equals("hawkey")){
            gameName="Hawkey";
            imageResure= com.example.hasib.avengersassemble.R.drawable.hawkeye;
        }else if (imageName.equals("captainAmrica")){
            gameName="CaptainAmrica";
            imageResure= com.example.hasib.avengersassemble.R.drawable.captainamrica;
        }else if (imageName.equals("IronMan")){
            gameName="IronMan";
            imageResure= com.example.hasib.avengersassemble.R.drawable.ironman;

        }else if (imageName.equals("thor")){

            gameName="Thor";
            imageResure= com.example.hasib.avengersassemble.R.drawable.thor;

        }else if (imageName.equals("supperMan")){

            gameName="SupperMan";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.supperman);
        }else if (imageName.equals("blackPanther")){
            gameName="BlackPanther";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.blackpanther);
        }else if (imageName.equals("quickSilver")){
            gameName="QuickSilver";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.quicksilver);
        }else if (imageName.equals("scarl")){
            gameName="Scarl";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.scarlterwitch);

        }else if (imageName.equals("wasp")){
            gameName="Wasp";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.wasp);

        }else  if (imageName.equals("antMan")){
            gameName="AntMan";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.antman);
        }else if (imageName.equals("doctoreStrane")){
            gameName="DoctoreStrane";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.doctorstrange);
        }else if (imageName.equals("falon")){
            gameName="Falon";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.falson);
        }else if (imageName.equals("vison")){
            gameName="Vison";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.vison);

        }else if (imageName.equals("warmansing")){
            gameName="Warmansing";
            imageResure=(com.example.hasib.avengersassemble.R.drawable.warmasigin);

        }

        return imageResure;

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
    protected void onRestart() {
        super.onRestart();


        sp = getSharedPreferences("Music",MODE_PRIVATE);
        if(sp.getBoolean("music",true))
        {
            mediaPlayer.start();
        }

    }

}
