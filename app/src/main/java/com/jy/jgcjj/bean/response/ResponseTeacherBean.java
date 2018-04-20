package com.jy.jgcjj.bean.response;


import com.jy.jgcjj.bean.base.TeacherBean;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseTeacherBean extends ResponseBaseBean {
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        TeacherBean info;

        public TeacherBean getInfo() {
            return info;
        }

        public void setInfo(TeacherBean info) {
            this.info = info;
        }
    }
}
