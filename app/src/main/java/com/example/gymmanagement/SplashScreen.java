package com.example.gymmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.splash);
        ImageView iv=(ImageView)findViewById(R.id.splashscreen);

    
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreen.this,WelcomeActivity.class);
                startActivity(mainIntent);
                finish();
            }
        }, 4000);
    }
}
