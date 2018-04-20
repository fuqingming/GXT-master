package com.jy.jgcjj.bean.response;


import com.jy.jgcjj.bean.base.LiveBeginingBean;
import com.jy.jgcjj.bean.base.LiveOverBean;
import com.jy.jgcjj.bean.base.LiveWillBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseFragmentLiveBean {
    private List<LiveOverBean> liveOverBeans;
    private List<LiveBeginingBean> liveBeginingBeans;
    private List<LiveWillBean> liveWillBeans;

    public ResponseFragmentLiveBean(List<LiveOverBean> liveOverBeans, List<LiveBeginingBean> liveBeginingBeans, List<LiveWillBean> liveWillBeans) {
        this.liveOverBeans = liveOverBeans;
        this.liveBeginingBeans = liveBeginingBeans;
        this.liveWillBeans = liveWillBeans;
    }

    public List<LiveOverBean> getLiveOverBeans() {
        return liveOverBeans;
    }

    public void setLiveOverBeans(List<LiveOverBean> liveOverBeans) {
        this.liveOverBeans = liveOverBeans;
    }

    public List<LiveBeginingBean> getLiveBeginingBeans() {
        return liveBeginingBeans;
    }

    public void setLiveBeginingBeans(List<LiveBeginingBean> liveBeginingBeans) {
        this.liveBeginingBeans = liveBeginingBeans;
    }

    public List<LiveWillBean> getLiveWillBeans() {
        return liveWillBeans;
    }

    public void setLiveWillBeans(List<LiveWillBean> liveWillBeans) {
        this.liveWillBeans = liveWillBeans;
    }
}
