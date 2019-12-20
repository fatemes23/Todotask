package com.sanai.tasky;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;


public class TodoTaskAdapter extends RecyclerView.Adapter<TodoTaskAdapter.MyTaskViewHolder> {

    List<ToDoTask> todoList;
    FragmentActivity mainActivity;

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
        ToDoTask toDoTask = todoList.get(i);
       viewHolder.title.setText(toDoTask.getTodoTitle());
       viewHolder.time.setText(toDoTask.getTodoTime());
       viewHolder.notTime.setText(toDoTask.getTodoNotifictionTime());
       viewHolder.body.setText(toDoTask.getTodoBody());

       if(toDoTask.getTodoNotifictionTime() != null){
           viewHolder.add.setVisibility(View.GONE);
           viewHolder.notTime.setVisibility(View.VISIBLE);
           viewHolder.ll.setBackgroundResource(R.drawable.bg_notofiction_yellow_todo);
       }
       else {
           viewHolder.add.setVisibility(View.VISIBLE);
           viewHolder.notTime.setVisibility(View.GONE);
           viewHolder.ll.setBackgroundResource(R.drawable.bg_notifiction_grey_todo);
              }

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

        MyTaskViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.todoTitle);
            time = (TextView) itemView.findViewById(R.id.todoTime);
            body = (TextView) itemView.findViewById(R.id.todoBody);
            notTime = (TextView) itemView.findViewById(R.id.todoNotifictionTime);
            add = (ImageButton) itemView.findViewById(R.id.todoAddTime);
            ll = (LinearLayout) itemView.findViewById(R.id.todoNotifictionLayout);

        }
    }


    public void removeItem(int position) {
        todoList.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, todoList.size());
    }



}

