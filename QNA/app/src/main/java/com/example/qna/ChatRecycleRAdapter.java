package com.example.qna;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.messaging.FirebaseMessaging;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatRecycleRAdapter extends RecyclerView.Adapter<ChatRecycleRAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private List<String> uidList = new ArrayList<>();
    private List<CheckData> checklist = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

    private static final String FCM_MSG_URL="https://fcm.googleapis.com/fcm/send";
    private String SERVER_KEY;

    private String myName;
    private String myNum;
    private String Subject;
    private boolean check = false;
    private String randomKey;

    //데이터 레이아웃
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textView_Username;
        private TextView textView_message;
        private CardView cardView;
        private View rootView;
        private LinearLayout whole_msg;
        private CheckBox check_box;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAbsoluteAdapterPosition();
                }
            });

            textView_Username = itemView.findViewById(R.id.username_textview);
            textView_message = itemView.findViewById(R.id.ChatRoomList);
            cardView = itemView.findViewById(R.id.ChatRoom_CardView);
            whole_msg = itemView.findViewById(R.id.whole_btn);
            check_box = itemView.findViewById(R.id.questioncheck);
            rootView = itemView;
        }
    }

    //ChatRecycle 생성자(채팅정보, context, 현재 사용자 이름)
    public ChatRecycleRAdapter(List<ChatData> myDataset,List<String>uidlist, Context context,String mynum, String subject) {
        mDataset = myDataset;
        uidList = uidlist;
        this.myNum = mynum;
        this.Subject = subject;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        int thisposition = position;
        int count;
        ChatData chat = mDataset.get(position);
        holder.textView_message.setText(chat.getMessage());

        //현재 사용자와 이름이 같으면 오른쪽, 다른 사용자가 보낸 문자는 왼쪽으로 정렬
        if (chat.getNum() != null && chat.getNum().equals(this.myNum)) {
            holder.textView_message.setGravity(Gravity.END);
            holder.cardView.setBackgroundColor(Color.parseColor("#F8EFA3"));  //현재 사용자가 작성한 질문은 노란색 배경
            holder.check_box.setGravity(Gravity.END);
            holder.whole_msg.setGravity(Gravity.END);  //현재 사용자가 작성한 메시지를 오른쪽 끝으로 정렬
        }

        else {
            holder.textView_Username.setText(chat.getNum());
            holder.textView_message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.textView_Username.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.cardView.setBackgroundColor(Color.parseColor("#FFFFFF"));  //다른 사용자가 작성한 질문 흰색배경
            holder.whole_msg.setGravity(Gravity.START); //다른 사용자 이름, 작성한 메시지 왼쪽으로 정렬
        }

//        holder.check_box.setChecked(check);
        holder.check_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chat.setCheck_cnt(chat.getCheck_cnt()+1);
                if(chat.getCheck_cnt() >= 1){
//                    sendPostToFCM_usr()
                }

                updateData(chat.getCheck_cnt(),thisposition);
            }
        });
//        holder.check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
////            @Override
////            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
////                chat.setCheck_cnt((chat.getCheck_cnt()+1));
////
////
//////                if(!isChecked){
//////                    check = true;
//////                    chat.setCheck_cnt(chat.getCheck_cnt()-1);
//////                    //                    updateData(chat.gestNum(),(chat.getCheck_cnt()+1), position);
//////                }
//////                else if(isChecked){
//////                    check = false;
//////                    chat.setCheck_cnt(chat.getCheck_cnt()+1);
////////                    updateData(chat.getNum(),(chat.getCheck_cnt()-1), position);
//////                }
////            }
////        });
//
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int thisposition = holder.getAbsoluteAdapterPosition();
                randomKey = uidList.get(thisposition);
                Context context = v.getContext();
                Intent respondAct = new Intent(context, RespondActivity.class);
                respondAct.putExtra("userId",myNum);
                respondAct.putExtra("subject",Subject);
                respondAct.putExtra("key",randomKey);
                respondAct.putExtra("Q",chat.getMessage());

                context.startActivity(respondAct);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public ChatData getChat(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size() - 1);
    }

    public void updateData(int Check_Cnt, int position){
        HashMap ChatInformUpdate = new HashMap();
        ChatInformUpdate.put("check_cnt", Check_Cnt);
        myRef.child("ChatRoom_Question").child(Subject).child("message").child(uidList.get(position)).updateChildren(ChatInformUpdate);
    }


    private void sendPostToFCM_usr(final String title, final String body, final String fcmToken){
        new Thread(new Runnable(){
            @Override
            public void run() {
                try{
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("title", title)
                            .put("body", body);
                    root.put("notification", notification);
                    root.put("to", fcmToken);

                    URL Url = new URL(FCM_MSG_URL);
                    HttpURLConnection httpUrlConn = (HttpURLConnection) Url.openConnection();
                    httpUrlConn.setRequestMethod("POST");
                    httpUrlConn.setDoOutput(true);
                    httpUrlConn.setDoInput(true);
                    httpUrlConn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    httpUrlConn.setRequestProperty("Accept", "application/json");
                    httpUrlConn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = httpUrlConn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    httpUrlConn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    // FCM에 다중 기기에게 푸시 알림 전송 요청
    private void sendPostToFCM_usrs(final String title, final String body, final List<String> fcmTokens) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    JSONObject root = new JSONObject();
                    JSONObject notification = new JSONObject();
                    notification.put("title", title)
                            .put("body", body);
                    root.put("notification", notification);

                    String fcmToken = FirebaseMessaging.getInstance().getToken().getResult();
                    for(String Token : fcmTokens)
                    {
                        if(Token != fcmToken)
                        {
                            root.put("to", fcmToken);
                        }
                    }

                    URL Url = new URL(FCM_MSG_URL);
                    HttpURLConnection httpUrlConn = (HttpURLConnection) Url.openConnection();
                    httpUrlConn.setRequestMethod("POST");
                    httpUrlConn.setDoOutput(true);
                    httpUrlConn.setDoInput(true);
                    httpUrlConn.addRequestProperty("Authorization", "key=" + SERVER_KEY);
                    httpUrlConn.setRequestProperty("Accept", "application/json");
                    httpUrlConn.setRequestProperty("Content-type", "application/json");
                    OutputStream os = httpUrlConn.getOutputStream();
                    os.write(root.toString().getBytes("utf-8"));
                    os.flush();
                    httpUrlConn.getResponseCode();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }




}
