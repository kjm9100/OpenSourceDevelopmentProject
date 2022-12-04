package com.example.qna;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MyPageFragment extends Fragment {
    private View view;
    private FirebaseDatabase database= FirebaseDatabase.getInstance();
    private DatabaseReference databaseReference=database.getReference();
    LinearLayout semester1, semester2,subjects,mypagelayout;
    CheckBox fresh1_1, fresh1_2,shopho1_1,shopho1_2,shopho1_3,shopho1_4,shopho1_5,shopho1_6,shopho1_7;
    CheckBox junior1_1,junior1_2,junior1_3,junior1_4,junior1_5,junior1_6,junior1_7;
    CheckBox senior1_1,senior1_2,senior1_3,senior1_4,senior1_5,senior1_6,senior1_7;
    CheckBox fresh2_1,fresh2_2,fresh2_3,shopho2_1,shopho2_2,shopho2_3,shopho2_4,shopho2_5,shopho2_6,shopho2_7;
    CheckBox junior2_1,junior2_2,junior2_3,junior2_4,junior2_5,junior2_6,junior2_7;
    CheckBox senior2_1,senior2_2,senior2_3,senior2_4,senior2_5,senior2_6,senior2_7;
    Button btnok, btncancel;
    TextView registrationQ, interestQ, answerQ, subjectChoice;
    View dialogView;
    int subnum=0;
    String[] sub=new String[10];
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat=new SimpleDateFormat("MM");
    public String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }
    public void adddata(String data, int i){
        DB db=new DB(data);
        databaseReference.child("subject").child("sub"+i).setValue(data);
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_mypage, container, false);
        int month=Integer.parseInt(getTime());
        for(int i=0;i<10;i++){
            sub[i]="";
        }
        registrationQ = (TextView) view.findViewById(R.id.registrationQ);
        interestQ = (TextView) view.findViewById(R.id.interestQ);
        answerQ = (TextView) view.findViewById(R.id.answerQ);
        subjectChoice = (TextView) view.findViewById(R.id.subjectChoice);

        semester1=(LinearLayout)view.findViewById(R.id.semester1);
        semester2=(LinearLayout)view.findViewById(R.id.semester2);

        subjects=(LinearLayout)view.findViewById(R.id.subjects);
        mypagelayout=(LinearLayout)view.findViewById(R.id.mypagelayout);

        btncancel=(Button)view.findViewById(R.id.btncancel);
        btnok=(Button)view.findViewById(R.id.btnok);

        fresh1_1=(CheckBox)view.findViewById(R.id.fresh1_1);
        fresh1_2=(CheckBox)view.findViewById(R.id.fresh1_2);
        fresh2_1=(CheckBox)view.findViewById(R.id.fresh2_1);
        fresh2_2=(CheckBox)view.findViewById(R.id.fresh2_2);
        fresh2_3=(CheckBox)view.findViewById(R.id.fresh2_3);

        shopho1_1=(CheckBox)view.findViewById(R.id.shopho1_1);
        shopho1_2=(CheckBox)view.findViewById(R.id.shopho1_2);
        shopho1_3=(CheckBox)view.findViewById(R.id.shopho1_3);
        shopho1_4=(CheckBox)view.findViewById(R.id.shopho1_4);
        shopho1_5=(CheckBox)view.findViewById(R.id.shopho1_5);
        shopho1_6=(CheckBox)view.findViewById(R.id.shopho1_6);
        shopho1_7=(CheckBox)view.findViewById(R.id.shopho1_7);
        shopho2_1=(CheckBox)view.findViewById(R.id.shopho2_1);
        shopho2_2=(CheckBox)view.findViewById(R.id.shopho2_2);
        shopho2_3=(CheckBox)view.findViewById(R.id.shopho2_3);
        shopho2_4=(CheckBox)view.findViewById(R.id.shopho2_4);
        shopho2_5=(CheckBox)view.findViewById(R.id.shopho2_5);
        shopho2_6=(CheckBox)view.findViewById(R.id.shopho2_6);
        shopho2_7=(CheckBox)view.findViewById(R.id.shopho2_7);

        junior1_1=(CheckBox)view.findViewById(R.id.junior1_1);
        junior1_2=(CheckBox)view.findViewById(R.id.junior1_2);
        junior1_3=(CheckBox)view.findViewById(R.id.junior1_3);
        junior1_4=(CheckBox)view.findViewById(R.id.junior1_4);
        junior1_5=(CheckBox)view.findViewById(R.id.junior1_5);
        junior1_6=(CheckBox)view.findViewById(R.id.junior1_6);
        junior1_7=(CheckBox)view.findViewById(R.id.junior1_7);
        junior2_1=(CheckBox)view.findViewById(R.id.junior2_1);
        junior2_2=(CheckBox)view.findViewById(R.id.junior2_2);
        junior2_3=(CheckBox)view.findViewById(R.id.junior2_3);
        junior2_4=(CheckBox)view.findViewById(R.id.junior2_4);
        junior2_5=(CheckBox)view.findViewById(R.id.junior2_5);
        junior2_6=(CheckBox)view.findViewById(R.id.junior2_6);
        junior2_7=(CheckBox)view.findViewById(R.id.junior2_7);

        senior1_1=(CheckBox)view.findViewById(R.id.senior1_1);
        senior1_2=(CheckBox)view.findViewById(R.id.senior1_2);
        senior1_3=(CheckBox)view.findViewById(R.id.senior1_3);
        senior1_4=(CheckBox)view.findViewById(R.id.senior1_4);
        senior1_5=(CheckBox)view.findViewById(R.id.senior1_5);
        senior1_6=(CheckBox)view.findViewById(R.id.senior1_6);
        senior1_7=(CheckBox)view.findViewById(R.id.senior1_7);
        senior2_1=(CheckBox)view.findViewById(R.id.senior2_1);
        senior2_2=(CheckBox)view.findViewById(R.id.senior2_2);
        senior2_3=(CheckBox)view.findViewById(R.id.senior2_3);
        senior2_4=(CheckBox)view.findViewById(R.id.senior2_4);
        senior2_5=(CheckBox)view.findViewById(R.id.senior2_5);
        senior2_6=(CheckBox)view.findViewById(R.id.senior2_6);
        senior2_7=(CheckBox)view.findViewById(R.id.senior2_7);

        registrationQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        interestQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        answerQ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
