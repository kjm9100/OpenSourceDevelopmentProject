package com.example.qna;

import android.content.Context;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.ArrayList;
import java.util.List;

public class ChatRecycleRAdapter extends RecyclerView.Adapter<ChatRecycleRAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private List<String> uidList = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    //    private FirebaseAuth auth = FirebaseAuth.getInstance();
    private String myName;
    private String myNum;

    //데이터 레이아웃
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView textView_Username;
        public TextView textView_message;
        public CardView cardView;
        public View rootView;
        public LinearLayout whole_msg;
        public Button check_btn;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView_Username = itemView.findViewById(R.id.username_textview);
            textView_message = itemView.findViewById(R.id.ChatRoomList);
            cardView = itemView.findViewById(R.id.ChatRoom_CardView);
            whole_msg = itemView.findViewById(R.id.whole_msg);
            check_btn = itemView.findViewById(R.id.btnCheck);
            rootView = itemView;
        }
    }

    //ChatRecycle 생성자(채팅정보, context, 현재 사용자 이름)
    public ChatRecycleRAdapter(List<ChatData> myDataset, Context context,String mynum) {
        mDataset = myDataset;
//        this.myName = myname;
        this.myNum = mynum;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.row_chat, parent, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        ChatData chat = mDataset.get(position);
        holder.textView_message.setText(chat.getMessage());

        //현재 사용자와 이름이 같으면 오른쪽, 다른 사용자가 보낸 문자는 왼쪽으로 정렬
        if (chat.getNum() != null && chat.getNum().equals(this.myNum)) {
            holder.textView_message.setGravity(Gravity.END);
//            holder.textView_Username.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
            holder.cardView.setBackgroundColor(Color.parseColor("#F8EFA3"));  //현재 사용자가 작성한 질문은 노란색 배경
            holder.check_btn.setGravity(Gravity.END);
            holder.whole_msg.setGravity(Gravity.END);  //현재 사용자가 작성한 메시지를 오른쪽 끝으로 정렬
        } else {
            holder.textView_Username.setText(chat.getNum());
            holder.textView_message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.textView_Username.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);
            holder.cardView.setBackgroundColor(Color.parseColor("#FFFFFF"));  //다른 사용자가 작성한 질문 흰색배경
            holder.whole_msg.setGravity(Gravity.START); //다른 사용자 이름, 작성한 메시지 왼쪽으로 정렬
        }

        holder.check_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onCheckClicked(database.getReference().child("group1").child("message").child(uidList.get(holder.getBindingAdapterPosition())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

//    public void onCheckClicked(DatabaseReference chatRef){
//        chatRef.runTransaction(new Transaction.Handler() {
//            @NonNull
//            @Override
//            public Transaction.Result doTransaction(@NonNull MutableData currentData) {
//                ChatData Chat = currentData.getValue(ChatData.class);
//                if (Chat == null) {
//                    return Transaction.success(currentData);
//                }
//                //공감버튼 누른 사람 확인
//                if(Chat.checks.containsKey(auth.getCurrentUser().getUid())) {
//                    Chat.check_cnt = Chat.check_cnt-1;
//                    Chat.checks.remove(auth.getCurrentUser().getUid());
//                }else {
//                    Chat.check_cnt = Chat.check_cnt+1;
//                    Chat.checks.put(auth.getCurrentUser().getUid(),true);
//                }
//                currentData.setValue(Chat);
//                return Transaction.success(currentData);
//            }
//            @Override
//            public void onComplete(@Nullable DatabaseError error, boolean committed, @Nullable DataSnapshot currentData) {
//                Log.d("CHATCHAT", "CHATTransaction:onComplete:" + error);
//            }
//        });
//    }

    public ChatData getChat(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addChat(ChatData chat) {
        mDataset.add(chat);
        notifyItemInserted(mDataset.size() - 1);


    }
}
