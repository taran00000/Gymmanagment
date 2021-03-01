package com.example.gymmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
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

public class Trainer_PersonalClient extends AppCompatActivity {

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    ProgressDialog pd;
    ListView listView;
    ArrayList<pojo> arrayList;
    String username,jfid;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.commonlist);

        listView=findViewById(R.id.commonlistd);

        SharedPreferences sp=getSharedPreferences("myprefdata",MODE_PRIVATE);
        jfid = sp.getString("mid","");

        listView.setDivider(null);
        listView.setDividerHeight(0);


        fetchClientList();

    }

    private void fetchClientList() {
        arrayList=new ArrayList<>();
        pd=new ProgressDialog(Trainer_PersonalClient.this);
        pd.setMessage("Loading....");
        pd.show();
        asyncHttpClient=new AsyncHttpClient();
        requestParams=new RequestParams();
        requestParams.put("trainerid",jfid);
        asyncHttpClient.get(CommanUtil.base_url+"fetchclient.php",requestParams,new JsonHttpResponseHandler(){
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
                        String kid=jb.getString("id");

                        pojo p=new pojo(username,kid);
                        arrayList.add(p);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Cust_Adp ca=new Cust_Adp(Trainer_PersonalClient.this,arrayList);
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
            View view1=inflater.inflate(R.layout.trainer_personalclient,viewGroup,false);
            TextView tv1=(TextView)view1.findViewById(R.id.client_name);
            LinearLayout prof=view1.findViewById(R.id.client_profile);
            LinearLayout ln2=view1.findViewById(R.id.client_chat);

            pojo p=arrayList.get(i);
            tv1.setText((i+1)+". "+p.getT_username());

            prof.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Trainer_PersonalClient.this,Profile.class).putExtra("getid",p.getUsid()));
                }
            });

            ln2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Trainer_PersonalClient.this,ChatActivity.class).putExtra(Constants.KEY_OTHER_PARTY_EMAIL,p.getUsid()).putExtra("husername",p.getT_username()));
                }
            });



            return view1;
        }
    }
}
