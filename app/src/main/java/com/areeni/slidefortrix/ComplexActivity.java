package com.areeni.slidefortrix;

import android.content.SharedPreferences;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

public class ComplexActivity extends AppCompatActivity {

    public int ltoosh;
    public int dinari;
    public int banat;
    public int banatd;
    public int king;
    public int trix;

    public int total;

    private AdView mAdView;

    private SharedPreferences names;
    private final static String PLAYER_NAMES = "playerNames";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        names = getSharedPreferences(PLAYER_NAMES,MODE_PRIVATE);

        TextView t1name = findViewById(R.id.team1Name);
        TextView t2name = findViewById(R.id.team2Name);

        t1name.setText(names.getString("myteam",null));
        t2name.setText(names.getString("otherteam",null));

        ClubsFragment fragment = new ClubsFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.mainFrame,fragment);
        transaction.commit();

    }

    public int getLtoosh() {
        return ltoosh;
    }

    public void setLtoosh(int ltoosh) {
        this.ltoosh = ltoosh;
    }

    public int getDinari() {
        return dinari;
    }

    public void setDinari(int dinari) {
        this.dinari = dinari;
    }

    public int getBanat() {
        return banat;
    }

    public void setBanat(int banat) {
        this.banat = banat;
    }

    public int getBanatd() {
        return banatd;
    }

    public void setBanatd(int banatd) {
        this.banatd = banatd;
    }

    public int getKing() {
        return king;
    }

    public void setKing(int king) {
        this.king = king;
    }

    public int getTrix() {
        return trix;
    }

    public void setTrix(int trix) {
        this.trix = trix;
    }



    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
