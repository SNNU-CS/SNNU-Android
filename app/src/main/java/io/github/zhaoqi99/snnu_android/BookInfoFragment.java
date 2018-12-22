package io.github.zhaoqi99.snnu_android;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;

import java.util.ArrayList;

import io.github.zhaoqi99.snnu_android.Model.BorrowInfoModel;
import io.github.zhaoqi99.snnu_android.Model.GradeModel;


/**
 * A simple {@link Fragment} subclass.
 */
public class BookInfoFragment extends Fragment {

    View view;
    RecyclerView recyclerView;
    BorrowInfoModel borrowInfoModel;
    ArrayList<BorrowInfoModel.Result> newsList = new ArrayList<>();

    public BookInfoFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_book_info, container, false);
        BookInfoFragment.GetTask getTask = new BookInfoFragment.GetTask();
        getTask.execute();

        recyclerView = view.findViewById(R.id.recycler_view_bookInfo);
        return view;
    }

    private class GetTask extends AsyncTask<Void, Void, BorrowInfoModel> {

        @Override
        protected BorrowInfoModel doInBackground(Void... strings) {
            try {
                getRemoteInfo();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return borrowInfoModel;
        }

        @Override
        protected void onPostExecute(BorrowInfoModel borrowInfoModel) {
            newsList = new ArrayList(borrowInfoModel.getResult());
            mBookInfoRecyclerViewAdapter myGradeRecyclerViewAdapter = new mBookInfoRecyclerViewAdapter(newsList);
            LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setAdapter(myGradeRecyclerViewAdapter);
        }

        private BorrowInfoModel getRemoteInfo() throws Exception {
            String ss = httprequest.httpRequest("http://118.24.104.99/api/v1/lib/getBorrowInfo");
            Gson gson = new GsonBuilder().create();
            borrowInfoModel = gson.fromJson(ss, BorrowInfoModel.class);
            return borrowInfoModel;
        }
    }
}
