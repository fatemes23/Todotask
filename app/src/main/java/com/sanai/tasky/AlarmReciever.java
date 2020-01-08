package com.sanai.tasky;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.Ringtone;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Handler;
import android.os.Vibrator;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import static android.support.v4.content.WakefulBroadcastReceiver.startWakefulService;

public class AlarmReciever extends BroadcastReceiver
{


    Ringtone ringtone;
    String title;
    String message;

    @Override
    public void onReceive(final Context context, Intent intent) {
        //this will update the UI with messag
        //this will sound the alarm tone
        //this will sound the alarm once, if you wish to
        //raise alarm in loop continuously then use MediaPlayer and setLooping(true)
        title = intent.getStringExtra("title");
        message = intent.getStringExtra("message");

        Uri alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE);
        if (alarmUri == null) {
            alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        }
        ringtone = RingtoneManager.getRingtone(context, alarmUri);
        ringtone.play();

        //After 1s stop the alarm
        // You can adjust the time depending upon your requirement.
        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ringtone.stop();
            }
        }, 7000);

        //this will send a notification message
        ComponentName comp = new ComponentName(context.getPackageName(),
                MainActivity.class.getName());
        startWakefulService(context, (intent.setComponent(comp)));
        setResultCode(Activity.RESULT_OK);

        NotificationHelper notificationHelper =  new NotificationHelper( context);
        NotificationCompat.Builder nb = notificationHelper.getChanellNotification(title,message);
        notificationHelper.getManager().notify(1,nb.build()  );


    }
}