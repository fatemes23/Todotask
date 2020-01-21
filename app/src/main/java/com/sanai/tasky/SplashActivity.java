package com.sanai.tasky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;

import org.w3c.dom.Text;

public class SplashActivity extends AppCompatActivity {
    ImageView logo;
    TextView txt ;
    FrameLayout lay;
    TextView txt2;
    static  DataBase dataBase ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        logo = findViewById(R.id.taskyLogo);
        txt = findViewById(R.id.taskyTxt);
        txt2 = findViewById(R.id.txt2);



        this.dataBase = new DataBase(this);
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


                //______________________________________________________________________________________

                SharedPreferences prefs = getSharedPreferences("tokesSharedPref", MODE_PRIVATE);
                String tokenAcsess = prefs.getString("usertoken", "N");//"No name defined" is the default value.
                if ( tokenAcsess.equals("N")){
                    Intent mainIntent = new Intent(SplashActivity.this, SignUp.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }else {



                        Ion.with(SplashActivity.this)
                                .load("http://192.241.136.152:3000/api/user/")
                                .setHeader("Authorization", "Bearer " + tokenAcsess)
                                .asString()
                                .setCallback(new FutureCallback<String>() {
                                    @Override
                                    public void onCompleted(Exception e, String result) {


                                        String esm;
                                        String[] arrOfStr = result.split(",", -1);
                                        Log.d("result3", arrOfStr[2].split(":", 2)[1]);
                                        Log.d("essssssssssssm", esm);
                                            Intent myIntent = new Intent(SplashActivity.this, MainActivity.class);

                                            myIntent.putExtra("name" , esm);
                                            startActivity(myIntent);



                                    }
                                });








                    Intent mainIntent = new Intent(SplashActivity.this, MainActivity.class);
                    SplashActivity.this.startActivity(mainIntent);
                    SplashActivity.this.finish();
                }
                //______________________________________________________________________________________

            }
        }, 4500);

    }



}