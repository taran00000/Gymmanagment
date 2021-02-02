package com.example.gymmanagement;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class SelectionPage extends AppCompatActivity {

    Button tr,me,admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainselection);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tr=(Button)findViewById(R.id.trainer);
        me=(Button)findViewById(R.id.member);
        admin=(Button)findViewById(R.id.admin);




        tr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectionPage.this,UserPage_TrainerList.class);
                i.putExtra("ustypes","admin");
                startActivity(i);

            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectionPage.this, Login_Activity.class);
                i.putExtra("ustypes","admin");
                startActivity(i);

            }
        });


        me.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(SelectionPage.this, Login_Activity.class);
                i.putExtra("ustypes","user");
                startActivity(i);

            }
        });

    }
    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}
