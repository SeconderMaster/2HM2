package com.example.lmj.a2hm2.Adapter;

import android.content.Context;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.initB;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.datatype.BmobFile;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.DownloadFileListener;

/**
 * Created by lmj on 2016/9/21.
 */
public class Community_pic_gridviewAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<BmobFile> mBmobFiles=new ArrayList<>();
    private GridView mGV;

    public Community_pic_gridviewAdapter(GridView GV, Context context, List<BmobFile> mBmobFiles){
        this.mBmobFiles.clear();
       this.mGV=GV;
        this.context=context;
        this.mBmobFiles.addAll(mBmobFiles);
    }
    @Override
    public int getCount() {
        return mBmobFiles.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        final ViewHolder mviewHolde;
        if (convertView==null){
            mviewHolde=new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.select_picture_recycel_item,null);
            mviewHolde.img_view= (ImageView) convertView.findViewById(R.id.img_view);
            convertView.setTag(mviewHolde);
        }else {
            mviewHolde= (ViewHolder) convertView.getTag();
        }
        BmobFile file_pic=mBmobFiles.get(position);
        new Download_pic(mviewHolde).execute(file_pic);
        AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                mGV.getHeight());
        convertView.setLayoutParams(param);
            return convertView;

    }
    class ViewHolder{
        ImageView img_view;
    }
    class Download_pic extends AsyncTask<BmobFile,Void,Void>{
     private ViewHolder mHolder;

        public Download_pic(ViewHolder holder) {
            mHolder = holder;
        }

        @Override
        protected Void doInBackground(BmobFile... params) {
            params[0].download(new DownloadFileListener() {
                @Override
                public void done(String s, BmobException e) {
                    if (e==null){
                        new initB().imageLoader.displayImage(ImageDownloader.Scheme.FILE.wrap(s),
                                mHolder.img_view, new initB().select_picture_options);
                    }
                }

                @Override
                public void onProgress(Integer integer, long l) {

                }
            });
            return null;
        }
    }
}