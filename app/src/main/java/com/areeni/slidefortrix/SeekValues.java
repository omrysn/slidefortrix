package com.areeni.slidefortrix;

import android.widget.SeekBar;

/**
 * Created by unknown on 22/12/17.
 */

public class SeekValues {

    public static int SeekValues(SeekBar seek) {

        int progress = seek.getProgress();

        int max = seek.getMax();

        int mid = max/2;

        int diff = progress - mid;

        if(diff > 0){

            return  max-progress;
        }else if(diff < 0){
            return -diff-1;
        }else{

            return mid;
        }

    }
}

