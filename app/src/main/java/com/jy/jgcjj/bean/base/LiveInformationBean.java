package com.jy.jgcjj.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 */

public class LiveInformationBean implements Serializable {

    private int icon;
    private String name;
    private String title;
    private String text;
    private String time;
    private String shareCount;
    private String commentCount;

    public LiveInformationBean(int icon, String name, String title, String text, String time, String shareCount, String commentCount) {
        this.icon = icon;
        this.name = name;
        this.title = title;
        this.text = text;
        this.time = time;
        this.shareCount = shareCount;
        this.commentCount = commentCount;
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public String getShareCount() {
        return shareCount;
    }

    public void setShareCount(String shareCount) {
        this.shareCount = shareCount;
    }

    public String getCommentCount() {
        return commentCount;
    }

    public void setCommentCount(String commentCount) {
        this.commentCount = commentCount;
    }
}
