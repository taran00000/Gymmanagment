package com.example.gymmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class User_Home_TransformationList extends AppCompatActivity {
    TextView viewdetails;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_home_transformationlist);
        viewdetails=findViewById(R.id.view_details);

        viewdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(User_Home_TransformationList.this,User_BodyTransformationDetails.class);
                startActivity(i);
            }
        });
    }
}
