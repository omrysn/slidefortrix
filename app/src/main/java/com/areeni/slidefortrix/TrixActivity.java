package com.areeni.slidefortrix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import org.w3c.dom.Text;

public class TrixActivity extends AppCompatActivity {
    private int trix;
    private boolean isScored = false;
    SharedPreferences prefs , names = null;
    private AdView mAdView;

    private final static String PLAYER_NAMES = "playerNames";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trix);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        SeekBar seek = findViewById(R.id.Tseek);

        prefs = getSharedPreferences("com.areeni.slidefortrix", MODE_PRIVATE);

        names = getSharedPreferences(PLAYER_NAMES,MODE_PRIVATE);

        TextView t1name = findViewById(R.id.team1Name);
        TextView t2name = findViewById(R.id.team2Name);

        t1name.setText(names.getString("myteam",null));
        t2name.setText(names.getString("otherteam",null));


        final TextView t1t = findViewById(R.id.team1T);
        final TextView t2t = findViewById(R.id.team2T);

        final ImageView image = findViewById(R.id.TrixImage);


        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isScored){
                    Intent intent = new Intent();
                    intent.putExtra("team1Score",trix);
                    setResult(RESULT_OK,intent);
                    finish();
                }

            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                int progress = seekBar.getProgress();
                isScored = true;
                switch (progress){
                    case 0:
                        t1t.setText("350");
                        t2t.setText("150");
                        trix=350;
                        break;
                    case 1:
                        t1t.setText("300");
                        t2t.setText("200");
                        trix=300;
                        break;
                    case 2:
                        t1t.setText("250");
                        t2t.setText("250");
                        trix=250;
                        break;
                    case 3:
                        t1t.setText("200");
                        t2t.setText("300");
                        trix=200;
                        break;
                    case 4:
                        t1t.setText("150");
                        t2t.setText("350");
                        trix=150;
                        break;
                    case 5:
                        t1t.setText("0");
                        t2t.setText("0");
                        trix=0;
                        isScored = false;
                        break;
                    case 6:
                        t1t.setText("350");
                        t2t.setText("150");
                        trix=350;
                        break;
                    case 7:
                        t1t.setText("300");
                        t2t.setText("200");
                        trix=300;
                        break;
                    case 8:
                        t1t.setText("250");
                        t2t.setText("250");
                        trix=250;
                        break;
                    case 9:
                        t1t.setText("200");
                        t2t.setText("300");
                        trix=200;
                        break;
                    case 10:
                        t1t.setText("150");
                        t2t.setText("350");
                        trix=150;
                        break;

                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {


            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrunTrix", true) && prefs.getBoolean("firstrunComplex",true)) {
            new TapTargetSequence(this)
                    .targets(
                            TapTarget
                                    .forView(findViewById(R.id.team1T),"This is your Score")
                                    .targetRadius(30)
                                    .cancelable(false)
                                    .dimColor(R.color.black)
                                    .transparentTarget(true)
                                    .drawShadow(true),
                            TapTarget
                                    .forView(findViewById(R.id.Tseek),"Move the seekbar to change the socre")
                                    .targetRadius(30)
                                    .cancelable(false)
                                    .dimColor(R.color.black)
                                    .transparentTarget(true)
                                    .drawShadow(true),
                            TapTarget
                                    .forView(findViewById(R.id.TrixImage),"Tap the image when done to move to the next game")
                                    .targetRadius(125)
                                    .cancelable(false)
                                    .dimColor(R.color.black)
                                    .transparentTarget(true)
                                    .drawShadow(true)

                    ).start();

            prefs.edit().putBoolean("firstrunTrix", false).apply();
        }
    }
}