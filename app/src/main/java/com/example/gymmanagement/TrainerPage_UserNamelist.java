package com.example.gymmanagement;


import android.app.ProgressDialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class TrainerPage_UserNameList extends AppCompatActivity {


    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    ProgressDialog pd;
    ListView listView;
    ArrayList<pojo> arrayList;
    String username;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commonlist);

        listView=findViewById(R.id.commonlistd);

        arrayList=new ArrayList<>();
        fetchUserList();


    }

    private void fetchUserList() {
        pd=new ProgressDialog(TrainerPage_UserNameList.this);
        pd.setMessage("Loading....");
        pd.show();
        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        requestParams.put("ustype","user");
        asyncHttpClient.get(CommanUtil.base_url+"fetchuser.php",requestParams,new JsonHttpResponseHandler(){
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
                        username=jb.getString("name");

                        pojo p=new pojo(username);
                        arrayList.add(p);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Cust_Adp ca=new Cust_Adp(TrainerPage_UserNameList.this,arrayList);
                listView.setAdapter(ca);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                super.onFailure(statusCode, headers, throwable, errorResponse);
                if (pd.isShowing()){
                    pd.dismiss();
                }
            }
        });
    }

    private class Cust_Adp extends BaseAdapter {
        LayoutInflater inflater;
        Context context;
        ArrayList<pojo>arrayList;

        public Cust_Adp(Context context, ArrayList<pojo> arrayList) {
            this.arrayList=arrayList;
            this.context=context;
        }

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            inflater=(LayoutInflater)context.getSystemService(LAYOUT_INFLATER_SERVICE);
            View view1=inflater.inflate(R.layout.trainer_userviewlist,viewGroup,false);
            TextView tv1=(TextView)view1.findViewById(R.id.user_name);
            ImageView iv1=(ImageView)view1.findViewById(R.id.delete_user);

            pojo p=arrayList.get(i);
            tv1.setText(p.getT_username());


            return view1;
        }
    }
}
