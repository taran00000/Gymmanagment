package com.example.gymmanagement;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;

public class UserProgramm_Details extends AppCompatActivity {
    String fetchid;
    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    String mybase_URL="http://ominfosolution.com/student/DDU_STUDENT/fetchdetails.php";

    ImageView imageView;
    TextView tv;
    ProgressDialog pd;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_programmdetailsview);

        imageView=findViewById(R.id.slider);
        tv=findViewById(R.id.img_my_title);

        pd=new ProgressDialog(UserProgramm_Details.this);
        pd.setMessage("Please Wait.....");
        pd.setIndeterminate(true);
        pd.setCancelable(true);
        pd.show();
        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        requestParams.put("id","1");
        asyncHttpClient.get(mybase_URL,requestParams,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);

                if (pd.isShowing()){
                    pd.dismiss();
                }

                try {
                    JSONArray ja=response.getJSONArray("data");
                    for (int i = 0; i <ja.length() ; i++) {
                        JSONObject jb=ja.getJSONObject(i);

                        tv.setText(jb.getString("name"));
                        Picasso.get().load(jb.getString("imageurl")).into(imageView);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                Toast.makeText(UserProgramm_Details.this, errorResponse+"", Toast.LENGTH_SHORT).show();
                Log.d("sds",errorResponse+"");
            }
        });
    }


}
