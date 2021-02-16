package com.example.gymmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class TrainerHomePage extends AppCompatActivity {
    LinearLayout fetchuser;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.trainer_homepage);

        fetchuser=findViewById(R.id.fetchuserdata);

        fetchuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TrainerHomePage.this,TrainerPage_UserNameList.class));
            }
        });
    }
}
