package com.sanai.tasky;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;


import java.util.ArrayList;

public class NextDaysActivity extends Fragment {
    String nameOfDay;
    static ImageButton addTask;
    DataBase dataBase;
    ArrayList<ToDoTask> myTask;
    RecyclerView recyclerView;
    TodoTaskAdapter taskAdapter;
    ImageView noTask ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_next_days,container,false);
        nameOfDay = getArguments().getString("nameOfDay");
        dataBase = PinActivity.dataBase;
        addTask =  view.findViewById(R.id.addTaskButtonInNext);
        noTask =  view.findViewById(R.id.NoTaskInNext);


        //______________________________________________________________________


        myTask = new ArrayList<ToDoTask>();

        myTask=dataBase.getTodayasTasks("todo",nameOfDay);

        if (myTask != null){
            noTask.setVisibility(View.INVISIBLE);
            recyclerView =(RecyclerView) view.findViewById(R.id.todoRecycleInNext);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(layoutManager);
            taskAdapter = new TodoTaskAdapter(myTask,this.getActivity());
            recyclerView.setAdapter(taskAdapter);

        }


        //_______________________________________________________________________




        return  view;
    }
}
