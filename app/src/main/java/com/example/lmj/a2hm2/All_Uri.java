package com.example.lmj.a2hm2;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class All_Uri extends AppCompatActivity {
    TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all__uri);
        tv= (TextView) findViewById(R.id.tv);
        System.out.println("开始");
        String[] proj = {MediaStore.Images.Media.DATA};
        ContentResolver contentResolver=this.getContentResolver();
        Cursor cursor=contentResolver.query(MediaStore.Images.Media.EXTERNAL_CONTENT_URI,
                proj,null,null,null);
        cursor.getCount();
        while(cursor.moveToNext()) {
            System.out.println(cursor.getString(cursor.getColumnIndex(MediaStore.Images.Media.DATA)));

        }
        cursor.close();
        System.out.println("结束");
    }
}
