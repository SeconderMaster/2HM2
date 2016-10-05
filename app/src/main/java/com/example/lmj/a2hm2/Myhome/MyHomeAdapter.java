package com.example.lmj.a2hm2.Myhome;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.lmj.a2hm2.Adapter.Community_pic_gridviewAdapter;
import com.example.lmj.a2hm2.MyView.MyGridView;
import com.example.lmj.a2hm2.R;
import com.example.lmj.a2hm2.Release.ReleaseBean;

import java.util.ArrayList;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by lmj on 2016/9/28.
 */
public class MyHomeAdapter extends RecyclerView.Adapter<MyHomeAdapter.MyViewHolder>{
    private ArrayList<ReleaseBean> releaseBeanArrayList;
    private Context mContext;
    public MyHomeAdapter(Context context, ArrayList<ReleaseBean> releaseBeanArrayList){
        this. mContext=context;
        this. releaseBeanArrayList=releaseBeanArrayList;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater. from(mContext).inflate(R.layout.my_home_fresh_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.my_home_item_userName.setText(releaseBeanArrayList.get(position).getGoodS_title());
        holder.my_home_item_time.setText(releaseBeanArrayList.get(position).getCreatedAt().substring(11, 19));
        holder.my_home_item_value.setText("￥"+releaseBeanArrayList.get(position).getGoods_pri());
        holder.my_home_item_content.setText(releaseBeanArrayList.get(position).getGoods_des());
//        holder.my_home_item_position.setText(Community_articleArrayList.get(position).getProductPlace());
        holder.my_home_item__comments_num.setText(releaseBeanArrayList.get(position).getComment_num());
        holder.my_home_item_goods_download_pic.setAdapter(new Community_pic_gridviewAdapter(holder.my_home_item_goods_download_pic,
                mContext,releaseBeanArrayList.get(position).getSelect_pics()));
        holder.my_home_item_thumb_up_num.setText(releaseBeanArrayList.get(position).getThumb_num());
        holder.my_home_item_thumb_up_num.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int x = Integer.parseInt(releaseBeanArrayList.get(position).getThumb_num()) + 1;
                holder.my_home_item_thumb_up_num.setText(x + "");
                releaseBeanArrayList.get(position).setThumb_num(x + "");
                releaseBeanArrayList.get(position).update(releaseBeanArrayList.get(position).getObjectId(), new UpdateListener() {
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
//        holder.community_discovery_item__comments_num.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it=new Intent(mContext,Community_comment_ac.class);
//                it.putExtra("id",Community_articleArrayList.get(position).getObjectId());
//                mContext.startActivity(it);
//            }
//        });
        holder.my_home_fresh_item_detailed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                System.out.println("123456");
                Intent i=new Intent(mContext,Goods_Item.class);
                i.putExtra("good_objectId",releaseBeanArrayList.get(position).getObjectId());
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return releaseBeanArrayList.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView my_home_item_userName,my_home_item_time,my_home_item_value,
                my_home_item_content,my_home_item_position,
                my_home_item__comments_num,my_home_item_thumb_up_num;
        ImageView my_home_item_userHead;
        MyGridView my_home_item_goods_download_pic;
        LinearLayout my_home_fresh_item_detailed;
        public MyViewHolder(View view) {
            super(view);
            my_home_item_goods_download_pic= (MyGridView) view.findViewById(R.id.my_home_item_goods_download_pic);
            my_home_item_userHead= (ImageView) view.findViewById(R.id.my_home_item_userHead);
            my_home_item_userName= (TextView) view.findViewById(R.id.my_home_item_userName);
            my_home_item_time= (TextView) view.findViewById(R.id.my_home_item_time);
            my_home_item_value= (TextView) view.findViewById(R.id.my_home_item_value);
            my_home_item_content= (TextView) view.findViewById(R.id.my_home_item_content);
            my_home_item_position= (TextView) view.findViewById(R.id.my_home_item_position);
            my_home_item__comments_num= (TextView) view.findViewById(R.id.my_home_item__comments_num);
            my_home_item_thumb_up_num= (TextView) view.findViewById(R.id.my_home_item_thumb_up_num);
            my_home_fresh_item_detailed= (LinearLayout) view.findViewById(R.id.my_home_fresh_item_detailed);
        }
    }
}
