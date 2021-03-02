package com.example.gymmanagement;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ScrollView;
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

public class ViewFeedback extends AppCompatActivity {

    AsyncHttpClient asyncHttpClient;
    RequestParams requestParams;
    ProgressDialog pd;
    ListView listView;
    ArrayList<pojo> arrayList;
    String username,usertype;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.commonlist);

        listView=findViewById(R.id.commonlistd);

        listView.setDivider(null);
        listView.setDividerHeight(0);

        fetchFeedback();
    }

    private void fetchFeedback() {
        arrayList=new ArrayList<>();
        pd=new ProgressDialog(ViewFeedback.this);
        pd.setMessage("Loading....");
        pd.show();
        asyncHttpClient=new AsyncHttpClient();
        asyncHttpClient.get(CommanUtil.base_url+"fetchfeedback.php",new JsonHttpResponseHandler(){
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
                        String email=jb.getString("email");
                        String feedback=jb.getString("feedback");

                        pojo p=new pojo(username,kid);
                        p.setIntro(email);
                        p.setRating(feedback);
                        arrayList.add(p);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Cust_Adp ca=new Cust_Adp(ViewFeedback.this,arrayList);
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
            View view1=inflater.inflate(R.layout.feedbacklayout,viewGroup,false);
            TextView tv1=(TextView)view1.findViewById(R.id.fedid);
            TextView tv2=(TextView)view1.findViewById(R.id.fedname);
            TextView tv3=(TextView)view1.findViewById(R.id.fedemail);
            TextView tv4=(TextView)view1.findViewById(R.id.fedfeedback);



            pojo p=arrayList.get(i);
            if (i<9) {
                tv1.setText("0"+p.getUsid());
            }
            else {
                tv1.setText(p.getUsid());
            }
            tv2.setText(p.getT_username());
            tv3.setText(p.getIntro());
            tv4.setText(p.getRating());


            return view1;
        }
    }
}
