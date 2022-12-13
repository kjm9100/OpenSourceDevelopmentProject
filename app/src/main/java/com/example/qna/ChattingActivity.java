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

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class ChattingActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private RecyclerView myRecyclerView;
    public RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<ChatData> chatList = new ArrayList<>();
    private List<String>uidList = new ArrayList<>();

    //로그인한 사용자 정보
    private Intent PreIntent1;
    private String CurrentUserName;
    private String CurrentUserPrivateNumber;
    private String CurrentSubject;

    private Button btn;
    private EditText ed2;

    private static final String FCM_MSG_URL="https://fcm.googleapis.com/fcm/send";
    private String SERVER_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_chatting1);

        btn = findViewById(R.id.btn);
        ed2 = findViewById(R.id.chatEdit);

        PreIntent1 = getIntent();
        CurrentUserName = PreIntent1.getStringExtra("userName");
        CurrentUserPrivateNumber = PreIntent1.getStringExtra("userNum");
        CurrentSubject = PreIntent1.getStringExtra("Subject");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = ed2.getText().toString();

                if(msg != null){
                    addChatDate(CurrentUserName,msg, CurrentUserPrivateNumber);
                }

                ed2.setText("");
            }
        });

        myRecyclerView = findViewById(R.id.my_recycler_view);
        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        uidList=new ArrayList<>();

        //데이터베이스 정보 가져와서 recycle 화면에 출력한다.
        myRef.child("ChatRoom_Question").child(CurrentSubject).child("message").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                uidList.clear();

                for(DataSnapshot snapShot : snapshot.getChildren()){
                    ChatData chat = snapShot.getValue(ChatData.class);
                    String uidKey = snapShot.getKey();
                    chatList.add(chat);
                    uidList.add(uidKey);
                }
                mAdapter = new ChatRecycleRAdapter(chatList,uidList,ChattingActivity.this, CurrentUserPrivateNumber,CurrentSubject);
                myRecyclerView.setAdapter(mAdapter);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });
    }

    //DB에 message와 보낸사람 정보 저장한다.
    public void addChatDate(String name, String message , String number){
        ChatData chatData = new ChatData();
        chatData.setNum(number);
        chatData.setMessage(message);
        chatData.setName(name);
        myRef.child("ChatRoom_Question").child(CurrentSubject).child("message").push().setValue(chatData);
        myRef.child("Users").child(CurrentUserPrivateNumber).child("UserQuestion").push().setValue(chatData);
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
}