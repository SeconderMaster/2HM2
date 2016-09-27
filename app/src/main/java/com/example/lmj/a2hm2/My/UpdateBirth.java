package com.example.lmj.a2hm2.My;

import android.content.Context;
import android.widget.Toast;

import cn.bmob.v3.BmobUser;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.UpdateListener;

/**
 * Created by lmj on 2016/9/24.
 */
public class UpdateBirth {
    private Context mcontext;
    public UpdateBirth(Context context, final String user_birth){
        this.mcontext=context;
        My_User newUser=new My_User();
        newUser.setUserBirth(user_birth);
        BmobUser bmobUser=BmobUser.getCurrentUser();
        newUser.update(bmobUser.getObjectId(), new UpdateListener() {
            @Override
            public void done(BmobException e) {
                if(e==null){
                    Edit_Data.my_birth.setText(user_birth.toString());
                    Toast.makeText(mcontext,"保存成功",Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(mcontext,"更新用户信息失败:" + e.getMessage(),Toast.LENGTH_SHORT).show();
                    System.out.println(e.getMessage());
                }
            }
        });
    }
}
