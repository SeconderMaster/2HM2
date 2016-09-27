package com.example.lmj.a2hm2.My;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class My_shippingAddress extends AppCompatActivity implements View.OnClickListener {

    private TextView ic_tb_my_shipping_address_back;
    private RecyclerView my_shipping_address_recyclerView;
    private TextView add_my_shipping_address;
    private ArrayList<ShippingAddress> shippingAddressArrayList=new ArrayList<ShippingAddress>();
    private ShippingAdapter shippingAdapter;
    static Typeface iconfont2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_shipping_address);
        initView();
        my_shipping_address_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        my_shipping_address_recyclerView.setAdapter(shippingAdapter=new ShippingAdapter(this,shippingAddressArrayList));
        shippingAdapter.setOnItemClickListener(new ShippingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent i=new Intent(My_shippingAddress.this,My_Shipping_Address_Edit.class);
                i.putExtra("select_my_shipping_address_ObjectId",shippingAddressArrayList.get(position).getObjectId());
                startActivity(i);
            }
        });
    }

    private void initView() {
        ic_tb_my_shipping_address_back= (TextView) findViewById(R.id.ic_tb_my_shipping_address_back);
        add_my_shipping_address= (TextView) findViewById(R.id.add_my_shipping_address);
        my_shipping_address_recyclerView= (RecyclerView) findViewById(R.id.my_shipping_address_recyclerView);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_my_shipping_address_back.setTypeface(iconfont1);
        iconfont2 = Typeface.createFromAsset(getAssets(), "ic_my_shipping_address/iconfont.ttf");
//        ic_tb_SMS_login_back.setTypeface(iconfont2);
        my_shipping_address_recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        my_shipping_address_recyclerView.setAdapter(shippingAdapter=new ShippingAdapter(getApplicationContext(),shippingAddressArrayList));
        BmobQuery<Shipping_address> query=new BmobQuery<Shipping_address>();
        query.addWhereEqualTo("userID",getCurrentUserID.getCurrentUserID());
        query.findObjects(new FindListener<Shipping_address>() {
            @Override
            public void done(List<Shipping_address> list, BmobException e) {
                if(e==null){
                    for(Shipping_address shipping_address:list) {
                        ShippingAddress shippingAddress=new ShippingAddress(shipping_address.getReceiver_name(),shipping_address.getReceiver_phone(),
                                shipping_address.getZip_code(),shipping_address.getProvince(),shipping_address.getCity(),shipping_address.getArea(),
                                shipping_address.getDetailed_address(),shipping_address.getObjectId(),shipping_address.getIsDefault());
                        shippingAddressArrayList.add(shippingAddress);
                    }
                    shippingAdapter.notifyDataSetChanged();
                }
                else {
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                }
            }
        });

        add_my_shipping_address.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.add_my_shipping_address:
                startActivity(new Intent(My_shippingAddress.this,Add_My_ShippingAddress_Province.class));
                break;
        }
    }

    public static class ShippingAdapter extends RecyclerView.Adapter<ShippingAdapter.MyViewHolder> {
        private OnItemClickListener mOnItemClickListener;
        private Context mContext;
        private ArrayList<ShippingAddress> shippingAddressArrayList;
        public ShippingAdapter (Context context,ArrayList<ShippingAddress> shippingAddressArrayList){
            this. mContext=context;
            this.shippingAddressArrayList=shippingAddressArrayList;
        }
        public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
        {
            this.mOnItemClickListener = mOnItemClickListener;
        }
        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater. from(mContext).inflate(R.layout.my_shippingaddress_item,parent, false);
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

            holder.receiver_name.setText(shippingAddressArrayList.get(position).getReceiver_name());
            holder.receiver_phone.setText(shippingAddressArrayList.get(position).getReceiver_phone());
            holder.province.setText(shippingAddressArrayList.get(position).getProvince());
            holder.city.setText(shippingAddressArrayList.get(position).getCity());
            holder.area.setText(shippingAddressArrayList.get(position).getArea());
            holder.detailed_address.setText(shippingAddressArrayList.get(position).getDetailed_address());
            if(shippingAddressArrayList.get(position).getIsDefault()==true){
                holder.ic_tb_my_shipping_address_select.setVisibility(View.VISIBLE);
                holder.ic_tb_my_shipping_address_select.setTypeface(iconfont2);
                holder.rl.setBackgroundColor(Color.parseColor("#5e6b85"));
                holder.receiver_name.setTextColor(Color.parseColor("#ffffff"));
                holder.receiver_phone.setTextColor(Color.parseColor("#ffffff"));
                holder.province.setTextColor(Color.parseColor("#ffffff"));
                holder.city.setTextColor(Color.parseColor("#ffffff"));
                holder.area.setTextColor(Color.parseColor("#ffffff"));
                holder.detailed_address.setTextColor(Color.parseColor("#ffffff"));
                holder.receiver.setTextColor(Color.parseColor("#ffffff"));
                holder.tv_shipping_address.setTextColor(Color.parseColor("#ffffff"));
            }
        }

        @Override
        public int getItemCount() {
            return shippingAddressArrayList.size();
        }

        public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView receiver_name,receiver_phone,province,city,area,detailed_address,receiver,tv_shipping_address,ic_tb_my_shipping_address_select;
            RelativeLayout rl;
            public MyViewHolder(View view) {
                super(view);
                receiver_name= (TextView) view.findViewById(R.id.receiver_name);
                receiver_phone= (TextView) view.findViewById(R.id.receiver_phone);
                province= (TextView) view.findViewById(R.id.province);
                city= (TextView) view.findViewById(R.id.city);
                area= (TextView) view.findViewById(R.id.area);
                detailed_address= (TextView) view.findViewById(R.id.detailed_address);
                receiver= (TextView) view.findViewById(R.id.receiver);
                tv_shipping_address= (TextView) view.findViewById(R.id.tv_shipping_address);
                ic_tb_my_shipping_address_select= (TextView) view.findViewById(R.id.ic_tb_my_shipping_address_select);
                rl= (RelativeLayout) view.findViewById(R.id.rl);
            }
        }
        public interface OnItemClickListener{
            void onItemClick(View view, int position);
        }
    }
}
