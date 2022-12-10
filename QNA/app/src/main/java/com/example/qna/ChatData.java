package com.example.qna;

import java.util.HashMap;
import java.util.Map;

public class ChatData {
    public String message; //사용자가 주고 받는 메시지
    public String name;    //사용자의 이름
    public String num;     //사용자 사번 또는 학번

    public int check_cnt=0;  //질문에 대한 공감 갯수 정보
    public boolean pushAlaCheck = false;


    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

//    public Map<String, Boolean> getChecks() {
//        return checks;
//    }
//
//    public void setChecks(Map<String, Boolean> checks) {
//        this.checks = checks;
//    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCheck_cnt() {
        return check_cnt;
    }

    public void setCheck_cnt(int check_cnt) {
        this.check_cnt = check_cnt;
    }

}
