package com.areeni.slidefortrix;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import static android.app.Activity.RESULT_OK;


/**
 * A simple {@link Fragment} subclass.
 */
public class KingFragment extends Fragment {


    public KingFragment() {
        // Required empty public constructor
    }

    boolean isScored = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =inflater.inflate(R.layout.fragment_king, container, false);

        final ComplexActivity activity = (ComplexActivity)getActivity();


        final SeekBar seek = v.findViewById(R.id.Kseek);

        final TextView t1k = v.findViewById(R.id.team1K);
        final TextView t2k = v.findViewById(R.id.team2K);

        final ImageView image = v.findViewById(R.id.KingsImage);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isScored){
                    int total = activity.getLtoosh()+activity.getDinari()
                            +activity.getBanat()
                            +activity.getBanatd()+activity.getKing();

                    activity.setTotal(total);
                    Intent intent = new Intent();
                    intent.putExtra("team1Score",total);
                    activity.setResult(RESULT_OK,intent);
                    activity.finish();
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
                        t1k.setText("-150");
                        t2k.setText("75");
                        activity.setKing(-150);
                        break;
                    case 1:
                        t1k.setText("-75");
                        t2k.setText("0");
                        activity.setKing(-75);
                        break;
                    case 2:
                        t1k.setText("75");
                        t2k.setText("-150");
                        activity.setKing(75);
                        break;
                    case 3:
                        t1k.setText("0");
                        t2k.setText("-75");
                        activity.setKing(0);
                        break;
                    case 4:
                        t1k.setText("0");
                        t2k.setText("0");
                        activity.setKing(0);
                        isScored = false;
                        break;
                    case 5:
                        t1k.setText("-75");
                        t2k.setText("0");
                        activity.setKing(-75);
                        break;
                    case 6:
                        t1k.setText("-150");
                        t2k.setText("75");
                        activity.setKing(-150);
                        break;
                    case 7:
                        t1k.setText("0");
                        t2k.setText("-75");
                        activity.setKing(0);
                        break;
                    case 8:
                        t1k.setText("75");
                        t2k.setText("-150");
                        activity.setKing(75);
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

        return v;
    }

}