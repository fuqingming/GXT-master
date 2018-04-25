package com.jy.jryjy.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 名师指导
 */

public class HallMoreBean implements Serializable {
    private String id;
    private String i_img;
    private String i_title;
    private String i_url;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getI_img() {
        return i_img;
    }

    public void setI_img(String i_img) {
        this.i_img = i_img;
    }

    public String getI_title() {
        return i_title;
    }

    public void setI_title(String i_title) {
        this.i_title = i_title;
    }

    public String getI_url() {
        return i_url;
    }

    public void setI_url(String i_url) {
        this.i_url = i_url;
    }
}
