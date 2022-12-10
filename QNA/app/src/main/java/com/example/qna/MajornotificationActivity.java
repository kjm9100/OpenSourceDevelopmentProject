package com.example.qna;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Locale;


public class MajornotificationActivity extends Activity{
    int line=0;
    String action="stop";
    final Bundle bundle = new Bundle();
    String url="로딩중";
    String dialogtitle="로딩중", dialogcontents="로딩중";
    View dialogView;
    String key="에러";
    int page=0;
    int last=111;
    String title="에러";
    Button btnprev, btnnext;
    TextView textviewtitle, textviewcontents, textviewpage;
    Button btn1,btn2, btn3,btn4,btn5,btn6,btn7,btn8,btn9,btn10,btn11,btn12,btn13,btn14,btn15,btn16,btn17,btn18,btn19,btn20,btn21;
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
    public void titlecrawl(Elements e){
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

    public void noticrawling() {
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
                    titlecrawl(elements);
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_majornotification);
        try {
            Document doc = Jsoup.connect("https://software.cbnu.ac.kr/sub0401").get();
            last = Integer.parseInt(doc.getElementsByAttributeValue("class", "frst_last bubble").get(1).text());
        }catch(Exception e){}
        noticrawling();
        textviewpage=(TextView) findViewById(R.id.textviewpage);

        btnprev=(Button)findViewById(R.id.btnprev);
        btnnext=(Button)findViewById(R.id.btnnext);

        btn1=(Button)findViewById(R.id.btn1);
        btn2=(Button)findViewById(R.id.btn2);
        btn3=(Button)findViewById(R.id.btn3);
        btn4=(Button)findViewById(R.id.btn4);
        btn5=(Button)findViewById(R.id.btn5);
        btn6=(Button)findViewById(R.id.btn6);
        btn7=(Button)findViewById(R.id.btn7);
        btn8=(Button)findViewById(R.id.btn8);
        btn9=(Button)findViewById(R.id.btn9);
        btn10=(Button)findViewById(R.id.btn10);
        btn11=(Button)findViewById(R.id.btn11);
        btn12=(Button)findViewById(R.id.btn12);
        btn13=(Button)findViewById(R.id.btn13);
        btn14=(Button)findViewById(R.id.btn14);
        btn15=(Button)findViewById(R.id.btn15);
        btn16=(Button)findViewById(R.id.btn16);
        btn17=(Button)findViewById(R.id.btn17);
        btn18=(Button)findViewById(R.id.btn18);
        btn19=(Button)findViewById(R.id.btn19);
        btn20=(Button)findViewById(R.id.btn20);
        btn21=(Button)findViewById(R.id.btn21);


        btnprev.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(page>0){
                    page = page - 1;
                    textviewpage.setText("로딩중");
                    action="prev";
                    noticrawling();
                }
                else if(page==0){
                    Toast tmsg=Toast.makeText(MajornotificationActivity.this, "마지막 페이지입니다. ",Toast.LENGTH_SHORT);
                    tmsg.show();
                }
            }
        });
        btnnext.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v) {
                if(page<last){
                    page = page + 1;
                    action="next";
                    textviewpage.setText("로딩중");
                    noticrawling();
                }
                else{
                    Toast tmsg=Toast.makeText(MajornotificationActivity.this, "마지막 페이지입니다. ",Toast.LENGTH_SHORT);
                    tmsg.show();
                }
            }
        });
        btn1.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn2.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn3.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn4.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn5.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn6.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn7.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn8.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn9.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn10.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });
        btn11.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });
        btn12.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });
        btn13.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn14.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn15.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn16.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });
        btn17.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });
        btn18.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });
        btn19.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn20.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });

        btn21.setOnClickListener(new View.OnClickListener(){
            Document dialogdoc=null;
            AlertDialog.Builder dlg=new AlertDialog.Builder(MajornotificationActivity.this);
            public void onClick(View v){
                dialogView=(View)View.inflate(MajornotificationActivity.this, R.layout.dialog, null);
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
                    textviewcontents.setText(dialogcontents);
                    dlg.setView(dialogView);
                    dlg.show();
                }
            };
        });
    }

}
