package com.example.qna;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class NoticeFragment extends Fragment implements View.OnClickListener {
    private View view;
    Button subjectButton, majorButton, libButton;
    LinearLayout majornoticeLayout,libnoticeLayout, subnoticeLayout;

    int line=0;
    String action="stop";
    final Bundle bundle = new Bundle();
    String url="로딩중";
    String dialogtitle="로딩중", dialogcontents="로딩중";
    View dialogView;
    int page=0;
    int last=111;
    String title="에러";
    Button btnprev, btnnext;
    TextView textviewtitle, textviewcontents, textviewpage;
    Button btn1,btn2, btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,btn21;

    int libline=0;
    String libaction="stop";
    final Bundle libbundle = new Bundle();
    String liburl="로딩중";
    String libdialogtitle="로딩중", libdialogcontents="로딩중";
    View libdialogView;

    int libpage=1;
    int liblast=100;
    String libtitle="에러";
    Button libbtnprev, libbtnnext;
    TextView libtextviewpage;
    Button btnfront, btnend, libbtnfront, libbtnend;
    Button libbtn1,libbtn2, libbtn3,libbtn4,libbtn5,libbtn6,libbtn7,libbtn8,libbtn9,libbtn10,libbtn11;
    Handler libbasichandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            libtextviewpage.setText(Integer.toString(libpage));

            libbtn11.setVisibility(View.VISIBLE);
            libbtn10.setVisibility(View.VISIBLE);
            libbtn9.setVisibility(View.VISIBLE);
            libbtn8.setVisibility(View.VISIBLE);
            libbtn7.setVisibility(View.VISIBLE);
            libbtn6.setVisibility(View.VISIBLE);
            libbtn5.setVisibility(View.VISIBLE);
            libbtn4.setVisibility(View.VISIBLE);
            libbtn3.setVisibility(View.VISIBLE);
            libbtn2.setVisibility(View.VISIBLE);

            libbtn1.setText("[중요] "+bundle.getString("title1"));                      //이런식으로 View를 메인 쓰레드에서 뿌려줘야한다.
            libbtn2.setText(bundle.getString("title2"));
            libbtn3.setText(bundle.getString("title3"));
            libbtn4.setText(bundle.getString("title4"));
            libbtn5.setText(bundle.getString("title5"));
            libbtn6.setText(bundle.getString("title6"));
            libbtn7.setText(bundle.getString("title7"));
            libbtn8.setText(bundle.getString("title8"));
            libbtn9.setText(bundle.getString("title9"));
            libbtn10.setText(bundle.getString("title10"));
            libbtn11.setText(bundle.getString("title11"));

            switch(libline){
                case 10:
                    libbtn11.setVisibility(View.GONE);
                case 9:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    break;
                case 8:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    libbtn9.setVisibility(View.GONE);
                    break;
                case 7:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    libbtn9.setVisibility(View.GONE);
                    libbtn8.setVisibility(View.GONE);
                    break;
                case 6:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    libbtn9.setVisibility(View.GONE);
                    libbtn8.setVisibility(View.GONE);
                    libbtn7.setVisibility(View.GONE);
                    break;
                case 5:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    libbtn9.setVisibility(View.GONE);
                    libbtn8.setVisibility(View.GONE);
                    libbtn7.setVisibility(View.GONE);
                    libbtn6.setVisibility(View.GONE);
                    break;
                case 4:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    libbtn9.setVisibility(View.GONE);
                    libbtn8.setVisibility(View.GONE);
                    libbtn7.setVisibility(View.GONE);
                    libbtn6.setVisibility(View.GONE);
                    libbtn5.setVisibility(View.GONE);
                    break;
                case 3:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    libbtn9.setVisibility(View.GONE);
                    libbtn8.setVisibility(View.GONE);
                    libbtn7.setVisibility(View.GONE);
                    libbtn6.setVisibility(View.GONE);
                    libbtn5.setVisibility(View.GONE);
                    libbtn4.setVisibility(View.GONE);
                    break;
                case 2:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    libbtn9.setVisibility(View.GONE);
                    libbtn8.setVisibility(View.GONE);
                    libbtn7.setVisibility(View.GONE);
                    libbtn6.setVisibility(View.GONE);
                    libbtn5.setVisibility(View.GONE);
                    libbtn4.setVisibility(View.GONE);
                    libbtn3.setVisibility(View.GONE);
                    break;
                case 1:
                    libbtn11.setVisibility(View.GONE);
                    libbtn10.setVisibility(View.GONE);
                    libbtn9.setVisibility(View.GONE);
                    libbtn8.setVisibility(View.GONE);
                    libbtn7.setVisibility(View.GONE);
                    libbtn6.setVisibility(View.GONE);
                    libbtn5.setVisibility(View.GONE);
                    libbtn4.setVisibility(View.GONE);
                    libbtn3.setVisibility(View.GONE);
                    libbtn2.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };
    public void libtitlecrawl(Elements e){
        libline=0;
        try{
            for (int i = 1; i < 12; i++) {
                //title=element.text();
                libtitle = e.get(i-1).text();
                liburl = e.get(i-1).attr("abs:href");
                libbundle.putString("title" + Integer.toString(i), libtitle);
                libbundle.putString("contents" + Integer.toString(i), liburl);
                libline=libline+1;

            }
        }catch(Exception ee){}
    }

    public void libnoticrawling() {
        new Thread() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect("https://cbnul.chungbuk.ac.kr/bbs/Bbs.ax?bbsID=1&page="+Integer.toString(libpage)+"&categoryID=0").get();

                    Elements elements = doc.select("tbody").select("a");          //회차 id값 가져오기
                    //Elements elements=doc.select("h4");
                    libtitlecrawl(elements);
                    //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                    Message msg = libbasichandler.obtainMessage();
                    msg.setData(libbundle);
                    libbasichandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    Handler basichandler = new Handler(Looper.getMainLooper()){
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            if(action.equals("next")) {
                textviewpage.setText(Integer.toString(page));
            }
            else if(action.equals("prev")) {
                if (page == 0) {
                    textviewpage.setText("중요");
                }
                else{
                    textviewpage.setText(Integer.toString(page));
                }
            }
            btn21.setVisibility(View.VISIBLE);
            btn20.setVisibility(View.VISIBLE);
            btn19.setVisibility(View.VISIBLE);
            btn18.setVisibility(View.VISIBLE);
            btn17.setVisibility(View.VISIBLE);

            btn1.setText(bundle.getString("title1"));                      //이런식으로 View를 메인 쓰레드에서 뿌려줘야한다.
            btn2.setText(bundle.getString("title2"));
            btn3.setText(bundle.getString("title3"));
            btn4.setText(bundle.getString("title4"));
            btn5.setText(bundle.getString("title5"));
            btn6.setText(bundle.getString("title6"));
            btn7.setText(bundle.getString("title7"));
            btn8.setText(bundle.getString("title8"));
            btn9.setText(bundle.getString("title9"));
            btn10.setText(bundle.getString("title10"));
            btn11.setText(bundle.getString("title11"));
            btn12.setText(bundle.getString("title12"));
            btn13.setText(bundle.getString("title13"));
            btn14.setText(bundle.getString("title14"));
            btn15.setText(bundle.getString("title15"));
            btn16.setText(bundle.getString("title16"));
            btn17.setText(bundle.getString("title17"));
            btn18.setText(bundle.getString("title18"));
            btn19.setText(bundle.getString("title19"));
            btn20.setText(bundle.getString("title20"));
            btn21.setText(bundle.getString("title21"));

            switch(line){
                case 20:
                    btn21.setVisibility(View.GONE);
                    break;
                case 19:
                    btn21.setVisibility(View.GONE);
                    btn20.setVisibility(View.GONE);
                    break;
                case 18:
                    btn21.setVisibility(View.GONE);
                    btn20.setVisibility(View.GONE);
                    btn19.setVisibility(View.GONE);
                    break;
                case 17:
                    btn21.setVisibility(View.GONE);
                    btn20.setVisibility(View.GONE);
                    btn19.setVisibility(View.GONE);
                    btn18.setVisibility(View.GONE);
                    break;
                case 16:
                    btn21.setVisibility(View.GONE);
                    btn20.setVisibility(View.GONE);
                    btn19.setVisibility(View.GONE);
                    btn18.setVisibility(View.GONE);
                    btn17.setVisibility(View.GONE);
                    break;
                default:
                    break;
            }
        }
    };
    public void majortitlecrawl(Elements e){
        line=0;
        try{
            if(page==0) {
                for (int i = 1; i < 19; i++) {
                    Element element = e.get(i);
                    //title=element.text();
                    title = element.text();
                    url = element.select("a").attr("href");
                    bundle.putString("title" + Integer.toString(i), title);
                    bundle.putString("contents" + Integer.toString(i), url);
                    line=line+1;
                }
            }else{
                for (int i = 19; i < 39 ; i++) {
                    int j=i;
                    Element element = e.get(i);
                    //title=element.text();
                    title = element.text();
                    url = element.select("a").attr("href");
                    bundle.putString("title" + Integer.toString(j-18), title);
                    bundle.putString("contents" + Integer.toString(j-18), url);
                    line=line+1;
                }
            }
        }catch(Exception ee){}
    }

    public void majornoticrawling() {
        new Thread() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    if (page != 0) {
                        doc = Jsoup.connect("https://software.cbnu.ac.kr/index.php?mid=sub0401&page=" + Integer.toString(page)).get();
                    } else {
                        doc = Jsoup.connect("https://software.cbnu.ac.kr/index.php?mid=sub0401&page=" + "1").get();
                    }

                    //doc = Jsoup.connect("https://software.cbnu.ac.kr/sub0204").get();
                    Elements elements = doc.getElementsByAttributeValue("class", "title");          //회차 id값 가져오기
                    //Elements elements=doc.select("h4");
                    majortitlecrawl(elements);
                    //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                    Message msg = basichandler.obtainMessage();
                    msg.setData(bundle);
                    basichandler.sendMessage(msg);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }.start();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_notice, container, false);

        subjectButton = (Button) view.findViewById(R.id.subjectButton);
        majorButton = (Button) view.findViewById(R.id.majorButton);
        libButton = (Button) view.findViewById(R.id.libButton);
        majornoticeLayout = (LinearLayout) view.findViewById(R.id.majorNoticeLayout);
        libnoticeLayout=(LinearLayout)view.findViewById(R.id.libnoticeLayout);
        majorButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
        subnoticeLayout=(LinearLayout)view.findViewById(R.id.subnoticeLayout);
        subjectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                majornoticeLayout.setVisibility(View.GONE);
                libnoticeLayout.setVisibility(View.GONE);
                subjectButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                majorButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                libButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                subnoticeLayout.setVisibility(View.VISIBLE);

