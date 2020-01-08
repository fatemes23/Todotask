package com.sanai.tasky;

import android.app.AlarmManager;

import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import android.support.v4.app.FragmentTransaction;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TimePicker;
import android.widget.Toast;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class MainActivity extends AppCompatActivity {

    Button sat, sun, mon, tue, wen, tur, fri;
    ImageButton menu;
    ArrayList<Integer> preDay, nextDay;
    FragmentTransaction fragmentTransaction;
    FrameLayout frameLayout;
    ArrayList<Button> arrayOfButton;
    int closeOrShowMenu = 0; //close 0 show 1
    LinearLayout linearLayout;
    HorizontalScrollView horizontalScrollView;
    Button change_password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayOfButton = new ArrayList<Button>();
        linearLayout = (LinearLayout) findViewById(R.id.llWithAlpha);//sihi poshte safe ye menu
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizentalScrollView);

        menu = (ImageButton) findViewById(R.id.menu);
        sat = (Button) findViewById(R.id.sat);
        sun = (Button) findViewById(R.id.sun);
        mon = (Button) findViewById(R.id.mon);
        tue = (Button) findViewById(R.id.tue);
        wen = (Button) findViewById(R.id.wen);
        tur = (Button) findViewById(R.id.thur);
        fri = (Button) findViewById(R.id.fri);
        arrayOfButton.add(sat);
        arrayOfButton.add(sun);
        arrayOfButton.add(mon);
        arrayOfButton.add(tue);
        arrayOfButton.add(wen);
        arrayOfButton.add(tur);
        arrayOfButton.add(fri);

        frameLayout = (FrameLayout) findViewById(R.id.containerForFrag);

        preDay = new ArrayList<Integer>();
        nextDay = new ArrayList<Integer>();
        //______________________________;

        TodayFragmentActivity todayFragmentActivity = new TodayFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("nameOfDay", witchDay());// avalin bar today
        todayFragmentActivity.setArguments(bundle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.containerForFrag, todayFragmentActivity);
        fragmentTransaction.commit();

        final Animation show = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_close_menu);
        final Animation close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_menu_show);

        final LinearLayout menu_frame = (LinearLayout) findViewById(R.id.menu_layout);
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (closeOrShowMenu == 0) {
                    menu.setImageResource(R.drawable.ic_close_black_24dp);
                    horizontalScrollView.setVisibility(View.GONE);
                    linearLayout.setAlpha((float) 0.4);
                    menu_frame.startAnimation(show);
                    menu_frame.setVisibility(View.VISIBLE);
                    closeOrShowMenu = 1;
                } else {

                    menu_frame.startAnimation(close);
                    linearLayout.setAlpha((float) 1.0);
                    menu_frame.setVisibility(View.GONE);
                    horizontalScrollView.setVisibility(View.VISIBLE);
                    menu.setImageResource(R.drawable.ic_menu);
                    closeOrShowMenu = 0;
                }

            }
        });

        makeArray();
        setOverduForPreiveousDay();
        //callAlarmForTodayTask();






    }
//    public void callAlarmForTodayTask(){
//        DataBase dataBase = PinActivity.dataBase;
//        ArrayList<ToDoTask> toDoTasks = new ArrayList<>();
//        toDoTasks = dataBase.getTodayasTasks("todo",witchDay());
//        for (int i =0 ; i < toDoTasks.size();i++){
//            String time = toDoTasks.get(i).todoNotifictionTime;
//            String title = toDoTasks.get(i).todoTitle;
//            String messege = toDoTasks.get(i).todoBody;
//
//            if(!time.equals("")){
//                String[] separated = time.split(":");
//                String hour =separated[0];
//                String min =separated[1];
//
//
//                Calendar calendar = Calendar.getInstance();
//                calendar.setTimeInMillis(System.currentTimeMillis());
//                calendar.set(Calendar.HOUR_OF_DAY, Integer.parseInt(hour));
//                calendar.set(Calendar.MINUTE, Integer.parseInt(min));
//                calendar.set(Calendar.SECOND, 0);
//
//                startAlertAtParticularTime(calendar,title , messege);
//            }
//
//
//        }
//    }

