package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.Community.bb_Community;
import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.TabPagerAdapter;

import java.util.ArrayList;

import cn.bmob.v3.BmobUser;

public class My_Data extends FragmentActivity implements ViewPager.OnPageChangeListener, View.OnClickListener {

    private TextView ic_my_data_back;
    private TextView ic_my_data_share;
    private TextView ic_my_data_QR_Code;
    private TextView ic_my_data_upload_user_head;
    private TextView ic_my_data_upload_background;
    private TextView my_data_user_name;
    private TextView my_data_user_last_time;
    private Button my_data_edit_data;
    private ViewPager my_data_viewPage;
    public ArrayList<Fragment> my_data_fragmentList;
    private RelativeLayout my_data_my;
    private RelativeLayout my_data_release;
    private RelativeLayout my_data_collect;
    private RelativeLayout my_data_evaluate;
    private static View my_data_my_view;
    private static View my_data_release_view;
    private static View my_data_collect_view;
    private static View my_data_evaluate_view;
    private static TextView tv_my_data_my;
    private static TextView tv_my_data_release;
    private static TextView tv_my_data_collect;
    private static TextView tv_my_data_evaluate;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__data);
        initView();
        setTabSelection(0);
        my_data_viewPage.setCurrentItem(0);
    }

    private void initView() {
        my_data_fragmentList = new ArrayList<Fragment>();
        my_data_fragmentList.add(new bb_Community(true));
        my_data_fragmentList.add(new My_Data_Release());
        my_data_fragmentList.add(new My_Data_Collect());
        my_data_fragmentList.add(new My_Data_Evaluate());
        my_data_viewPage= (ViewPager) findViewById(R.id.my_data_viewPage);
        my_data_viewPage.setAdapter(new TabPagerAdapter(getSupportFragmentManager(), my_data_fragmentList));
        my_data_viewPage.setOnPageChangeListener(this);
        ic_my_data_back= (TextView) findViewById(R.id.ic_my_data_back);
        ic_my_data_share= (TextView) findViewById(R.id.ic_my_data_share);
        ic_my_data_QR_Code= (TextView) findViewById(R.id.ic_my_data_QR_Code);
        ic_my_data_upload_user_head= (TextView) findViewById(R.id.ic_my_data_upload_user_head);
        ic_my_data_upload_background= (TextView) findViewById(R.id.ic_my_data_upload_background);
        my_data_user_name= (TextView) findViewById(R.id.my_data_user_name);
        tv_my_data_my= (TextView) findViewById(R.id.tv_my_data_my);
        tv_my_data_release= (TextView) findViewById(R.id.tv_my_data_release);
        tv_my_data_collect= (TextView) findViewById(R.id.tv_my_data_collect);
        tv_my_data_evaluate= (TextView) findViewById(R.id.tv_my_data_evaluate);
        my_data_user_last_time= (TextView) findViewById(R.id.my_data_user_last_time);
        my_data_edit_data= (Button) findViewById(R.id.my_data_edit_data);
        my_data_my= (RelativeLayout) findViewById(R.id.my_data_my);
        my_data_release= (RelativeLayout) findViewById(R.id.my_data_release);
        my_data_collect= (RelativeLayout) findViewById(R.id.my_data_collect);
        my_data_evaluate= (RelativeLayout) findViewById(R.id.my_data_evaluate);
        my_data_my_view=findViewById(R.id.my_data_my_view);
        my_data_release_view=findViewById(R.id.my_data_release_view);
        my_data_collect_view=findViewById(R.id.my_data_collect_view);
        my_data_evaluate_view=findViewById(R.id.my_data_evaluate_view);

        my_data_user_name.setText((String) BmobUser.getObjectByKey("username"));

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_my_data_back.setTypeface(iconfont1);

        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_my_data/iconfont.ttf");
        ic_my_data_share.setTypeface(iconfont);ic_my_data_QR_Code.setTypeface(iconfont);
        ic_my_data_upload_user_head.setTypeface(iconfont);ic_my_data_upload_background.setTypeface(iconfont);

        my_data_my.setOnClickListener(this);
        my_data_release.setOnClickListener(this);
        my_data_collect.setOnClickListener(this);
        my_data_evaluate.setOnClickListener(this);
        ic_my_data_back.setOnClickListener(this);
        ic_my_data_share.setOnClickListener(this);
        ic_my_data_QR_Code.setOnClickListener(this);
        ic_my_data_upload_user_head.setOnClickListener(this);
        ic_my_data_upload_background.setOnClickListener(this);
        my_data_edit_data.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_data_my:
                setTabSelection(0);
                my_data_viewPage.setCurrentItem(0);
                break;
            case R.id.my_data_release:
                setTabSelection(1);
                my_data_viewPage.setCurrentItem(1);
                break;
            case R.id.my_data_collect:
                setTabSelection(2);
                my_data_viewPage.setCurrentItem(2);
                break;
            case R.id.my_data_evaluate:
                setTabSelection(3);
                my_data_viewPage.setCurrentItem(3);
                break;
            case R.id.ic_my_data_back:
                finish();
                break;
            case R.id.ic_my_data_share:
                Toast.makeText(My_Data.this,"分享",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_my_data_QR_Code:
                Toast.makeText(My_Data.this,"二维码",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_my_data_upload_user_head:
                Toast.makeText(My_Data.this,"更换头像",Toast.LENGTH_SHORT).show();
                break;
            case R.id.ic_my_data_upload_background:
                Toast.makeText(My_Data.this,"更换背景",Toast.LENGTH_SHORT).show();
                break;
            case R.id.my_data_edit_data:
                startActivity(new Intent(My_Data.this,Edit_Data.class));
                Toast.makeText(My_Data.this,"编辑",Toast.LENGTH_SHORT).show();
                break;
        }
    }
    private static void clearSelection(){
        tv_my_data_my.setTextColor(Color.parseColor("#757575"));
        tv_my_data_release.setTextColor(Color.parseColor("#757575"));
        tv_my_data_collect.setTextColor(Color.parseColor("#757575"));
        tv_my_data_evaluate.setTextColor(Color.parseColor("#757575"));
        my_data_my_view.setBackgroundColor(Color.parseColor("#ffffff"));
        my_data_release_view.setBackgroundColor(Color.parseColor("#ffffff"));
        my_data_collect_view.setBackgroundColor(Color.parseColor("#ffffff"));
        my_data_evaluate_view.setBackgroundColor(Color.parseColor("#ffffff"));
    }
    private static void setTabSelection(int i) {
        clearSelection();
        switch (i){
            case 0:
                tv_my_data_my.setTextColor(Color.parseColor("#000000"));
                my_data_my_view.setBackgroundColor(Color.parseColor("#FEDB43"));
                break;
            case 1:
                tv_my_data_release.setTextColor(Color.parseColor("#000000"));
                my_data_release_view.setBackgroundColor(Color.parseColor("#FEDB43"));
                break;
            case 2:
                tv_my_data_collect.setTextColor(Color.parseColor("#000000"));
                my_data_collect_view.setBackgroundColor(Color.parseColor("#FEDB43"));
                break;
            case 3:
                tv_my_data_evaluate.setTextColor(Color.parseColor("#000000"));
                my_data_evaluate_view.setBackgroundColor(Color.parseColor("#FEDB43"));
                break;
        }
    }
}
