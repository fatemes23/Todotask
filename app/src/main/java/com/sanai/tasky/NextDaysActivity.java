package com.sanai.tasky;

import android.support.v4.app.Fragment;
import android.support.annotation.Nullable;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;


import java.util.ArrayList;

public class NextDaysActivity extends Fragment {
    static String nameOfDay;
    static ImageButton addTask;
   static DataBase dataBase;
    static ArrayList<ToDoTask> myTask;
    static RecyclerView recyclerView;
    static TodoTaskAdapter taskAdapter;
    ImageView noTask ;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_next_days,container,false);
        nameOfDay = getArguments().getString("nameOfDay");
        dataBase = PinActivity.dataBase;
        addTask =  view.findViewById(R.id.addTaskButtonInNext);
        noTask =  view.findViewById(R.id.NoTaskInNext);

        recyclerView = view.findViewById(R.id.todoRecycleInNext);




        //______________________________________________________________________


        myTask = new ArrayList<ToDoTask>();

        myTask=dataBase.getTodayasTasks("todo",nameOfDay);

        if (myTask != null){
            noTask.setVisibility(View.GONE);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
            recyclerView.setLayoutManager(layoutManager);
            taskAdapter = new TodoTaskAdapter(myTask,this.getActivity());
            recyclerView.setAdapter(taskAdapter);

        }


        ///_________swipe controlle
        SwipControllerNextDay swipeController = new SwipControllerNextDay(taskAdapter);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);

        //_______________________________________________________________________

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Add task", Toast.LENGTH_LONG ).show();
               // parentAlpha.setAlpha((float) .4);
                Bundle bundle = new Bundle();
                bundle.putString("nameOfDay", nameOfDay);
                bundle.putString("whichFrag", "next");
                AddTaskFragmentActivity fragmentActivity = new AddTaskFragmentActivity();
                fragmentActivity.setArguments(bundle);
                getFragmentManager().beginTransaction().setCustomAnimations( R.anim.slide_up_to_bottom, 0, 0, R.anim.slide_up_to_bottom).add(R.id.nestedFrameInNext,fragmentActivity).commit();
                addTask.setEnabled(false);



            }
        });


        return  view;
    }
    public  void saveNewTask() {

      //  parentAlpha.setAlpha((float)1.0);
        addTask.setEnabled(true);
        //ezafe krdn b database

        update(recyclerView);
       // taskAdapter.notifyItemInserted(0);




    }

    public  static  void cancleNewTask(){
       // parentAlpha.setAlpha((float)1.0);
        addTask.setEnabled(true);


    }

    public  void update(RecyclerView recyclerView){
        myTask = new ArrayList<ToDoTask>();

        myTask=PinActivity.dataBase.getTodayasTasks("todo",nameOfDay);


            LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
            recyclerView.setLayoutManager(layoutManager);
            taskAdapter = new TodoTaskAdapter(myTask,getActivity());
            recyclerView.setAdapter(taskAdapter);

    }


}
