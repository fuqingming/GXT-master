package com.jy.jryjy.bean.response;

import com.jy.jryjy.bean.base.BannerBean;
import com.jy.jryjy.bean.base.HallMoreBean;
import com.jy.jryjy.bean.base.LiveNowBean;

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
        private List<LiveNowBean> trailer_now = new ArrayList<>();
        private List<HallMoreBean> index_icon = new ArrayList<>();
        private Juemi juemi;
        private Mingshi mingshi;
        private Jingxuan jingxuan;
        private Shichang shichang;

        public Jingxuan getJingxuan() {
            return jingxuan;
        }

        public void setJingxuan(Jingxuan jingxuan) {
            this.jingxuan = jingxuan;
        }

        public Shichang getShichang() {
            return shichang;
        }

        public void setShichang(Shichang shichang) {
            this.shichang = shichang;
        }

        public List<BannerBean> getBanner() {
            return banner;
        }

        public void setBanner(List<BannerBean> banner) {
            this.banner = banner;
        }

        public List<LiveNowBean> getTrailer_now() {
            return trailer_now;
        }

        public void setTrailer_now(List<LiveNowBean> trailer_now) {
            this.trailer_now = trailer_now;
        }

        public Juemi getJuemi() {
            return juemi;
        }

        public void setJuemi(Juemi juemi) {
            this.juemi = juemi;
        }

        public Mingshi getMingshi() {
            return mingshi;
        }

        public void setMingshi(Mingshi mingshi) {
            this.mingshi = mingshi;
        }

        public List<HallMoreBean> getIndex_icon() {
            return index_icon;
        }

        public void setIndex_icon(List<HallMoreBean> index_icon) {
            this.index_icon = index_icon;
        }
    }

    public class Juemi{
        private String n_id;
        private String title;
        private String desc;
        private String createtime;
        private String n_photo;
        private String t_id;
        private String t_nic_name;
        private String cate_id;
        private String catename;
        private String detail_url;

        public String getN_id() {
            return n_id;
        }

        public void setN_id(String n_id) {
            this.n_id = n_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getN_photo() {
            return n_photo;
        }

        public void setN_photo(String n_photo) {
            this.n_photo = n_photo;
        }

        public String getT_id() {
            return t_id;
        }

        public void setT_id(String t_id) {
            this.t_id = t_id;
        }

        public String getT_nic_name() {
            return t_nic_name;
        }

        public void setT_nic_name(String t_nic_name) {
            this.t_nic_name = t_nic_name;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }
    }

    public class Mingshi{
        private String n_id;
        private String title;
        private String desc;
        private String createtime;
        private String n_photo;
        private String t_id;
        private String t_nic_name;
        private String cate_id;
        private String catename;
        private String detail_url;

        public String getN_id() {
            return n_id;
        }

        public void setN_id(String n_id) {
            this.n_id = n_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getN_photo() {
            return n_photo;
        }

        public void setN_photo(String n_photo) {
            this.n_photo = n_photo;
        }

        public String getT_id() {
            return t_id;
        }

        public void setT_id(String t_id) {
            this.t_id = t_id;
        }

        public String getT_nic_name() {
            return t_nic_name;
        }

        public void setT_nic_name(String t_nic_name) {
            this.t_nic_name = t_nic_name;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }
    }

    public class Jingxuan{
        private String n_id;
        private String title;
        private String desc;
        private String createtime;
        private String n_photo;
        private String t_id;
        private String t_nic_name;
        private String cate_id;
        private String catename;
        private String detail_url;

        public String getN_id() {
            return n_id;
        }

        public void setN_id(String n_id) {
            this.n_id = n_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getN_photo() {
            return n_photo;
        }

        public void setN_photo(String n_photo) {
            this.n_photo = n_photo;
        }

        public String getT_id() {
            return t_id;
        }

        public void setT_id(String t_id) {
            this.t_id = t_id;
        }

        public String getT_nic_name() {
            return t_nic_name;
        }

        public void setT_nic_name(String t_nic_name) {
            this.t_nic_name = t_nic_name;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }

        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }
    }

    public class Shichang{
        private String n_id;
        private String title;
        private String desc;
        private String createtime;
        private String n_photo;
        private String t_id;
        private String t_nic_name;
        private String cate_id;
        private String catename;
        private String detail_url;

        public String getN_id() {
            return n_id;
        }

        public void setN_id(String n_id) {
            this.n_id = n_id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public String getCreatetime() {
            return createtime;
        }

        public void setCreatetime(String createtime) {
            this.createtime = createtime;
        }

        public String getN_photo() {
            return n_photo;
        }

        public void setN_photo(String n_photo) {
            this.n_photo = n_photo;
        }

        public String getT_id() {
            return t_id;
        }

        public void setT_id(String t_id) {
            this.t_id = t_id;
        }

        public String getT_nic_name() {
            return t_nic_name;
        }

        public void setT_nic_name(String t_nic_name) {
            this.t_nic_name = t_nic_name;
        }

        public String getCate_id() {
            return cate_id;
        }

        public void setCate_id(String cate_id) {
            this.cate_id = cate_id;
        }

        public String getCatename() {
            return catename;
        }

        public void setCatename(String catename) {
            this.catename = catename;
        }
        public String getDetail_url() {
            return detail_url;
        }

        public void setDetail_url(String detail_url) {
            this.detail_url = detail_url;
        }
    }
}
