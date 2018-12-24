package io.github.zhaoqi99.snnu_android;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.Spinner;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import io.github.zhaoqi99.snnu_android.Adapter.AbsGridAdapter;
import io.github.zhaoqi99.snnu_android.Adapter.CourseAdapter;
import io.github.zhaoqi99.snnu_android.Model.CourseModel;



/**
 * A simple {@link Fragment} subclass.
 */
public class CourseTab extends Fragment {

    View view;
    CourseModel courseModel;
    private Spinner spinner;
    private GridView detailCource;
    private String[][] contents;
    private AbsGridAdapter secondAdapter;
    private List<String> dataList;
    private ArrayAdapter<String> spinnerAdapter;
    public CourseTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        GetTask getTask = new GetTask();
        getTask.execute();
        view = inflater.inflate(R.layout.fragment_course_tab, container, false);
        return view;
    }

    private class GetTask extends AsyncTask<Void, Void, CourseModel> {

        @Override
        protected CourseModel doInBackground(Void... strings) {
            try {
                getRemoteInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return courseModel;
        }

        @Override
        protected void onPostExecute(CourseModel courseModel) {
            //更新UI
            spinner = (Spinner)view.findViewById(R.id.switchWeek);
            detailCource = (GridView)view.findViewById(R.id.courseDetail);
            ///////////////第一种方式创建Adapater
//        List<String> list = init();
//        adapter = new MyAdapter(this, list);s
//        detailCource.setAdapter(adapter);
            ///////////////第二种方式创建Adapter
            fillStringArray();
            secondAdapter = new AbsGridAdapter(getContext());
            secondAdapter.setContent(contents, 9, 7);
            detailCource.setAdapter(secondAdapter);
            //////////////创建Spinner数据
            fillDataList();
            spinnerAdapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_dropdown_item, dataList);
            spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spinner.setAdapter(spinnerAdapter);
        }

        private CourseModel getRemoteInfo() throws Exception {
            String ss = httprequest.httpRequest("http://118.24.104.99:/api/v1/urp/tempCourses");


            Gson gson = new GsonBuilder().create();
            courseModel = gson.fromJson(ss, CourseModel.class);
            return courseModel;
        }
    }
    /**
     * 准备数据
     */


    public void fillStringArray() {
        contents = new String[9][7];

        for(int i=0;i < 9; i++)
        {
            for(int j=0; j<7; j++)
            {
                contents[i][j] = "";
            }
        }
        List<CourseModel.Data> data = courseModel.getData();
        for(int i=0; i < data.size(); i++)
        {
            String name = data.get(i).getName();
            List<CourseModel.Info> infos = data.get(i).getInfo();
            for(int j = 0; j < infos.size(); j++)
            {
                contents[Integer.parseInt(infos.get(j).getTimeOfClass())-1][Integer.parseInt(infos.get(j).getDay())-1] = name+"@"+infos.get(j).getBuildings()+infos.get(j).getRoom();
            }
        }
    }

    public void fillDataList() {
        dataList = new ArrayList<>();
        for(int i = 1; i < 19; i++) {
            dataList.add("第" + i + "周");
        }
    }
}
