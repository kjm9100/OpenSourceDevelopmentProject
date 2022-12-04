package com.example.qna;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;
    private HomeFragment frag1;
    private NoticeFragment frag2;
    private ChattingFragment frag3;
    private MyPageFragment frag4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigationView);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
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

                return true;
            }
        });
        frag1 = new HomeFragment();
        frag2 = new NoticeFragment();
        frag3 = new ChattingFragment();
        frag4 = new MyPageFragment();
        setFragment(0);
    }

    private void setFragment(int n){
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n){
            case 0:
                ft.replace(R.id.main_frameLayout, frag1);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frameLayout, frag2);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frameLayout, frag3);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frameLayout, frag4);
                ft.commit();
                break;
        }

    }
}
