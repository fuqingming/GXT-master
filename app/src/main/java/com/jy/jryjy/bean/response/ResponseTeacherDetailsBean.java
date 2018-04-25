package com.jy.jryjy.bean.response;


import com.jy.jryjy.bean.base.TeacherBean;
import com.jy.jryjy.bean.base.TeacherDetailsBean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseTeacherDetailsBean extends ResponseBaseBean {
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private TeacherDetailsBean teacher;

        private List<TeacherDetail> TeacherDetail;

        public TeacherDetailsBean getTeacher() {
            return teacher;
        }

        public void setTeacher(TeacherDetailsBean teacher) {
            this.teacher = teacher;
        }

        public List<ResponseTeacherDetailsBean.TeacherDetail> getTeacherDetail() {
            return TeacherDetail;
        }

        public void setTeacherDetail(List<ResponseTeacherDetailsBean.TeacherDetail> teacherDetail) {
            TeacherDetail = teacherDetail;
        }
    }

    public class TeacherDetail  implements Serializable{
        private String m_id;
        private String m_time;
        private String m_title;
        private String m_start_time;
        private String m_end_time;
        private String m_t_id;
        private String video_url;
        private String t_id;
        private String t_name;
        private String t_nic_name;
        private String t_pwd;
        private String t_room;
        private String t_photo;
        private String t_brief;
        private String t_strategy;
        private String t_type;
        private String r_room_id;
        private String r_type;
        private String c_photo;

        public String getM_id() {
            return m_id;
        }

        public void setM_id(String m_id) {
            this.m_id = m_id;
        }

        public String getM_time() {
            return m_time;
        }

        public void setM_time(String m_time) {
            this.m_time = m_time;
        }

        public String getM_title() {
            return m_title;
        }

        public void setM_title(String m_title) {
            this.m_title = m_title;
        }

        public String getM_start_time() {
            return m_start_time;
        }

        public void setM_start_time(String m_start_time) {
            this.m_start_time = m_start_time;
        }

        public String getM_end_time() {
            return m_end_time;
        }

        public void setM_end_time(String m_end_time) {
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

        public String getC_photo() {
            return c_photo;
        }

        public void setC_photo(String c_photo) {
            this.c_photo = c_photo;
        }
    }
}
