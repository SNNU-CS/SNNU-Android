package io.github.zhaoqi99.snnu_android.Adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import io.github.zhaoqi99.snnu_android.Model.GradeModel;
import io.github.zhaoqi99.snnu_android.R;

public class myGradeRecyclerViewAdapter extends RecyclerView.Adapter<myGradeRecyclerViewAdapter.ViewHolder> {
    private List<GradeModel.Data> DataList;

    public myGradeRecyclerViewAdapter( List<GradeModel.Data> DataList)
    {
        this.DataList = DataList;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView name;
        TextView shuxing;
        TextView xuefen;
        TextView score;
        CardView cardView;

        public ViewHolder(View view)
        {
            super(view);
            name=(TextView)view.findViewById(R.id.grade_course_name);
            shuxing =(TextView)view.findViewById(R.id.grade_course_shuxing);
            xuefen=(TextView)view.findViewById(R.id.grade_course_xuefen);
            score=(TextView)view.findViewById(R.id.grade_course_score);
            cardView=(CardView)view.findViewById(R.id.card_view_grade);
        }
    }
    @Override
    public int getItemCount() {
        return DataList.size();
    }

    @Override
    public void onBindViewHolder(myGradeRecyclerViewAdapter.ViewHolder holder, int position) {
        GradeModel.Data data = DataList.get(position);
        holder.name.setText(data.get课程名());
        holder.shuxing.setText(data.get课程属性());
        holder.xuefen.setText(data.get学分());
        holder.score.setText(data.get成绩());

    }

    @Override
    public myGradeRecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grade_recycler_layout,parent,false);
        final myGradeRecyclerViewAdapter.ViewHolder holder = new myGradeRecyclerViewAdapter.ViewHolder(view);
        return holder;
    }
}
