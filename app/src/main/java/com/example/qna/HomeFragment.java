package com.example.qna;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class HomeFragment extends Fragment {
    private View view;

    TextView textviewcalendar;
    String cal;
    long mNow;
    Date mDate;
    SimpleDateFormat mFormat=new SimpleDateFormat("MM");
    public String getTime(){
        mNow = System.currentTimeMillis();
        mDate = new Date(mNow);
        return mFormat.format(mDate);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_home, container, false);

        textviewcalendar=(TextView)view.findViewById(R.id.textviewcalendar);
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
                    cal=elements.get(month).select("ul").text().replace(" [", "\n[").replace("]", "] ");

                } catch (IOException e) {
                    e.printStackTrace();
                }
                bundle.putString("cal", cal);
                Message msg = handler.obtainMessage();
                msg.setData(bundle);
                handler.sendMessage(msg);
            }
        }.start();

        return view;
    }

    Handler handler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            textviewcalendar.setText(bundle.getString("cal"));
        }
    };

}