package com.areeni.slidefortrix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class TeamNamesActivity extends AppCompatActivity {

    private final static String PLAYER_NAMES = "playerNames";

    private EditText myteam;
    private EditText otherteam;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_names);

//        mAdView = findViewById(R.id.adView);
//        AdRequest adRequest = new AdRequest.Builder().build();
//        mAdView.loadAd(adRequest);

        SharedPreferences prefs = getSharedPreferences(PLAYER_NAMES,MODE_PRIVATE);
        String t1 = prefs.getString("myteam",null);
        String t2 = prefs.getString("otherteam",null);
        myteam = findViewById(R.id.myTeam);
        otherteam = findViewById(R.id.otherTeam);

        if(t1!=null){
            myteam.setText(t1);
        }

        if(t2!=null){
            otherteam.setText(t2);
        }


        Button go = findViewById(R.id.partnersGoButton);

        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences prefs = getSharedPreferences(PLAYER_NAMES,MODE_PRIVATE);
                SharedPreferences.Editor editor = prefs.edit();
                editor.putString("myteam",myteam.getText().toString().trim());
                editor.putString("otherteam",otherteam.getText().toString().trim());
                editor.apply();

                if(isEmpty(myteam) || isEmpty(otherteam)){
                    Toast.makeText(TeamNamesActivity.this, "Please Enter Team Names!", Toast.LENGTH_SHORT).show();
                }else{
                    startActivity(new Intent(TeamNamesActivity.this,ScoresActivity.class));
                    finish();
                }


            }
        });
    }

    private boolean isEmpty(EditText e){
        return e.getText().toString().trim().matches("");
    }
}
