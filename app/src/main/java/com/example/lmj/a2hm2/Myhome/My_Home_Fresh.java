package com.example.lmj.a2hm2.Myhome;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.Release.ReleaseBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lmj on 2016/9/28.
 */
public class My_Home_Fresh extends Fragment {
    private View my_home_fresh;
    private RecyclerView my_home_fresh_RecyclerView;
    private ArrayList<ReleaseBean> releaseBeanArrayList=new ArrayList<>();
    private MyHomeAdapter myHomeAdapter;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        my_home_fresh = inflater.inflate(R.layout.my_home_fresh_fragment, container, false);
        initView();
        new getDate().execute();
        return my_home_fresh;
    }

    private void initView() {
        my_home_fresh_RecyclerView= (RecyclerView) my_home_fresh.findViewById(R.id.my_home_fresh_RecyclerView);

        my_home_fresh_RecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        my_home_fresh_RecyclerView.setAdapter(myHomeAdapter=new MyHomeAdapter(getActivity(),releaseBeanArrayList));
    }

    class getDate extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            BmobQuery<ReleaseBean> query=new BmobQuery<ReleaseBean>();
            query.findObjects(new FindListener<ReleaseBean>() {
                @Override
                public void done(List<ReleaseBean> list, BmobException e) {
                    if(e==null){
                        if (list != null) {
                            releaseBeanArrayList.clear();
                            releaseBeanArrayList.addAll(list);
                        }
                        myHomeAdapter.notifyDataSetChanged();
                    }
                    else {
                        Log.i("","失败："+e.getMessage()+","+e.getErrorCode());
                    }
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            myHomeAdapter.notifyDataSetChanged();
        }
    }
}
