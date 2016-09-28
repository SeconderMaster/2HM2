package com.example.lmj.a2hm2.Release;

import com.example.lmj.a2hm2.My.My_User;

import java.util.ArrayList;
import java.util.List;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

/**
 * Created by wfh on 2016/9/26.
 */
public class ReleaseBean extends BmobObject {
    private String mGoodS_title;
    private My_User article;
    private String article_name;
    private String comment_num;
    private String thumb_num;
    public My_User getArticle() {
        return article;
    }
    public void setArticle(My_User article) {
        this.article = article;
    }

    public String getArticle_name() {
        return article_name;
    }

    public void setArticle_name(String article_name) {
        this.article_name = article_name;
    }

    public String getComment_num() {
        return comment_num;
    }

    public void setComment_num(String comment_num) {
        this.comment_num = comment_num;
    }

    public String getThumb_num() {
        return thumb_num;
    }

    public void setThumb_num(String thumb_num) {
        this.thumb_num = thumb_num;
    }

    private String mGoods_des;
    private String mGoods_classify;
    private String mGoods_pri;
    public List<BmobFile> getSelect_pics() {
        return select_pics;
    }

    public void setSelect_pics(List<BmobFile> select_pics) {
        this.select_pics .addAll(select_pics);
    }

    private List<BmobFile> select_pics=new ArrayList<>();

    public String getGoods_pri() {
        return mGoods_pri;
    }

    public void setGoods_pri(String goods_pri) {
        mGoods_pri = goods_pri;
    }

    public String getGoods_classify() {
        return mGoods_classify;
    }

    public void setGoods_classify(String goods_classify) {
        mGoods_classify = goods_classify;
    }

    public String getGoods_des() {
        return mGoods_des;
    }

    public void setGoods_des(String goods_des) {
        mGoods_des = goods_des;
    }

    public String getGoodS_title() {
        return mGoodS_title;
    }

    public void setGoodS_title(String goodS_title) {
        mGoodS_title = goodS_title;
    }

}
