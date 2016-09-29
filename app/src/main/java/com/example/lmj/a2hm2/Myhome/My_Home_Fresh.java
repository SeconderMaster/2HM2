package com.example.lmj.a2hm2.Myhome;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.lmj.a2hm2.R;

/**
 * Created by lmj on 2016/9/28.
 */
public class My_Home_Fresh extends Fragment {
    private View my_home_fresh;
    private RecyclerView my_home_fresh_RecyclerView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        my_home_fresh = inflater.inflate(R.layout.my_home_fresh_fragment, container, false);
        initView();
        return my_home_fresh;
    }

    private void initView() {
        my_home_fresh_RecyclerView= (RecyclerView) my_home_fresh.findViewById(R.id.my_home_fresh_RecyclerView);
    }
}
