package com.jy.jryjy.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 股轩堂名师
 */

public class TeacherListBean implements Serializable {
    private String t_id;
    private String t_name;
    private String t_nic_name;
    private String t_pwd;
    private String t_room;
    private String t_photo;
    private String t_brief;
    private String t_strategy;
    private String t_type;

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getT_nic_name() {
        return t_nic_name;
    }

    public void setT_nic_name(String t_nic_name) {
        this.t_nic_name = t_nic_name;
    }

    public String getT_pwd() {
        return t_pwd;
    }

    public void setT_pwd(String t_pwd) {
        this.t_pwd = t_pwd;
    }

    public String getT_room() {
        return t_room;
    }

    public void setT_room(String t_room) {
        this.t_room = t_room;
    }

    public String getT_photo() {
        return t_photo;
    }

    public void setT_photo(String t_photo) {
        this.t_photo = t_photo;
    }

    public String getT_brief() {
        return t_brief;
    }

    public void setT_brief(String t_brief) {
        this.t_brief = t_brief;
    }

    public String getT_strategy() {
        return t_strategy;
    }

    public void setT_strategy(String t_strategy) {
        this.t_strategy = t_strategy;
    }

    public String getT_type() {
        return t_type;
    }

    public void setT_type(String t_type) {
        this.t_type = t_type;
    }
}
