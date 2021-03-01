package com.example.gymmanagement;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.Query.Direction;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;


public class ChatActivity extends AppCompatActivity {

    private final ArrayList<ModelMessage> messageList = new ArrayList<>();
    private FirebaseFirestore db;
    public String room_name;
    PrefHelper prefHelper;
    private RecyclerView messages;
    private EditText text;


    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        this.setContentView(R.layout.activity_chat);
        prefHelper = new PrefHelper(this);
        String namechat=getIntent().getStringExtra("husername");
        getSupportActionBar().setTitle(namechat+"\'s chat");


        db = FirebaseFirestore.getInstance();
        messages =  findViewById(R.id.messages);

        SharedPreferences sp=getSharedPreferences("myprefdata",MODE_PRIVATE);
        String utype=sp.getString("udob","");
        String oneid=sp.getString("mid","");

        messages.setLayoutManager(new LinearLayoutManager( this, RecyclerView.VERTICAL, false));


        messages.setAdapter(new MessageListAdapter(this, messageList, oneid));

       // room_name=Constants.getNewChatHead(utype,oneid,getIntent().getStringExtra(Constants.KEY_OTHER_PARTY_EMAIL));

      //  Toast.makeText(this, room_name+"", Toast.LENGTH_SHORT).show();
        room_name = Constants.getChatHead(oneid, getIntent().getExtras().getString(Constants.KEY_OTHER_PARTY_EMAIL));

        text = findViewById(R.id.text);
        getRoomMessages(room_name);
        findViewById(R.id.send).setOnClickListener(new OnClickListener() {
            public final void onClick(View it) {

                String text_message = text.getText().toString();
                if(text_message.length() ==0){
                    Toast.makeText(ChatActivity.this, "Not Valid TEXT", Toast.LENGTH_SHORT).show();
                    return;
                }
                text.setText("");
                ModelMessage modelMessage = new ModelMessage(text_message, oneid, false, FieldValue.serverTimestamp());
                sendSms(room_name, modelMessage);
            }
        });

    }

    private void sendSms(String roomName, ModelMessage modelMessage) {

        db.collection(roomName).add(modelMessage);
    }

    private  void getRoomMessages(String room_name) {

        Query docRef = db.collection(room_name).orderBy("timestamp", Direction.ASCENDING);
        docRef.addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                messageList.clear();
                for (QueryDocumentSnapshot doc : value) {
                    ModelMessage modelMessage = new ModelMessage();
                    modelMessage.setText(doc.get(Constants.TEXT).toString());
                    modelMessage.setSender(doc.get(Constants.SENDER).toString());
                    modelMessage.setTimestamp(FieldValue.serverTimestamp());
                    messageList.add(modelMessage);
                }

                Log.d("messageList",messageList.size()+"asd");
                messages.getAdapter().notifyDataSetChanged();
                messages.scrollToPosition(messageList.size() - 1);
            }
        });


    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return true;
    }
}

