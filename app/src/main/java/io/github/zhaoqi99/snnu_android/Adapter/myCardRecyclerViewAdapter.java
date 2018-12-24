package io.github.zhaoqi99.snnu_android.Adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.zhaoqi99.snnu_android.Model.CardMessage;
import io.github.zhaoqi99.snnu_android.R;

public class myCardRecyclerViewAdapter extends  RecyclerView.Adapter<myCardRecyclerViewAdapter.ViewHolder>{
    private List<CardMessage> messageList;

    public myCardRecyclerViewAdapter(List<CardMessage> messageList)
    {
        this.messageList = messageList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView Frequency;
        TextView Date;
        TextView Location;
        TextView TransAmount;
        TextView Balance;
        public ViewHolder(View view)
        {
            super(view);
            Frequency = (TextView)view.findViewById(R.id.txt_Frequency);
            Date = (TextView)view.findViewById(R.id.txt_Date2);
            TransAmount = (TextView)view.findViewById(R.id.txt_TransAmount);
            Balance = (TextView)view.findViewById(R.id.txt_Balance);
            Location = (TextView)view.findViewById(R.id.txt_Location);
        }
    }
    @Override
    public int getItemCount() {
        return messageList.size();
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CardMessage message = messageList.get(position);
        holder.Date.setText(message.GetDate());
        holder.Frequency.setText(message.GetFrequency());
        holder.TransAmount.setText(message.GetTransAmount());
        holder.Balance.setText(message.GetBalance());
        holder.Location.setText(message.GetLocation());
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_recycler_layout
                ,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }
}
