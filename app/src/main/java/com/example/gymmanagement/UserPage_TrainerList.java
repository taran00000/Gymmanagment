package com.example.gymmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class UserPage_TrainerList extends AppCompatActivity {
    LinearLayout layout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userpage_trainerlist);

        layout=findViewById(R.id.aboutsample);

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(UserPage_TrainerList.this,AboutUs.class);
                startActivity(i);
            }
        });
    }
}
