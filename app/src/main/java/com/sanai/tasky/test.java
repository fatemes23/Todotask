package com.sanai.tasky;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Calendar;


 class test extends AppCompatActivity {

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
    // ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        arrayOfButton = new ArrayList<Button>();
        linearLayout = (LinearLayout) findViewById(R.id.llWithAlpha);//sihi poshte safe ye menu
        horizontalScrollView = (HorizontalScrollView) findViewById(R.id.horizentalScrollView);

        //   viewPager = findViewById(R.id.view_pager);


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





//
//
//        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//            @Override
//            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                colorBottun(position);
//                if (position==0 ){
//                    openFrag(Calendar.SATURDAY);
//                }else{
//                    openFrag(position);
//                }
////                if (position==7){
////                    viewPager.setCurrentItem(0);
////                }else{
////                    viewPager.setCurrentItem(position);
////                }
//            }
//
//            @Override
//            public void onPageSelected(int position) {
//                // your code here
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int state) {
//
//            }
//        });
//
//
//
//        class ViewPagerAdapter extends FragmentPagerAdapter {
//
//
//
//             public ViewPagerAdapter(FragmentManager fm) {
//                super(fm);
//            }
//
//            @Override
//            public Fragment getItem(int position) {
//
//
//                switch (position) {
//                    case 0 :
//                        Log.d("taaaaaaapositon" , position + " ");
//
//                        return  openFrag(Calendar.SATURDAY);
//
//                    case Calendar.SUNDAY:
//
//                        Log.d("taaaaaaapositon" , position + " ");
//                        return openFrag(Calendar.SUNDAY);
//
//                    case Calendar.MONDAY:
//
//                        Log.d("taaaaaaapositon" , position + " ");
//                        return  openFrag(Calendar.MONDAY);
//
//                    case Calendar.TUESDAY:
//
//                        Log.d("taaaaaaapositon" , position + " ");
//                       return openFrag(Calendar.TUESDAY);
//
//                    case Calendar.WEDNESDAY:
//                        return  openFrag(Calendar.WEDNESDAY);
//
//                    case Calendar.THURSDAY:
//                        Log.d("taaaaaaapositon" , position + " ");
//                        return openFrag(Calendar.THURSDAY);
//
//                    case Calendar.FRIDAY:
//                        Log.d("taaaaaaapositon" , position + " ");
//                       return openFrag(Calendar.FRIDAY);
//
//
//
//                }
//                return null; //does not happen
//            }
//
//            @Override
//            public int getCount() {
//                return 7; //seven fragments
//            }
//
//            public Fragment openFrag(int day){
//                //TOO  NEXT DAY INA SHANBE 7 E
//                Log.d("daaaaaaaaaaaay", "openFrag: day" + day + "contain " +preDay.contains(day) );
//                Calendar calendar = Calendar.getInstance();
//                int toooday = calendar.get(Calendar.DAY_OF_WEEK);
//                String[] nameOfDays = { "sun", "mon", "tue", "wen", "thur", "fri","sat"};
//
//                if (nextDay.contains(day)){
//                    NextDaysActivity nextDaysActivity = new NextDaysActivity();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("nameOfDay", nameOfDays[day-1]);
//                    nextDaysActivity.setArguments(bundle);
//
//                    return  nextDaysActivity;
//
//                }else if(preDay.contains(day)){
//                    PreviousDaysActivity previousDaysActivity = new PreviousDaysActivity();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("nameOfDay", nameOfDays[day-1]);
//                    previousDaysActivity.setArguments(bundle);
//
//                    return  previousDaysActivity;
//
//                }else if(toooday==day){
//                    TodayFragmentActivity todayFragmentActivity = new TodayFragmentActivity();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("nameOfDay", witchDay());
//                    todayFragmentActivity.setArguments(bundle);
//
//                    return  todayFragmentActivity;
//                }
//                return null;
//            }
//
//
//
//        }
//        //_____________________________
//
//        viewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
        //_______________________________
        TodayFragmentActivity todayFragmentActivity = new TodayFragmentActivity();
        Bundle bundle = new Bundle();
        bundle.putString("nameOfDay", witchDay());// avalin bar today
        todayFragmentActivity.setArguments(bundle);

        fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.add(R.id.containerForFrag, todayFragmentActivity);
        fragmentTransaction.commit();

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
                preDay.add(7);
                preDay.add(1);
                preDay.add(2);
                preDay.add(3);
                preDay.add(4);
                nextDay.add(6);
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
        Log.d("clicktaaaaaaaaaaaag", "doForDay:  clickddddddddddddn");

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
//            if (IntegerDay==7){
//                viewPager.setCurrentItem(0);
//
//            }else{
//                viewPager.setCurrentItem(IntegerDay);
//            }
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerForFrag, previousDaysActivity);
            fragmentTransaction.commit();


        } else if (nextDay.contains(IntegerDay)) {
            NextDaysActivity nextDaysActivity = new NextDaysActivity();
            Bundle bundle = new Bundle();
            bundle.putString("nameOfDay", nameOfDays[IntegerDay-1]);
            nextDaysActivity.setArguments(bundle);
            //Toast.makeText(getApplicationContext(),nameOfDays[IntegerDay]+"   in next",Toast.LENGTH_LONG).show();

//            if (IntegerDay==7){
//                viewPager.setCurrentItem(0);
//            }else{
//                viewPager.setCurrentItem(IntegerDay);
//            }
            Log.d("taaaaadfjfijff", "doForDay: next day");
            fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.containerForFrag, nextDaysActivity);
            fragmentTransaction.commit();

        } else {


            TodayFragmentActivity todayFragmentActivity = new TodayFragmentActivity();
            Bundle bundle = new Bundle();
            bundle.putString("nameOfDay", witchDay());
            todayFragmentActivity.setArguments(bundle);
            Calendar calendar = Calendar.getInstance();
            int toooday = calendar.get(Calendar.DAY_OF_WEEK);

//
//            if (toooday==7){
//                viewPager.setCurrentItem(0);
//            }else{
//                viewPager.setCurrentItem(toooday);
//            }
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

        ArrayList <ToDoTask> temp =SplashActivity.dataBase.getTodayasTasks("todo",previusDay);
        for (int i =0 ; i<temp.size() ; i++){
            SplashActivity.dataBase.update_todo_done(temp.get(i).id,"overdue");
        }

    }


    public  void colorBottun(int currentItem){

        switch (currentItem) {
            // switch (1) {
            case Calendar.SUNDAY:

                for (Button btn : arrayOfButton) {
                    btn.setBackgroundResource(R.drawable.shape_rectangle);
                    btn.setTextColor(Color.BLACK);
                }
                Log.d("taaaaaaaaaaaaaaaag1" , currentItem + " ");
                sun.setBackgroundResource(R.drawable.pink_rec);
                sun.setTextColor(Color.WHITE);


                break;
            case Calendar.MONDAY:
                for (Button btn : arrayOfButton) {
                    btn.setBackgroundResource(R.drawable.shape_rectangle);
                    btn.setTextColor(Color.BLACK);
                }
                Log.d("taaaaaaaaaaaaaaaag1" , currentItem + " ");
                mon.setBackgroundResource(R.drawable.pink_rec);
                mon.setTextColor(Color.WHITE);

                break;
            case Calendar.TUESDAY:
                for (Button btn : arrayOfButton) {
                    btn.setBackgroundResource(R.drawable.shape_rectangle);
                    btn.setTextColor(Color.BLACK);
                }
                Log.d("taaaaaaaaaaaaaaaag1" , currentItem + " ");
                tue.setBackgroundResource(R.drawable.pink_rec);
                tue.setTextColor(Color.WHITE);

                break;
            case Calendar.WEDNESDAY:
                for (Button btn : arrayOfButton) {
                    btn.setBackgroundResource(R.drawable.shape_rectangle);
                    btn.setTextColor(Color.BLACK);
                }
                Log.d("taaaaaaaaaaaaaaaag1" , currentItem + " ");
                wen.setBackgroundResource(R.drawable.pink_rec);
                wen.setTextColor(Color.WHITE);

                break;

            case Calendar.THURSDAY:
                for (Button btn : arrayOfButton) {
                    btn.setBackgroundResource(R.drawable.shape_rectangle);
                    btn.setTextColor(Color.BLACK);
                }
                Log.d("taaaaaaaaaaaaaaaag1" , currentItem + " ");
                tur.setBackgroundResource(R.drawable.pink_rec);
                tur.setTextColor(Color.WHITE);

                break;

            //
            case Calendar.FRIDAY:
                for (Button btn : arrayOfButton) {
                    btn.setBackgroundResource(R.drawable.shape_rectangle);
                    btn.setTextColor(Color.BLACK);
                }
                Log.d("taaaaaaaaaaaaaaaag1" , currentItem + " ");
                fri.setBackgroundResource(R.drawable.pink_rec);
                fri.setTextColor(Color.WHITE);

                break;

            //
            case 0 :
                for (Button btn : arrayOfButton) {
                    btn.setBackgroundResource(R.drawable.shape_rectangle);
                    btn.setTextColor(Color.BLACK);
                }
                Log.d("taaaaaaaaaaaaaaaag1" , currentItem + " ");
                sat.setBackgroundResource(R.drawable.pink_rec);
                sat.setTextColor(Color.WHITE);

                break;
        }

    }



    public Fragment openFrag(int day){
        //TOO  NEXT DAY INA SHANBE 7 E
        Log.d("daaaaaaaaaaaay", "openFrag: day" + day + "contain " +preDay.contains(day) );
        Calendar calendar = Calendar.getInstance();
        int toooday = calendar.get(Calendar.DAY_OF_WEEK);
        String[] nameOfDays = { "sun", "mon", "tue", "wen", "thur", "fri","sat"};

        if (nextDay.contains(day)){
            NextDaysActivity nextDaysActivity = new NextDaysActivity();
            Bundle bundle = new Bundle();
            bundle.putString("nameOfDay", nameOfDays[day-1]);
            nextDaysActivity.setArguments(bundle);

            return  nextDaysActivity;

        }else if(preDay.contains(day)){
            PreviousDaysActivity previousDaysActivity = new PreviousDaysActivity();
            Bundle bundle = new Bundle();
            bundle.putString("nameOfDay", nameOfDays[day-1]);
            previousDaysActivity.setArguments(bundle);

            return  previousDaysActivity;

        }else if(toooday==day){
            TodayFragmentActivity todayFragmentActivity = new TodayFragmentActivity();
            Bundle bundle = new Bundle();
            bundle.putString("nameOfDay", witchDay());
            todayFragmentActivity.setArguments(bundle);

            return  todayFragmentActivity;
        }
        return null;
    }



}