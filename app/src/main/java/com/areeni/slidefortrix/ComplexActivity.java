package com.areeni.slidefortrix;

import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ComplexActivity extends AppCompatActivity {

    public int ltoosh;
    public int dinari;
    public int banat;
    public int banatd;
    public int king;
    public int trix;

    public int total;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complex);
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