//
//    public void startAlertAtParticularTime(Calendar calendar, String title , String messege) {
//
//          if(!calendar.before(Calendar.getInstance())){
//        // alarm first vibrate at 14 hrs and 40 min and repeat itself at ONE_HOUR interval
//        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
//        Intent intent = new Intent(this, AlarmReciever.class);
//        intent.putExtra("title" , title);
//        intent.putExtra("message" , messege);
//
//
//        if (calendar.before(Calendar.getInstance())) {
//            calendar.add(Calendar.DATE, 1);
//        }
//
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                this.getApplicationContext(), 1, intent, 0);
//        alarmManager.setExact(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis() ,pendingIntent);
//          }
//
//
//    }




    public void  cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(this, AlarmReciever.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                this.getApplicationContext(), 1, intent, 0);
        alarmManager.cancel(pendingIntent);

    }

    public void makeArray() {
        // saturday 0
        //sunday 1
        //monday 2
        //tuesday 3
        //wensday 4
        //thursday 5
        //friday 6

        Calendar calendar = Calendar.getInstance();

        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch (day) {
            // switch (1) {
            case Calendar.SUNDAY:
                // Current day is Sunday
                sun.setBackgroundResource(R.drawable.pink_rec);
                sun.setText("Today");
                preDay.add(7);
                nextDay.add(2);
                nextDay.add(3);
                nextDay.add(4);
                nextDay.add(5);
                nextDay.add(6);

                break;
            case Calendar.MONDAY:
                // Current day is Monday
                mon.setBackgroundResource(R.drawable.pink_rec);
                mon.setText("Today");
                preDay.add(7);
                preDay.add(1);
                nextDay.add(3);
                nextDay.add(4);
                nextDay.add(5);
                nextDay.add(6);
                break;
            case Calendar.TUESDAY:
                // etc.
                tue.setBackgroundResource(R.drawable.pink_rec);
                tue.setText("Today");
                preDay.add(7);
                preDay.add(1);
                preDay.add(2);
                nextDay.add(4);
                nextDay.add(5);
                nextDay.add(6);
                break;
            case Calendar.WEDNESDAY:
                //
                wen.setBackgroundResource(R.drawable.pink_rec);
                wen.setText("Today");
                preDay.add(7);
                preDay.add(1);
                preDay.add(2);
                preDay.add(3);
                nextDay.add(5);
                nextDay.add(6);
                break;

            case Calendar.THURSDAY:
                tur.setBackgroundResource(R.drawable.pink_rec);
                tur.setText("Today");
                break;

            //
            case Calendar.FRIDAY:
                fri.setBackgroundResource(R.drawable.pink_rec);
                fri.setText("Today");
                preDay.add(7);
                preDay.add(1);
                preDay.add(2);
                preDay.add(3);
                preDay.add(4);
                preDay.add(5);
                break;

            //
            case Calendar.SATURDAY:
                sat.setBackgroundResource(R.drawable.pink_rec);
                sat.setText("Today");
                nextDay.add(1);
                nextDay.add(2);
                nextDay.add(3);
                nextDay.add(4);
                nextDay.add(5);
                nextDay.add(6);
                break;

            //
        }

    }

    public void doForDay(View view) {

        for (Button btn : arrayOfButton) {
            btn.setBackgroundResource(R.drawable.shape_rectangle);
            btn.setTextColor(Color.BLACK);
        }

        Button button = (Button) view;
        button.setBackgroundResource(R.drawable.pink_rec);
        button.setTextColor(Color.WHITE);



        String txtButton = button.getText().toString();
        int IntegerDay = -1;

        if (txtButton.equals("Sat")) {
            IntegerDay = 7;
        } else if (txtButton.equals("Sun")) {
            IntegerDay = 1;

        } else if (txtButton.equals("Mon")) {
            IntegerDay = 2;

        } else if (txtButton.equals("Tue")) {
            IntegerDay = 3;

        } else if (txtButton.equals("Wen")) {
            IntegerDay = 4;

        } else if (txtButton.equals("Tur")) {
            IntegerDay = 5;

        } else if (txtButton.equals("Fri")) {
            IntegerDay = 6;

        }




        //**************************//
        String[] nameOfDays = { "sun", "mon", "tue", "wen", "thur", "fri","sat"};

        if (preDay.contains(IntegerDay)) {


            PreviousDaysActivity previousDaysActivity = new PreviousDaysActivity();
            Bundle bundle = new Bundle();
            bundle.putString("nameOfDay", nameOfDays[IntegerDay-1]);
            previousDaysActivity.setArguments(bundle);
            //Toast.makeText(getApplicationContext(),nameOfDays[IntegerDay]+"inprevious",Toast.LENGTH_LONG).show();
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerForFrag, previousDaysActivity);
            fragmentTransaction.commit();

        } else if (nextDay.contains(IntegerDay)) {
            NextDaysActivity nextDaysActivity = new NextDaysActivity();
            Bundle bundle = new Bundle();
            bundle.putString("nameOfDay", nameOfDays[IntegerDay-1]);
            nextDaysActivity.setArguments(bundle);
            //Toast.makeText(getApplicationContext(),nameOfDays[IntegerDay]+"   in next",Toast.LENGTH_LONG).show();

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerForFrag, nextDaysActivity);
            fragmentTransaction.commit();
        } else {


            TodayFragmentActivity todayFragmentActivity = new TodayFragmentActivity();
            Bundle bundle = new Bundle();
            bundle.putString("nameOfDay", witchDay());
            todayFragmentActivity.setArguments(bundle);
            // Toast.makeText(getApplicationContext(),nameOfDays[IntegerDay]+"today",Toast.LENGTH_LONG).show();

            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerForFrag, todayFragmentActivity);
            fragmentTransaction.commit();

        }

    }

    public String witchDay() {

        // saturday 7
        //sunday 1
        //monday 2
        //tuesday 3
        //wensday 4
        //thursday 5
        //friday 6

        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch(day){
            //  switch(1){
            case Calendar.SATURDAY:
                return "sat";
            case Calendar.SUNDAY:
                return "sun";
            case Calendar.MONDAY:
                return "mon";
            case Calendar.TUESDAY:
                return "tue";
            case Calendar.WEDNESDAY:
                return "wen";
            case Calendar.THURSDAY:
                return "thur";
            case Calendar.FRIDAY:
                return "fri";
            default:
                return null;


        }


    }

    public void setOverduForPreiveousDay(){
        String previusDay ;
        Calendar calendar = Calendar.getInstance();
        int day = calendar.get(Calendar.DAY_OF_WEEK);
        switch(day){
            //switch(1){
            case Calendar.SUNDAY:
                previusDay= "sat";
            case Calendar.MONDAY:
                previusDay= "sun";
            case Calendar.TUESDAY:
                previusDay= "mon";
            case Calendar.WEDNESDAY:
                previusDay= "tue";
            case Calendar.THURSDAY:
                previusDay= "wen";
            case Calendar.FRIDAY:
                previusDay= "thur";
            case Calendar.SATURDAY:
                previusDay= "fri";
            default:
                previusDay= "sat";

        }

        ArrayList <ToDoTask> temp =PinActivity.dataBase.getTodayasTasks("todo",previusDay);
        for (int i =0 ; i<temp.size() ; i++){
            PinActivity.dataBase.update_todo_done(temp.get(i).id,"overdue");
        }

    }


}