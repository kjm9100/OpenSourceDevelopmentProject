package com.example.qna;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;


import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ChattingFragment extends Fragment implements View.OnClickListener{
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private List<String> subList;    //현재 사용자가 선택한 교과목명 리스트
    private int SubjectNum;

    private int i ;
    private View view;
    private String CurrentUserName;
    private String CurrentUesrNumId;
    private Button subjectButton1, subjectButton2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        CurrentUserName =bundle.getString("Name");
        CurrentUesrNumId =bundle.getString("UserNum");

        subList = new ArrayList<>();

        view = inflater.inflate(R.layout.fragment_chatting, container, false);

        ListView listview = view.findViewById(R.id.listView);

        myRef.child("UserData").child("Students").child(CurrentUesrNumId).child("Subject").addValueEventListener(new ValueEventListener() {
            @Override
            //                Log.d("SUbjectcheck", snapshot.getValue().toString());
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                subList.clear();
                    for(DataSnapshot datasnapshot : snapshot.getChildren()) {
                        String subData = datasnapshot.getValue().toString();
//                        Log.d("SUBJECTCHECK----",subData);
                        subList.add(subData);
//                        Log.d("SUBJECTCHECK----",subList.toString());
                        i++;
                    }
                    SubjectNum = i;
                    i=0;
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        final ArrayAdapter<String>adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, subList);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String currSub = (String)parent.getItemAtPosition(position);
                Intent intent =new Intent(getActivity(), ChattingActivity.class);
                intent.putExtra("userNum",CurrentUesrNumId);
                intent.putExtra("userName",CurrentUserName);
                intent.putExtra("Subject",currSub);
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });
//        subjectButton1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Toast.makeText(getContext(),"subject1", Toast.LENGTH_SHORT).show();
//                Intent intent =new Intent(getActivity(), ChattingActivity.class);
//                intent.putExtra("userNum",CurrentUesrNumId);
//                intent.putExtra("userName",CurrentUserName);
//                intent.putExtra("Subject","subject1");
//                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
//                startActivity(intent);
//            }
//        });
        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
