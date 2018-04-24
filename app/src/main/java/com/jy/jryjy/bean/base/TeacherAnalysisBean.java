package com.jy.jryjy.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 老师盘析
 * */

public class TeacherAnalysisBean implements Serializable {

    public static final int MORNING = 0;
    public static final int NOON = 1;
    public static final int NIGHT = 2;
    public static final int WEEK = 3;
    public static final int MONTH = 4;

    private String n_id;
    private String title;
    private String desc;
    private String createtime;
    private String n_photo;
    private String t_id;
    private String t_nic_name;
    private String t_photo;
    private String cate_id;
    private String catename;
    private String detail_url;
    private String neican_id;

    public TeacherAnalysisBean(String t_nic_name, String neican_id,String t_id) {
        this.t_id = t_id;
        this.t_nic_name = t_nic_name;
        this.neican_id = neican_id;
    }

    public static int getMORNING() {
        return MORNING;
    }

    public static int getNOON() {
        return NOON;
    }

    public static int getNIGHT() {
        return NIGHT;
    }

    public static int getWEEK() {
        return WEEK;
    }

    public static int getMONTH() {
        return MONTH;
    }

    public String getN_id() {
        return n_id;
    }

    public void setN_id(String n_id) {
        this.n_id = n_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getN_photo() {
        return n_photo;
    }

    public void setN_photo(String n_photo) {
        this.n_photo = n_photo;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_nic_name() {
        return t_nic_name;
    }

    public void setT_nic_name(String t_nic_name) {
        this.t_nic_name = t_nic_name;
    }

    public String getCate_id() {
        return cate_id;
    }

    public void setCate_id(String cate_id) {
        this.cate_id = cate_id;
    }

    public String getCatename() {
        return catename;
    }

    public void setCatename(String catename) {
        this.catename = catename;
    }

    public String getT_photo() {
        return t_photo;
    }

    public void setT_photo(String t_photo) {
        this.t_photo = t_photo;
    }

    public String getDetail_url() {
        return detail_url;
    }

    public void setDetail_url(String detail_url) {
        this.detail_url = detail_url;
    }

    public String getNeican_id() {
        return neican_id;
    }

    public void setNeican_id(String neican_id) {
        this.neican_id = neican_id;
    }
}
