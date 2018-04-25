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
        private Juemi juemi;
        private Juemituijian juemituijian;
        public Juemi getJuemi() {
            return juemi;
        }

        public void setJuemi(Juemi juemi) {
            this.juemi = juemi;
        }

        public Juemituijian getJuemituijian() {
            return juemituijian;
        }

        public void setJuemituijian(Juemituijian juemituijian) {
            this.juemituijian = juemituijian;
        }
    }

    public class Juemi{
        private List<TeacherAnalysisBean> data;


        public List<TeacherAnalysisBean> getData() {
            return data;
        }

        public void setData(List<TeacherAnalysisBean> data) {
            this.data = data;
        }


    }

    public class Juemituijian{
        private List<TeacherAnalysisBean> data;

        public List<TeacherAnalysisBean> getData() {
            return data;
        }

        public void setData(List<TeacherAnalysisBean> data) {
            this.data = data;
        }
    }
}
