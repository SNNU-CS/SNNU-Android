package io.github.zhaoqi99.snnu_android;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


import java.util.ArrayList;

import io.github.zhaoqi99.snnu_android.Adapter.myGradeRecyclerViewAdapter;
import io.github.zhaoqi99.snnu_android.Model.GradeModel;

/**
 * A simple {@link Fragment} subclass.
 */
public class GradeTab extends Fragment {


    public GradeTab() {
        // Required empty public constructor
    }
    View view;
    RecyclerView recyclerView;
    GradeModel gradeModel;
    ArrayList<GradeModel.Data> newsList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        GetTask getTask=new GetTask();
        getTask.execute();

        view = inflater.inflate(R.layout.fragment_grade_tab, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_grade);

        return view;
    }
    private class GetTask extends AsyncTask<Void,Void,GradeModel > {

        @Override
        protected GradeModel doInBackground(Void... strings) {
            try {
                getRemoteInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return gradeModel;
        }

        @Override
        protected void onPostExecute(GradeModel gradeModel) {
            newsList=new ArrayList(gradeModel.getData());
            myGradeRecyclerViewAdapter myGradeRecyclerViewAdapter = new myGradeRecyclerViewAdapter(newsList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myGradeRecyclerViewAdapter);
        }

        private GradeModel getRemoteInfo() throws Exception {
            String ss=httprequest.httpRequest("http://118.24.104.99:/api/v1/urp/getGrade");
            Gson gson = new GsonBuilder().create();
            gradeModel=gson.fromJson(ss,GradeModel.class);
            return gradeModel;
        }
    }

}
