package com.sanai.tasky;


import android.support.v4.app.Fragment;

import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

//
public class PreviousDaysActivity extends Fragment {
    static String nameOfDay;
    static DataBase dataBase;
    static ArrayList<DoneTask> doneTasks;
    static ArrayList<OverdueTask> overedue;
    ImageView noDone;
    ImageView noOverdue;
    static DoneTaskAdapter doneTaskAdapter ;
    static OverdueTaskAdapter overdueTaskAdapter ;
    static RecyclerView doneRecycle;
    static  RecyclerView overdueRecycle;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_previous_days,container,false);

        //________________________________________________________________________________________

        nameOfDay = getArguments().getString("nameOfDay");
        dataBase = SplashActivity.dataBase;
        doneRecycle= view.findViewById(R.id.doneRecycleInPre);
        overdueRecycle= view.findViewById(R.id.overDueRecycleInPre);
        doneTasks = new ArrayList<DoneTask>();
        overedue = new ArrayList<OverdueTask>() ;
        noDone = view.findViewById(R.id.noDone);
        noOverdue = view.findViewById(R.id.noOverdue);
        Log.d("taagPreviousDayActivity", "ooooooooooonCreateView: PreviousDaysActivity  " + nameOfDay);
        //________________________________________________________________________________________
       // overedue.add(new OverdueTask("title for test",99));
        ArrayList<ToDoTask> temp = SplashActivity.dataBase.getTodayasTasks("overdue  " , nameOfDay);
        for (int i =0 ; i< temp.size(); i++){
            noOverdue.setVisibility(View.GONE);
            overedue.add(new OverdueTask(temp.get(i).todoTitle , temp.get(i).id));// dare overdue set mikone
        }


        LinearLayoutManager llm1= new LinearLayoutManager(this.getActivity());
        overdueRecycle.setLayoutManager(llm1);
        overdueTaskAdapter = new OverdueTaskAdapter(overedue,this.getActivity());
        overdueRecycle.setAdapter(overdueTaskAdapter);
        //_______________________________________________________________________________________
        ArrayList<ToDoTask> temp2 = SplashActivity.dataBase.getTodayasTasks("done" , nameOfDay);
        //Toast.makeText(getActivity() , temp2.size()+"  " + nameOfDay,Toast.LENGTH_LONG).show();
        for (int i =0 ; i< temp2.size(); i++){
            noDone.setVisibility(View.GONE);
            doneTasks.add(new DoneTask(temp2.get(i).todoTitle , temp2.get(i).id));
        }

        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        doneRecycle.setLayoutManager(llm);
        doneTaskAdapter = new DoneTaskAdapter(doneTasks,this.getActivity());
        doneRecycle.setAdapter(doneTaskAdapter);



        //_____________________________________________________________________




        return  view;

    }
}
