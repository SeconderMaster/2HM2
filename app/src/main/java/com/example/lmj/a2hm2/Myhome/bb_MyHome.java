package com.example.lmj.a2hm2.Myhome;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.TabPagerAdapter;

import java.util.ArrayList;

/**
 * Created by lmj on 2016/9/11.
 */
public class bb_MyHome extends Fragment implements View.OnClickListener,ViewPager.OnPageChangeListener{
    private View v_myhome;
    private TextView tb_scan;
    private TextView tb_list;
    private static TextView tv_my_home_fresh;
    private static TextView tv_my_home_la;
    private TextView tb_search;
//    private Banner banner;
    private RelativeLayout my_home_fresh;
    private RelativeLayout my_home_la;
    private static View my_home_fresh_view;
    private static View my_home_la_view;
    private ViewPager my_home_viewPager;
    public ArrayList<Fragment> My_Home_fragmentList;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v_myhome=inflater.inflate(R.layout.myhome_fragment,container,false);
        initView();
//        initBanner();
        setTabSelection(0);
        my_home_viewPager.setCurrentItem(0);
        return v_myhome;
    }

    @Override
    public void onResume() {
        super.onResume();
        initView();
    }
//
//    private void initBanner() {
//        banner= (Banner) v_myhome.findViewById(R.id.banner_myhome);
//        String[] images = getResources().getStringArray(R.array.url);
//        banner.setImages(images);

//    }

    private void initView() {
        tb_scan= (TextView) v_myhome.findViewById(R.id.tb_scan);
        tb_list= (TextView) v_myhome.findViewById(R.id.tb_list);
        tb_search= (TextView) v_myhome.findViewById(R.id.tb_search);
        tv_my_home_fresh= (TextView) v_myhome.findViewById(R.id.tv_my_home_fresh);
        tv_my_home_la= (TextView) v_myhome.findViewById(R.id.tv_my_home_la);
        my_home_fresh= (RelativeLayout) v_myhome.findViewById(R.id.my_home_fresh);
        my_home_la= (RelativeLayout) v_myhome.findViewById(R.id.my_home_la);
        my_home_fresh_view= (View) v_myhome.findViewById(R.id.my_home_fresh_view);
        my_home_la_view= (View) v_myhome.findViewById(R.id.my_home_la_view);
        my_home_viewPager= (ViewPager) v_myhome.findViewById(R.id.my_home_viewPager);

        My_Home_fragmentList=new ArrayList<Fragment>();
        My_Home_fragmentList.add(new My_Home_Fresh());
        My_Home_fragmentList.add(new My_Home_La());

        my_home_viewPager.setAdapter(new TabPagerAdapter(getActivity().getSupportFragmentManager(), My_Home_fragmentList));
        my_home_viewPager.setOnPageChangeListener(this);


        Typeface iconfont = Typeface.createFromAsset(getActivity().getAssets(), "ic_myhome_topbar/iconfont.ttf");
        tb_scan.setTypeface(iconfont);
        tb_list.setTypeface(iconfont);
        tb_search.setOnClickListener(this);
        my_home_fresh.setOnClickListener(this);
        my_home_la.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tb_search:
                Intent i=new Intent(getActivity(),MH_SearchView.class);
                i.putExtra("searched_key","");
                startActivity(i);
                break;
            case R.id.my_home_fresh:
                setTabSelection(0);
                my_home_viewPager.setCurrentItem(0);
                break;
            case R.id.my_home_la:
                setTabSelection(1);
                my_home_viewPager.setCurrentItem(1);
                break;
        }
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
        tv_my_home_fresh.setTextColor(Color.parseColor("#757575"));
        tv_my_home_la.setTextColor(Color.parseColor("#757575"));
        my_home_fresh_view.setBackgroundColor(Color.parseColor("#ffffff"));
        my_home_la_view.setBackgroundColor(Color.parseColor("#ffffff"));
    }
    private static void setTabSelection(int i) {
        clearSelection();
        switch (i){
            case 0:
                tv_my_home_fresh.setTextColor(Color.parseColor("#333333"));
                my_home_fresh_view.setBackgroundColor(Color.parseColor("#FEDB43"));
                break;
            case 1:
                tv_my_home_la.setTextColor(Color.parseColor("#333333"));
                my_home_la_view.setBackgroundColor(Color.parseColor("#FEDB43"));
                break;
        }
    }
}
