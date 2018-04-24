package com.jy.jryjy.bean.response;

import com.jy.jryjy.bean.base.RoomBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseTrainBean extends ResponseBaseBean {
    private Content content;

    public Content getContent() {
        return content;
    }

    public void setContent(Content content) {
        this.content = content;
    }

    public class Content{
        private List<RoomBean> room = new ArrayList<>();

        public List<RoomBean> getRoom() {
            return room;
        }

        public void setRoom(List<RoomBean> room) {
            this.room = room;
        }
    }
}
