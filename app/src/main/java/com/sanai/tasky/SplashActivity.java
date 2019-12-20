package com.sanai.tasky;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {
    private final int SPLASH_DISPLAY_LENGTH = 3000;
    ImageView logo;
    TextView txt ;
    FrameLayout lay;
    TextView txt2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
       logo = (ImageView)findViewById(R.id.taskyLogo);
       txt = (TextView)findViewById(R.id.taskyTxt);
       txt2 = (TextView)findViewById(R.id.txt2);

        Animation clcwise = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.clockwise);
        Animation fade = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fade);
        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        //logo.startAnimation(clcwise);
        //txt.startAnimation(clcwise);
        lay = (FrameLayout) findViewById(R.id.linear);
        txt.startAnimation(clcwise);
        logo.startAnimation(clcwise);
        txt2.startAnimation(shake);





        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will  start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashActivity.this, PinActivity.class);
                SplashActivity.this.startActivity(mainIntent);
                SplashActivity.this.finish();
            }
        }, 4500);

    }}

