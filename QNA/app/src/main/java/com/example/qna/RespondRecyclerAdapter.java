package com.example.qna;

import android.content.Context;
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
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.HashMap;
import java.util.List;

public class RespondRecyclerAdapter extends RecyclerView.Adapter<RespondRecyclerAdapter.MyViewHolder> {
    private String myNum;
    private String Subject;
    private List<RespondData>mDataset;
    private List<String>uidList;
    private String res;

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView respond;
        private CardView back;
        private TextView name;
        private View rootView;
        private LinearLayout whole_rsp;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            respond = itemView.findViewById(R.id.RespondRoom_List);
            back = itemView.findViewById(R.id.RespondRoom_CardView);
            name = itemView.findViewById(R.id.respond_name_textview);
            whole_rsp = itemView.findViewById(R.id.whole_msg);
            rootView = itemView;
        }
    }

    //ChatRecycle 생성자(채팅정보, context, 현재 사용자 이름)
    public RespondRecyclerAdapter(List<RespondData> myDataset, Context context, String mynum, String subject) {
        mDataset = myDataset;
        this.myNum = mynum;
        this.Subject = subject;
    }

    @NonNull
    @Override
    public RespondRecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.respondlayout, parent, false);
        RespondRecyclerAdapter.MyViewHolder vh = new RespondRecyclerAdapter.MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull RespondRecyclerAdapter.MyViewHolder holder, final int position) {
        RespondData respondD = mDataset.get(position);
        holder.respond.setText(respondD.getRespond());

        holder.name.setText(respondD.getUserNumId());
        holder.back.setBackgroundColor(Color.parseColor("#F8EFA3"));  //현재 사용자가 작성한 질문은 노란색 배경
        holder.whole_rsp.setGravity(Gravity.START);  //현재 사용자가 작성한 메시지를 오른쪽 끝으로 정렬
    }

    @Override
    public int getItemCount() {
        return mDataset == null ? 0 : mDataset.size();
    }

    public RespondData getRespon(int position) {
        return mDataset != null ? mDataset.get(position) : null;
    }

    public void addRes(RespondData resp) {
        mDataset.add(resp);
        notifyItemInserted(mDataset.size() - 1);
    }
}
