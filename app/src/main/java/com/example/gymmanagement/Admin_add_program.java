package com.example.gymmanagement;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Locale;

import cz.msebera.android.httpclient.Header;

public class Admin_Add_programm extends AppCompatActivity {

    EditText et1,et2,et3,et4,et5,et6,et7,et8,et9;
    ImageView cativ;
    Button button;
    String currentDateandTime;

    Uri fileuri;
    ProgressDialog pd;
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_add_programme);

        et1=findViewById(R.id.pd_name);
        et2=findViewById(R.id.pd_intro);
        et3=findViewById(R.id.pd_rating);
        et4=findViewById(R.id.pd_descrption);
        et5=findViewById(R.id.pd_steps);
        et6=findViewById(R.id.pd_benefits);
        et7=findViewById(R.id.pd_category);
        et8=findViewById(R.id.pd_thumbnail);
        cativ=findViewById(R.id.cativload);
        button=findViewById(R.id.submit_pro);

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault());
        currentDateandTime = sdf.format(new Date());

        cativ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CropImage.activity()
                        .setGuidelines(CropImageView.Guidelines.ON)
                        .start(Admin_Add_programm.this);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });

    }

    private void insertData() {
        pd=new ProgressDialog(Admin_Add_programm.this);
        pd.setMessage("Please Wait.....");
        pd.show();
        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        requestParams.put("name",et1.getText().toString());
        requestParams.put("intro",et2.getText().toString());
        requestParams.put("rating",et3.getText().toString());
        requestParams.put("description",et4.getText().toString());
        requestParams.put("steps",et5.getText().toString());
        requestParams.put("benefits",et6.getText().toString());
        requestParams.put("category",et7.getText().toString());
        requestParams.put("thumbnail",et8.getText().toString());
        requestParams.put("gifurl","Image"+currentDateandTime+".jpg");
        try {
            Log.d("fileuri",fileuri.getPath());
            requestParams.put("image",new File(fileuri.getPath()));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        asyncHttpClient.post(CommanUtil.base_url+"addprogramme.php", requestParams, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {

                Toast.makeText(Admin_Add_programm.this, responseString, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                if (pd.isShowing()){
                    pd.dismiss();
                }
               /* Intent intent=new Intent(CateringPage.this,OrganizorDrawer.class);
                startActivity(intent);
                finish();*/
                Toast.makeText(Admin_Add_programm.this, responseString, Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

        if (resultCode == Activity.RESULT_OK && data != null) {

            if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
                CropImage.ActivityResult result = CropImage.getActivityResult(data);
                if (resultCode == RESULT_OK) {
                    Uri resultUri = result.getUri();
                    fileuri=resultUri;
                    try {
                        Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), resultUri);
                        cativ.setImageBitmap(bitmap);
                        cativ.setScaleType(ImageView.ScaleType.FIT_XY);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }


                } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                    Exception error = result.getError();
                }

                return;
            }

        }
        super.onActivityResult(requestCode, resultCode, data);

    }

}
