package com.sanai.tasky;

import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageButton;
import android.widget.TextView;



import java.util.List;


public class DoneTaskAdapter extends RecyclerView.Adapter<DoneTaskAdapter.MyTaskViewHolder> {

    List<DoneTask> doneTaskList;
    FragmentActivity mainActivity;

    public DoneTaskAdapter(List<DoneTask> myTasks, FragmentActivity mainActivity) {
        this.doneTaskList = myTasks;
        this.mainActivity = mainActivity;
    }

    @Override
    public MyTaskViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_done_task, viewGroup, false);
        MyTaskViewHolder pvh = new MyTaskViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(final MyTaskViewHolder viewHolder, final int i) {

        //viewHolder.icon.setImageResource(doneTaskList.get(i).iconRsc);
       DoneTask doneTask =doneTaskList.get(i);
       viewHolder.title.setText(doneTask.getDoneTitle());

    }

    @Override
    public int getItemCount() {
        return doneTaskList.size();
    }

    public static class MyTaskViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        /*TextView time ;
        TextView body;
        TextView notTime;
        ImageButton add;*/

        MyTaskViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.doneTitle);

        }
    }

}

