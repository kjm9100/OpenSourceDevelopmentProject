package com.example.qna;

public class UserData {
    private String userName;
    private String Num;
    private String Passwd;
    private String FCM;


    public String getFCM() {
        return FCM;
    }

    public void setFCM(String FCM) {
        this.FCM = FCM;
    }
    public String getNum() {
        return Num;
    }

    public void setNum(String num) {
        Num = num;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPasswd() {
        return Passwd;
    }

    public void setPasswd(String passwd) {
        Passwd = passwd;
    }

}
