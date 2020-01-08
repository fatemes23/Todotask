package com.sanai.tasky;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import java.util.Locale;

public class NotificationHelper extends ContextWrapper {
    public static final String id ="notif";
    public static final String name ="todo";
    private NotificationManager notificationManager;
    public NotificationHelper(Context base) {
        super(base);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.O){
            createChannel();
        }

    }
    @TargetApi(Build.VERSION_CODES.O)
    public void createChannel(){
        NotificationChannel notificationChannel = new NotificationChannel(id,name, NotificationManager.IMPORTANCE_DEFAULT);
        notificationChannel.enableLights(true);
        notificationChannel.enableVibration(true);
        notificationChannel.setLightColor(R.color.khakestry);
        notificationChannel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);

        getManager().createNotificationChannel(notificationChannel);
    }
    public NotificationManager getManager(){
        if(notificationManager==null){
            notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }

    public NotificationCompat.Builder  getChanellNotification(String title , String message  ){
        return new NotificationCompat.Builder(getApplicationContext(),id )
                .setContentTitle(title)
                .setContentText( message)
                .setSmallIcon(R.drawable.ic_tasky_logo);
    }




}

