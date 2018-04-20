package com.jy.jgcjj.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 公开课
 * */

public class TeacherDetailsBean implements Serializable {


    private int pic;
    private String title;
    private String time;
    private String name;
    private int account;

    public TeacherDetailsBean(int pic, String title, String time, String name, int account) {
        this.pic = pic;
        this.name = name;
        this.time = time;
        this.title = title;
        this.account = account;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }
}
