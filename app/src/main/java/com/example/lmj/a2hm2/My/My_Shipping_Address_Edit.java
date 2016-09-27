package com.example.lmj.a2hm2.My;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.QueryListener;
import cn.bmob.v3.listener.UpdateListener;

public class My_Shipping_Address_Edit extends AppCompatActivity implements View.OnClickListener {

    private TextView ic_tb_my_shipping_address_edit_back;
    private TextView tv_my_shipping_address_edit_province_city;
    private TextView ic_my_shipping_address_edit_province_city;
    private TextView ic_my_shipping_address_edit_area;
    private TextView ic_my_shipping_address_edit_detailed_address;
    private TextView ic_my_shipping_address_edit_receiver_name;
    private TextView ic_my_shipping_address_edit_receiver_phone;
    private TextView ic_my_shipping_address_edit_zip_code;
    private TextView my_shipping_address_edit_save;
    private RelativeLayout my_shipping_address_edit_province_city;
    private RelativeLayout my_shipping_address_edit_delete;
    private EditText et_my_shipping_address_edit_area;
    private EditText et_my_shipping_address_edit_detailed_address;
    private EditText et_my_shipping_address_edit_receiver_name;
    private EditText et_my_shipping_address_edit_receiver_phone;
    private EditText et_my_shipping_address_edit_zip_code;
    private CheckBox cb_my_shipping_address_edit_isDefault;
    String getObjectId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my__shipping__address__edit);
        initView();
    }

    private void initView() {
        ic_tb_my_shipping_address_edit_back= (TextView) findViewById(R.id.ic_tb_my_shipping_address_edit_back);
        tv_my_shipping_address_edit_province_city= (TextView) findViewById(R.id.tv_my_shipping_address_edit_province_city);
        ic_my_shipping_address_edit_province_city= (TextView) findViewById(R.id.ic_my_shipping_address_edit_province_city);
        ic_my_shipping_address_edit_area= (TextView) findViewById(R.id.ic_my_shipping_address_edit_area);
        ic_my_shipping_address_edit_detailed_address= (TextView) findViewById(R.id.ic_my_shipping_address_edit_detailed_address);
        ic_my_shipping_address_edit_receiver_name= (TextView) findViewById(R.id.ic_my_shipping_address_edit_receiver_name);
        ic_my_shipping_address_edit_receiver_phone= (TextView) findViewById(R.id.ic_my_shipping_address_edit_receiver_phone);
        ic_my_shipping_address_edit_zip_code= (TextView) findViewById(R.id.ic_my_shipping_address_edit_zip_code);
        my_shipping_address_edit_save= (TextView) findViewById(R.id.my_shipping_address_edit_save);
        my_shipping_address_edit_province_city= (RelativeLayout) findViewById(R.id.my_shipping_address_edit_province_city);
        my_shipping_address_edit_delete= (RelativeLayout) findViewById(R.id.my_shipping_address_edit_delete);
        et_my_shipping_address_edit_area= (EditText) findViewById(R.id.et_my_shipping_address_edit_area);
        et_my_shipping_address_edit_detailed_address= (EditText) findViewById(R.id.et_my_shipping_address_edit_detailed_address);
        et_my_shipping_address_edit_receiver_name= (EditText) findViewById(R.id.et_my_shipping_address_edit_receiver_name);
        et_my_shipping_address_edit_receiver_phone= (EditText) findViewById(R.id.et_my_shipping_address_edit_receiver_phone);
        et_my_shipping_address_edit_zip_code= (EditText) findViewById(R.id.et_my_shipping_address_edit_zip_code);
        cb_my_shipping_address_edit_isDefault= (CheckBox) findViewById(R.id.cb_my_shipping_address_edit_isDefault);

        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_my_shipping_address_edit_back.setTypeface(iconfont1);
        Typeface iconfont2 = Typeface.createFromAsset(getAssets(), "ic_my/iconfont.ttf");
        ic_my_shipping_address_edit_province_city.setTypeface(iconfont2);ic_my_shipping_address_edit_area.setTypeface(iconfont2);
        ic_my_shipping_address_edit_detailed_address.setTypeface(iconfont2);ic_my_shipping_address_edit_receiver_name.setTypeface(iconfont2);
        ic_my_shipping_address_edit_receiver_phone.setTypeface(iconfont2);ic_my_shipping_address_edit_zip_code.setTypeface(iconfont2);

        Intent i=getIntent();
        getObjectId=i.getStringExtra("select_my_shipping_address_ObjectId");
        initObjectId(getObjectId);

        my_shipping_address_edit_save.setOnClickListener(this);
    }

    private void initObjectId(String getObjectId) {
        BmobQuery<Shipping_address>query=new BmobQuery<Shipping_address>();
        query.getObject(getObjectId, new QueryListener<Shipping_address>() {
            @Override
            public void done(Shipping_address shipping_address, BmobException e) {
                if(e==null){
                    tv_my_shipping_address_edit_province_city.setText(shipping_address.getProvince()+shipping_address.getCity());
                    et_my_shipping_address_edit_area.setText(shipping_address.getArea());
                    et_my_shipping_address_edit_detailed_address.setText(shipping_address.getDetailed_address());
                    et_my_shipping_address_edit_receiver_name.setText(shipping_address.getReceiver_name());
                    et_my_shipping_address_edit_receiver_phone.setText(shipping_address.getReceiver_phone());
                    et_my_shipping_address_edit_zip_code.setText(shipping_address.getZip_code());
                }
                else {
                    Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(My_Shipping_Address_Edit.this,"失败："+e.getMessage()+","+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.my_shipping_address_edit_save:
                Save_Shipping_Address(getObjectId);
                break;
        }
    }

    private void Save_Shipping_Address(String getObjectId) {
        Shipping_address s=new Shipping_address();
        s.setReceiver_name(et_my_shipping_address_edit_receiver_name.getText().toString());
        s.setReceiver_phone(et_my_shipping_address_edit_receiver_phone.getText().toString());
        s.setZip_code(et_my_shipping_address_edit_zip_code.getText().toString());
//        s.setProvince();
//        s.setCity();
        s.setArea(et_my_shipping_address_edit_area.getText().toString());
        s.setDetailed_address(et_my_shipping_address_edit_detailed_address.getText().toString());
        if(cb_my_shipping_address_edit_isDefault.isChecked()){
            BmobQuery<Shipping_address> s1=new BmobQuery<Shipping_address>();
            s1.addWhereEqualTo("userID",getCurrentUserID.getCurrentUserID());
            BmobQuery<Shipping_address> s2=new BmobQuery<Shipping_address>();
            s2.addWhereEqualTo("isDefault",true);
            List<BmobQuery<Shipping_address>> andQuerys=new ArrayList<BmobQuery<Shipping_address>>();
            andQuerys.add(s1);
            andQuerys.add(s2);
            BmobQuery<Shipping_address>query=new BmobQuery<Shipping_address>();
            query.and(andQuerys);
            query.findObjects(new FindListener<Shipping_address>() {
                @Override
                public void done(List<Shipping_address> list, BmobException e) {
                    if (e==null){
                        System.out.println("aaaaaaaaa"+list.size());
                        for(int i=0;i<list.size();i++){
                            System.out.println(list.get(i).getObjectId());
//                            list.get(i).setIsDefault(false);
                            Shipping_address s3 = new Shipping_address();
                            s3.setIsDefault(false);
                            s3.update(list.get(i).getObjectId(), new UpdateListener() {

                                @Override
                                public void done(BmobException e) {
                                    if(e==null){
                                        Log.i("bmob","更新成功");
                                    }else{
                                        Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode());
                                    }
                                }
                            });
                        }
                    }
                    else {
                        Log.i("bmob","失败："+e.getMessage()+","+e.getErrorCode());
                    }
                }
            });

            s.setIsDefault(true);
        }
        s.update(getObjectId, new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if (e==null){
                    Toast.makeText(My_Shipping_Address_Edit.this,"保存成功",Toast.LENGTH_SHORT).show();
                    My_Shipping_Address_Edit.this.finish();
                    startActivity(new Intent(My_Shipping_Address_Edit.this,My_shippingAddress.class));
                }
                else {
                    Log.i("bmob","更新失败："+e.getMessage()+","+e.getErrorCode());
                    Toast.makeText(My_Shipping_Address_Edit.this,"更新失败："+e.getMessage()+","+e.getErrorCode(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
