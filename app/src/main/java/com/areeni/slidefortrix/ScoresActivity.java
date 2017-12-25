package com.areeni.slidefortrix;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetView;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.util.ArrayList;
import java.util.List;


public class ScoresActivity extends AppCompatActivity implements View.OnClickListener{

    private boolean isFabOpen = false;
    private FloatingActionButton mfloat, cfloat, tfloat,ufloat;
    private Animation fab_open,fab_close, rotate_forward,rotate_backward,leftToright,rightToleft;


    private List<Integer> prevScores = new ArrayList<>();

    private static final int REQUEST_CODE_COMPLEX = 1;
    private static final int REQUEST_CODE_TRIX = 2;

    private int total = 0;

    private int curruntScore =0;

    private int counter =0;
    private TextView t2Total,t1Total,trixname,complexname,undo;

    SharedPreferences prefs = null;

    LinearLayout scores;

    private AdView mAdView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scores);


        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        prefs = getSharedPreferences("com.areeni.slidefortrix", MODE_PRIVATE);

        loadingResources();

        mfloat.setOnClickListener(this);
        cfloat.setOnClickListener(this);
        tfloat.setOnClickListener(this);
        ufloat.setOnClickListener(this);

    }
    private void loadingResources(){
        mfloat = findViewById(R.id.mFlow);
        cfloat = findViewById(R.id.complexFlow);
        tfloat = findViewById(R.id.trixFlow);
        ufloat = findViewById(R.id.ufloat);

        t1Total = findViewById(R.id.team1Total);
        t2Total = findViewById(R.id.team2Total);

        trixname = findViewById(R.id.trixName);
        complexname = findViewById(R.id.complexName);
        undo = findViewById(R.id.undo);


        scores = findViewById(R.id.ScoresList);


        fab_close = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_close);
        fab_open = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.fab_open);
        rotate_backward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_backward);
        rotate_forward = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.rotate_forward);
        leftToright = AnimationUtils.loadAnimation(getApplicationContext(),R.anim.lefttoright);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();

        switch (id){
            case R.id.mFlow:
                if(counter < 8){

                    animateFAB();
                }
                else{
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage(R.string.newgame)
                            .setCancelable(false)
                            .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                    startActivity(new Intent(ScoresActivity.this,ScoresActivity.class));
                                    finish();
                                }
                            })
                            .setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int id) {
                                   finish();
                                }
                            });
                    AlertDialog alert = builder.create();
                    alert.show();
                }

                break;
            case R.id.complexFlow:
                Intent intent1 = new Intent(this,ComplexActivity.class);
                startActivityForResult(intent1,REQUEST_CODE_COMPLEX);
                animateFAB();
                break;
            case R.id.trixFlow:
                Intent intent2 = new Intent(this,TrixActivity.class);
                startActivityForResult(intent2,REQUEST_CODE_TRIX);
                animateFAB();
                break;
            case R.id.ufloat:
                int index =scores.getChildCount()-1;
                if(index>0){
                    int childId = scores.getChildAt(index).getId();
                    if(childId == R.id.divider){
                        scores.removeViewAt(index);
                        scores.removeViewAt(index-1);
                        counter--;
                        counter--;
                    }else{

                        scores.removeViewAt(index);
                        counter--;
                    }
                    total-=curruntScore;
                    t1Total.setText(String.valueOf(total));
//                        Log.d("arrayall", "onClick: array"+prevScores);
                    if(curruntScore >0){
                        int t2s = Integer.valueOf(t2Total.getText().toString())-(500-curruntScore);
                        t2Total.setText(String.valueOf(t2s));
                        prevScores.remove(prevScores.size()-1);
                        curruntScore = prevScores.get(prevScores.size()-1);
//                        Log.d("newCurrent", "onClick: new current"+curruntScore);
//                        Log.d("newarray", "onClick: new array "+prevScores);
                    }else{
                        int t2s = Integer.valueOf(t2Total.getText().toString())-(-500-curruntScore);
                        t2Total.setText(String.valueOf(t2s));
                        prevScores.remove(prevScores.size()-1);
                        curruntScore = prevScores.get(prevScores.size()-1);
//                        Log.d("newCurrent", "onClick: new current"+curruntScore);
//                        Log.d("newarray", "onClick: new array "+prevScores);
                    }
                }
                break;
        }
    }

    public void animateFAB() {

        if(isFabOpen){
            mfloat.startAnimation(rotate_backward);

//            cfloat.startAnimation(fab_close);
//            tfloat.startAnimation(fab_close);
//            ufloat.startAnimation(fab_close);
//
//            trixname.startAnimation(fab_close);
//            complexname.startAnimation(fab_close);
//            undo.startAnimation(fab_close);

            cfloat.setVisibility(View.GONE);
            tfloat.setVisibility(View.GONE);
            ufloat.setVisibility(View.GONE);

            trixname.setVisibility(View.GONE);
            complexname.setVisibility(View.GONE);
            undo.setVisibility(View.GONE);

            cfloat.setClickable(false);
            tfloat.setClickable(false);
            ufloat.setClickable(false);

        }else{
            mfloat.startAnimation(rotate_forward);

            cfloat.setVisibility(View.VISIBLE);
            tfloat.setVisibility(View.VISIBLE);
            ufloat.setVisibility(View.VISIBLE);

            trixname.setVisibility(View.VISIBLE);
            complexname.setVisibility(View.VISIBLE);
            undo.setVisibility(View.VISIBLE);

//            trixname.startAnimation(fab_open);
//            complexname.startAnimation(fab_open);
//            undo.startAnimation(fab_open);
//
//            cfloat.startAnimation(fab_open);
//            tfloat.startAnimation(fab_open);
//            ufloat.startAnimation(fab_close);

            cfloat.setClickable(true);
            tfloat.setClickable(true);
            ufloat.setClickable(true);
        }

        isFabOpen = !isFabOpen;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);


        View row = getLayoutInflater().inflate(R.layout.row_layout,null,false);
        View divider = getLayoutInflater().inflate(R.layout.divider_layout,null,false);
        TextView t1 = row.findViewById(R.id.team1Score);
        TextView game =row.findViewById(R.id.GameName);
        TextView t2 = row.findViewById(R.id.team2Score);

        if(requestCode == REQUEST_CODE_COMPLEX){
            if(resultCode==RESULT_OK){

                curruntScore = data.getIntExtra("team1Score",-1);
                total+= curruntScore;
                prevScores.add(curruntScore);
                t1.setText(String.valueOf(curruntScore));
                game.setText(R.string.complexname);
                t2.setText(String.valueOf(-500-curruntScore));
                scores.addView(row);
                counter++;
                if(counter % 2 == 0){

                    t1Total.setText(String.valueOf(total));
                    t2Total.setText(String.valueOf(-total));
                }else{
                    t1Total.setText(String.valueOf(total));
                    t2Total.setText(String.valueOf(-500-total));
                }

            }

        }

        if(requestCode == REQUEST_CODE_TRIX){
            if(resultCode==RESULT_OK){

                curruntScore = data.getIntExtra("team1Score",-1);
                total+=curruntScore;
                prevScores.add(curruntScore);
                t1.setText(String.valueOf(curruntScore));
                game.setText(R.string.trixname);
                t2.setText(String.valueOf(500-curruntScore));
                scores.addView(row);
                counter++;
                if(counter % 2 == 0){

                    t1Total.setText(String.valueOf(total));
                    t2Total.setText(String.valueOf(-total));
                }else{
                    t1Total.setText(String.valueOf(total));
                    t2Total.setText(String.valueOf(500-total));
                }

            }

        }
        switch (scores.getChildCount()){
            case 2:
                scores.addView(divider);
                break;
            case 5:
                scores.addView(divider);
                break;
            case 8:
                scores.addView(divider);
                break;
            case 11:
                Toast.makeText(this, "Game Over!", Toast.LENGTH_SHORT).show();
                break;
        }

    }
    long lastPress;
    @Override
    public void onBackPressed() {
        long currentTime = System.currentTimeMillis();
        if(currentTime - lastPress > 5000){
            Toast.makeText(getBaseContext(), "Press back again to exit!", Toast.LENGTH_LONG).show();
            lastPress = currentTime;
        }else{
            super.onBackPressed();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (prefs.getBoolean("firstrun", true)) {

            TapTargetView.showFor(this,
                    TapTarget.forView(findViewById(R.id.mFlow),
                            "Press the button to choose a game")
                            .targetRadius(60)
                            .transparentTarget(true)
                            .dimColor(R.color.black)
                            .cancelable(false)
            );

            prefs.edit().putBoolean("firstrun", false).apply();
        }
    }
}
//todo: add a way to remove last entry from scores and decrease the counter by 1