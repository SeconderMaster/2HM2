package com.example.lmj.a2hm2.My;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.DatePicker;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lmj.a2hm2.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import cn.bmob.v3.BmobUser;

public class Edit_Data extends AppCompatActivity implements View.OnClickListener {

    private TextView ic_tb_edit_data_back;
    private RelativeLayout edit_data_userHead;
    private RelativeLayout edit_data_background_image;
    private RelativeLayout edit_data_sex;
    private RelativeLayout edit_data_birth;
    private RelativeLayout edit_data_address;
    private RelativeLayout edit_data_shipping_address;
    private RelativeLayout edit_data_resume;
    private TextView ic_edit_data_userHead;
    private TextView ic_edit_data_background_image;
    private TextView ic_edit_data_sex;
    private TextView ic_edit_data_birth;
    private TextView ic_edit_data_address;
    private TextView ic_edit_data_shipping_address;
    private TextView ic_edit_data_resume;
    private TextView edit_data_i_like;
    private TextView edit_data_ID_card_verified;
    private TextView edit_data_mobilePhone_verified;
    private TextView edit_data_email_verified;
    private TextView ic_edit_data_ID_card_verified;
    private TextView ic_edit_data_mobilePhone_verified;
    private TextView ic_edit_data_email_verified;
    public static TextView my_sex;
    public static TextView my_birth;
    public static TextView my_address;
    public static TextView my_shipping_address;
    public static TextView my_resume;
    public static boolean isAddress=false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit__data);
        initView();
    }

    private void initView() {
        ic_tb_edit_data_back= (TextView) findViewById(R.id.ic_tb_edit_data_back);
        ic_edit_data_userHead= (TextView) findViewById(R.id.ic_edit_data_userHead);
        ic_edit_data_background_image= (TextView) findViewById(R.id.ic_edit_data_background_image);
        ic_edit_data_sex= (TextView) findViewById(R.id.ic_edit_data_sex);
        ic_edit_data_birth= (TextView) findViewById(R.id.ic_edit_data_birth);
        ic_edit_data_address= (TextView) findViewById(R.id.ic_edit_data_address);
        ic_edit_data_shipping_address= (TextView) findViewById(R.id.ic_edit_data_shipping_address);
        ic_edit_data_resume= (TextView) findViewById(R.id.ic_edit_data_resume);
        edit_data_i_like= (TextView) findViewById(R.id.edit_data_i_like);
        edit_data_ID_card_verified= (TextView) findViewById(R.id.edit_data_ID_card_verified);
        edit_data_mobilePhone_verified= (TextView) findViewById(R.id.edit_data_mobilePhone_verified);
        edit_data_email_verified= (TextView) findViewById(R.id.edit_data_email_verified);
        ic_edit_data_ID_card_verified= (TextView) findViewById(R.id.ic_edit_data_ID_card_verified);
        ic_edit_data_mobilePhone_verified= (TextView) findViewById(R.id.ic_edit_data_mobilePhone_verified);
        ic_edit_data_email_verified= (TextView) findViewById(R.id.ic_edit_data_email_verified);
        edit_data_userHead= (RelativeLayout) findViewById(R.id.edit_data_userHead);
        edit_data_background_image= (RelativeLayout) findViewById(R.id.edit_data_background_image);
        edit_data_sex= (RelativeLayout) findViewById(R.id.edit_data_sex);
        edit_data_birth= (RelativeLayout) findViewById(R.id.edit_data_birth);
        edit_data_address= (RelativeLayout) findViewById(R.id.edit_data_address);
        edit_data_shipping_address= (RelativeLayout) findViewById(R.id.edit_data_shipping_address);
        edit_data_resume= (RelativeLayout) findViewById(R.id.edit_data_resume);
        my_sex= (TextView) findViewById(R.id.my_sex);
        my_birth= (TextView) findViewById(R.id.my_birth);
        my_address= (TextView) findViewById(R.id.my_address);
        my_shipping_address= (TextView) findViewById(R.id.my_shipping_address);
        my_resume= (TextView) findViewById(R.id.my_resume);
        if(BmobUser.getObjectByKey("userSex")!=null){
            my_sex.setText((String) BmobUser.getObjectByKey("userSex"));
        }
        if(BmobUser.getObjectByKey("userBirth")!=null){
            my_birth.setText((String) BmobUser.getObjectByKey("userBirth"));
        }
        if(BmobUser.getObjectByKey("userAddress")!=null){
            System.out.println("地址"+(String) BmobUser.getObjectByKey("userAddress"));
            my_address.setText((String) BmobUser.getObjectByKey("userAddress"));
        }
        Typeface iconfont1 = Typeface.createFromAsset(getAssets(), "ic_search/iconfont.ttf");
        ic_tb_edit_data_back.setTypeface(iconfont1);
        Typeface iconfont2 = Typeface.createFromAsset(getAssets(), "ic_edit_data/iconfont.ttf");
        ic_edit_data_ID_card_verified.setTypeface(iconfont2);ic_edit_data_mobilePhone_verified.setTypeface(iconfont2);
        ic_edit_data_email_verified.setTypeface(iconfont2);
        Typeface iconfont3 = Typeface.createFromAsset(getAssets(), "ic_my/iconfont.ttf");
        ic_edit_data_userHead.setTypeface(iconfont3);ic_edit_data_background_image.setTypeface(iconfont3);
        ic_edit_data_sex.setTypeface(iconfont3);ic_edit_data_birth.setTypeface(iconfont3);
        ic_edit_data_address.setTypeface(iconfont3);ic_edit_data_shipping_address.setTypeface(iconfont3);
        ic_edit_data_resume.setTypeface(iconfont3);

        ic_tb_edit_data_back.setOnClickListener(this);
        edit_data_userHead.setOnClickListener(this);
        edit_data_background_image.setOnClickListener(this);
        edit_data_sex.setOnClickListener(this);
        edit_data_birth.setOnClickListener(this);
        edit_data_address.setOnClickListener(this);
        edit_data_shipping_address.setOnClickListener(this);
        edit_data_resume.setOnClickListener(this);
        edit_data_i_like.setOnClickListener(this);
        edit_data_ID_card_verified.setOnClickListener(this);
        edit_data_mobilePhone_verified.setOnClickListener(this);
        edit_data_email_verified.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_tb_edit_data_back:
                finish();
                break;
            case R.id.edit_data_userHead:
                Toast.makeText(Edit_Data.this,"更换头像",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_data_background_image:
                Toast.makeText(Edit_Data.this,"更换背景",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_data_sex:
                new AlertDialog.Builder(this).setItems(
                        new String[]{"男", "女"}, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                switch (which){
                                    case 0:
                                        UpdateSex updateSex1=new UpdateSex(Edit_Data.this,"男");
                                        break;
                                    case 1:
                                        UpdateSex updateSex2=new UpdateSex(Edit_Data.this,"女");
                                        break;
                                }
                            }
                        }).show();
                break;
            case R.id.edit_data_birth:
                final Calendar calendar=Calendar.getInstance();
                DatePickerDialog pickerDialog = new DatePickerDialog(Edit_Data.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker arg0, int year, int month, int day) {
                        calendar.set(year, month, day);
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
                        String date= format.format(calendar.getTime()).toString();
                        UpdateBirth updateBirth = new UpdateBirth(Edit_Data.this, date);
                    }
                }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
                pickerDialog.show();
                break;
            case R.id.edit_data_address:
                isAddress=true;
                startActivity(new Intent(Edit_Data.this,Add_My_ShippingAddress_Province.class));
                Edit_Data.this.finish();
//                Toast.makeText(Edit_Data.this,"地址",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_data_shipping_address:
                startActivity(new Intent(Edit_Data.this,My_shippingAddress.class));
//                Toast.makeText(Edit_Data.this,"收货地址",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_data_resume:
                Toast.makeText(Edit_Data.this,"介绍",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_data_i_like:
                Toast.makeText(Edit_Data.this,"喜欢",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_data_ID_card_verified:
                Toast.makeText(Edit_Data.this,"身份认证",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_data_mobilePhone_verified:
                Toast.makeText(Edit_Data.this,"手机认证",Toast.LENGTH_SHORT).show();
                break;
            case R.id.edit_data_email_verified:
                Toast.makeText(Edit_Data.this,"邮箱认证",Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
