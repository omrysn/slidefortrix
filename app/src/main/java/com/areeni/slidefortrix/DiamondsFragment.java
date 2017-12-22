package com.areeni.slidefortrix;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */

public class DiamondsFragment extends Fragment {


    public DiamondsFragment() {
        // Required empty public constructor
    }

    boolean isScored = false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_diamonds, container, false);

        final ComplexActivity activity = (ComplexActivity)getActivity();

        SeekBar seek = v.findViewById(R.id.Dseek);

        final TextView t1D = v.findViewById(R.id.team1D);
        final TextView t2D = v.findViewById(R.id.team2D);

        final ImageView image = v.findViewById(R.id.DiamondsImage);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(isScored){
                    QueensFragment fragment = new QueensFragment();
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

                    t1D.setText(String.valueOf(value));
                    t2D.setText(String.valueOf(mid-value-1));
                    activity.setDinari(value*-10);
                    isScored = true;
                }else{
                    t1D.setText("0");
                    t2D.setText("0");
                    isScored = false;
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