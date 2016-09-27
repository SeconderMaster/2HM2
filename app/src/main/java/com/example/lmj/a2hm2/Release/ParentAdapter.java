package com.example.lmj.a2hm2.Release;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.initB;
import com.example.lmj.a2hm2.utils.ImageFloder;
import com.nostra13.universalimageloader.core.download.ImageDownloader;

import java.util.ArrayList;

/**
 * Created by lmj on 2016/9/21.
 */
public class ParentAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<ImageFloder> mparents;
    private ListView mGV;
    public ParentAdapter(ListView GV, Context context, ArrayList<ImageFloder> mparents){
       this.mGV=GV;
        this.context=context;
        this.mparents = mparents;
    }
    @Override
    public int getCount() {
        return mparents.size();
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
            convertView= LayoutInflater.from(context).inflate(R.layout.mparent_listview_item,null);
            mviewHolde.dirname= (TextView) convertView.findViewById(R.id.parent_name);
            mviewHolde.img_first= (ImageView) convertView.findViewById(R.id.first_pic);
            convertView.setTag(mviewHolde);
        }else {
            mviewHolde= (ViewHolder) convertView.getTag();
        }
        ImageFloder mparent=mparents.get(position);
        new initB().imageLoader.displayImage(ImageDownloader.Scheme.FILE.wrap(mparent.getFirstImagePath()),
                mviewHolde.img_first, new initB().select_picture_options);
        AbsListView.LayoutParams param = new AbsListView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                mGV.getHeight()/4);
        convertView.setLayoutParams(param);
        mviewHolde.dirname.setText(mparent.getName()+"("+mparent.getCount()+")");
            return convertView;

    }
    class ViewHolder{
        TextView dirname;
        ImageView img_first;
    }
}