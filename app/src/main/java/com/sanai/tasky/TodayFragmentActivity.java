package com.sanai.tasky;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;


import java.util.ArrayList;

public  class TodayFragmentActivity extends Fragment  {

    static ArrayList<ToDoTask> myTask;
    ArrayList<DoneTask> doneTasks;
   static RecyclerView recyclerView;
    RecyclerView doneRecyclerView;
    static TodoTaskAdapter taskAdapter;
    DoneTaskAdapter donetaskAdapter;
   static ImageButton addTask;
    static LinearLayout parentAlpha ;
    static DataBase dataBase;
    static String nameOfDay;
    int flag = 0;
    static TodayFragmentActivity todayFragmentActivity;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.activity_today_fragment,container,false);

        //#############################################################################
        nameOfDay = this.getArguments().getString("nameOfDay");
        dataBase = PinActivity.dataBase;
        addTask = (ImageButton) view.findViewById(R.id.addTaskButton);
        parentAlpha = (LinearLayout) view.findViewById(R.id.alphaForAddTask);
        todayFragmentActivity = new TodayFragmentActivity();
        //______________________________________________________________________


        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "Add task", Toast.LENGTH_LONG ).show();
                parentAlpha.setAlpha((float) .4);
               AddTaskFragmentActivity fragmentActivity = new AddTaskFragmentActivity();
               getFragmentManager().beginTransaction().setCustomAnimations( R.anim.slide_up_to_bottom, 0, 0, R.anim.slide_up_to_bottom).add(R.id.nestedFrame,fragmentActivity).commit();
                addTask.setEnabled(false);



            }
        });
        //_______________________________________________________________________



        myTask = new ArrayList<ToDoTask>();
        recyclerView =(RecyclerView) view.findViewById(R.id.todoRecycle);

        myTask=dataBase.getTodayasTasks("todo",nameOfDay);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this.getActivity());
        recyclerView.setLayoutManager(layoutManager);
        taskAdapter = new TodoTaskAdapter(myTask,this.getActivity());
        recyclerView.setAdapter(taskAdapter);



        //_____________________________________________________________________________\\
        doneTasks =new ArrayList<DoneTask>();
        doneTasks.add(new DoneTask("Shopping"));
        doneTasks.add(new DoneTask("Hw"));
        doneTasks.add(new DoneTask("Shopping"));
        doneTasks.add(new DoneTask("Hw"));
        doneTasks.add(new DoneTask("Shopping"));
        doneTasks.add(new DoneTask("Hw"));
        doneTasks.add(new DoneTask("Shopping"));

        doneRecyclerView = (RecyclerView)view.findViewById(R.id.doneRecycle);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        doneRecyclerView.setLayoutManager(llm);
        donetaskAdapter = new DoneTaskAdapter(doneTasks,this.getActivity());
        doneRecyclerView.setAdapter(donetaskAdapter);

        return  view;
    }

    public static void saveNewTask(String title , String body , String todoime,String alarmtime) {

        parentAlpha.setAlpha((float)1.0);
        addTask.setEnabled(true);
        //ezafe krdn b database
        ToDoTask toDoTask = new ToDoTask(title,todoime,body,alarmtime);
        PinActivity.dataBase.insertTask(toDoTask,nameOfDay,"todo");
        update(recyclerView);



    }

    public  static  void cancleNewTask(){
        parentAlpha.setAlpha((float)1.0);
        addTask.setEnabled(true);


    }

    public  void update(RecyclerView recyclerView){
         myTask = new ArrayList<ToDoTask>();

        myTask=dataBase.getTodayasTasks("todo",nameOfDay);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        taskAdapter = new TodoTaskAdapter(myTask,getActivity());
        recyclerView.setAdapter(taskAdapter);

    }

}
