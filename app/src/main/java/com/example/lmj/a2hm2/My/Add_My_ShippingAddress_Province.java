package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.lmj.a2hm2.R;

import java.util.ArrayList;

public class Add_My_ShippingAddress_Province extends AppCompatActivity {
    public static Add_My_ShippingAddress_Province instance=null;
    private TextView ic_tb_add_my_shipping_address_province_back;
    private RecyclerView add_my_shipping_address_province_recyclerView;
    static ArrayList<String> provinceArrayList;
    private AddShippingAdapter addShippingAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__my__shipping_address__province);
        instance=this;
        initView();
        add_my_shipping_address_province_recyclerView.setLayoutManager(new LinearLayoutManager(this));
        add_my_shipping_address_province_recyclerView.setAdapter(addShippingAdapter =new AddShippingAdapter(this,provinceArrayList));
        addShippingAdapter.setOnItemClickListener(new AddShippingAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                System.out.println(provinceArrayList.get(position));
                Intent i=new Intent(Add_My_ShippingAddress_Province.this,Add_My_ShippingAddress_City.class);
                i.putExtra("add_my_shipping_address_province",provinceArrayList.get(position));
                startActivity(i);
            }
        });
    }

    private void initView() {
        ic_tb_add_my_shipping_address_province_back= (TextView) findViewById(R.id.ic_tb_add_my_shipping_address_province_back);
        add_my_shipping_address_province_recyclerView= (RecyclerView) findViewById(R.id.add_my_shipping_address_province_recyclerView);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_add_my_shipping_address_province_back.setTypeface(iconfont1);

        provinceArrayList=new ArrayList<String>();
        initProvinceArrayList();
    }



    private void initProvinceArrayList() {
        provinceArrayList.add("北京");provinceArrayList.add("天津"); provinceArrayList.add("上海");
        provinceArrayList.add("重庆"); provinceArrayList.add("河北省"); provinceArrayList.add("河南省");
        provinceArrayList.add("云南省"); provinceArrayList.add("辽宁省"); provinceArrayList.add("黑龙江省");
        provinceArrayList.add("湖南省"); provinceArrayList.add("安徽省"); provinceArrayList.add("山东省");
        provinceArrayList.add("新疆维吾尔"); provinceArrayList.add("江苏省"); provinceArrayList.add("浙江省");
        provinceArrayList.add("江西省"); provinceArrayList.add("湖北省"); provinceArrayList.add("广西壮族");
        provinceArrayList.add("甘肃省"); provinceArrayList.add("山西省"); provinceArrayList.add("内蒙古");
        provinceArrayList.add("陕西省"); provinceArrayList.add("吉林省"); provinceArrayList.add("福建省");
        provinceArrayList.add("贵州省"); provinceArrayList.add("广东省"); provinceArrayList.add("青海省");
        provinceArrayList.add("西藏"); provinceArrayList.add("四川省"); provinceArrayList.add("宁夏回族");
        provinceArrayList.add("海南省"); provinceArrayList.add("台湾省"); provinceArrayList.add("香港特别行政区");
        provinceArrayList.add("澳门特别行政区");
    }
}
