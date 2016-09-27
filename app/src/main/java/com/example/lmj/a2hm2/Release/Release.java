package com.example.lmj.a2hm2.Release;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.MainActivity;
import com.example.lmj.a2hm2.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;
import cn.bmob.v3.listener.UploadBatchListener;

public class Release extends AppCompatActivity implements View.OnClickListener {
    private TextView ic_tb_release_cancel;
    private TextView ic_tb_release_more;
    private TextView ic_release_add_picture;
    private Spinner ic_release_classify;
    private LinearLayout add_picture_linear;
    private RelativeLayout release_classify;
    private ArrayList<String> pic_dir=null;
    private EditText mGoods_title;
    private EditText mGoods_des;
    private EditText mGoods_pri;
    private Button mGoods_commit;

    private static final int SELECT_PICTURE=1001;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_release);
        initView();

    }

    private void initView() {
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
        }
    }

    private void IssueData(){
        final String[]  select_pic=new String[pic_dir.size()];
        int i=0;
        final ReleaseBean mReleaseBean=new ReleaseBean();
        mReleaseBean.setGoodS_title(mGoods_title.getText().toString());
        mReleaseBean.setGoods_des(mGoods_des.getText().toString());
        mReleaseBean.setGoods_pri(mGoods_pri.getText().toString());
        mReleaseBean.setGoods_classify(ic_release_classify.toString());
        if(pic_dir!=null){
            for (String pic_path:pic_dir
                 ) {
                select_pic[i++]=pic_path;
            }
        }
        if (select_pic!=null){
            BmobFile.uploadBatch(select_pic, new UploadBatchListener() {
                @Override
                public void onSuccess(List<BmobFile> list, List<String> list1) {
                    if (list!=null) {
                        if (!list.isEmpty()&&list.size()==pic_dir.size()) {
                            mReleaseBean.setSelect_pics(list);
                            mReleaseBean.save(new SaveListener<String>() {
                                @Override
                                public void done(String s, BmobException e) {
                                    if (e == null) {
                                        Toast.makeText(getApplicationContext(), "下载成功", Toast.LENGTH_SHORT).show();
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
