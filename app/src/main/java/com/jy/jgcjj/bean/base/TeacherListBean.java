package com.jy.jgcjj.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 股轩堂名师
 */

public class TeacherListBean implements Serializable {
    private int icon;
    private String name;
    private String classCount;
    private String persons;
    private String text;

    public TeacherListBean(int icon, String name, String classCount, String persons, String text) {
        this.icon = icon;
        this.name = name;
        this.classCount = classCount;
        this.persons = persons;
        this.text = text;
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

    public String getClassCount() {
        return classCount;
    }

    public void setClassCount(String classCount) {
        this.classCount = classCount;
    }

    public String getPersons() {
        return persons;
    }

    public void setPersons(String persons) {
        this.persons = persons;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
