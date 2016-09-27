
package com.example.lmj.a2hm2.Release;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.initB;
import com.example.lmj.a2hm2.utils.ImageFloder;
import com.nostra13.universalimageloader.core.listener.PauseOnScrollListener;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Select_Picture extends AppCompatActivity implements View.OnClickListener{

    private TextView ic_tb_select_picture_back;
    private TextView select_picture_select_album;
    private RelativeLayout select_picture_confirm_relative;
    private GridView mGridView;
    private TextView mSelect_pic_count;
    private ArrayList<ImageFloder> mImageFloders = new ArrayList<ImageFloder>();
    private GridView msmall_select;
    private ListView mParent;
    ArrayList<String> ArrayList_imageUrls;//所有图片URL
    ArrayList<String> select_imageUrls=new ArrayList<>();//所有图片URL
    ArrayList<String> mParentDir=new ArrayList<>();//所有图片URL
    private int screenWidth;
    private int screenHeight;
    int totalCount = 0;
    private File mImgDir;
    private int mPicsSize;
    private static final int MENU_SELECT_ALL = 0;//选了几张
    private static final int MENU_UNSELECT_ALL = MENU_SELECT_ALL + 1;
    private TextView mActionText;
    private ParentAdapter mParentAdapter;
    ImageAdapter imageAdapter;
    SelectAdapter mSelectAdapter;
    private Handler mHandler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mSelectAdapter.notifyDataSetChanged();
            mSelect_pic_count.setText(select_imageUrls.size()+"/10");
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select__picture);
        getAndroiodScreenProperty();
        initImageUrls();//获得所有图片的url
        initView();
        imageAdapter=new ImageAdapter(mGridView,this,ArrayList_imageUrls,select_imageUrls,mHandler);
        mParentAdapter=new ParentAdapter(mParent,this,mImageFloders);
        mSelectAdapter=new SelectAdapter(msmall_select,this,select_imageUrls);
        mGridView.setAdapter(imageAdapter);
        mParent.setAdapter(mParentAdapter);
        msmall_select.setAdapter(mSelectAdapter);
        mGridView.setOnScrollListener(new PauseOnScrollListener(
                new initB().imageLoader, true, true));
        msmall_select.setOnScrollListener(new PauseOnScrollListener(
                new initB().imageLoader, true, true));
        mParent.setOnScrollListener(new PauseOnScrollListener(
                new initB().imageLoader, true, true));
        Toast.makeText(this,ArrayList_imageUrls.size()+"",Toast.LENGTH_SHORT).show();
    }

    private void initView() {
        mSelect_pic_count = (TextView) findViewById(R.id.select_pic_count);
        ic_tb_select_picture_back= (TextView) findViewById(R.id.ic_tb_select_picture_back);
        select_picture_select_album= (TextView) findViewById(R.id.select_picture_select_album);
        select_picture_confirm_relative= (RelativeLayout) findViewById(R.id.select_picture_confirm_relative);

        mGridView= (GridView) findViewById(R.id.select_picture_gridView);
        msmall_select= (GridView) findViewById(R.id.small_select);
        mParent= (ListView) findViewById(R.id.select_picture_listView);
//        mGridView.setChoiceMode(GridView.CHOICE_MODE_MULTIPLE_MODAL);
//        mGridView.setMultiChoiceModeListener(this);

        Typeface iconfont = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_select_picture_back.setTypeface(iconfont);

        ic_tb_select_picture_back.setOnClickListener(this);
        select_picture_select_album.setOnClickListener(this);
        select_picture_confirm_relative.setOnClickListener(this);
        mParent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selected(mImageFloders.get(position));
            }
        });
//        msmall_select.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                select_imageUrls.remove(position);
//                mSelectAdapter.notifyDataSetChanged();
//            }
//        });
    }




    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_select_picture_back:
                Select_Picture.this.finish();
                break;
            case R.id.select_picture_select_album:
