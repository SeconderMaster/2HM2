package com.example.lmj.a2hm2.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lmj.a2hm2.Community.Community_comment_ac;
import com.example.lmj.a2hm2.MyView.MyGridView;
import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.Release.ReleaseBean;

import java.util.ArrayList;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by lmj on 2016/9/28.
 */
public class CommunityArticleAdapter extends RecyclerView.Adapter<CommunityArticleAdapter.MyViewHolder> {
    private ArrayList<ReleaseBean> Community_articleArrayList;
    private Context mContext;
    public CommunityArticleAdapter (Context context, ArrayList<ReleaseBean> Community_articleArrayList){
        this. mContext=context;
        this. Community_articleArrayList=Community_articleArrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater. from(mContext).inflate(R.layout.test,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.community_discovery_item_userName.setText(Community_articleArrayList.get(position).getArticle_name());
        holder.community_discovery_item_time.setText(Community_articleArrayList.get(position).getCreatedAt().substring(11, 19));
        holder.community_discovery_item_content.setText(Community_articleArrayList.get(position).getGoodS_title());
//        holder.community_discovery_item_position.setText(Community_articleArrayList.get(position).getProductPlace());
        holder.community_discovery_item__comments_num.setText(Community_articleArrayList.get(position).getComment_num());
        holder.community_discovery_item_thumb_up_num.setText(Community_articleArrayList.get(position).getThumb_num());
        holder.community_discovery_item_download_pic.setAdapter(new Community_pic_gridviewAdapter(holder.community_discovery_item_download_pic,
                mContext,Community_articleArrayList.get(position).getSelect_pics()));
        holder.community_discovery_item_thumb_up_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(Community_articleArrayList.get(position).getThumb_num()) + 1;
                holder.community_discovery_item_thumb_up_num.setText(x + "");
                Community_articleArrayList.get(position).setThumb_num(x + "");
                Community_articleArrayList.get(position).update(Community_articleArrayList.get(position).getObjectId(), new UpdateListener() {
                    @Override
                    public void done(BmobException e) {
                        if (e == null) {
                            Log.i("wfh", "更新成功");
                        } else {
                            Log.i("wfh", "更新失败：" + e.getMessage() + "," + e.getErrorCode());
                        }
                    }
                });
            }
        });
        holder.community_discovery_item__comments_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it=new Intent(mContext,Community_comment_ac.class);
                it.putExtra("id",Community_articleArrayList.get(position).getObjectId());
                mContext.startActivity(it);
            }
        });
    }

    @Override
    public int getItemCount() {
        return Community_articleArrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView community_discovery_item_userName,community_discovery_item_time,
                community_discovery_item_content,community_discovery_item_position,
                community_discovery_item__comments_num,community_discovery_item_thumb_up_num;
        ImageView community_discovery_item_userHead;
        MyGridView community_discovery_item_download_pic;
        public MyViewHolder(View view) {
            super(view);
            community_discovery_item_download_pic= (MyGridView) view.findViewById(R.id.community_goods_download_pic);
            community_discovery_item_userHead= (ImageView) view.findViewById(R.id.community_discovery_item_userHead);
            community_discovery_item_userName= (TextView) view.findViewById(R.id.community_discovery_item_userName);
            community_discovery_item_time= (TextView) view.findViewById(R.id.community_discovery_item_time);
            community_discovery_item_content= (TextView) view.findViewById(R.id.community_discovery_item_content);
            community_discovery_item_position= (TextView) view.findViewById(R.id.community_discovery_item_position);
            community_discovery_item__comments_num= (TextView) view.findViewById(R.id.community_discovery_item__comments_num);
            community_discovery_item_thumb_up_num= (TextView) view.findViewById(R.id.community_discovery_item_thumb_up_num);
        }
    }
}
