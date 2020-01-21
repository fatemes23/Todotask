package com.sanai.tasky;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.ArrayList;
import java.util.logging.Level;

public class DataBase extends SQLiteOpenHelper {
    public static final String  DATABASE_NAME = "tasks.db";
    public static final String  TASK_TABLE = "taskTable";
    public static final String  col_0 = "taskid";
    public static final String col_1 = "tasktitle"; // column set mikone
    public static final String col_2 = "taskdescription"; //column name bara ye user table
    public static final String col_3 = "duetime"; //
    public static final String col_4 = "alarmtime";
    public static final String col_5 = "dayoftask";
    public static final String col_6 = "status";//done overdue va ..
    public static final String col_7 = "pic";//done overdue va ..
    private    static final int DATABASE_VERSION =    3;




    public DataBase( Context context ) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String CREATE_LEVEL_TABLE = "CREATE    TABLE " + TASK_TABLE
                + "(" + col_0 + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + col_1 + " TEXT,"
                + col_2 + " TEXT,"
                + col_3 + " TEXT,"
                + col_4 + " TEXT,"
                + col_5 + " TEXT,"
                + col_6 + " TEXT,"
                + col_7 + " TEXT  )";
        sqLiteDatabase.execSQL(CREATE_LEVEL_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int DATABASE_VERSION) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TASK_TABLE);
        onCreate(sqLiteDatabase);
    }

    public void insertTask(ToDoTask taskCard , String dayOfTask ,String status ){

        ContentValues values = new ContentValues();
        // values.put(col_0, taskCard.id);
        values.put(col_1, taskCard.todoTitle);
        values.put(col_2, taskCard.todoBody);
        values.put(col_3, taskCard.todoTime);
        values.put(col_4, taskCard.todoNotifictionTime);
        values.put(col_5, dayOfTask);
        values.put(col_6, status);
        values.put(col_7, taskCard.picString);
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert(TASK_TABLE, null, values);

    }

    //// bayad status to do bashe va dayoftask oon chizi bashe ke behesh midim
    public ArrayList<ToDoTask> getTodayasTasks(String status , String day ){
        String sql = "select * from " + TASK_TABLE + " where status = ? and dayoftask = ? " ;
        SQLiteDatabase db = this.getReadableDatabase();
        ArrayList<ToDoTask> todaysTasks = new ArrayList<>();
        Cursor cursor = db.rawQuery(sql,new String[]{status,day});
        if(cursor.moveToFirst()){
            do{
                int id = Integer.parseInt(cursor.getString(0));

                String title = cursor.getString(1);
                String description = cursor.getString(2);
                String dueTime = cursor.getString(3);
                String alarmtime = cursor.getString(4);
                String pic = cursor.getString(7);
                ToDoTask taskCard = new ToDoTask(title,dueTime,description,alarmtime , pic);
                taskCard.id=id;


                todaysTasks.add(taskCard);
            }while (cursor.moveToNext());
        }
        cursor.close();
        return todaysTasks;
    }
    public void update_todo_done(int taskId , String status ){
        SQLiteDatabase dataBase=this.getWritableDatabase();
        String query =  "UPDATE "+ TASK_TABLE +
                " SET "+col_6+ " = '" +status + "'" +
                "WHERE " + col_0 +" = " + taskId ;
        Cursor updateResult = dataBase.rawQuery(query , null);
        updateResult.moveToFirst();//in bara in ke database update beshe
        updateResult.close();
    }

    public void update_taskTitle(int taskId , String taskTitle ){
        SQLiteDatabase dataBase=this.getWritableDatabase();
        String query =  "UPDATE "+ TASK_TABLE +
                " SET "+col_1+ " = '" +taskTitle + "'" +
                "WHERE " + col_0 +" = " + taskId ;
        Cursor updateResult = dataBase.rawQuery(query , null);
        updateResult.moveToFirst();//in bara in ke database update beshe
        updateResult.close();
    }
    public void update_taskdescription(int taskId , String taskdescription ){
        SQLiteDatabase dataBase=this.getWritableDatabase();
        String query =  "UPDATE "+ TASK_TABLE +
                " SET "+col_2+ " = '" +taskdescription + "'" +
                "WHERE " + col_0 +" = " + taskId ;
        Cursor updateResult = dataBase.rawQuery(query , null);
        updateResult.moveToFirst();//in bara in ke database update beshe
        updateResult.close();
    }
    public void update_duetime(int taskId , String duetime ){
        SQLiteDatabase dataBase=this.getWritableDatabase();
        String query =  "UPDATE "+ TASK_TABLE +
                " SET "+col_3+ " = '" +duetime + "'" +
                "WHERE " + col_0 +" = " + taskId ;
        Cursor updateResult = dataBase.rawQuery(query , null);
        updateResult.moveToFirst();//in bara in ke database update beshe
        updateResult.close();
    }
    public void update_alarmtime(int taskId , String alarmtime ){
        SQLiteDatabase dataBase=this.getWritableDatabase();
        String query =  "UPDATE "+ TASK_TABLE +
                " SET "+col_3+ " = '" +alarmtime + "'" +
                "WHERE " + col_0 +" = " + taskId ;
        Cursor updateResult = dataBase.rawQuery(query , null);
        updateResult.moveToFirst();//in bara in ke database update beshe
        updateResult.close();
    }
    public void delete_task( int taskId){
        SQLiteDatabase dataBase=this.getWritableDatabase();
        String query ="DELETE FROM " + TASK_TABLE+
                " WHERE "+ col_0 +" = " + taskId;
        Cursor updateResult = dataBase.rawQuery(query , null);
        updateResult.moveToFirst();//in bara in ke database update beshe
        updateResult.close();
    }


    //______________________________________________________________________
    public String getNameOfDay(int taskId){
        String sql = "select * from " + TASK_TABLE + " where taskid =  " + taskId;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor updateResult = db.rawQuery(sql , null);
        String day="";
        if(updateResult.moveToFirst()) {
            day = updateResult.getString(5);
        }
        return day;
    }

    public void goToNextDay_update_nameofday(int taskId , String day){
        SQLiteDatabase dataBase=this.getWritableDatabase();
        String query =  "UPDATE "+ TASK_TABLE +
                " SET "+col_5+ " = '" +day + "'" +
                "WHERE " + col_0 +" = " + taskId ;
        Cursor updateResult = dataBase.rawQuery(query , null);
        updateResult.moveToFirst();//in bara in ke database update beshe
        updateResult.close();
    }


}