package com.jy.jryjy.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 */

public class LiveWillBean implements Serializable {

    private String time;
    private String type;
    private String name;
    private String title;

    public LiveWillBean(String time, String type, String name, String title) {
        this.time = time;
        this.type = type;
        this.name = name;
        this.title = title;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
