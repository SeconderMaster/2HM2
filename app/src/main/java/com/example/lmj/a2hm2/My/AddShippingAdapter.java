package com.example.lmj.a2hm2.My;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;

import java.util.ArrayList;

/**
 * Created by lmj on 2016/9/26.
 */
public class AddShippingAdapter extends RecyclerView.Adapter<AddShippingAdapter.MyViewHolder> {
    private OnItemClickListener mOnItemClickListener;
    private Context mContext;
    private ArrayList<String> arrayList;
    public AddShippingAdapter (Context context,ArrayList<String> arrayList){
        this. mContext=context;
        this.arrayList=arrayList;
    }
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater. from(mContext).inflate(R.layout.add_my_shippingaddress_item,parent, false);
        MyViewHolder holder= new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        if(mOnItemClickListener!=null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.itemView, pos);
                }
            });
        }
        holder.add_my_shipping_address_item_tv.setText(arrayList.get(position));
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView add_my_shipping_address_item_tv;
        public MyViewHolder(View view) {
            super(view);
            add_my_shipping_address_item_tv= (TextView) view.findViewById(R.id.add_my_shipping_address_item_tv);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(View view, int position);
    }
}