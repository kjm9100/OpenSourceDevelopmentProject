package com.example.qna;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class NoticeFragment extends Fragment implements View.OnClickListener {
    private View view;
    Button subjectButton, majorButton, SWButton;
    LinearLayout noticeLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notice, container, false);

        subjectButton = (Button) view.findViewById(R.id.subjectButton);
        majorButton = (Button) view.findViewById(R.id.majorButton);
        SWButton = (Button) view.findViewById(R.id.SWButton);
        noticeLayout = (LinearLayout) view.findViewById(R.id.noticeLayout);

        subjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeLayout.setVisibility(View.GONE);
                subjectButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                majorButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                SWButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.noticeFragment, new NoticeFragment1());
//                fragmentTransaction.commit();
            }
        });

        majorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeLayout.setVisibility(View.GONE);
                subjectButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                majorButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                SWButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.noticeFragment, new NoticeFragment2());
//                fragmentTransaction.commit();
            }
        });

        SWButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                noticeLayout.setVisibility(View.GONE);
                subjectButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                majorButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                SWButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.noticeFragment, new NoticeFragment3());
//                fragmentTransaction.commit();
            }
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}
