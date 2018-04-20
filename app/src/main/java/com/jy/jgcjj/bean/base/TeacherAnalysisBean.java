package com.jy.jgcjj.bean.base;

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
    private int icon;
    private String name;
    private String time;
    private String title;
    private String titleText;
    private int type;

    public TeacherAnalysisBean(int icon, String name, String time, String title, String titleText, int type) {
        this.icon = icon;
        this.name = name;
        this.time = time;
        this.title = title;
        this.titleText = titleText;
        this.type = type;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitleText() {
        return titleText;
    }

    public void setTitleText(String titleText) {
        this.titleText = titleText;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
