package io.github.zhaoqi99.snnu_android;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import io.github.zhaoqi99.snnu_android.Model.NoticeMessage;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsTab extends Fragment {
    private RecyclerView recyclerView;
    private mNoticeRecyclerViewAdapter mNoticeRecyclerViewAdapter;

    public void setDep(String dep) {
        this.dep = dep;
    }

    private String dep;

    public void setType(String type) {
        this.type = type;
    }

    private String type;
    View view;
    ArrayList<NoticeMessage> newsList = new ArrayList<>();
    String result;

    public NewsTab() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        QueryTask queryTask=new QueryTask();
        queryTask.execute(dep);

        view = inflater.inflate(R.layout.fragment_tab, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

   private class QueryTask extends AsyncTask<String, Integer, String> {

        @Override
        protected String doInBackground(String... strings) {
            try {
                getRemoteInfo(strings[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            newsList=getNotice(result);
            mNoticeRecyclerViewAdapter = new mNoticeRecyclerViewAdapter(newsList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(mNoticeRecyclerViewAdapter);
        }

        private void getRemoteInfo(String dep) throws Exception {
            String Target_URL = "";
            String namespace = "";
            String methodName = " ";
            if(type=="通知")
            {
               Target_URL = "http://118.24.104.99:8080/Notice.asmx";
                namespace = "http://webxml.zhaoqi.vip/";
               methodName = "getNoticeByDepartment ";
            }
            else
            {
                Target_URL = "http://118.24.104.99:8080/News.asmx";
                namespace = "http://webxml.zhaoqi.vip/";
                methodName = "getNewsByDepartment ";
            }
            SoapObject request = new SoapObject(namespace, methodName);
            request.addProperty("dep", dep);
            SoapSerializationEnvelope envelope = new
                    SoapSerializationEnvelope(SoapSerializationEnvelope.VER12);
            envelope.bodyOut = request;
            envelope.dotNet = true;
            HttpTransportSE httpTransportSE = new HttpTransportSE(Target_URL);
            httpTransportSE.call(null, envelope);
            SoapObject object = (SoapObject) envelope.bodyIn;
            result = object.getProperty(0).toString();
        }
    }

    private ArrayList<NoticeMessage> getNotice(String str) {
        String str_Title = "";
        String str_Date = "";
        String str_Department = "";
        String str_Type = "";
        String str_Link = "";
        ArrayList<NoticeMessage> messageList = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            if (i + 5 < str.length() && str.substring(i, i + 5).equals("Title")) {

                i += 6;
                int tag1 = str.indexOf(';', i);
                if (str.charAt(tag1 + 1) != ' ') {
                    tag1++;
                    tag1 = str.indexOf(';', tag1);
                }
                str_Title = str.substring(i, tag1);//"Title";
                i = tag1 + 1;
                i += 6;
                int tag2 = str.indexOf(';', i);

                str_Link = str.substring(i, tag2);//"Link";
                i = tag2 + 1;
                i += 6;
                int tag3 = str.indexOf(';', i);
                int tag3x = str.indexOf("T", i);
                str_Date = str.substring(i, tag3x);//"Data";
                i = tag3 + 1;
                i += 6;
                int tag4 = str.indexOf(';', i);

                str_Type = str.substring(i, tag4);//"Type";
                i = tag4 + 1;
                i += 12;
                int tag5 = str.indexOf(';', i);

                str_Department = str.substring(i, tag5);//"Department";
                i = tag5 + 1;
                if (str_Title.length() > 17)
                    str_Title = str_Title.substring(0, 17) + "...";
                NoticeMessage message = new NoticeMessage("   " + str_Title, "时间：" + str_Date, str_Link, str_Type, str_Department);
                messageList.add(message);
            }
        }
        return  messageList;
    }
}
