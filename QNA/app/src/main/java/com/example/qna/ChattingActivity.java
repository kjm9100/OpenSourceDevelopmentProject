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
    private EditText ed1, ed2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatting);

        btn = findViewById(R.id.btn);
        ed2 = findViewById(R.id.edit2);
        //send 버튼을 누르면
//        auth = FirebaseAuth.getInstance();

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
            }
        });

        myRecyclerView = findViewById(R.id.my_recycler_view);
        myRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        myRecyclerView.setLayoutManager(mLayoutManager);

        chatList = new ArrayList<>();
        mAdapter = new ChatRecycleRAdapter(chatList,ChattingActivity.this, CurrentUserPrivateNumber);

        myRecyclerView.setAdapter(mAdapter);

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
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error){

            }
        });

        //데이터베이스 정보 가져와서 recycle 화면에 출력한다.
//        myRef.child("group2").addChildEventListener(new ChildEventListener() {
        myRef.child("ChatRoom_Question").child(CurrentSubject).child("message").addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                Log.d("CHATCHAT", snapshot.getValue().toString());
                ChatData chat = snapshot.getValue(ChatData.class);
                ((ChatRecycleRAdapter)mAdapter).addChat(chat);
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

    //DB에 message와 보낸사람 이름을 저장한다.
    public void addChatDate(String name, String message , String number){
//        ChatData ChatData = new ChatData(name,message);
//        myRef.child("user").child(name).setValue(ChatData);
        ChatData chatData = new ChatData();
        chatData.setNum(number);
        chatData.setMessage(message);
        chatData.setName(name);
        myRef.child("ChatRoom_Question").child(CurrentSubject).child("message").push().setValue(chatData);
        myRef.child("Users").child(CurrentUserPrivateNumber).child("UserQuestion").push().setValue(chatData);
//            myRef.child("group2").push().setValue(chatData);

    }
}