package com.jy.jryjy.bean.response;


import com.jy.jryjy.bean.base.BannerBean;
import com.jy.jryjy.bean.base.LiveBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseLiveBean  extends ResponseBaseBean{
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private List<LiveBean> trailer_info;
        private List<BannerBean> banner;

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<LiveBean> getTrailer_info() {
            return trailer_info;
        }

        public void setTrailer_info(List<LiveBean> trailer_info) {
            this.trailer_info = trailer_info;
        }
    }

}
