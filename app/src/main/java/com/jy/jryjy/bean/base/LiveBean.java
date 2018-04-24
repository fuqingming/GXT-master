package com.jy.jryjy.bean.base;

import java.io.Serializable;

/**
 * Created by HH
 * Date: 2017/11/20
 * 直播
 */

public class LiveBean implements Serializable {

    public static final int LIVEING = 2;        //直播中
    public static final int PLAYBACK = 3;       //回放
    public static final int LIVE_REMINDING = 1; //直播提醒
//    public static final int SET_UP = 3;         //已设置
    //1即将直播  2  直播中  3直播回放
    private String m_id;
    private long m_time;
    private String m_title;
    private long m_start_time;
    private long m_end_time;
    private String m_t_id;
    private String video_url;
    private String t_nic_name;
    private String t_photo;
    private String t_room;
    private String r_room_id;
    private String r_type;
    private String r_icon;
    private int type;
    private String m_content;

    public String getM_content() {
        return m_content;
    }

    public void setM_content(String m_content) {
        this.m_content = m_content;
    }

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

    public String getT_room() {
        return t_room;
    }

    public void setT_room(String t_room) {
        this.t_room = t_room;
    }

    public String getR_room_id() {
        return r_room_id;
    }

    public void setR_room_id(String r_room_id) {
        this.r_room_id = r_room_id;
    }

    public String getR_type() {
        return r_type;
    }

    public void setR_type(String r_type) {
        this.r_type = r_type;
    }

    public String getR_icon() {
        return r_icon;
    }

    public void setR_icon(String r_icon) {
        this.r_icon = r_icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
