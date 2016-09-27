package com.example.lmj.a2hm2.Community;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lmj.a2hm2.Msg.bb_Msg;
import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.TabPagerAdapter;

import java.util.ArrayList;

/**
 * Created by lmj on 2016/9/27.
 */
public class bbb_Community extends Fragment implements ViewPager.OnPageChangeListener, View.OnClickListener {
    private RelativeLayout tb_comment_discovery;
    private RelativeLayout tb_comment_my;
    private static TextView tb_tv_comment_discovery;
    private static TextView tb_tv_comment_my;
    private static View tb_comment_discovery_view;
    private static View tb_comment_my_view;
    private View community_fragment;
    public ArrayList<Fragment> Community_fragmentList;
    private ViewPager community_viewPager;



    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        community_fragment = inflater.inflate(R.layout.bbb, container, false);
        initView();
        return community_fragment;
    }

    private void initView() {
        tb_comment_discovery= (RelativeLayout) community_fragment.findViewById(R.id.tb_comment_discovery);
        tb_comment_my= (RelativeLayout) community_fragment.findViewById(R.id.tb_comment_my);
        tb_tv_comment_discovery=(TextView)community_fragment.findViewById(R.id.tb_tv_comment_discovery);
        tb_tv_comment_my=(TextView)community_fragment.findViewById(R.id.tb_tv_comment_my);
        tb_comment_discovery_view=(View) community_fragment.findViewById(R.id.tb_comment_discovery_view);
        tb_comment_my_view=(View)community_fragment.findViewById(R.id.tb_comment_my_view);
        community_viewPager=(ViewPager) community_fragment.findViewById(R.id.community_viewPager);


        Community_fragmentList=new ArrayList<Fragment>();
        Community_fragmentList.add(new bb_Community());
        Community_fragmentList.add(new bb_Msg());

        tb_comment_discovery.setOnClickListener(this);
        tb_comment_my.setOnClickListener(this);

        community_viewPager.setAdapter(new TabPagerAdapter(getActivity().getSupportFragmentManager(), Community_fragmentList));
        community_viewPager.setOnPageChangeListener(this);
        setTabSelection(0);
        community_viewPager.setCurrentItem(0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        setTabSelection(position);

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private static void clearSelection(){
        tb_tv_comment_discovery.setTextColor(Color.parseColor("#757575"));
        tb_tv_comment_my.setTextColor(Color.parseColor("#757575"));
        tb_comment_discovery_view.setBackgroundColor(Color.parseColor("#FEDB43"));
        tb_comment_my_view.setBackgroundColor(Color.parseColor("#FEDB43"));
    }
    private static void setTabSelection(int i) {
        clearSelection();
        switch (i){
            case 0:
                tb_tv_comment_discovery.setTextColor(Color.parseColor("#000000"));
                tb_comment_discovery_view.setBackgroundColor(Color.parseColor("#000000"));
                break;
            case 1:
                tb_tv_comment_my.setTextColor(Color.parseColor("#000000"));
                tb_comment_my_view.setBackgroundColor(Color.parseColor("#000000"));
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tb_comment_discovery:
                setTabSelection(0);
                community_viewPager.setCurrentItem(0);
                break;
            case R.id.tb_comment_my:
                setTabSelection(1);
                community_viewPager.setCurrentItem(1);
                break;
        }
    }
}
