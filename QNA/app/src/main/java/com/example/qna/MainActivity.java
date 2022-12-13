package com.example.qna;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment frag1;
    private NoticeFragment frag2;
    private ChattingFragment frag3;
    private MyPageFragment frag4;
    private String CurrentUser;
    private String CurrentIDNum;

    private String FCMtoken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Intent MainInformIntent = getIntent();
        CurrentUser = MainInformIntent.getStringExtra("Name");
        CurrentIDNum =MainInformIntent.getStringExtra("Number");
        FCMtoken =MainInformIntent.getStringExtra("FCM");

        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener(){
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){
                    case R.id.homeView:
                        setFragment(0);
                        break;
                    case R.id.noticeView:
                        setFragment(1);
                        break;
                    case R.id.chattingView:View:
                        setFragment(2);
                        break;
                    case R.id.mypageView:
                        setFragment(3);
                        break;
                }
            }
        });
        frag1 = new HomeFragment();
        frag2 = new NoticeFragment();
        frag3 = new ChattingFragment();
        frag4 = new MyPageFragment();
        setFragment(0);
    }

    private void setFragment(int n){
        //사용자 정보를 넘겨주기 위해 bundle 사용 -  사용자 이름과 StdId넘겨준다.
        Bundle UserDataBundle = new Bundle();
        UserDataBundle.putString("Name",CurrentUser);
        UserDataBundle.putString("UserNum",CurrentIDNum);
        UserDataBundle.putString("FCMT", FCMtoken);

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        switch (n){
            case 0:
                frag1.setArguments(UserDataBundle);
                ft.replace(R.id.main_frameLayout, frag1);
                ft.commit();
                break;
            case 1:
                frag2.setArguments(UserDataBundle);
                ft.replace(R.id.main_frameLayout, frag2);
                ft.commit();
                break;
            case 2:
                frag3.setArguments(UserDataBundle);
                ft.replace(R.id.main_frameLayout, frag3);
                ft.commit();
                break;
            case 3:
                frag4.setArguments(UserDataBundle);
                ft.replace(R.id.main_frameLayout, frag4);
                ft.commit();
                break;
        }

    }
}
