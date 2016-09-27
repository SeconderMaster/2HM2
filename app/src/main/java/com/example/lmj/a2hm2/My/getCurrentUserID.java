package com.example.lmj.a2hm2.My;

import cn.bmob.v3.BmobUser;

/**
 * Created by lmj on 2016/9/24.
 */
public class getCurrentUserID {
    public static String getCurrentUserID(){
        String objectId=(String) BmobUser.getObjectByKey("objectId");
        return objectId;
    }
}
