package com.jy.jgcjj.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 直播
 */

public class LiveBean implements Serializable {

    public static final int LIVEING = 0;        //直播中
    public static final int PLAYBACK = 1;       //回放
    public static final int LIVE_REMINDING = 2; //直播提醒
    public static final int SET_UP = 3;         //已设置

    private String name;
    private int icon;
    private String time;
    private String timeType;
    private String text;
    private int liveType;

    public LiveBean(String name, int icon, String time, String timeType, String text, int liveType) {
        this.name = name;
        this.icon = icon;
        this.time = time;
        this.timeType = timeType;
        this.text = text;
        this.liveType = liveType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getTimeType() {
        return timeType;
    }

    public void setTimeType(String timeType) {
        this.timeType = timeType;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLiveType() {
        return liveType;
    }

    public void setLiveType(int liveType) {
        this.liveType = liveType;
    }
}
