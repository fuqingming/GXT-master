package com.jy.jgcjj.bean.response;


import com.jy.jgcjj.bean.base.LoginBean;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseLoginBean extends ResponseBaseBean {
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private LoginBean info;

        public LoginBean getInfo() {
            return info;
        }

        public void setInfo(LoginBean info) {
            this.info = info;
        }
    }
}
