package com.sanai.tasky;


import android.graphics.drawable.Drawable;
import android.support.v4.app.FragmentActivity;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class TodoTaskAdapter extends RecyclerView.Adapter<TodoTaskAdapter.MyTaskViewHolder>  {

    List<ToDoTask> todoList;
    FragmentActivity mainActivity;
    int id ;


    public TodoTaskAdapter(List<ToDoTask> myTasks, FragmentActivity mainActivity) {
        this.todoList = myTasks;
        this.mainActivity = mainActivity;

    }




    @Override
    public MyTaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_todo_task, viewGroup, false);
        MyTaskViewHolder pvh = new MyTaskViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final MyTaskViewHolder viewHolder, final int i) {

        //viewHolder.icon.setImageResource(todoList.get(i).iconRsc);
        final ToDoTask toDoTask = todoList.get(i);
       viewHolder.title.setText(toDoTask.getTodoTitle());
       viewHolder.time.setText(toDoTask.getTodoTime());
       viewHolder.notTime.setText(toDoTask.getTodoNotifictionTime());
       viewHolder.body.setText(toDoTask.getTodoBody());
       if (toDoTask.getPicString() != ""){
           ImageClass img = new ImageClass();
           viewHolder.picImage.setImageBitmap(img.byteArrayToBitMap(img.loadImageFromStorage1(toDoTask.getPicString() +".JPEG")));
       }else{

           Drawable placeholder = viewHolder.picImage.getContext().getResources().getDrawable(R.drawable.ic_tasky_logo);
           viewHolder.picImage.setImageDrawable(placeholder);
           Log.d("khaaaaaaaaaaar" ,"saaaaaaaaaaaaaaaaaaaaaaag");

       }

        //viewHolder.notTime.setText(id+"test");


        viewHolder.forward.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id = todoList.get(i).getIdtOdO();
               String thisDay =  SplashActivity.dataBase.getNameOfDay(id);
               String nextDay = getNextDay(thisDay);
               SplashActivity.dataBase.goToNextDay_update_nameofday(id,nextDay);
                todoList.remove(i);
                notifyItemRemoved(i);

            }
        });
        viewHolder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        //____________________________________________________________________________________
       if( !toDoTask.getTodoNotifictionTime().equals("")){
           viewHolder.add.setVisibility(View.GONE);
           viewHolder.notTime.setVisibility(View.VISIBLE);
           viewHolder.ll.setBackgroundResource(R.drawable.bg_notofiction_yellow_todo);
       }
       else {
           viewHolder.add.setVisibility(View.VISIBLE);
           viewHolder.notTime.setVisibility(View.GONE);
           viewHolder.ll.setBackgroundResource(R.drawable.bg_notifiction_grey_todo);
              }
        //____________________________________________________________________________________



    }


    @Override
    public int getItemCount() {
        return todoList.size();
    }

    public static class MyTaskViewHolder extends RecyclerView.ViewHolder {

        TextView title;
        TextView time ;
        TextView body;
        TextView notTime;
        ImageButton add;
        LinearLayout ll ;
        ImageButton forward ;
        ImageView picImage;


        MyTaskViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.todoTitle);
            time = (TextView) itemView.findViewById(R.id.todoTime);
            body = (TextView) itemView.findViewById(R.id.todoBody);
            notTime = (TextView) itemView.findViewById(R.id.todoNotifictionTime);
            add = (ImageButton) itemView.findViewById(R.id.todoAddTime);
            forward = itemView.findViewById(R.id.forwardTask);
            ll = (LinearLayout) itemView.findViewById(R.id.todoNotifictionLayout);
            picImage = (ImageView) itemView.findViewById(R.id.todotaskPic);



        }


    }


    public void removeItem(int position) {
        todoList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, todoList.size());
    }


    public void deleteItem(int position) {
        ToDoTask toDoTask = todoList.get(position);
        //delet from data base
       // Log.d("taaaaaaaaaaaaaaaag" , "  " +toDoTask.id);
        SplashActivity.dataBase.delete_task(toDoTask.id);
        todoList.remove(position);
        notifyItemRemoved(position);

    }
    public void updateToDone(int position) {
        ToDoTask toDoTask = todoList.get(position);
        //delet from data base
        // Log.d("taaaaaaaaaaaaaaaag" , "  " +toDoTask.id);
        SplashActivity.dataBase.update_todo_done(toDoTask.id , "done");
        todoList.remove(position);
        notifyItemRemoved(position);
        TodayFragmentActivity.updateToDoneTask();

    }

    public String getNextDay(String day) {
        String nextday;

        switch (day) {
            case "sat":
                nextday = "sun";
                break;
            case "sun":
                nextday = "mon";
                break;
            case "mon":
                nextday = "tue";
                break;
            case "tue":
                nextday = "wen";
                break;
            case "wen":
                nextday = "thur";
                break;
            case "thur":
                nextday = "fri";
                break;
            case "fri":
                nextday = "sat";
                break;
            default:
                nextday = null;

        }

        return  nextday;
    }

}

