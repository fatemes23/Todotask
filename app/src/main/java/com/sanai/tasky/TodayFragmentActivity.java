package com.sanai.tasky;

import android.app.TimePickerDialog;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;



import java.util.ArrayList;
import java.util.Calendar;

public  class TodayFragmentActivity extends Fragment  {

    static ArrayList<ToDoTask> myTask;
    static ArrayList<DoneTask> doneTasks;
   static RecyclerView recyclerView;
    static RecyclerView doneRecyclerView;
    static TodoTaskAdapter taskAdapter;
    static DoneTaskAdapter donetaskAdapter;
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
        dataBase = SplashActivity.dataBase;
        addTask = (ImageButton) view.findViewById(R.id.addTaskButton);
        parentAlpha = (LinearLayout) view.findViewById(R.id.alphaForAddTask);
        todayFragmentActivity = new TodayFragmentActivity();
        //______________________________________________________________________
        //Toast.makeText(getActivity(),"day : "+nameOfDay,Toast.LENGTH_LONG).show();
        Log.d("taaagintodayfragament", "ooooooooooooooooooonCreateView: in to day " + nameOfDay);

        addTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(getActivity(), "Add task", Toast.LENGTH_LONG ).show();
                parentAlpha.setAlpha((float) .4);
                Bundle bundle = new Bundle();
                bundle.putString("nameOfDay", nameOfDay);
                bundle.putString("whichFrag", "today");

               AddTaskFragmentActivity fragmentActivity = new AddTaskFragmentActivity();
                fragmentActivity.setArguments(bundle);
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

        ///_________swipe controlle
        SwipController swipeController = new SwipController(taskAdapter);
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(recyclerView);



        //_____________________________________________________________________________\\
        doneTasks =new ArrayList<DoneTask>();
        ArrayList<ToDoTask> temp =dataBase.getTodayasTasks("done",nameOfDay);
        for( int i =0 ; i < temp.size() ; i++){
            doneTasks.add(new DoneTask(temp.get(i).todoTitle , temp.get(i).id));
        }

        doneRecyclerView = (RecyclerView)view.findViewById(R.id.doneRecycle);
        LinearLayoutManager llm = new LinearLayoutManager(this.getActivity());
        doneRecyclerView.setLayoutManager(llm);
        donetaskAdapter = new DoneTaskAdapter(doneTasks,this.getActivity());
        doneRecyclerView.setAdapter(donetaskAdapter);

        donetaskAdapter.notifyDataSetChanged();//update


        return  view;
    }

    public  void saveNewTask() {

        parentAlpha.setAlpha((float)1.0);
        addTask.setEnabled(true);
        //ezafe krdn b database

        updateToDoTask(recyclerView);



    }

    public  static  void cancleNewTask(){
        parentAlpha.setAlpha((float)1.0);
        addTask.setEnabled(true);


    }

    public  void updateToDoTask(RecyclerView recyclerView){

        myTask.clear();
        myTask.addAll(dataBase.getTodayasTasks("todo",nameOfDay));
        taskAdapter.notifyDataSetChanged();
    }
    public static   void updateToDoneTask(){

        doneTasks.clear();
        ArrayList<ToDoTask> temp =dataBase.getTodayasTasks("done",nameOfDay);
        ArrayList<DoneTask> temp2 = new ArrayList<>();
        for( int i =0 ; i < temp.size() ; i++){
            temp2.add(new DoneTask(temp.get(i).todoTitle , temp.get(i).id));
        }

        doneTasks.addAll(temp2);
        donetaskAdapter.notifyDataSetChanged();
    }
    public  static void  showCustomTimePicker(final TextView txt, FragmentActivity fragmentActivity)
    {

        final Calendar myCalender = Calendar.getInstance();
        final int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);

        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);

                    txt.setText(hourOfDay+":"+minute);


                }
            }


        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(fragmentActivity, TimePickerDialog.THEME_HOLO_DARK , myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose time :");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();

    }



}
