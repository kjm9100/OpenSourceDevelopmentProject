package com.example.qna;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ChatRecycleRAdapter extends RecyclerView.Adapter<ChatRecycleRAdapter.MyViewHolder> {
    private List<ChatData> mDataset;
    private List<String> uidList = new ArrayList<>();
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference();

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
            whole_msg = itemView.findViewById(R.id.whole_msg);
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
        ChatData chat = mDataset.get(position);
        holder.textView_message.setText(chat.getMessage());
//        randomKey = uidList.get(position);

        //현재 사용자와 이름이 같으면 오른쪽, 다른 사용자가 보낸 문자는 왼쪽으로 정렬
        if (chat.getNum() != null && chat.getNum().equals(this.myNum)) {
            holder.textView_message.setGravity(Gravity.END);
//            holder.textView_Username.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);
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

        holder.check_box.setOnCheckedChangeListener(null);
        holder.check_box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(!isChecked){
                    check = true;
                    chat.checks.put(myNum, true);
                    chat.setCheck_cnt(chat.getCheck_cnt()-1);
                    //                    updateData(chat.gestNum(),(chat.getCheck_cnt()+1), position);
                }
                else if(isChecked){
                    check = false;
                    chat.checks.remove(myNum);
                    chat.setCheck_cnt(chat.getCheck_cnt()+1);
//                    updateData(chat.getNum(),(chat.getCheck_cnt()-1), position);
                }
            }
        });

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

    public void updateData(String UserNumId, int Check_Cnt, int position){
        HashMap ChatInformUpdate = new HashMap();
//        ChatInformUpdate.put("num", UserNumId);
        ChatInformUpdate.put("check_cnt", Check_Cnt);

        myRef.child("ChatRoom_Question").child(Subject).child("message").child(uidList.get(position)).updateChildren(ChatInformUpdate);
//        myRef.child("ChatRoom_Question").child(Subject).child("message").child(uidList.get(position)).updateChildren("checks");
    }
}
