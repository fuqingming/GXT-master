package com.jy.jryjy.bean.response;

import com.jy.jryjy.bean.base.ChatLiveMessageBean;

import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseChatLiveMessageBean extends ResponseBaseBean {
    public List<ChatLiveMessageBean> content;

    public List<ChatLiveMessageBean> getContent() {
        return content;
    }

    public void setContent(List<ChatLiveMessageBean> content) {
        this.content = content;
    }
}