//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.noticeFragment, new NoticeFragment1());
//                fragmentTransaction.commit();
            }
        });

        majorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                majornoticrawling();
                subnoticeLayout.setVisibility(View.GONE);
                libnoticeLayout.setVisibility(View.GONE);
                majornoticeLayout.setVisibility(View.VISIBLE);
                subjectButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                majorButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                libButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));

//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.noticeFragment, new NoticeFragment2());
//                fragmentTransaction.commit();
            }
        });

        libButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                libnoticrawling();
                subnoticeLayout.setVisibility(View.GONE);
                libtextviewpage.setText("로딩중");
                libnoticeLayout.setVisibility(View.VISIBLE);
                majornoticeLayout.setVisibility(View.GONE);
                subjectButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                majorButton.setBackgroundColor(getResources().getColor(R.color.colorPrimary));
                libButton.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));

//                FragmentManager fragmentManager = getParentFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.noticeFragment, new NoticeFragment3());
//                fragmentTransaction.commit();
            }
        });
        try{
            Document doc=Jsoup.connect("https://cbnul.chungbuk.ac.kr/bbs/Bbs.ax?bbsID=1&page=1").get();
            liblast=Integer.parseInt(doc.getElementsByAttributeValue("class", "next").get(1).attr("href").replace("Bbs.ax?bbsID=1&page=",""));
        }catch(Exception e){}

        btnfront=(Button)view.findViewById(R.id.btnfront);
        btnend=(Button)view.findViewById(R.id.btnend);
        libbtnfront=(Button)view.findViewById(R.id.libbtnfront);
        libbtnend=(Button)view.findViewById(R.id.libbtnend);

        //libnoticrawling();
        libtextviewpage=(TextView) view.findViewById(R.id.libtextviewpage);

        libbtnprev=(Button)view.findViewById(R.id.libbtnprev);
        libbtnnext=(Button)view.findViewById(R.id.libbtnnext);

        btn1=(Button)view.findViewById(R.id.btn1);
        btn2=(Button)view.findViewById(R.id.btn2);
        btn3=(Button)view.findViewById(R.id.btn3);
        btn4=(Button)view.findViewById(R.id.btn4);
        btn5=(Button)view.findViewById(R.id.btn5);
        btn6=(Button)view.findViewById(R.id.btn6);
        btn7=(Button)view.findViewById(R.id.btn7);
        btn8=(Button)view.findViewById(R.id.btn8);
        btn9=(Button)view.findViewById(R.id.btn9);
        btn10=(Button)view.findViewById(R.id.btn10);
        btn11=(Button)view.findViewById(R.id.btn11);
        btn12=(Button)view.findViewById(R.id.btn12);
        btn13=(Button)view.findViewById(R.id.btn13);
        btn14=(Button)view.findViewById(R.id.btn14);
        btn15=(Button)view.findViewById(R.id.btn15);
        btn16=(Button)view.findViewById(R.id.btn16);
        btn17=(Button)view.findViewById(R.id.btn17);
        btn18=(Button)view.findViewById(R.id.btn18);
        btn19=(Button)view.findViewById(R.id.btn19);
        btn20=(Button)view.findViewById(R.id.btn20);
        btn21=(Button)view.findViewById(R.id.btn21);

        libbtn1=(Button)view.findViewById(R.id.libbtn1);
        libbtn2=(Button)view.findViewById(R.id.libbtn2);
        libbtn3=(Button)view.findViewById(R.id.libbtn3);
        libbtn4=(Button)view.findViewById(R.id.libbtn4);
        libbtn5=(Button)view.findViewById(R.id.libbtn5);
        libbtn6=(Button)view.findViewById(R.id.libbtn6);
        libbtn7=(Button)view.findViewById(R.id.libbtn7);
        libbtn8=(Button)view.findViewById(R.id.libbtn8);
        libbtn9=(Button)view.findViewById(R.id.libbtn9);
        libbtn10=(Button)view.findViewById(R.id.libbtn10);
        libbtn11=(Button)view.findViewById(R.id.libbtn11);

        try {
            Document doc = Jsoup.connect("https://software.cbnu.ac.kr/sub0401").get();
            last = Integer.parseInt(doc.getElementsByAttributeValue("class", "frst_last bubble").get(1).text());
        }catch(Exception e){}
        majornoticrawling();
        textviewpage=(TextView) view.findViewById(R.id.textviewpage);

        btnprev=(Button)view.findViewById(R.id.btnprev);
        btnnext=(Button)view.findViewById(R.id.btnnext);

        libbtnfront.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                libpage=1;
                libtextviewpage.setText("로딩중");
                libnoticrawling();
            }
        });
        libbtnend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                libpage=liblast;
                libtextviewpage.setText("로딩중");
                libnoticrawling();
            }
        });

        libbtnprev.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(libpage>1){
                    libpage = libpage - 1;
                    libtextviewpage.setText("로딩중");
                    libaction="prev";
                    libnoticrawling();
                }
                else if(libpage==1){
                    Toast tmsg=Toast.makeText(getContext(), "마지막 페이지입니다. ",Toast.LENGTH_SHORT);
                    tmsg.show();
                }
            }
        });
        libbtnnext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(libpage<liblast){
                    libpage = libpage + 1;
                    libaction = "next";
                    libtextviewpage.setText("로딩중");
                    libnoticrawling();
                }else{
                    Toast tmsg=Toast.makeText(getContext(), "마지막 페이지입니다. ",Toast.LENGTH_SHORT);
                    tmsg.show();
                }
            }
        });
        libbtn1.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title1");
                            liburl=libbundle.getString("contents1").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText("[중요] "+libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn2.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title2");
                            liburl=libbundle.getString("contents2").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn3.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title3");
                            liburl=libbundle.getString("contents3").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn4.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title4");
                            liburl=libbundle.getString("contents4").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn5.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title5");
                            liburl=libbundle.getString("contents5").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn6.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title6");
                            liburl=libbundle.getString("contents6").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn7.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title7");
                            liburl=libbundle.getString("contents7").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn8.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title8");
                            liburl=libbundle.getString("contents8").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn9.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title9");
                            liburl=libbundle.getString("contents9").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn10.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title10");
                            liburl=libbundle.getString("contents10").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        libbtn11.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                libdialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            libdialogtitle=libbundle.getString("title10");
                            liburl=libbundle.getString("contents10").toLowerCase().replace("id", "ID").replace("detail", "Detail");
                            dialogdoc=Jsoup.connect(liburl).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "xed");
                            libdialogcontents=elements.text().replace(". ", "\n");
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
                            Message msg = handler.obtainMessage();
                            msg.setData(libbundle);
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
                    textviewtitle=(TextView)libdialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(libdialogtitle);
                    textviewcontents=(TextView)libdialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(libdialogcontents+"\n");
                    dlg.setView(libdialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btnfront.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                page=0;
                textviewpage.setText("로딩중");
                action="prev";
                majornoticrawling();
            }
        });

        btnend.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                page=last;
                textviewpage.setText("로딩중");
                action="next";
                majornoticrawling();
            }
        });

        btnprev.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(page>0){
                    page = page - 1;
                    textviewpage.setText("로딩중");
                    action="prev";
                    majornoticrawling();
                }
                else if(page==0){
                    Toast.makeText(getContext(), "마지막 페이지입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(page<last){
                    page = page + 1;
                    action="next";
                    textviewpage.setText("로딩중");
                    majornoticrawling();
                }
                else{
                    Toast.makeText(getContext(), "마지막 페이지입니다.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title1");
                            url=bundle.getString("contents1").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title2");
                            url=bundle.getString("contents2").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title3");
                            url=bundle.getString("contents3").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn4.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title4");
                            url=bundle.getString("contents4").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn5.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title5");
                            url=bundle.getString("contents5").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn6.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title6");
                            url=bundle.getString("contents6").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn7.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title7");
                            url=bundle.getString("contents7").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn8.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title8");
                            url=bundle.getString("contents8").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn9.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title9");
                            url=bundle.getString("contents9").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn10.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title10");
                            url=bundle.getString("contents10").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });
        btn11.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title11");
                            url=bundle.getString("contents11").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });
        btn12.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title12");
                            url=bundle.getString("contents12").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });
        btn13.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title13");
                            url=bundle.getString("contents13").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn14.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title14");
                            url=bundle.getString("contents14").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn15.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title15");
                            url=bundle.getString("contents15").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn16.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title16");
                            url=bundle.getString("contents16").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });
        btn17.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title17");
                            url=bundle.getString("contents17").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });
        btn18.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title18");
                            url=bundle.getString("contents18").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });
        btn19.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title19");
                            url=bundle.getString("contents19").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn20.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title19");
                            url=bundle.getString("contents19").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        btn21.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(getContext());
            public void onClick(View v){
                dialogView=(View)View.inflate(getContext(), R.layout.dialog, null);
                new Thread(){
                    @Override
                    public void run() {
                        Document doc = null;
                        try {
                            dialogtitle=bundle.getString("title19");
                            url=bundle.getString("contents19").toLowerCase();
                            dialogdoc=Jsoup.connect(url).get();
                            Elements elements= dialogdoc.getElementsByAttributeValue("class", "rd_body clear");
                            dialogcontents=elements.text();
                            //핸들러를 이용해서 Thread()에서 가져온 데이터를 메인 쓰레드에 보내준다.
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
                    textviewtitle=(TextView)dialogView.findViewById(R.id.textviewtitle);
                    textviewtitle.setText(dialogtitle);
                    textviewcontents=(TextView)dialogView.findViewById(R.id.textviewcontents);
                    textviewcontents.setText(dialogcontents+"\n");
                    dlg.setView(dialogView);
                    dlg.setPositiveButton("확인", new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which){

                        }
                    });
                    dlg.show();
                }
            };
        });

        return view;
    }

    @Override
    public void onClick(View v) {

    }
}