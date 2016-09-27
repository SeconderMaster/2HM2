package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.SaveListener;

public class Add_My_ShippingAddress_Other extends AppCompatActivity implements View.OnClickListener {

    private TextView ic_tb_add_my_shipping_address_other_back;
    private TextView tv_add_my_shipping_address_other_province_city;
    private TextView add_my_shipping_address_save;
    private EditText et_add_my_shipping_address_other_detailed_address;
    private EditText et_add_my_shipping_address_other_area;
    private EditText et_add_my_shipping_address_other_receiver_name;
    private EditText et_add_my_shipping_address_other_receiver_phone;
    private EditText et_add_my_shipping_address_other_zip_code;
    String getProvince;
    String getCity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__my__shipping_address__other);
        initView();
    }

    private void initView() {
        ic_tb_add_my_shipping_address_other_back= (TextView) findViewById(R.id.ic_tb_add_my_shipping_address_other_back);
        tv_add_my_shipping_address_other_province_city= (TextView) findViewById(R.id.tv_add_my_shipping_address_other_province_city);
        add_my_shipping_address_save= (TextView) findViewById(R.id.add_my_shipping_address_save);
        et_add_my_shipping_address_other_detailed_address= (EditText) findViewById(R.id.et_add_my_shipping_address_other_detailed_address);
        et_add_my_shipping_address_other_area= (EditText) findViewById(R.id.et_add_my_shipping_address_other_area);
        et_add_my_shipping_address_other_receiver_name= (EditText) findViewById(R.id.et_add_my_shipping_address_other_receiver_name);
        et_add_my_shipping_address_other_receiver_phone= (EditText) findViewById(R.id.et_add_my_shipping_address_other_receiver_phone);
        et_add_my_shipping_address_other_zip_code= (EditText) findViewById(R.id.et_add_my_shipping_address_other_zip_code);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_add_my_shipping_address_other_back.setTypeface(iconfont1);

        Intent i=getIntent();
        getProvince=i.getStringExtra("add_my_shipping_address_province");
        getCity=i.getStringExtra("add_my_shipping_address_city");
        tv_add_my_shipping_address_other_province_city.setText(i.getStringExtra("add_my_shipping_address_province")+
                i.getStringExtra("add_my_shipping_address_city"));

        ic_tb_add_my_shipping_address_other_back.setOnClickListener(this);
        add_my_shipping_address_save.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_add_my_shipping_address_other_back:
                Add_My_ShippingAddress_Other.this.finish();
                break;
            case R.id.add_my_shipping_address_save:
                if(et_add_my_shipping_address_other_receiver_name.getText().length()<2||
                        et_add_my_shipping_address_other_receiver_name.getText().length()>10){
                    Toast.makeText(Add_My_ShippingAddress_Other.this,"收货人姓名：2-10个字符限制",Toast.LENGTH_SHORT).show();
                }
                else {
                    if (et_add_my_shipping_address_other_area.getText().length()<2||
                            et_add_my_shipping_address_other_area.getText().length()>10){
                        Toast.makeText(Add_My_ShippingAddress_Other.this,"区/县：2-10个字符限制",Toast.LENGTH_SHORT).show();
                    }
                    else {
                        if (et_add_my_shipping_address_other_detailed_address.getText().length()<5||
                                et_add_my_shipping_address_other_detailed_address.getText().length()>30){
                            Toast.makeText(Add_My_ShippingAddress_Other.this,"街道/详细地址：5-30个字符限制",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            String phoneNumber=et_add_my_shipping_address_other_receiver_phone.getText().toString();
                            String regExp = "^[1]([3][0-9]{1}|59|58|53|88|89)[0-9]{8}$";
                            Pattern p = Pattern.compile(regExp);
                            Matcher m = p.matcher(phoneNumber);
                            if(!m.find()){
                                Toast.makeText(Add_My_ShippingAddress_Other.this,"请输入正确手机号",Toast.LENGTH_SHORT).show();
                            }
                            {
                                if (et_add_my_shipping_address_other_zip_code.getText().length()!=6){
                                    Toast.makeText(Add_My_ShippingAddress_Other.this,"请输入正确邮编",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Shipping_address shipping_address=new Shipping_address();
                                    shipping_address.setUserID(getCurrentUserID.getCurrentUserID());
                                    shipping_address.setReceiver_name(et_add_my_shipping_address_other_receiver_name.getText().toString());
                                    shipping_address.setReceiver_phone(et_add_my_shipping_address_other_receiver_phone.getText().toString());
                                    shipping_address.setZip_code(et_add_my_shipping_address_other_zip_code.getText().toString());
                                    shipping_address.setProvince(getProvince);
                                    shipping_address.setCity(getCity);
                                    shipping_address.setArea(et_add_my_shipping_address_other_area.getText().toString());
                                    shipping_address.setDetailed_address(et_add_my_shipping_address_other_detailed_address.getText().toString());
                                    shipping_address.save(new SaveListener<String>() {
                                        @Override
                                        public void done(String s, BmobException e) {
                                            if(e==null){
                                                Toast.makeText(Add_My_ShippingAddress_Other.this,"保存成功",Toast.LENGTH_SHORT).show();
                                                startActivity(new Intent(Add_My_ShippingAddress_Other.this,My_shippingAddress.class));
                                            }
                                            else {
                                                Toast.makeText(Add_My_ShippingAddress_Other.this,e.getMessage()+","+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                                                Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                                            }
                                        }
                                    });
                                }
                            }
                        }
                    }

                }
        }
    }
}
