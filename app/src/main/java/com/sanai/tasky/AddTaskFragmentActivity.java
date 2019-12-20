package com.sanai.tasky;


import android.app.TimePickerDialog;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;


import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;


public class AddTaskFragmentActivity extends Fragment{


    Button  cancle;
    Button  save;
    TextView todoTime ;
    TextView alarmTime ;
    EditText title_editText , body_editTExt  ;
    String title , body , hour1 , hour2 , min1 , min2,time1,time2;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_add_task_fragment,container,false);

        title_editText = view.findViewById(R.id.titleInput);
        body_editTExt = view.findViewById(R.id.noteInput);


        //************************************************************************************************
        cancle = (Button) view.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().setCustomAnimations( R.anim.slide_down_to_up, R.anim.slide_down_to_up)
                        .remove(AddTaskFragmentActivity.this).commit();

                TodayFragmentActivity.cancleNewTask();


            }
        });

        //*************************************************************************************************

        todoTime = (TextView) view.findViewById(R.id.toDoTimeSelect);
        todoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomTimePicker(todoTime);
            }
        });
        //_________________________________________________________________________
        alarmTime = (TextView) view.findViewById(R.id.alarmTimeSelect);
        alarmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomTimePicker(alarmTime);
            }
        });


        //******************************************************************************
        save = (Button) view.findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                title = title_editText.getText().toString();
                body = body_editTExt.getText().toString();
                //__________________________________________________
                String todoTime_str = todoTime.getText().toString();
                String[] separated = todoTime_str.split(" ");
                time1=separated[0]; // to do time

                //__________________________________________________

                String alarmTime_str = alarmTime.getText().toString();
                 separated = alarmTime_str.split(" ");
                time2=separated[0]; // to do time

                //__________________________________________________




                if(title.matches("")&& time1.matches("")){
                    Toast.makeText(getActivity(),"title  and to do time can't be empty",Toast.LENGTH_LONG).show();


                }
                 else if(title.matches("")) {
                     Toast.makeText(getActivity(),"title can't be empty",Toast.LENGTH_LONG).show();
                }else if (time1.matches("")){
                     Toast.makeText(getActivity(),"todo time can't be empty",Toast.LENGTH_LONG).show();

                 }else{
                    getFragmentManager().beginTransaction().setCustomAnimations( R.anim.slide_down_to_up, R.anim.slide_down_to_up)
                            .remove(AddTaskFragmentActivity.this).commit();
                    TodayFragmentActivity.saveNewTask(title,body,time1,time2);
                }



            }
        });

        //**************************************************************************

        return  view;
    }


    public void showCustomTimePicker(final TextView txtview)
    {

        final Calendar myCalender = Calendar.getInstance();
        int hour = myCalender.get(Calendar.HOUR_OF_DAY);
        int minute = myCalender.get(Calendar.MINUTE);


        TimePickerDialog.OnTimeSetListener myTimeListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if (view.isShown()) {
                    myCalender.set(Calendar.HOUR_OF_DAY, hourOfDay);
                    myCalender.set(Calendar.MINUTE, minute);


                    Spannable wordtoSpan = new SpannableString(hourOfDay +":" + minute +"  tap to change the time" );
                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#FEDC97")), 0, (hourOfDay +":" + minute).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#D8D8D8")), (hourOfDay +":" + minute).length()+1, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtview.setText(wordtoSpan);

                }
            }


        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), TimePickerDialog.THEME_HOLO_DARK , myTimeListener, hour, minute, true);
        timePickerDialog.setTitle("Choose time :");
        timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        timePickerDialog.show();
    }



}
