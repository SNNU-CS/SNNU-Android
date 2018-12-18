package io.github.zhaoqi99.snnu_android;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class myGradeRecyclerViewAdapter extends RecyclerView.Adapter<myGradeRecyclerViewAdapter.ViewHolder> {
    private List<GradeModel.Data> DataList;

    public myGradeRecyclerViewAdapter( List<GradeModel.Data> DataList)
    {
        this.DataList = DataList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView Title;
        TextView Date;
        TextView Department;
        public ViewHolder(View view)
        {
            super(view);
//            Title = (TextView)view.findViewById(R.id.txt_Title);
//            Date = (TextView)view.findViewById(R.id.txt_Date);
//            Department = (TextView)view.findViewById(R.id.txt_Department);
        }
    }
    @Override
    public int getItemCount() {
        return DataList.size();
    }

    @Override
    public void onBindViewHolder(myGradeRecyclerViewAdapter.ViewHolder holder, int position) {
        GradeModel.Data data = DataList.get(position);
//        holder.Title.setText(message.GetTitle());
//        holder.Date.setText(message.GetDate());
//        holder.Department.setText(message.GetDepartment());
    }

    @Override
    public myGradeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.notice_recycler_layout,parent,false);
        final myGradeRecyclerViewAdapter.ViewHolder holder = new myGradeRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }
}
