package com.jy.jryjy.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 名师指导
 */

public class LiveNowBean implements Serializable {

    private String m_id;
    private long m_time;
    private String m_title;
    private long m_start_time;
    private long m_end_time;
    private String m_t_id;
    private String video_url;
    private String m_content;
    private String t_nic_name;
    private String t_photo;
    private String r_id;
    private String r_pwd;
    private String r_is_screte;
    private String r_t_online;
    private String r_people;
    private String r_icon;
    private String r_room_id;
    private String r_room_breif;
    private String type;

    public String getM_id() {
        return m_id;
    }

    public void setM_id(String m_id) {
        this.m_id = m_id;
    }

    public long getM_time() {
        return m_time;
    }

    public void setM_time(long m_time) {
        this.m_time = m_time;
    }

    public String getM_title() {
        return m_title;
    }

    public void setM_title(String m_title) {
        this.m_title = m_title;
    }

    public long getM_start_time() {
        return m_start_time;
    }

    public void setM_start_time(long m_start_time) {
        this.m_start_time = m_start_time;
    }

    public long getM_end_time() {
        return m_end_time;
    }

    public void setM_end_time(long m_end_time) {
        this.m_end_time = m_end_time;
    }

    public String getM_t_id() {
        return m_t_id;
    }

    public void setM_t_id(String m_t_id) {
        this.m_t_id = m_t_id;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getM_content() {
        return m_content;
    }

    public void setM_content(String m_content) {
        this.m_content = m_content;
    }

    public String getT_nic_name() {
        return t_nic_name;
    }

    public void setT_nic_name(String t_nic_name) {
        this.t_nic_name = t_nic_name;
    }

    public String getT_photo() {
        return t_photo;
    }

    public void setT_photo(String t_photo) {
        this.t_photo = t_photo;
    }

    public String getR_id() {
        return r_id;
    }

    public void setR_id(String r_id) {
        this.r_id = r_id;
    }

    public String getR_pwd() {
        return r_pwd;
    }

    public void setR_pwd(String r_pwd) {
        this.r_pwd = r_pwd;
    }

    public String getR_is_screte() {
        return r_is_screte;
    }

    public void setR_is_screte(String r_is_screte) {
        this.r_is_screte = r_is_screte;
    }

    public String getR_t_online() {
        return r_t_online;
    }

    public void setR_t_online(String r_t_online) {
        this.r_t_online = r_t_online;
    }

    public String getR_people() {
        return r_people;
    }

    public void setR_people(String r_people) {
        this.r_people = r_people;
    }

    public String getR_icon() {
        return r_icon;
    }

    public void setR_icon(String r_icon) {
        this.r_icon = r_icon;
    }

    public String getR_room_id() {
        return r_room_id;
    }

    public void setR_room_id(String r_room_id) {
        this.r_room_id = r_room_id;
    }

    public String getR_room_breif() {
        return r_room_breif;
    }

    public void setR_room_breif(String r_room_breif) {
        this.r_room_breif = r_room_breif;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}