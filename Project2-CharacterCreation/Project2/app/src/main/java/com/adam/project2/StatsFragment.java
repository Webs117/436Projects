package com.adam.project2;

/**
 * Created by Adam on 7/4/2017.
 */

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class StatsFragment extends Fragment {

    TextView title;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.stats_fragment, container, false);

        title = (TextView) view.findViewById(R.id.Class_Title);

        // Inflate the layout for this fragment
        return view;
    }

    public void loadValues(String characterClass){
        title.setText(characterClass);
    }
}