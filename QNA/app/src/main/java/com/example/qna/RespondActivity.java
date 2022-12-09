package com.example.qna;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class RespondActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private static final String FCM_MSG_URL="https://fcm.googleapis.com/fcm/send";
    private String SERVER_KEY;

    private List<String> uidList;
    private List<RespondData> ResList;

    private RecyclerView respondRecyclerView;
    private RecyclerView.Adapter rAdapter;
    private RecyclerView.LayoutManager rLayoutManager;

    private TextView QuestionView;
    //    private ScrollView ;
    private EditText respond;
    private Button send;
    private String CurrentUser;
    private String Subject;
    private String randomKey;
    private String Question;

    private Intent informIntent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respond);

        informIntent = getIntent();
        CurrentUser = informIntent.getStringExtra("userId");
        Subject = informIntent.getStringExtra("subject");
        randomKey = informIntent.getStringExtra("key");
        Question = informIntent.getStringExtra("Q");

        QuestionView = (TextView) findViewById(R.id.questionView);
        QuestionView.setText(Question);
        respond = (EditText) findViewById(R.id.respondEdit);
        send = (Button) findViewById(R.id.respondSendBtn);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Respond = respond.getText().toString();
                if(Respond!=null){
                    addRespond(Respond,CurrentUser);
                }
            }
        });

        respondRecyclerView = findViewById(R.id.respondView);
        respondRecyclerView.setHasFixedSize(true);
        rLayoutManager = new LinearLayoutManager(this);
        respondRecyclerView.setLayoutManager(rLayoutManager);

        ResList = new ArrayList<>();
//        uidList=new ArrayList<>();
        rAdapter = new RespondRecyclerAdapter(ResList,RespondActivity.this,CurrentUser,Subject);
        respondRecyclerView.setAdapter(rAdapter);

//        Log.d("RANDOMKEY___", randomKey);
        myRef.child("ChatRoom_Question").child(Subject).child("respond").child(randomKey).addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("RESPONDRESPOND", snapshot.getValue().toString());
                RespondData resp = snapshot.getValue(RespondData.class);
                ((RespondRecyclerAdapter)rAdapter).addRes(resp);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void addRespond(String respond, String userid){
        RespondData resData = new RespondData();
        resData.setRespond(respond);
        resData.setUserNumId(userid);
        myRef.child("ChatRoom_Question").child(Subject).child("respond").child(randomKey).push().setValue(resData);
        myRef.child("Users").child(userid).child("Question_Answer").push().setValue(respond);
    }

    private void sendPostToFCM_usr(final String title, final String body, final String fcmToken){
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("title", title)
                            .put("body", body);
                    root.put("notification", notification);
                    root.put("to", fcmToken);

                    URL Url = new URL(FCM_MSG_URL);
                    HttpURLConnection httpUrlConn = (HttpURLConnection) Url.openConnection();
                    httpUrlConn.setRequestMethod("POST");
                    httpUrlConn.setDoOutput(true);
                    httpUrlConn.setDoInput(true);
                    httpUrlConn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    httpUrlConn.setRequestProperty("Accept", "application/json");
                    httpUrlConn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = httpUrlConn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    httpUrlConn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // FCM에 다중 기기에게 푸시 알림 전송 요청
    private void sendPostToFCM_usrs(final String title, final String body, final List<String> fcmTokens) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("title", title)
                            .put("body", body);
                    root.put("notification", notification);

                    String fcmToken = FirebaseMessaging.getInstance().getToken().getResult();
                    for(String Token : fcmTokens)
                    {
                        if(Token != fcmToken)
                        {
                            root.put("to", fcmToken);
                        }
                    }

                    URL Url = new URL(FCM_MSG_URL);
                    HttpURLConnection httpUrlConn = (HttpURLConnection) Url.openConnection();
                    httpUrlConn.setRequestMethod("POST");
                    httpUrlConn.setDoOutput(true);
                    httpUrlConn.setDoInput(true);
                    httpUrlConn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    httpUrlConn.setRequestProperty("Accept", "application/json");
                    httpUrlConn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = httpUrlConn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    httpUrlConn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}