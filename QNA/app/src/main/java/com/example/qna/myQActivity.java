package com.example.qna;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class myQActivity extends AppCompatActivity {
    private FirebaseDatabase db = FirebaseDatabase.getInstance();
    private DatabaseReference dbRef = db.getReference();
    private List<String>myQList;

    private String CurrentUserName;
    private String CurrentUesrNumId;
    private ListView qlistview;
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_qactivity);

        Intent intent = getIntent();
        CurrentUserName = intent.getStringExtra("userName");
        CurrentUesrNumId = intent.getStringExtra("userNum");

        qlistview = findViewById(R.id.listView2);
        myQList = new ArrayList<>();
        i = 0;

        dbRef.child("Users").child(CurrentUesrNumId).child("UserQuestion").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                myQList.clear();
                for(DataSnapshot datasnapshot : snapshot.getChildren()) {
                    String QData = datasnapshot.getValue().toString();
                    Log.d("QUESTIONCHECK----",QData);
                    myQList.add(QData);
                    i++;
                }
                i=0;
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        final ArrayAdapter<String>adapter = new ArrayAdapter<>(getApplicationContext(),android.R.layout.simple_list_item_1, myQList);
        qlistview.setAdapter(adapter);
    }
}