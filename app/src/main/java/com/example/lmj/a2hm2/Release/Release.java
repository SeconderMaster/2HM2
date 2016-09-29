package com.example.lmj.a2hm2.Release;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.lmj.a2hm2.MainActivity;
import com.example.lmj.a2hm2.My.My_User;
import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.SpacesItemDecoration;
import com.example.lmj.a2hm2.initB;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

public class Release extends AppCompatActivity implements View.OnClickListener {
    private TextView ic_tb_release_cancel;
    private TextView ic_tb_release_more;
    private TextView ic_release_add_picture;
    private HorizontalScrollView mScrollView;
    private RecyclerView mRecyclerView;
    private Spinner ic_release_classify;
    private LinearLayout add_picture_linear;
    private RelativeLayout release_classify;
    private ArrayList<String> pic_dir=null;
    private EditText mGoods_title;
    private EditText mGoods_des;
    private EditText mGoods_pri;
    private Button mGoods_commit;
    private My_User userinfo;
    private boolean IsCommunity=false;
    private static final int SELECT_PICTURE=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        Intent it=getIntent();
        if (it!=null)
        IsCommunity=it.getExtras().getBoolean("iscommunity");
        initView();

    }
    @Override
    protected void onResume() {
        super.onResume();
        userinfo= BmobUser.getCurrentUser(My_User.class);
        if(userinfo !=null){
            mGoods_commit.setEnabled(true);
        }else {
            mGoods_commit.setEnabled(false);
        }
    }
    private void initView() {
        mScrollView= (HorizontalScrollView) findViewById(R.id.select_pic_scroll);
        mRecyclerView= (RecyclerView) findViewById(R.id.select_pic_recycle);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this,10));
        SpacesItemDecoration spacesItemDecoration=new SpacesItemDecoration(10);
        mRecyclerView.addItemDecoration(spacesItemDecoration);
        ic_tb_release_cancel= (TextView) findViewById(R.id.ic_tb_release_cancel);
        ic_tb_release_more= (TextView) findViewById(R.id.ic_tb_release_more);
        ic_release_add_picture= (TextView) findViewById(R.id.ic_release_add_picture);
        ic_release_classify= (Spinner) findViewById(R.id.ic_release_classify);
        add_picture_linear= (LinearLayout) findViewById(R.id.add_picture_linear);
        release_classify= (RelativeLayout) findViewById(R.id.release_classify);
        mGoods_title= (EditText) findViewById(R.id.goods_title);
        mGoods_des= (EditText) findViewById(R.id.goods_des);
        mGoods_pri= (EditText) findViewById(R.id.goods_pri);
        mGoods_commit= (Button) findViewById(R.id.goods_commit);
        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_release/iconfont.ttf");
        ic_tb_release_cancel.setTypeface(iconfont);ic_tb_release_more.setTypeface(iconfont);
        ic_release_add_picture.setTypeface(iconfont);
        Typeface into = Typeface.createFromAsset(getAssets(), "ic_my/iconfont.ttf");
//        ic_release_classify.setTypeface(into);
        mGoods_commit.setOnClickListener(this);
        ic_tb_release_cancel.setOnClickListener(this);
        ic_tb_release_more.setOnClickListener(this);
        add_picture_linear.setOnClickListener(this);
//        ic_release_classify.setOnClickListener(this);
        release_classify.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.goods_commit:
                IssueData();

                break;
            case R.id.ic_tb_release_cancel:
                startActivity(new Intent(Release.this, MainActivity.class));
                Release.this.finish();
                break;
            case R.id.ic_tb_release_more:
                break;
            case R.id.add_picture_linear:
                startActivityForResult(new Intent(Release.this, Select_Picture.class), SELECT_PICTURE);
                break;
            case R.id.release_classify:
                startActivity(new Intent(Release.this,Release_Classify_List.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SELECT_PICTURE&&resultCode==RESULT_OK){
          pic_dir=data.getExtras().getStringArrayList("uri");
            Log.i("wfh",pic_dir.size()+"");
            if (!pic_dir.isEmpty()){
                mScrollView.setVisibility(View.VISIBLE);
                mRecyclerView.setAdapter(new Select_pic_adapter(getApplicationContext(),pic_dir));
            }
        }
    }

    class Select_pic_adapter extends RecyclerView.Adapter<Select_pic_adapter.MyViewHoldaer>{

        private Context mContext;
        private ArrayList<String> select_uri;
        private LayoutInflater mInflater;
        public Select_pic_adapter(Context context, ArrayList<String> select_uri) {
            mContext = context;
            this.select_uri = select_uri;
            mInflater=LayoutInflater.from(context);
        }

        @Override
        public MyViewHoldaer onCreateViewHolder(ViewGroup parent, int viewType) {

            MyViewHoldaer holdaer=new MyViewHoldaer(mInflater.inflate(R.layout.select_picture_recycel_item,parent,false));
            return holdaer;
        }

        @Override
        public void onBindViewHolder(MyViewHoldaer holder, int position) {
            new initB().imageLoader.displayImage(ImageDownloader.Scheme.FILE.wrap(select_uri.get(position)),
                    holder.mpic, new initB().select_picture_options);
        }

        @Override
        public int getItemCount() {
            return select_uri.size();
        }
        class MyViewHoldaer extends RecyclerView.ViewHolder {
             ImageView mpic;
            public MyViewHoldaer(View itemView) {
                super(itemView);
                mpic= (ImageView) itemView.findViewById(R.id.img_view);
            }
        }
    }
    private void IssueData(){
        final String[]  select_pic=new String[pic_dir.size()];
        int i=0;
        final ReleaseBean mReleaseBean=new ReleaseBean();
        mReleaseBean.setGoodS_title(mGoods_title.getText().toString());
        mReleaseBean.setGoods_des(mGoods_des.getText().toString());
        mReleaseBean.setGoods_pri(mGoods_pri.getText().toString());
        mReleaseBean.setGoods_classify((String) (ic_release_classify.getSelectedItem()));
        mReleaseBean.setArticle(userinfo);
        mReleaseBean.setArticle_name(userinfo.getUsername());
        mReleaseBean.setThumb_num("0");
        mReleaseBean.setComment_num("0");
        if (IsCommunity){
            mReleaseBean.setIsCommunity("true");
        }else {
            mReleaseBean.setIsCommunity("false");
        }
        if(pic_dir!=null){
            for (String pic_path:pic_dir
                 ) {
                select_pic[i++]=pic_path;
            }
        }
        if (select_pic.length==pic_dir.size()){
            BmobFile.uploadBatch(select_pic, new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> list, List<String> list1) {
                    if (list!=null) {
                        if (!list.isEmpty()&&list.size()==pic_dir.size()) {
                            mReleaseBean.addAllUnique("select_pics",list);
                            mReleaseBean.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        setResult(RESULT_OK);
                                       finish();
                                    } else {
                                        Log.i("wfh", e.getErrorCode() + "," + e.getMessage());
                                    }
                                }
                            });
                        }
                    }
                }

                @Override
                public void onProgress(int i, int i1, int i2, int i3) {

                }

                @Override
                public void onError(int i, String s) {

                }
            });
        }
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(Release.this,MainActivity.class));
    }
}
