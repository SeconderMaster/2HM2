package com.example.lmj.a2hm2.My;

import android.graphics.Typeface;
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
import android.widget.TextView;

import com.example.lmj.a2hm2.Adapter.CommunityArticleAdapter;
import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.Release.ReleaseBean;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

/**
 * Created by lmj on 2016/9/11.
 */
public class My_Data_Release extends Fragment{
    private TextView community_add_btn;
    private View ic_community_add;
    private ArrayList<ReleaseBean> marticle=new ArrayList<>();
    private CommunityArticleAdapter madapter;
    private RecyclerView community_view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        ic_community_add = inflater.inflate(R.layout.community_fragment, container, false);
        initview();
        initgetdata();
        return ic_community_add;


    }
    private void initgetdata() {
            new getMy().execute();
    }



    private void initview() {
        community_view= (RecyclerView) ic_community_add.findViewById(R.id.lv_community);
        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "ic_community_add/iconfont.ttf");
        community_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        community_view.setAdapter(madapter = new CommunityArticleAdapter(getActivity(), marticle));
    }
    class getMy extends AsyncTask<Void,Void,Void> {

        @Override
        protected Void doInBackground(Void... params) {
            BmobQuery<ReleaseBean> query=new BmobQuery<ReleaseBean>();
            query.addWhereEqualTo("article", BmobUser.getCurrentUser(My_User.class));
            query.addWhereEqualTo("isCommunity","true");
            query.findObjects(new FindListener<ReleaseBean>() {
                @Override
                public void done(List<ReleaseBean> list, BmobException e) {
                    if(e==null){
                        if (list != null) {
                            marticle.clear();
                            marticle.addAll(list);
                        }
                        madapter.notifyDataSetChanged();
                    }
                    else {
                        Log.i("wfh","失败："+e.getMessage()+","+e.getErrorCode());
                    }
                }
            });

            return null;
        }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            madapter.notifyDataSetChanged();
        }
    }
}
