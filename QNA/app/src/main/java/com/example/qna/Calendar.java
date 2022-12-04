package com.example.qna;

import java.text.SimpleDateFormat;
import java.util.Date;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Date;

public class Calendar extends Activity{
    TextView textviewcalendar;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat=new SimpleDateFormat("MM");

    public String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home);

        textviewcalendar=(TextView)findViewById(R.id.textviewcalendar);
        textviewcalendar.setText("loading");
        final Bundle bundle = new Bundle();
        new Thread(){
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://www.chungbuk.ac.kr/site/www/sub.do?key=1853").get();
                    Elements elements = doc.getElementsByAttributeValue("class", "schedule");          //회차 id값 가져오기
                    int month=Integer.parseInt(getTime());

                    switch(month){
                        case 1:
                        case 2:
                            month=month+9;
                            break;
                        default:
                            month=month-3;
                            break;
                    }
                    String cal=elements.get(month).select("ul").get(0).text();

                    bundle.putString("cal", cal);                               //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                    Message msg = handler.obtainMessage();
                    msg.setData(bundle);
                    handler.sendMessage(msg);

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }
    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            textviewcalendar.setText(bundle.getString("cal"));
        }
    };

}
