package com.example.qna;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class ChattingFragment extends Fragment implements View.OnClickListener{
    private View view;
    private String CurrentUserName;
    private String CurrentUesrNumId;
    Button subjectButton1, subjectButton2;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        CurrentUserName =bundle.getString("Name");
        CurrentUesrNumId =bundle.getString("UserNum");

        view = inflater.inflate(R.layout.fragment_chatting, container, false);

        subjectButton1 = (Button) view.findViewById(R.id.subjectButton1);
        subjectButton2 = (Button) view.findViewById(R.id.subjectButton2);

//        Intent chatIntent = new Intent();

        subjectButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"subject1", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getActivity(), ChattingActivity.class);
                intent.putExtra("userNum",CurrentUesrNumId);
                intent.putExtra("userName",CurrentUserName);
                intent.putExtra("Subject","subject1");
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);

            }
        });

        subjectButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(),"subject2", Toast.LENGTH_SHORT).show();
                Intent intent =new Intent(getActivity(), ChattingActivity.class);
                intent.putExtra("userNum",CurrentUesrNumId);
                intent.putExtra("userName",CurrentUserName);
                intent.putExtra("Subject","subject2");

                intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                startActivity(intent);
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
