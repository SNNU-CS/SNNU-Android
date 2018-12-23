package io.github.zhaoqi99.snnu_android;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import io.github.zhaoqi99.snnu_android.Model.CourseModel;



/**
 * A simple {@link Fragment} subclass.
 */
public class CourseTab extends Fragment {

    View view;
    CourseModel courseModel;
//    ArrayList<GradeModel.Data> newsList = new ArrayList<>();

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
        }

        private CourseModel getRemoteInfo() throws Exception {
            String ss = httprequest.httpRequest("http://118.24.104.99:/api/v1/urp/getCourses");
            Gson gson = new GsonBuilder().create();
            courseModel = gson.fromJson(ss, CourseModel.class);
            return courseModel;
        }
    }
}
