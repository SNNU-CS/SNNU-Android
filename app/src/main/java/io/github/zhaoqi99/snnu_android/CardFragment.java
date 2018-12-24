package io.github.zhaoqi99.snnu_android;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import io.github.zhaoqi99.snnu_android.Adapter.myCardRecyclerViewAdapter;
import io.github.zhaoqi99.snnu_android.Model.CardMessage;

/**
 * A simple {@link Fragment} subclass.
 */
public class CardFragment extends Fragment {


    public CardFragment() {
        // Required empty public constructor
    }

    View view;
    String result;
    RecyclerView recyclerView;
    Button button;
    EditText editText;
    private ArrayList<CardMessage> messageList2 ;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_card, container, false);
        recyclerView = view.findViewById(R.id.recycler_view_card);
        button=view.findViewById(R.id.button_search);
        editText=view.findViewById(R.id.editText_id);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QueryTask queryTask=new QueryTask();
                String id=editText.getText().toString();
                queryTask.execute(id);
            }
        });
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
            messageList2 = getCard(result);
            myCardRecyclerViewAdapter myCardRecyclerViewAdapter = new myCardRecyclerViewAdapter(messageList2);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myCardRecyclerViewAdapter);
        }

        private void getRemoteInfo(String id) throws Exception {
            String Target_URL = "http://http://118.24.104.99:8080/CampusCard.asmx";
            String namespace = "http://webxml.zhaoqi.vip/";
            String methodName = "getConsumptionDdetails";
            SoapObject request = new SoapObject(namespace, methodName);
            request.addProperty("id", id);
            SoapSerializationEnvelope envelope = new
                    SoapSerializationEnvelope(SoapSerializationEnvelope.VER12);
            envelope.bodyOut = request;
            envelope.dotNet = true;
            HttpTransportSE httpTransportSE = new HttpTransportSE(Target_URL);
            httpTransportSE.call(null, envelope);
            SoapObject object = (SoapObject) envelope.bodyIn;
            result = object.getProperty(0).toString();
        }

        private ArrayList<CardMessage> getCard(String str) {
            String str_Date = "";
            String str_Frequency = "";
            String str_OrigiAmount = "";
            String str_TransAmount = "";
            String str_Balance = "";
            String str_Location = "";
            ArrayList<CardMessage> messageList2=new ArrayList<>();
            for (int i = 0; i < str.length(); i++) {
                if (i + 4 < str.length() && str.substring(i, i + 4).equals("Date")) {
                    i += 5;
                    int tag1 = str.indexOf(';', i);
                    str_Date = str.substring(i, tag1);
                    str_Date = str_Date.replace("T", "  ");  //Date
                    i = tag1 + 1;
                    i += 11;
                    int tag2 = str.indexOf(';', i);
                    str_Frequency = str.substring(i, tag2);    //Frequency
                    i = tag2 + 1;
                    i += 13;
                    int tag3 = str.indexOf(';', i);
                    str_OrigiAmount = str.substring(i, tag3);    //OrigiAmount
                    i = tag3 + 1;
                    i += 13;
                    int tag4 = str.indexOf(';', i);
                    str_TransAmount = str.substring(i, tag4);    //TransAmount
                    i = tag4 + 1;
                    i += 9;
                    int tag5 = str.indexOf(';', i);
                    str_Balance = str.substring(i, tag5);
                    i = tag5 + 1;
                    i += 10;
                    int tag6 = str.indexOf(';', i);
                    str_Location = str.substring(i, tag6);
                    i = tag6 + 1;
                    CardMessage message = new CardMessage("时间：" + str_Date, "次数：" + str_Frequency, str_OrigiAmount, "消费：" + str_TransAmount + "元", "余额：" + str_Balance + "元", "地点：" + str_Location);
                    messageList2.add(message);
                }
            }
            return messageList2;
        }
    }
}
