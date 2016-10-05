package com.example.lmj.a2hm2.Myhome;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lmj.a2hm2.Adapter.Community_pic_gridviewAdapter;
import com.example.lmj.a2hm2.MainActivity;
import com.example.lmj.a2hm2.MyView.MyGridView;
import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.Release.ReleaseBean;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.QueryListener;

public class Goods_Item extends AppCompatActivity {
    private TextView ic_goods_item_back;
    private TextView ic_goods_item_share;
    private TextView ic_goods_item_more;
    private ImageView goods_item_userHead;
    private TextView goods_item_userName;
    private TextView goods_item_time;
    private TextView goods_item_value;
    private TextView goods_item_content;
    private TextView goods_item_thumb_up_num;
    private RecyclerView goods_item_comments_recyclerView;
    private RecyclerView goods_item_guess_you_like_recyclerView;
    private LinearLayout goods_item_comment;
    private LinearLayout goods_item_thumb_up;
    private TextView goods_item_want;
    private MyGridView goods_item_goods_download_pic;
    String good_objectId;
    ReleaseBean goodBean=new ReleaseBean();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods__item);
        initView();
    }

    private void initView() {
        ic_goods_item_back= (TextView) findViewById(R.id.ic_goods_item_back);
        ic_goods_item_share= (TextView) findViewById(R.id.ic_goods_item_share);
        ic_goods_item_more= (TextView) findViewById(R.id.ic_goods_item_more);
        goods_item_userName= (TextView) findViewById(R.id.goods_item_userName);
        goods_item_time= (TextView) findViewById(R.id.goods_item_time);
        goods_item_value= (TextView) findViewById(R.id.goods_item_value);
        goods_item_content= (TextView) findViewById(R.id.goods_item_content);
        goods_item_thumb_up_num= (TextView) findViewById(R.id.goods_item_thumb_up_num);
        goods_item_want= (TextView) findViewById(R.id.goods_item_want);
        goods_item_userHead= (ImageView) findViewById(R.id.goods_item_userHead);
//        goods_item_comments_recyclerView= (RecyclerView) findViewById(R.id.goods_item_comments_recyclerView);
        goods_item_guess_you_like_recyclerView= (RecyclerView) findViewById(R.id.goods_item_guess_you_like_recyclerView);
        goods_item_comment= (LinearLayout) findViewById(R.id.goods_item_comment);
        goods_item_thumb_up= (LinearLayout) findViewById(R.id.goods_item_thumb_up);
        goods_item_goods_download_pic= (MyGridView)findViewById(R.id.goods_item_goods_download_pic);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_goods_item_back.setTypeface(iconfont1);
        Typeface iconfont2 = Typeface.createFromAsset(getAssets(), "ic_my_data/iconfont.ttf");
        ic_goods_item_share.setTypeface(iconfont2);
        Typeface iconfont3 = Typeface.createFromAsset(getAssets(), "ic_release/iconfont.ttf");
        ic_goods_item_more.setTypeface(iconfont3);

        Intent i=getIntent();
        good_objectId=i.getStringExtra("good_objectId");

        BmobQuery<ReleaseBean>query=new BmobQuery<ReleaseBean>();
        query.getObject(good_objectId, new QueryListener<ReleaseBean>() {
            @Override
            public void done(ReleaseBean releaseBean, BmobException e) {
                if(e==null){
                    goods_item_userName.setText(releaseBean.getGoodS_title());
                    goods_item_time.setText(releaseBean.getCreatedAt().substring(11, 19));
                    goods_item_value.setText("￥"+releaseBean.getGoods_pri());
                    goods_item_content.setText(releaseBean.getGoods_des());
                    goods_item_thumb_up_num.setText("点赞"+releaseBean.getThumb_num());
                    goods_item_goods_download_pic.setAdapter(new Community_pic_gridviewAdapter
                            (goods_item_goods_download_pic, Goods_Item.this,releaseBean.getSelect_pics()));
                    System.out.println(releaseBean.getSelect_pics());
                }
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Goods_Item.this, MainActivity.class));
    }
}
