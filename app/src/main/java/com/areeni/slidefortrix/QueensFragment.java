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

public class QueensFragment extends Fragment implements View.OnClickListener{


    public QueensFragment() {
        // Required empty public constructor
    }
    boolean isScored = false;
    private TextView t1QD;
    private TextView t2QD;
    private int t1=0,t2=0;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_queens, container, false);

        final ComplexActivity activity = (ComplexActivity)getActivity();

        SeekBar seek = v.findViewById(R.id.Qseek);

        t1QD = v.findViewById(R.id.team1QD);
        t2QD = v.findViewById(R.id.team2QD);
        final TextView t1Q = v.findViewById(R.id.team1Q);
        final TextView t2Q = v.findViewById(R.id.team2Q);



        t1QD.setOnClickListener(this);
        t2QD.setOnClickListener(this);

        final ImageView image = v.findViewById(R.id.QueensImage);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                activity.setBanatd((t1-t2)*25);

                if(isScored){
                    KingFragment fragment = new KingFragment();
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

                    t1Q.setText(String.valueOf(value));
                    t2Q.setText(String.valueOf(mid-value-1));
                    activity.setBanat(value*-25);
                    isScored = true;
                }else{
                    t1Q.setText("0");
                    t2Q.setText("0");
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


    @Override
    public void onClick(View view) {

        int id =view.getId();

        switch (id){
            case R.id.team1QD:
                if(t1<4){
                    t1++;
                    t1QD.setText(String.valueOf(t1));
                }else{
                    t1=0;
                    t1QD.setText("0");
                }
                break;

            case R.id.team2QD:
                if(t2<4){
                    t2++;
                    t2QD.setText(String.valueOf(t2));
                }else{
                    t2=0;
                    t2QD.setText("0");
                }
                break;
        }
    }

}
