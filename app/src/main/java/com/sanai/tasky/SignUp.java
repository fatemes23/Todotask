package com.sanai.tasky;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;

import android.widget.Toast;

import com.google.gson.JsonObject;
import com.koushikdutta.async.future.FutureCallback;
import com.koushikdutta.ion.Ion;


public class SignUp extends AppCompatActivity {
    EditText pass;//uernmae
    EditText confirm;//password
    Button logIn;

    FrameLayout frameLayout;
    SharedPreferences.Editor editor;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        pass = findViewById(R.id.pass);
        confirm = findViewById(R.id.confirm);
        logIn = findViewById(R.id.sign);
        frameLayout = findViewById(R.id.parent);
        frameLayout.getBackground().setAlpha(160);

        editor = getSharedPreferences("tokesSharedPref", MODE_PRIVATE).edit();


        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                total();

            }
        });



    }

    public void total(){

        EditText usernametext = (EditText) findViewById(R.id.pass);
        final EditText passwordtext = (EditText) findViewById(R.id.confirm);


        if(!(usernametext.getText().toString().matches("")&& passwordtext.getText().toString().matches(""))){

            SharedPreferences prefs = getSharedPreferences("tokesSharedPref", MODE_PRIVATE);
            String tokenAcsess = prefs.getString("usertoken", "N");//"No name defined" is the default value.

            if ((tokenAcsess.equals("N"))) {//bar e avl bood

                getToken(usernametext.getText().toString(),passwordtext.getText().toString());

            }else{

                final String passwordOfToken = prefs.getString("password", "N");//"No name defined" is the default value.
                Ion.with(SignUp.this)
                        .load("http://192.241.136.152:3000/api/user/")
                        .setHeader("Authorization", "Bearer " + tokenAcsess)
                        .asString()
                        .setCallback(new FutureCallback<String>() {
                            @Override
                            public void onCompleted(Exception e, String result) {


                                String esm;
                                String famil;
                                String[] arrOfStr = result.split(",", -1);
                                Log.d("result3", arrOfStr[2].split(":", 2)[1]);

                                String usernameOfToken = arrOfStr[2].split(":", 2)[1].replace("\"", "");
                                EditText usernametext = (EditText) findViewById(R.id.pass);

                                Log.d(" edit text token" ,usernametext +" " + usernameOfToken);
                                if (usernameOfToken.equals(usernametext.getText().toString()) && passwordOfToken.equals(passwordtext.getText().toString()) ){

                                    Intent myIntent = new Intent(SignUp.this, MainActivity.class);
                                    esm = arrOfStr[3].split(":", 2)[1].toString().replace("\"", "");
                                    myIntent.putExtra("name" , esm);
                                    startActivity(myIntent);

                                }else{
                                   Toast.makeText(getApplicationContext() , " user or password incorrect" ,Toast.LENGTH_LONG).show();

                                }

                            }
                        });
            }


        }else {

            //age  edit text null bo0d
        }




    }

    public void getToken(final String username, final String password) {

        // if ((test.equals("N"))) {
        Ion.getDefault(this).configure().setLogging("MyLogs", Log.DEBUG);

        JsonObject json = new JsonObject();
        json.addProperty("username", username);
        json.addProperty("password", password);

        Ion.with(SignUp.this)
                .load("http://192.241.136.152:3000/api/token/")
                .setJsonObjectBody(json)
                .asJsonObject()
                .setCallback(new FutureCallback<JsonObject>() {
                    @Override
                    public void onCompleted(Exception e, JsonObject result) {

                        try{


                        editor.putString("usertoken", result.get("access").toString().replace("\"", ""));


                        Ion.with(SignUp.this)
                                .load("http://192.241.136.152:3000/api/user/")
                                .setHeader("Authorization", "Bearer " + result.get("access").toString().replace("\"", ""))
                                .asString()
                                .setCallback(new FutureCallback<String>() {
                                    @Override
                                    public void onCompleted(Exception e, String result) {
                                      //  TextView textView = (TextView) findViewById(R.id.textView);
                                        Log.d("result2", result);
                                        String esm;
                                        String famil;
                                        String[] arrOfStr = result.split(",", -1);
                                        Log.d("result3", arrOfStr[3].split(":", 2)[1]);
                                       // textView.setText(arrOfStr[3].split(":", 2)[1].toString().replace("\"", ""));
                                        String usernameOfToken = arrOfStr[2].split(":", 2)[1].replace("\"", "");

                                        if (usernameOfToken.equals(username) ){
                                            editor.putString("password" ,password);
                                            editor.apply();
                                            Intent myIntent = new Intent(SignUp.this, MainActivity.class);
                                            esm = arrOfStr[3].split(":", 2)[1].toString().replace("\"", "");
                                            myIntent.putExtra("name" , esm);
                                            startActivity(myIntent);
                                        }

                                        esm = arrOfStr[3].split(":", 2)[1].toString().replace("\"", "");
                                        famil = arrOfStr[4].split(":", 2)[1].toString().replace("\"", "");
                                        Log.d("esm and famil", esm + " " + famil);
                                    }
                                });

                        }catch (Exception e1){
                            Toast.makeText(getApplicationContext() , "username and password not valid"  ,Toast.LENGTH_LONG).show();

                        }
                    }


                });



    }
}









