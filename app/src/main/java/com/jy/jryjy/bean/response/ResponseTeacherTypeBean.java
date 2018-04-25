package com.jy.jryjy.bean.response;

import com.jy.jryjy.bean.base.TeacherAnalysisBean;
import com.jy.jryjy.bean.base.TeacherTypeBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseTeacherTypeBean extends ResponseBaseBean {
    private List<TeacherTypeBean> content;

    public List<TeacherTypeBean> getContent() {
        return content;
    }

    public void setContent(List<TeacherTypeBean> content) {
        this.content = content;
    }
}
