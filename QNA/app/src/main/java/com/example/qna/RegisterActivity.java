package com.example.qna;

import static java.sql.DriverManager.println;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();
    private ArrayAdapter adapter;
    private Spinner spinner;

    private Button RegisterButton;
    private EditText Name;
    private EditText StdId;
    private EditText Passwd;
    private String userType="Students";
    private String testFCM;

    private RadioGroup SelectUserType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        Log.d("TOKENTOKENCHECK", testFCM);
        spinner = (Spinner) findViewById(R.id.majorSpinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.major,
                android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Name=(EditText)findViewById(R.id.nameText) ;
        StdId=(EditText)findViewById(R.id.idText) ;
        Passwd=(EditText)findViewById(R.id.pdText) ;

        RegisterButton=(Button)findViewById(R.id.registerButton);
        SelectUserType =(RadioGroup) findViewById(R.id.genderGroup);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(new OnCompleteListener<String>() {
            @Override
            public void onComplete(@NonNull Task<String> task) {
                testFCM = task.getResult().toString();
            }
        });

        SelectUserType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                if (checkedId == R.id.genderProfessor) {
                    Toast.makeText(getApplicationContext(),"교수", Toast.LENGTH_LONG).show();
                    userType = "Professor";
                }
//                if (checkedId == R.id.genderStudent) {
                else if(checkedId == R.id.genderStudent){
                    Toast.makeText(getApplicationContext(),"학생", Toast.LENGTH_LONG).show();
                    userType = "Students";
                }
            }
        });

        RegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String NAME = Name.getText().toString();
                String NUM = StdId.getText().toString();
                String UPW = Passwd.getText().toString();

                myRef.child("UserData").child(userType).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        //저장된 학번(사번), ID중 중복되는것이 있는지 확인한다.
                        UserData userCheck = snapshot.child(snapshot.getKey().toString()).getValue(UserData.class);

                        if(snapshot.child(NUM).exists()){
//                            Toast.makeText(getApplicationContext(),"Sign Fail- id", Toast.LENGTH_LONG).show();
                        }
                        else{
                            //만약 학생이라고 체크하면 학생그룹에 데이터 저장
                            if (userType == "Students") {
                                addStdData(NAME, NUM, UPW, testFCM);
                                userType = "";
//                                testFCM = FirebaseMessaging.getInstance().getToken().getResult();
                                //회원가입 성공시 회원가입 성공 화면으로 넘어감, 성공 토스트 메시지 출력
                                Toast.makeText(getApplicationContext(),"Success Sign",Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent1.putExtra("StdId", NUM);
                                intent1.putExtra("name", NAME);
                                intent1.putExtra("FCM", testFCM);
//                                intent1.putExtra("FCM", testFCM);
                                startActivity(intent1);
                            }
                            //교수자라고 체크하면 교수님 그룹에 데이터 저장
                            else if (userType=="Professor") {
                                addProfData(NAME, NUM, UPW, testFCM);
                                userType = "";
                                //회원가입 성공시 회원가입 성공 화면으로 넘어감, 성공 토스트 메시지 출력
                                Toast.makeText(getApplicationContext(),"Success Sign",Toast.LENGTH_LONG).show();
                                Intent intent1 = new Intent(RegisterActivity.this, LoginActivity.class);
                                intent1.putExtra("StdId", NUM);
                                intent1.putExtra("name", NAME);
                                intent1.putExtra("FCM", testFCM);

                                startActivity(intent1);
                            }
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }
    //회원가입한 사용자의 Id, Passwd, 학번,이름 데이터베이스에 저장
    //데이터베이스 users 항목의 학생 그룹에 데이터 저장
    public void addStdData(String userName, String userstdNum, String userPasswd, String test){
        UserData userData = new UserData();
        userData.setUserName(userName);
        userData.setNum(userstdNum);
        userData.setPasswd(userPasswd);
        userData.setFCM(test);
        myRef.child("UserData").child("Students").child(userstdNum).setValue(userData);
    }

    //데이터베이스 users 항목의 교수님 그룹에 데이터 저장
    public void addProfData(String userName, String userproNum, String userPasswd, String test){
        UserData userData = new UserData();
        userData.setUserName(userName);
        userData.setNum(userproNum);
        userData.setPasswd(userPasswd);
        userData.setFCM(test);
        myRef.child("UserData").child("Professor").child(userproNum).setValue(userData);
    }
}