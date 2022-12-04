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

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class RespondActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

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
}