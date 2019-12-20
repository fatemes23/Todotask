package com.sanai.tasky;


import android.support.v4.app.Fragment;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

public class PreviousDaysActivity extends Fragment {
    String nameOfDay;
    DataBase dataBase;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_previous_days,container,false);


        nameOfDay = getArguments().getString("nameOfDay");
        dataBase = PinActivity.dataBase;

        Toast.makeText(getActivity(),nameOfDay+"        inprevious",Toast.LENGTH_LONG).show();

        return  view;

    }
}
