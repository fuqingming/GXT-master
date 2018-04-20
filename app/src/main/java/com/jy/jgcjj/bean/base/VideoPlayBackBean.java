package com.jy.jgcjj.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 */

public class VideoPlayBackBean implements Serializable {

    private int pic;
    private String title;
    private String name;
    private String text;
    private String time;

    public VideoPlayBackBean(int pic, String title, String name, String text, String time) {
        this.pic = pic;
        this.title = title;
        this.name = name;
        this.text = text;
        this.time = time;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
