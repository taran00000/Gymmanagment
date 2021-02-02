package com.example.gymmanagement;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class RegisterPage extends AppCompatActivity {

    TextView lg;
    EditText unm,email,pass,mobilenno,raddress;;
    String username,emailid,password,mobileno;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    Button signup;
    String  mpattern="^[+]?[0-9]{10,13}$";

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    String mnp,ab;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        mnp=getIntent().getStringExtra("ustypes");

        lg=(TextView)findViewById(R.id.lg);
        signup=(Button)findViewById(R.id.r_btn);
        unm=(EditText)findViewById(R.id.rusername);
        email=(EditText)findViewById(R.id.remail);
        raddress=(EditText)findViewById(R.id.raddres);
        pass=(EditText)findViewById(R.id.rpassword);
        mobilenno=(EditText)findViewById(R.id.rmobileno);
        asyncHttpClient=new AsyncHttpClient();
        requestParams =new RequestParams();

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkfeild();
            }
        });


        lg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RegisterPage.this, Login_Activity.class);
                startActivity(i);
                finish();

            }
        });
    }
    private void insertdata() {
        requestParams.put("name",unm.getText().toString());
        requestParams.put("email",email.getText().toString());
        requestParams.put("imeino",mobilenno.getText().toString());
        requestParams.put("password",pass.getText().toString());
        requestParams.put("cpsw",pass.getText().toString());
        requestParams.put("ustype","user");
        requestParams.put("gender","male");
        requestParams.put("address",raddress.getText().toString());
        requestParams.put("androidid",mobilenno.getText().toString());
        asyncHttpClient.post("http://192.168.2.166/register.php",requestParams,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
              /*  try {

                    ab = response.getString("response");
                }catch (JSONException e){
                    e.printStackTrace();
                }*/
                Intent uh = new Intent(RegisterPage.this, Login_Activity.class);
                startActivity(uh);
                //  if (ab.equals("Insert Success")) {
                //  Intent uh = new Intent(RegisterPage.this, Login_Activity.class);
                //  startActivity(uh);
                // } else if (ab.equals("User already registered")) {
                //  Toast.makeText(RegisterPage.this, "This Email Is Already Registered", Toast.LENGTH_SHORT).show();
                // } else {
                //     Toast.makeText(RegisterPage.this, "Please Check Data Connection", Toast.LENGTH_SHORT).show();
                //}
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONArray errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(RegisterPage.this, errorResponse+"", Toast.LENGTH_SHORT).show();
            }
        });

    }
    private void checkfeild() {

        username=unm.getText().toString();
        emailid=email.getText().toString();
        password=pass.getText().toString();
        mobileno=mobilenno.getText().toString();

        if(username.isEmpty())
        {
            unm.setError("Please Enter User Name");
        }
        else if(!emailid.matches(emailpattern))
        {
            email.setError("Please Enter Valid Email ");
        }
        else if(emailid.isEmpty())
        {
            email.setError("Please Enter Email ");
        }
        else if(raddress.getText().toString().isEmpty())
        {
            raddress.setError("Please Enter Address ");
        }
        else if(mobileno.isEmpty())
        {
            mobilenno.setError("Please Enter Mobile Number ");
        }
        else if((mobilenno.getText().toString().length()<10 || mobileno.length()>13 || mobileno.matches(mpattern)==false))
        {
            mobilenno.setError("Please Enter Valid Mobile Number ");
        }
        else if(password.isEmpty())
        {
            pass.setError("Please Enter Password");
        }
        else
        {

            insertdata();

        }

    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }

}
