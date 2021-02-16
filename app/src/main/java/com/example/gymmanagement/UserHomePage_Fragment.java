package com.example.gymmanagement;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class UserHomePage_Fragment extends Fragment {
    LinearLayout loadprogramm;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.activity_homepage_user,container,false);

        loadprogramm=view.findViewById(R.id.userprec);

        loadprogramm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                UserProgrammlist_Fragment bmifrag=new UserProgrammlist_Fragment();
                FragmentTransaction ft=getFragmentManager().beginTransaction();
                ft.replace(R.id.loadanyfragment,bmifrag);
                ft.addToBackStack(null);
                ft.commit();
            }
        });



        return view;
    }
}
