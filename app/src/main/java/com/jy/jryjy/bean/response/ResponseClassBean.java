package com.jy.jryjy.bean.response;

import com.jy.jryjy.bean.base.BannerBean;
import com.jy.jryjy.bean.base.ClassBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseClassBean extends ResponseBaseBean {
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private List<ClassBean> trailer_after;
        private List<BannerBean> banner;

        public List<ClassBean> getTrailer_after() {
            return trailer_after;
        }

        public void setTrailer_after(List<ClassBean> trailer_after) {
            this.trailer_after = trailer_after;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }
    }
}
