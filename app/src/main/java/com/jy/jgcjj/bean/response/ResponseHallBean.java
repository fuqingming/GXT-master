package com.jy.jgcjj.bean.response;

import com.jy.jgcjj.bean.base.BannerBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseHallBean extends ResponseBaseBean {
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private List<BannerBean> banner = new ArrayList<>();

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }
    }
}
