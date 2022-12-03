package com.example.qna;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
//    private Button loginBTN;
//    private Button registerBTN;
    private EditText Id;
    private EditText Passwd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        loginBTN = (Button)findViewById(R.id.loginButton);
//        registerBTN = (Button)findViewById(R.id.registerButton);
        Id = (EditText) findViewById(R.id.idText);
        Passwd = (EditText) findViewById(R.id.pdText);

        TextView registerButton = (TextView) findViewById(R.id.registerButton);
        final Button loginButton = (Button) findViewById(R.id.loginButton);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent registerIntent = new Intent(LoginActivity.this,
                        RegisterActivity.class);
                LoginActivity.this.startActivity(registerIntent);
            }
        });
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId = Id.getText().toString();
                String userPasswd = Passwd.getText().toString();

                if (userId == "") {
                    // 토스트 메시지 - 공백은 입력할 수 없습니다.
                    Toast.makeText(getApplicationContext(), "공백은 입력할 수 없습니다.", Toast.LENGTH_LONG).show();
                } else {
                    myRef.child("UserData").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Log.d("UserCheck",snapshot.child(userId).child("passwd").getValue().toString());
                            if (snapshot.child("Students").child(userId).exists()) {                                    //학생 사용자의 저장된 아이디중 같은 아이디 있는지 확인한다.
                                UserData userdata = snapshot.child("Students").child(userId).getValue(UserData.class);  //비밀번호를 비교하기 위해 입력한 아이디와 같은 아이디의 유저 정보를 가져와 저장한다.
                                if (userdata.getPasswd() != null && userdata.getPasswd().equals(userPasswd)) {              //입력한 비밀번호가 공백이 아니고 저장된 비밀번호와 같을 때 로그인 성공
//                                    Intent intent = new Intent(loginActivity.this, MainActivity.class);    //로그인 성공시 메인화면으로 넘어간다.
                                    Toast toast = Toast.makeText(LoginActivity.this,
                                            "Login succeeded!", Toast.LENGTH_SHORT);
                                    toast.show();

                                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    //로그인한 사용자의 정보도 넘겨준다.
//                                    MainIntent.putExtra("ID", userId);
                                    MainIntent.putExtra("Name", userdata.getUserName());
                                    MainIntent.putExtra("Number", userdata.getNum());
                                    LoginActivity.this.startActivity(MainIntent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 입력하세요.", Toast.LENGTH_LONG).show();
                                }
                            } else if (snapshot.child("Professor").child(userId).exists()) {                            //학생 사용자 리스트에서 아이디를 찾지 못하면 교수 사용자 리스트로 넘어감
                                UserData userdata = snapshot.child("Professor").child(userId).getValue(UserData.class);
                                if (userdata.getPasswd() != null && userdata.getPasswd().equals(userPasswd)) {
                                    Toast toast = Toast.makeText(LoginActivity.this,
                                            "Login succeeded!", Toast.LENGTH_SHORT);
                                    toast.show();

                                    Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                    //로그인한 사용자의 정보도 넘겨준다.
//                                    MainIntent.putExtra("ID", userId);
                                    MainIntent.putExtra("Name", userdata.getUserName());
                                    MainIntent.putExtra("Number", userdata.getNum());
                                    LoginActivity.this.startActivity(MainIntent);
                                    finish();
                                } else {
                                    Toast.makeText(getApplicationContext(), "비밀번호를 다시 입력하세요.", Toast.LENGTH_LONG).show();
                                }
                            } else {
                                Toast.makeText(getApplicationContext(), "아이디를 다시 입력하세요.", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });
    }
}