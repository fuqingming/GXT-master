package com.jy.jryjy.bean.response;


import com.jy.jryjy.bean.base.BannerBean;
import com.jy.jryjy.bean.base.LiveBean;
import com.jy.jryjy.bean.base.TeacherListBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseTeacherListBean extends ResponseBaseBean{
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private List<TeacherListBean> data;

        public List<TeacherListBean> getData() {
            return data;
        }

        public void setData(List<TeacherListBean> data) {
            this.data = data;
        }
    }

}