//                Toast.makeText(Select_Picture.this,"选择相册",Toast.LENGTH_SHORT).show();
                if(mParent.getVisibility()== View.GONE){
                    mGridView.setVisibility(View.GONE);
                    mParent.setVisibility(View.VISIBLE);
                }else {
                    mParent.setVisibility(View.GONE);
                    mGridView.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.select_picture_confirm_relative:
                Toast.makeText(Select_Picture.this,"确定",Toast.LENGTH_SHORT).show();
                Intent it=new Intent(this,Release.class);
                it.putExtra("uri",select_imageUrls);
                setResult(RESULT_OK,it);
                finish();
                break;
        }
    }
    private void initImageUrls() {
        ArrayList_imageUrls=new ArrayList<>();
        String[] proj = {MediaStore.Images.Media.DATA};
        ContentResolver contentResolver=this.getContentResolver();
        Cursor cursor=contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                proj,null,null, null);
        cursor.getCount();
        String firstImage = null;
        while(cursor.moveToNext()) {
            ArrayList_imageUrls.add(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            System.out.println(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));
            // 获取图片的路径
            String path = cursor.getString(cursor
                    .getColumnIndex(MediaStore.Images.Media.DATA));

            Log.e("TAG", path);
            // 拿到第一张图片的路径
            if (firstImage == null)
                firstImage = path;
            // 获取该图片的父路径名
            File parentFile = new File(path).getParentFile();
            if (parentFile == null)
                continue;
            String dirPath = parentFile.getAbsolutePath();
            ImageFloder imageFloder = null;
            // 利用一个HashSet防止多次扫描同一个文件夹（不加这个判断，图片多起来还是相当恐怖的~~）
            if (mParentDir.contains(dirPath))
            {
                continue;
            } else
            {
                mParentDir.add(dirPath);
                // 初始化imageFloder
                imageFloder = new ImageFloder();
                imageFloder.setDir(dirPath);
                imageFloder.setFirstImagePath(path);
            }
            int picSize = parentFile.list(new FilenameFilter()
            {
                @Override
                public boolean accept(File dir, String filename)
                {
                    if (filename.endsWith(".jpg")
                            || filename.endsWith(".png")
                            || filename.endsWith(".jpeg"))
                        return true;
                    return false;
                }
            }).length;
            totalCount += picSize;

            imageFloder.setCount(picSize);
            Log.i("wfh", "路径：" + imageFloder.getDir() + "图片位置：" + imageFloder.getFirstImagePath());
            mImageFloders.add(imageFloder);

            if (picSize > mPicsSize)
            {
                mPicsSize = picSize;
                mImgDir = parentFile;
            }
        }

        Collections.reverse(ArrayList_imageUrls);
        cursor.close();
        mParentDir = null;

    }//获取所有图片url
    public void getAndroiodScreenProperty(){
        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics dm = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;// 屏幕宽度（像素）
        int height= dm.heightPixels; // 屏幕高度（像素）
        float density = dm.density;//屏幕密度（0.75 / 1.0 / 1.5）
        int densityDpi = dm.densityDpi;//屏幕密度dpi（120 / 160 / 240）
        //屏幕宽度算法:屏幕宽度（像素）/屏幕密度
        screenWidth = (int) (width/density);//屏幕宽度(dp)
        screenHeight = (int)(height/density);//屏幕高度(dp)
    }
    public void selected(ImageFloder floder)
    {
        ArrayList_imageUrls.clear();
        List<String> mParent_select=new ArrayList<>();
        mImgDir = new File(floder.getDir());
        mParent_select = Arrays.asList(mImgDir.list(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String filename) {
                if (filename.endsWith(".jpg") || filename.endsWith(".png")
                        || filename.endsWith(".jpeg"))
                    return true;
                return false;
            }
        }));
        /**
         * 可以看到文件夹的路径和图片的路径分开保存，极大的减少了内存的消耗；
         */
        for (String path:mParent_select
             ) {
            ArrayList_imageUrls.add(floder.getDir()+"/"+path);
        }
        if(mGridView.getVisibility()== View.GONE){
            mGridView.setVisibility(View.VISIBLE);
            mParent.setVisibility(View.GONE);
        }else{
            mParent.setVisibility(View.VISIBLE);
            mGridView.setVisibility(View.GONE);
        }
        imageAdapter.notifyDataSetChanged();
        mParent_select=null;
    }
}
