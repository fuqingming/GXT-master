package com.jy.jryjy.bean.response;

import com.jy.jryjy.bean.base.TeacherAnalysisBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseNewsAnalysisBean extends ResponseBaseBean {
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private List<TeacherAnalysisBean> juemi = new ArrayList<>();
        private List<TeacherAnalysisBean> banner = new ArrayList<>();

        public List<TeacherAnalysisBean> getJuemi() {
            return juemi;
        }

        public void setJuemi(List<TeacherAnalysisBean> juemi) {
            this.juemi = juemi;
        }

        public List<TeacherAnalysisBean> getBanner() {
            return banner;
        }

        public void setBanner(List<TeacherAnalysisBean> banner) {
            this.banner = banner;
        }
    }
}
