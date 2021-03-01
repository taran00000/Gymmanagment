package com.example.gymmanagement;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.eightbitlab.bottomnavigationbar.BottomBarItem;
import com.eightbitlab.bottomnavigationbar.BottomNavigationBar;


public class FragmentLoad extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_fragmentload);

        BottomNavigationBar bottomBar = (BottomNavigationBar) findViewById(R.id.bottomBar);

        BottomBarItem item = new BottomBarItem(R.drawable.home, R.string.home);
        bottomBar.addTab(item);
        BottomBarItem item2 = new BottomBarItem(R.drawable.bmi, R.string.BMI);
        bottomBar.addTab(item2);
        BottomBarItem item3 = new BottomBarItem(R.drawable.coach, R.string.Trianer_List);
        bottomBar.addTab(item3);
        BottomBarItem item4 = new BottomBarItem(R.drawable.clipboard, R.string.My_Profile);
        bottomBar.addTab(item4);
        BottomBarItem item5 = new BottomBarItem(R.drawable.user, R.string.About_Us);
        bottomBar.addTab(item5);

        


        UserHomePage_Fragment uhfrag=new UserHomePage_Fragment();
        FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.loadanyfragment,uhfrag);
        ft.commit();


        bottomBar.setOnSelectListener(new BottomNavigationBar.OnSelectListener() {
            @Override
            public void onSelect(int position) {
                if (position == 1){
                    BMICalculatorFragment bmifrag=new BMICalculatorFragment();
                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.loadanyfragment,bmifrag);
                    ft.commit();
                }else if (position == 0){
                    UserHomePage_Fragment uhfrag=new UserHomePage_Fragment();
                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.loadanyfragment,uhfrag);
                    ft.commit();
                }else if(position == 2){
                    UserTrainerList_Fragment utlfrag=new UserTrainerList_Fragment();
                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.loadanyfragment,utlfrag);
                    ft.commit();
                }else if (position == 3){
                    UserProfile_Fragment upfrag=new UserProfile_Fragment();
                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.loadanyfragment,upfrag);
                    ft.commit();
                }else if (position == 4){
                    UserAboutUS_Fragment uabfrag=new UserAboutUS_Fragment();
                    FragmentTransaction ft=getSupportFragmentManager().beginTransaction();
                    ft.replace(R.id.loadanyfragment,uabfrag);
                    ft.commit();
                }
            }
        });

    }
}
