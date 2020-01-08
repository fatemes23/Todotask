package com.sanai.tasky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {
    EditText pass;
    EditText confirm;
    Button Sign;
    static SharedPreferences shPref;
    FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        pass = findViewById(R.id.pass);
        confirm = findViewById(R.id.confirm);
        Sign = findViewById(R.id.sign);
        frameLayout = findViewById(R.id.parent);
        frameLayout.getBackground().setAlpha(160);

        shPref = getSharedPreferences("MyPassword",getApplicationContext().MODE_PRIVATE);


        if (shPref.contains("pass")) {
            String userPass = (shPref.getString("pass", null));
            //boro b pin activity v bhsh pass bede
            Intent mainIntent = new Intent(SignUp.this, PinActivity.class);
            SignUp.this.startActivity(mainIntent);
            SignUp.this.finish();

        }
        else{
            Sign.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String pass_str = pass.getText().toString();
                    String confirm_str = confirm.getText().toString();

                    if( pass_str.length()==4 &&  pass_str.equals(confirm_str)){
                        SharedPreferences.Editor sEdit = shPref.edit();
                        sEdit.putString("pass", pass_str);
                        sEdit.apply();

                        SplashActivity.changeShred(pass_str);
                        Toast.makeText(getApplicationContext()," match",Toast.LENGTH_LONG).show();
                        Intent mainIntent = new Intent(SignUp.this, PinActivity.class);
                        mainIntent.putExtra("KEY",pass_str);
                        SignUp.this.startActivity(mainIntent);
                        SignUp.this.finish();
                    }

                    else {
                        Toast.makeText(getApplicationContext(),"password doesn't match",Toast.LENGTH_LONG).show();

                    }


                }
            });
        }




    }
}
