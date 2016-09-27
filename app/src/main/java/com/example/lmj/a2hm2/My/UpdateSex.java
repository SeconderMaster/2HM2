package com.example.lmj.a2hm2.My;

import android.content.Context;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by lmj on 2016/9/24.
 */
public class UpdateSex {
    private Context mcontext;
    public UpdateSex(Context context, final String user_sex){
        this.mcontext=context;
        My_User newUser=new My_User();
        newUser.setUserSex(user_sex);
        BmobUser bmobUser=BmobUser.getCurrentUser();
        newUser.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    if(user_sex=="男"){
                        Edit_Data.my_sex.setText("男");
                    }else {
                        Edit_Data.my_sex.setText("女");
                    }

                    Toast.makeText(mcontext,"保存成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(mcontext,"更新用户信息失败:" + e.getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
