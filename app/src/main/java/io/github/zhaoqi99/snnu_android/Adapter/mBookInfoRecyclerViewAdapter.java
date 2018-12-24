package io.github.zhaoqi99.snnu_android.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.List;

import io.github.zhaoqi99.snnu_android.Model.BorrowInfoModel;
import io.github.zhaoqi99.snnu_android.R;


public class mBookInfoRecyclerViewAdapter extends RecyclerView.Adapter <mBookInfoRecyclerViewAdapter.ViewHolder>{

    private List<BorrowInfoModel.Result> resultList;

    public mBookInfoRecyclerViewAdapter(List<BorrowInfoModel.Result> resultList)
    {
        this.resultList = resultList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView user;
        TextView author;
        TextView bookname;
        TextView deadline;
        TextView loc;
        TextView campus;
        CardView cardView;
        public ViewHolder(View view)
        {
            super(view);
            user = (TextView)view.findViewById(R.id.bookInfo_user);
            bookname=(TextView)view.findViewById(R.id.bookInfo_bookname);
            author=(TextView)view.findViewById(R.id.bookInfo_author);
            deadline=(TextView)view.findViewById(R.id.bookInfo_deadline);
            loc=(TextView)view.findViewById(R.id.bookInfo_loc);
            campus=(TextView)view.findViewById(R.id.bookInfo_campus);
            cardView=(CardView)view.findViewById(R.id.card_view_book);
        }
    }
    @Override
    public int getItemCount() {
        return resultList.size();
    }

    @Override
    public void onBindViewHolder(mBookInfoRecyclerViewAdapter.ViewHolder holder, int position) {
        SimpleDateFormat sft=new SimpleDateFormat("yyyy-MM-dd");
        BorrowInfoModel.Result message = resultList.get(position);
        holder.user.setText("预约者:"+message.get预约者());
        holder.bookname.setText("书名:"+message.get书名());
        holder.author.setText("著者:"+message.get著者());
        holder.deadline.setText("保留结束日期:"+sft.format(message.get保留结束日期()));
        holder.loc.setText("取书地点:"+message.get取书地点());
        holder.campus.setText("单册分馆:"+message.get单册分馆());
    }

    @Override
    public mBookInfoRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_recycler_layout
                ,parent,false);
        mBookInfoRecyclerViewAdapter.ViewHolder holder = new mBookInfoRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }





}
