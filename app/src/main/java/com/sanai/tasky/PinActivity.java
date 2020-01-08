package com.sanai.tasky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.support.v4.widget.ImageViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Type;
import java.util.ArrayList;
//
public class PinActivity extends AppCompatActivity {

    ArrayList<Button> buttons = new ArrayList<Button>();
    ImageButton del ;
    static  DataBase dataBase;
    ArrayList<ImageView>  circle = new ArrayList<ImageView>();
    String[] pass= new String[4] ;
    int i =0;
    String correct_pass = "";

    static SharedPreferences shPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pin);

        shPref = getSharedPreferences("password",getApplicationContext().MODE_PRIVATE);


        Bundle extras = getIntent().getExtras();
        if(extras !=null) {
             correct_pass = extras.getString("KEY");
            SharedPreferences.Editor sEdit = shPref.edit();
            sEdit.putString("pass", correct_pass);
            sEdit.apply();
             Toast.makeText(getApplicationContext(),correct_pass,Toast.LENGTH_LONG).show();
        }else {
            correct_pass = shPref.getString("pass",null);

        }



        this.dataBase = new DataBase(this);
        buttons.add((Button) findViewById(R.id.one));
        buttons.add((Button) findViewById(R.id.two));
        buttons.add((Button) findViewById(R.id.three));
        buttons.add((Button) findViewById(R.id.four));
        buttons.add((Button) findViewById(R.id.five));
        buttons.add((Button) findViewById(R.id.six));
        buttons.add((Button) findViewById(R.id.seven));
        buttons.add((Button) findViewById(R.id.eight));
        buttons.add((Button) findViewById(R.id.nine));
        buttons.add((Button) findViewById(R.id.zero));
        del =  findViewById(R.id.delet);
        circle.add((ImageView) findViewById(R.id.cir1));
        circle.add((ImageView) findViewById(R.id.cir2));
        circle.add((ImageView) findViewById(R.id.cir3));
        circle.add((ImageView) findViewById(R.id.cir4));

        for(final Button btn : buttons) {
            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(i<4){
                        pass[i] = (btn.getText().toString());
                        circle.get(i).setImageResource(R.drawable.yellow_circle);
                        i++;
                    }
                    if(i==4){
                        Toast.makeText(getApplicationContext(),correct_pass+"",Toast.LENGTH_LONG).show();
                        checkPass(pass);
                    }

                }
            });
        }
        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(i>0){
                    circle.get(i).setImageResource(R.drawable.ic_brightness_1_black_24dp);
                    i--;

                }
                else if(i==0){
                    circle.get(i).setImageResource(R.drawable.ic_brightness_1_black_24dp);

                }
            }
        });



    }


    public void  checkPass (String[] pass){

        String password = pass[0]+pass[1]+pass[2]+pass[3]+"";
        if (password.equals(correct_pass)){

            Intent mainIntent = new Intent(PinActivity.this, MainActivity.class);
            PinActivity.this.startActivity(mainIntent);
            PinActivity.this.finish();


        }


        Animation shake = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.shake);
        circle.get(0).startAnimation(shake);
        circle.get(1).startAnimation(shake);
        circle.get(2).startAnimation(shake);
        circle.get(3).startAnimation(shake);



        (circle.get(0)).setImageResource(R.drawable.ic_brightness_1_black_24dp);
        (circle.get(1)).setImageResource(R.drawable.ic_brightness_1_black_24dp);
        (circle.get(2)).setImageResource(R.drawable.ic_brightness_1_black_24dp);
        (circle.get(3)).setImageResource(R.drawable.ic_brightness_1_black_24dp);
        i=0;
        return ;

    }

}