//===============================================================================================//
        fresh1_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fresh1_1.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=fresh1_1.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        fresh1_1.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(fresh1_1.getText().toString())){
                            sub[i]=fresh1_1.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        fresh1_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fresh1_2.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=fresh1_2.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        fresh1_2.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(fresh1_2.getText().toString())){
                            sub[i]=fresh1_2.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        fresh2_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fresh2_1.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=fresh2_1.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        fresh2_1.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(fresh2_1.getText().toString())){
                            sub[i]=fresh2_1.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        fresh2_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fresh2_2.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=fresh2_2.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        fresh2_2.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(fresh2_2.getText().toString())){
                            sub[i]=fresh2_2.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        fresh2_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(fresh2_3.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=fresh2_3.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        fresh2_3.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(fresh2_3.getText().toString())){
                            sub[i]=fresh2_3.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho1_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho1_1.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho1_1.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho1_1.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho1_1.getText().toString())){
                            sub[i]=shopho1_1.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho1_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho1_2.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho1_2.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho1_2.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho1_2.getText().toString())){
                            sub[i]=shopho1_2.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho1_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho1_3.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho1_3.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho1_3.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho1_3.getText().toString())){
                            sub[i]=shopho1_3.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho1_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho1_4.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho1_4.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho1_4.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho1_4.getText().toString())){
                            sub[i]=shopho1_4.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho1_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho1_5.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho1_5.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho1_5.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho1_5.getText().toString())){
                            sub[i]=shopho1_5.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho1_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho1_6.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho1_6.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho1_6.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho1_6.getText().toString())){
                            sub[i]=shopho1_6.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho1_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho1_7.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho1_7.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho1_7.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho1_7.getText().toString())){
                            sub[i]=shopho1_7.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho2_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho2_1.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho2_1.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho2_1.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho2_1.getText().toString())){
                            sub[i]=shopho2_1.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho2_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho2_2.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho2_2.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho2_2.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho2_2.getText().toString())){
                            sub[i]=shopho2_2.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho2_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho2_3.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho2_3.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho2_3.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho2_3.getText().toString())){
                            sub[i]=shopho2_3.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho2_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho2_4.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho2_4.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho2_4.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho2_4.getText().toString())){
                            sub[i]=shopho2_4.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho2_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho2_5.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho2_5.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho2_5.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho2_5.getText().toString())){
                            sub[i]=shopho2_5.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho2_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho2_6.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho2_6.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho2_6.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho2_6.getText().toString())){
                            sub[i]=shopho2_6.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        shopho2_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(shopho2_7.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=shopho2_7.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        shopho2_7.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(shopho2_7.getText().toString())){
                            sub[i]=shopho2_7.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior1_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior1_1.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior1_1.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior1_1.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior1_1.getText().toString())){
                            sub[i]=junior1_1.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior1_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior1_2.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior1_2.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior1_2.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior1_2.getText().toString())){
                            sub[i]=junior1_2.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior1_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior1_3.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior1_3.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior1_3.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior1_3.getText().toString())){
                            sub[i]=junior1_3.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior1_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior1_4.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior1_4.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior1_4.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior1_4.getText().toString())){
                            sub[i]=junior1_4.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior1_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior1_5.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior1_5.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior1_5.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior1_5.getText().toString())){
                            sub[i]=junior1_5.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior1_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior1_6.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior1_6.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior1_6.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior1_6.getText().toString())){
                            sub[i]=junior1_6.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior1_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior1_7.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior1_7.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior1_7.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior1_7.getText().toString())){
                            sub[i]=junior1_7.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior2_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior2_1.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior2_1.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior2_1.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior2_1.getText().toString())){
                            sub[i]=junior2_1.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior2_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior2_2.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior2_2.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior2_2.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior2_2.getText().toString())){
                            sub[i]=junior2_2.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior2_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior2_3.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior2_3.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior2_3.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior2_3.getText().toString())){
                            sub[i]=junior2_3.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior2_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior2_4.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior2_4.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior2_4.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior2_4.getText().toString())){
                            sub[i]=junior2_4.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior2_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior2_5.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior2_5.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior2_5.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior2_5.getText().toString())){
                            sub[i]=junior2_5.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior2_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior2_6.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior2_6.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior2_6.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior2_6.getText().toString())){
                            sub[i]=junior2_6.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        junior2_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(junior2_7.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=junior2_7.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        junior2_7.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(junior2_7.getText().toString())){
                            sub[i]=junior2_7.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior1_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior1_1.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior1_1.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior1_1.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior1_1.getText().toString())){
                            sub[i]=senior1_1.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior1_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior1_2.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior1_2.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior1_2.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior1_2.getText().toString())){
                            sub[i]=senior1_2.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior1_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior1_3.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior1_3.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior1_3.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior1_3.getText().toString())){
                            sub[i]=senior1_3.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior1_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior1_4.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior1_4.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior1_4.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior1_4.getText().toString())){
                            sub[i]=senior1_4.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior1_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior1_5.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior1_5.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior1_5.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior1_5.getText().toString())){
                            sub[i]=senior1_5.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior1_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior1_6.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior1_6.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior1_6.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior1_6.getText().toString())){
                            sub[i]=senior1_6.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior1_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior1_7.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior1_7.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior1_7.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior1_7.getText().toString())){
                            sub[i]=senior1_7.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior2_1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior2_1.isChecked()){
                    if(subnum<10){
                        for(int i=0;i<10;i++){
                            subnum=subnum+1;
                            if(sub[i].equals("")){
                                sub[i]=senior2_1.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior2_1.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior2_1.getText().toString())){
                            sub[i]=senior2_1.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior2_2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior2_2.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior2_2.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior2_2.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior2_2.getText().toString())){
                            sub[i]=senior2_2.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior2_3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior2_3.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior2_3.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior2_3.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior2_3.getText().toString())){
                            sub[i]=senior2_3.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior2_4.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior2_4.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior2_4.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior2_4.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior2_4.getText().toString())){
                            sub[i]=senior2_4.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior2_5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior2_5.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior2_5.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior2_5.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior2_5.getText().toString())){
                            sub[i]=senior2_5.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior2_6.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior2_6.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior2_6.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior2_6.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior2_6.getText().toString())){
                            sub[i]=senior2_6.getText().toString();
                            break;
                        }
                    }
                }
            }
        });
        senior2_7.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(senior2_7.isChecked()){
                    if(subnum<10){
                        subnum=subnum+1;
                        for(int i=0;i<10;i++){
                            if(sub[i].equals("")){
                                sub[i]=senior2_7.getText().toString();
                                break;
                            }
                        }
                    }
                    else{
                        senior2_7.setChecked(false);
                        Toast.makeText(getContext(),"You should choose up to 10", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    subnum=subnum-1;
                    for(int i=0;i<10;i++){
                        if(sub[i].equals(senior2_7.getText().toString())){
                            sub[i]=senior2_7.getText().toString();
                            break;
                        }
                    }
                }
            }
        });

        btnok.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                subjects.setVisibility(View.GONE);
                mypagelayout.setVisibility(View.VISIBLE);
                for(int i=0;i<10;i++) {
                    if(!sub[i].equals("")) {
                        adddata(sub[i],i+1);
                    }
                }
            }
        });

        btncancel.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                subnum=0;
                subjects.setVisibility(View.GONE);
                mypagelayout.setVisibility(View.VISIBLE);
                fresh1_1.setChecked(false);
                fresh1_2.setChecked(false);
                fresh2_1.setChecked(false);
                fresh2_2.setChecked(false);
                fresh2_3.setChecked(false);
                shopho1_1.setChecked(false);
                shopho1_2.setChecked(false);
                shopho1_3.setChecked(false);
                shopho1_4.setChecked(false);
                shopho1_5.setChecked(false);
                shopho1_6.setChecked(false);
                shopho1_7.setChecked(false);
                shopho2_1.setChecked(false);
                shopho2_2.setChecked(false);
                shopho2_3.setChecked(false);
                shopho2_4.setChecked(false);
                shopho2_5.setChecked(false);
                shopho2_6.setChecked(false);
                shopho2_7.setChecked(false);
                junior1_1.setChecked(false);
                junior1_2.setChecked(false);
                junior1_3.setChecked(false);
                junior1_4.setChecked(false);
                junior1_5.setChecked(false);
                junior1_6.setChecked(false);
                junior1_7.setChecked(false);
                junior2_1.setChecked(false);
                junior2_2.setChecked(false);
                junior2_3.setChecked(false);
                junior2_4.setChecked(false);
                junior2_5.setChecked(false);
                junior2_6.setChecked(false);
                junior2_7.setChecked(false);
                senior1_1.setChecked(false);
                senior1_2.setChecked(false);
                senior1_3.setChecked(false);
                senior1_4.setChecked(false);
                senior1_5.setChecked(false);
                senior1_6.setChecked(false);
                senior1_7.setChecked(false);
                senior2_1.setChecked(false);
                senior2_2.setChecked(false);
                senior2_3.setChecked(false);
                senior2_4.setChecked(false);
                senior2_5.setChecked(false);
                senior2_6.setChecked(false);
                senior2_7.setChecked(false);
            }

        });


        subjectChoice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                subjects.setVisibility(View.VISIBLE);
                mypagelayout.setVisibility(View.GONE);
                if(Integer.parseInt(getTime())<8){
                    semester1.setVisibility(View.VISIBLE);
                    semester2.setVisibility(View.GONE);
                }
                else{
                    semester1.setVisibility(View.GONE);
                    semester2.setVisibility(View.VISIBLE);
                }
            }
        });

        return view;
    }
}
