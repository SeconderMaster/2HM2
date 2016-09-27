package com.example.lmj.a2hm2.My;

import cn.bmob.v3.BmobUser;

/**
 * Created by lmj on 2016/9/13.
 */
public class My_User extends BmobUser {
    private String userSign;
    private String userShippingAddress;
    private String userResume;
    private String userHead;
    private String userAddress;
    private String userSex;
    private String userBirth;
    private String backgroundImage;

    public String getUserSign() {
        return userSign;
    }

    public void setUserSign(String userSign) {
        this.userSign = userSign;
    }

    public String getUserShippingAddress() {
        return userShippingAddress;
    }

    public void setUserShippingAddress(String userShippingAddress) {
        this.userShippingAddress = userShippingAddress;
    }

    public String getUserResume() {
        return userResume;
    }

    public void setUserResume(String userResume) {
        this.userResume = userResume;
    }

    public String getUserHead() {
        return userHead;
    }

    public void setUserHead(String userHead) {
        this.userHead = userHead;
    }

    public String getUserAddress() {
        return userAddress;
    }

    public void setUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public String getUserBirth() {
        return userBirth;
    }

    public void setUserBirth(String useBirth) {
        this.userBirth = useBirth;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }


}
