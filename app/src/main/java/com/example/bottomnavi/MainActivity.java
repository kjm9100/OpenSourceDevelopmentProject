package com.example.bottomnavi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fragManager;
    private FragmentTransaction fragTransaction;
    private Frag_home frag_home;
    private Frag_notification frag_notification;
    private Frag_qna frag_qna;
    private Frag_myPage frag_myPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId())
                {
                    case R.id.action_home:
                        setFrag(0);
                        break;
                    case R.id.action_notification:
                        setFrag(1);
                        break;
                    case R.id.action_QnA:
                        setFrag(2);
                        break;
                    case R.id.action_mypage:
                        setFrag(3);
                        break;
                }

                return true;
            }
        });

        frag_home = new Frag_home();
        frag_notification = new Frag_notification();
        frag_qna = new Frag_qna();
        frag_myPage = new Frag_myPage();
        setFrag(0); // 첫 화면을 홈 화면으로 지정

    }

    // 지정 화면으로 설정
    private void setFrag(int n) {

        fragManager = getSupportFragmentManager();
        fragTransaction = fragManager.beginTransaction();

        /*
         * 0 : home
         * 1 : notification
         * 2 : qna
         * 3 : myPage
         */
        switch(n)
        {
            case 0:
                fragTransaction.replace(R.id.main_frame, frag_home);
                fragTransaction.commit();
                break;
            case 1:
                fragTransaction.replace(R.id.main_frame, frag_notification);
                fragTransaction.commit();
                break;
            case 2:
                fragTransaction.replace(R.id.main_frame, frag_qna);
                fragTransaction.commit();
                break;
            case 3:
                fragTransaction.replace(R.id.main_frame, frag_myPage);
                fragTransaction.commit();
                break;
        }
    }
}