package com.jy.jryjy.bean.response;


import com.jy.jryjy.bean.base.MessageBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseMessageBean extends ResponseBaseBean {
    private List<MessageBean> content;

    public List<MessageBean> getContent() {
        return content;
    }

    public void setContent(List<MessageBean> content) {
        this.content = content;
    }
}
