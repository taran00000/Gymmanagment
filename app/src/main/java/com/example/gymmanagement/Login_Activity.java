package com.example.gymmanagement;


import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class Login_Activity extends AppCompatActivity {
    TextView reg_page;
    Button login;

    TextView signup;
    EditText email,pass;
    String emailid,password,login_reponse,uemail,uname,udob,mid,uaddr,umob,mnp;
    String emailpattern="[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
    private static String url="";
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    ProgressDialog pd;
    String myab;
    private  int MULTIPLE_PERMISSIONS=123;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        checkPermissions();
        setContentView(R.layout.activity_login);
        myab=getIntent().getStringExtra("ustypes");
        reg_page=findViewById(R.id.register_page);
        login=findViewById(R.id.loginbutton);
        email=(EditText)findViewById(R.id.lemail);
        pass=(EditText)findViewById(R.id.lpassword);
        asyncHttpClient = new AsyncHttpClient();
        requestParams = new RequestParams();

        SharedPreferences sp=getSharedPreferences("myprefdata",MODE_PRIVATE);
        String vallogin=sp.getString("loginvalidate","");
        String utype=sp.getString("udob","");
        if (vallogin.equals("validlogin") && utype.equals("admin")){
            Intent mk = new Intent(Login_Activity.this, UserHomePage.class);
            startActivity(mk);
            finish();
        }else if (vallogin.equals("validlogin") && utype.equals("user")){
            Intent mk = new Intent(Login_Activity.this, UserHomePage.class);
            startActivity(mk);
            finish();
        }else {

        }

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkfeild();
            }
        });

        reg_page.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(Login_Activity.this,RegisterPage.class);
                i.putExtra("ustypes",myab);
                startActivity(i);

            }
        });
    }

    String[] permissions= new String[]{
            Manifest.permission.READ_EXTERNAL_STORAGE,
            Manifest.permission.ACCESS_NETWORK_STATE,
            Manifest.permission.RECEIVE_BOOT_COMPLETED,
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };

    private  boolean checkPermissions() {
        int result;
        List<String> listPermissionsNeeded = new ArrayList<>();
        for (String p:permissions) {
            result = ContextCompat.checkSelfPermission(Login_Activity.this,p);
            if (result != PackageManager.PERMISSION_GRANTED) {
                listPermissionsNeeded.add(p);
            }
        }
        if (!listPermissionsNeeded.isEmpty()) {
            ActivityCompat.requestPermissions(Login_Activity.this, listPermissionsNeeded.toArray(new String[listPermissionsNeeded.size()]),MULTIPLE_PERMISSIONS );
            return false;
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        if(requestCode==MULTIPLE_PERMISSIONS)
        {
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                // permissions granted.
            } else {
                // no permissions granted.
            }
            return;
        }
    }
    private void checkfeild() {

        emailid=email.getText().toString();
        password=pass.getText().toString();
        if(emailid.isEmpty())
        {
            email.setError("Please Enter Email");
        }
        else if(!emailid.matches(emailpattern))
        {
            email.setError("Please Enter Valid Email");
        }
        else if (password.isEmpty())
        {
            pass.setError("Please Enter Password");
        }
        else{
            login();
        }

    }

    private void login() {
        pd=new ProgressDialog(Login_Activity.this);
        pd.setMessage("Please Wait.....");
        pd.show();
        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        requestParams.put("email",emailid);
        requestParams.put("password",password);
        asyncHttpClient.get("http://192.168.2.166/login.php",requestParams,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                if (pd.isShowing()){
                    pd.dismiss();
                }
                try {
                    login_reponse=response.getString("responce");
                    JSONArray ja=response.getJSONArray("data");

                    JSONObject jb = ja.getJSONObject(0);
                    uname = jb.getString("name");
                    uemail = jb.getString("email");
                    udob = jb.getString("ustype");
                    uaddr = jb.getString("gender");
                    mid = jb.getString("id");
                    umob=jb.getString("androidid");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (login_reponse.equals("Valid Login")){
                    if (udob.equals("admin")) {
                        Intent mk = new Intent(Login_Activity.this, UserHomePage.class);
                        startActivity(mk);
                        finish();
                    } else if (udob.equals("user")) {
                        Intent mk = new Intent(Login_Activity.this, UserHomePage.class);
                        startActivity(mk);
                        finish();
                    } else {

                    }
                    SharedPreferences sp = getSharedPreferences("myprefdata", MODE_PRIVATE);
                    SharedPreferences.Editor spe = sp.edit();
                    spe.putString("loginvalidate", "validlogin");
                    spe.putString("uname", uname);
                    spe.putString("uemail", uemail);
                    spe.putString("udob", udob);
                    spe.putString("uadd", uaddr);
                    spe.putString("mid", mid);
                    spe.putString("umob",umob);
                    spe.commit();
                }else {
                    Toast.makeText(Login_Activity.this, "Incorrect Password", Toast.LENGTH_SHORT).show();
                }

            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity();
        finish();
    }
}