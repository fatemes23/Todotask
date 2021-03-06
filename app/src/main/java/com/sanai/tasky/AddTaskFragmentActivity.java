package com.sanai.tasky;


import android.Manifest;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
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
import android.widget.ImageView;
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
    String title , body ,time1,time2;
    String nameOfDay;
    String whichFrag;
    ImageView selectedPic ; //ax entkhab shode imageview
    TextView selectPic ; //button bry raftn b camera v grftn y ax
    Bitmap photo;
    String img_name = "";
    private static final int CAMERA_REQUEST = 1888;
    private static final int MY_CAMERA_PERMISSION_CODE = 100;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.activity_add_task_fragment,container,false);


        title_editText = view.findViewById(R.id.titleInput);
        body_editTExt = view.findViewById(R.id.noteInput);
        nameOfDay = this.getArguments().getString("nameOfDay");
        whichFrag = this.getArguments().getString("whichFrag");
        selectedPic = view.findViewById(R.id.selectedImg);
        selectPic  = view.findViewById(R.id.selectPic);

        //************************************************************************************************

        selectPic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED)
                {
                    requestPermissions(new String[]{Manifest.permission.CAMERA}, MY_CAMERA_PERMISSION_CODE);
                }
                else
                {
                    Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                    startActivityForResult(cameraIntent, CAMERA_REQUEST);
                }

            }
        });

        //************************************************************************************************
        cancle = (Button) view.findViewById(R.id.cancle);
        cancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().beginTransaction().setCustomAnimations( R.anim.slide_down_to_up, R.anim.slide_down_to_up)
                        .remove(AddTaskFragmentActivity.this).commit();
                if (whichFrag.equals("today")){
                    TodayFragmentActivity.cancleNewTask();

                }else if( whichFrag.equals("next")){
                    NextDaysActivity.cancleNewTask();

                }


            }
        });

        //*************************************************************************************************

        todoTime = (TextView) view.findViewById(R.id.toDoTimeSelect);
        todoTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomTimePickerForTodo(todoTime);
            }
        });
        //_________________________________________________________________________
        alarmTime = (TextView) view.findViewById(R.id.alarmTimeSelect);
        alarmTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCustomTimePickerForAlarm(alarmTime);
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
                time2=separated[0]; // notifiction time

                if (time2.equals("")){
                    Toast.makeText(getActivity(),time2,Toast.LENGTH_LONG).show();

                }
                //__________________________________________________



                //__________________________________________________




                if(title.matches("")&& time1.matches("")){
                    Toast.makeText(getActivity(),"title or to do time can't be empty",Toast.LENGTH_LONG).show();


                }
                else if(title.matches("")) {
                    Toast.makeText(getActivity(),"title can't be empty",Toast.LENGTH_LONG).show();
                }else if (time1.matches("")){
                    Toast.makeText(getActivity(),"todo time can't be empty",Toast.LENGTH_LONG).show();

                }else{
                    getFragmentManager().beginTransaction().setCustomAnimations( R.anim.slide_down_to_up, R.anim.slide_down_to_up)
                            .remove(AddTaskFragmentActivity.this).commit();
                    ToDoTask toDoTask = new ToDoTask(title,time1,body,time2,img_name);
                    SplashActivity.dataBase.insertTask(toDoTask,nameOfDay,"todo");

                    if (whichFrag.equals("today")){
                        TodayFragmentActivity todayFragmentActivity = new TodayFragmentActivity();
                        todayFragmentActivity.saveNewTask();
                    }else if( whichFrag.equals("next")){
                        NextDaysActivity nextDaysActivity = new NextDaysActivity();
                        nextDaysActivity.saveNewTask();
                    }


                }



            }
        });

        //**************************************************************************

        return  view;
    }


    public void showCustomTimePickerForTodo(final TextView txtview )
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
                    myCalender.set(Calendar.SECOND,0);


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

    public void showCustomTimePickerForAlarm(final TextView txtview )
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
                    myCalender.set(Calendar.SECOND,0);
                    //day ro hm inja set koni...n


                    Spannable wordtoSpan = new SpannableString(hourOfDay +":" + minute +"  tap to change the time" );
                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#FEDC97")), 0, (hourOfDay +":" + minute).length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    wordtoSpan.setSpan(new ForegroundColorSpan(Color.parseColor("#D8D8D8")), (hourOfDay +":" + minute).length()+1, wordtoSpan.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    txtview.setText(wordtoSpan);


                }
                if(whichFrag=="today") {// dar vaghe bayad roooz ro be calender ezaf konm // vali migm age emrooz bood seet kon..
                    title = title_editText.getText().toString();
                    body = body_editTExt.getText().toString();
                    myCalender.set(Calendar.DAY_OF_WEEK, Calendar.getInstance().get(Calendar.DAY_OF_WEEK));
                    startAlertAtParticularTime(myCalender, title, body);
                }

            }




        };

            TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(), TimePickerDialog.THEME_HOLO_DARK, myTimeListener, hour, minute, true);
            timePickerDialog.setTitle("Choose time :");
            timePickerDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
            timePickerDialog.show();

    }
    public void startAlertAtParticularTime(Calendar calendar, String title , String messege) {

      if(!calendar.before(Calendar.getInstance())){
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(getContext(), AlarmReciever.class);
        intent.putExtra("title" , title);
        intent.putExtra("message" , messege);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getContext(), 1, intent, 0);
        alarmManager.setExact(alarmManager.RTC_WAKEUP,calendar.getTimeInMillis() ,pendingIntent);

        }




    }
    public void  cancelAlarm(){
        AlarmManager alarmManager = (AlarmManager) getContext().getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getContext(), AlarmReciever.class);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(
                getContext(), 1, intent, 0);
        alarmManager.cancel(pendingIntent);

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults)
    {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == MY_CAMERA_PERMISSION_CODE)
        {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED)
            {
                Toast.makeText(getContext(), "camera permission granted", Toast.LENGTH_LONG).show();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent, CAMERA_REQUEST);
            }
            else
            {
                Toast.makeText(getContext(), "camera permission denied", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == CAMERA_REQUEST && resultCode == Activity.RESULT_OK)
        {
            photo = (Bitmap) data.getExtras().get("data");

            if (photo !=  null){

                ImageClass img = new ImageClass();
                NameGenerator nameGenerator = new NameGenerator();
                 img_name = nameGenerator.randomIdentifier();
                img.createDirectoryAndSaveFile(photo, img_name+".JPEG"   );

                selectedPic.setImageBitmap(img.byteArrayToBitMap(img.loadImageFromStorage1(img_name +".JPEG")));

            }


            //selectedPic.setImageBitmap(photo);


            //String str_adrs = saveToInternalStorage(photo);
            //Toast.makeText(getContext(),"the adrs is : "+str_adrs,Toast.LENGTH_LONG).show();
            //loadImageFromStorage("/data/user/0/com.sanai.tasky/app_imageDir/profile.jpg",selectedPic);
            //loadImageFromStorage(str_adrs,selectedPic);

        }
    }


}
