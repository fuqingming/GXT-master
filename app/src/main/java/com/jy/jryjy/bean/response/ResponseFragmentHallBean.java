package com.jy.jryjy.bean.response;


/**
 * Created by HH
 * Date: 2017/12/7
 */

public class ResponseFragmentHallBean {
    private LiveEvery1 liveEvery1;
    private LiveEvery2 liveEvery2;
    private LiveEvery3 liveEvery3;
    private LiveEvery4 liveEvery4;
    private LiveEvery5 liveEvery5;

    public ResponseFragmentHallBean(LiveEvery1 liveEvery1, LiveEvery2 liveEvery2, LiveEvery3 liveEvery3, LiveEvery4 liveEvery4, LiveEvery5 liveEvery5) {
        this.liveEvery1 = liveEvery1;
        this.liveEvery2 = liveEvery2;
        this.liveEvery3 = liveEvery3;
        this.liveEvery4 = liveEvery4;
        this.liveEvery5 = liveEvery5;
    }

    public LiveEvery1 getLiveEvery1() {
        return liveEvery1;
    }

    public void setLiveEvery1(LiveEvery1 liveEvery1) {
        this.liveEvery1 = liveEvery1;
    }

    public LiveEvery2 getLiveEvery2() {
        return liveEvery2;
    }

    public void setLiveEvery2(LiveEvery2 liveEvery2) {
        this.liveEvery2 = liveEvery2;
    }

    public LiveEvery3 getLiveEvery3() {
        return liveEvery3;
    }

    public void setLiveEvery3(LiveEvery3 liveEvery3) {
        this.liveEvery3 = liveEvery3;
    }

    public LiveEvery4 getLiveEvery4() {
        return liveEvery4;
    }

    public void setLiveEvery4(LiveEvery4 liveEvery4) {
        this.liveEvery4 = liveEvery4;
    }

    public LiveEvery5 getLiveEvery5() {
        return liveEvery5;
    }

    public void setLiveEvery5(LiveEvery5 liveEvery5) {
        this.liveEvery5 = liveEvery5;
    }

    public static class LiveEvery1{
        private String title;
        private String titleName;
        private String time;

        public LiveEvery1(String title,String titleName,String time) {
            this.title = title;
            this.titleName = titleName;
            this.time = time;
        }
        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleName() {
            return titleName;
        }

        public void setTitleName(String titleName) {
            this.titleName = titleName;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
    public static class LiveEvery2{
        private int icon;
        private String name;
        private String time;
        private String title;
        private String text;
        private int pic;

        public LiveEvery2( int icon,String name,String time,String title, String text,int pic ) {
            this.icon = icon;
            this.name = name;
            this.title = title;
            this.text = text;
            this.pic = pic;
            this.time = time;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
    public static class LiveEvery3{
        private int icon;
        private String name;
        private String time;
        private String title;
        private String titleText;
        private int type;

        public LiveEvery3( int icon,String name,  String time,String title, String titleText) {
            this.icon = icon;
            this.name = name;
            this.time = time;
            this.title = title;
            this.titleText = titleText;
        }

        public int getIcon() {
            return icon;
        }

        public void setIcon(int icon) {
            this.icon = icon;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getTitleText() {
            return titleText;
        }

        public void setTitleText(String titleText) {
            this.titleText = titleText;
        }
    }
    public static class LiveEvery4{
        private String title;
        private String time;
        private int pic;

        public LiveEvery4(String title, String time, int pic) {
            this.title = title;
            this.time = time;
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
    public static class LiveEvery5{
        private String title;
        private String time;
        private int pic;

        public LiveEvery5(String title, String time, int pic) {
            this.title = title;
            this.time = time;
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPic() {
            return pic;
        }

        public void setPic(int pic) {
            this.pic = pic;
        }

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
