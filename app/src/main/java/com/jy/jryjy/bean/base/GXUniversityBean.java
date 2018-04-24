package com.jy.jryjy.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 股轩大学堂
 * */

public class GXUniversityBean implements Serializable {

    private String title;
    private String time;
    private int pic;

    public GXUniversityBean(String title, String time, int pic) {
        this.title = title;
        this.time = time;
        this.pic = pic;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }


}
