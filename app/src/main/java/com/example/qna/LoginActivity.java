package com.example.qna;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

import org.w3c.dom.Text;

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
                int StdIDLength = userId.length();

                if (userId == "") {
                    // ????????? ????????? - ????????? ????????? ??? ????????????.
                    Toast.makeText(getApplicationContext(), "????????? ????????? ??? ????????????.", Toast.LENGTH_LONG).show();
                } else {
                    myRef.child("UserData").addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
//                        Log.d("UserCheck",snapshot.child(userId).child("passwd").getValue().toString());
                            if (StdIDLength == 10) {
//                                Log.d("USERTYPETYPE", "studentSuccess");
                                if (snapshot.child("Students").child(userId).exists()) {                                    //?????? ???????????? ????????? ???????????? ?????? ????????? ????????? ????????????.
                                    UserData userdata = snapshot.child("Students").child(userId).getValue(UserData.class);  //??????????????? ???????????? ?????? ????????? ???????????? ?????? ???????????? ?????? ????????? ????????? ????????????.
                                    if (userdata.getPasswd() != null && userdata.getPasswd().equals(userPasswd)) {              //????????? ??????????????? ????????? ????????? ????????? ??????????????? ?????? ??? ????????? ??????
//                                    Intent intent = new Intent(loginActivity.this, MainActivity.class);    //????????? ????????? ?????????????????? ????????????.
                                        Toast toast = Toast.makeText(LoginActivity.this,
                                                "Login succeeded!", Toast.LENGTH_SHORT);
                                        toast.show();

                                        Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        //???????????? ???????????? ????????? ????????????.
//                                    MainIntent.putExtra("ID", userId);
                                        MainIntent.putExtra("Name", userdata.getUserName());
                                        MainIntent.putExtra("Number", userdata.getNum());
                                        MainIntent.putExtra("FCM",userdata.getFCM());
                                        LoginActivity.this.startActivity(MainIntent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "??????????????? ?????? ???????????????.", Toast.LENGTH_LONG).show();
                                    }
                                }
                            }
                            else {
//                                Log.d("USERTYPETYPE", "professorSuccess");
                                if (snapshot.child("Professor").child(userId).exists()) {                            //?????? ????????? ??????????????? ???????????? ?????? ????????? ?????? ????????? ???????????? ?????????
                                    UserData userdata = snapshot.child("Professor").child(userId).getValue(UserData.class);
                                    if (userdata.getPasswd() != null && userdata.getPasswd().equals(userPasswd)) {
                                        Toast toast = Toast.makeText(LoginActivity.this,
                                                "Login succeeded!", Toast.LENGTH_SHORT);
                                        toast.show();

                                        Intent MainIntent = new Intent(LoginActivity.this, MainActivity.class);
                                        //???????????? ???????????? ????????? ????????????.
//                                    MainIntent.putExtra("ID", userId);
                                        MainIntent.putExtra("Name", userdata.getUserName());
                                        MainIntent.putExtra("Number", userdata.getNum());
                                        MainIntent.putExtra("FCM",userdata.getFCM());
                                        LoginActivity.this.startActivity(MainIntent);
                                        finish();
                                    } else {
                                        Toast.makeText(getApplicationContext(), "??????????????? ?????? ???????????????.", Toast.LENGTH_LONG).show();
                                    }
                                } else {
                                    Toast.makeText(getApplicationContext(), "???????????? ?????? ???????????????.", Toast.LENGTH_LONG).show();
                                }
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