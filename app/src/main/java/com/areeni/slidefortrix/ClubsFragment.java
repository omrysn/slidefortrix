package com.areeni.slidefortrix;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import com.getkeepsafe.taptargetview.TapTarget;
import com.getkeepsafe.taptargetview.TapTargetSequence;

import static android.content.Context.MODE_PRIVATE;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClubsFragment extends Fragment {

    public ClubsFragment() {
        // Required empty public constructor
    }
    SharedPreferences prefs = null;
    boolean isScored = false;
    ComplexActivity activity;
    View v;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        v = inflater.inflate(R.layout.fragment_clubs, container, false);
        activity = (ComplexActivity)getActivity();
        prefs = activity.getSharedPreferences("com.areeni.slidefortrix", MODE_PRIVATE);
        SeekBar seek = v.findViewById(R.id.Lseek);
        final TextView t1L = v.findViewById(R.id.team1L);
        final TextView t2L = v.findViewById(R.id.team2L);

        final ImageView image = v.findViewById(R.id.ClubsImage);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isScored){
                    DiamondsFragment fragment = new DiamondsFragment();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.mainFrame,fragment);
                    transaction.addToBackStack(null);
                    transaction.commit();
                }

            }
        });

        seek.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

                int value = SeekValues.SeekValues(seekBar);
                int mid = seekBar.getMax()/2;
                if(value!= mid){
                    t1L.setText(String.valueOf(value));
                    t2L.setText(String.valueOf(mid-value-1));
                    activity.setLtoosh(value*-15);
                    isScored = true;

                }else{
                    t1L.setText("0");
                    t2L.setText("0");
                    isScored=false;
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


    @Override
    public void onResume() {
        super.onResume();

        if (prefs.getBoolean("firstrunComplex", true) && prefs.getBoolean("firstrunTrix", true)) {
            new TapTargetSequence(activity)
                    .targets(
                            TapTarget
                                    .forView(v.findViewById(R.id.team1L),"This is your Score")
                                    .targetRadius(70)
                                    .cancelable(false)
                                    .dimColor(R.color.black)
                                    .drawShadow(true),
                            TapTarget
                                    .forView(v.findViewById(R.id.Lseek),"Move the seekbar to change the socre")
                                    .targetRadius(70)
                                    .cancelable(false)
                                    .dimColor(R.color.black)
                                    .drawShadow(true),
                            TapTarget
                                    .forView(v.findViewById(R.id.ClubsImage),"Tap the image when done to move to the next game")
                                    .targetRadius(125)
                                    .cancelable(false)
                                    .dimColor(R.color.black)
                                    .transparentTarget(true)
                                    .drawShadow(true)

                    ).start();
            prefs.edit().putBoolean("firstrunComplex", false).apply();
        }

    }
}
