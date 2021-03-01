package com.example.gymmanagement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class Admin_HomePage extends AppCompatActivity {
    LinearLayout userlist,trainerlist,addprogramme,viewfeedback,add_trainer,adduser,viewprof,requestlist;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_homepage);

        userlist=findViewById(R.id.user_ad_list);
        trainerlist=findViewById(R.id.trainer_ad_list);
        addprogramme=findViewById(R.id.admin_add_programme);
        viewfeedback=findViewById(R.id.viewfeedback);
        add_trainer=findViewById(R.id.add_trainer);
        adduser=findViewById(R.id.add_user);
        requestlist=findViewById(R.id.requestlist);
        viewprof=findViewById(R.id.admin_viewprofile);


        addprogramme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_HomePage.this,Admin_Add_programm.class));
            }
        });
        viewfeedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_HomePage.this,ViewFeedback.class));
            }
        });
        viewprof.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_HomePage.this,Profile.class));
            }
        });





        add_trainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_HomePage.this,RegisterPage.class).putExtra("ustypes","trainer").putExtra("sender","admin"));
            }
        });
        adduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_HomePage.this,RegisterPage.class).putExtra("ustypes","user").putExtra("sender","admin"));
            }
        });



        userlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_HomePage.this,TrainerPage_UserNameList.class).putExtra("usertype","user"));
            }
        });

        trainerlist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Admin_HomePage.this,TrainerPage_UserNameList.class).putExtra("usertype","trainer"));
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
