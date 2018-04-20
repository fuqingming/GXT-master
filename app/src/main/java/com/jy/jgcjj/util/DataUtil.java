package com.jy.jgcjj.util;

import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.base.LiveBean;
import com.jy.jgcjj.bean.base.LiveBeginingBean;
import com.jy.jgcjj.bean.base.LiveOverBean;
import com.jy.jgcjj.bean.base.LiveWillBean;
import com.jy.jgcjj.bean.base.TeacherDetailsBean;
import com.jy.jgcjj.bean.base.VideoPlayBackBean;
import com.jy.jgcjj.bean.response.ResponseFragmentHallBean;
import com.jy.jgcjj.bean.response.ResponseFragmentLiveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/4/9.
 */

public class DataUtil {
    public static List<LiveBean> initLiveBean(){
        List<LiveBean> arrLiveBean = new ArrayList<>();
        LiveBean classOpenBean = new LiveBean("沈志华",R.mipmap.gxt_icon,"09:00","早盘点晴","接替永吉股份上位题材切换运用…",LiveBean.LIVE_REMINDING);
        arrLiveBean.add(classOpenBean);
        LiveBean classOpenBean1 = new LiveBean("黄忠恒",R.mipmap.gxt_icon,"09:00","早盘点晴","接替永吉股份上位题材切换运用…",LiveBean.LIVEING);
        arrLiveBean.add(classOpenBean1);
        LiveBean classOpenBean2 = new LiveBean("吴。刚",R.mipmap.gxt_icon,"09:00","早盘点晴","接替永吉股份上位题材切换运用…",LiveBean.PLAYBACK);
        arrLiveBean.add(classOpenBean2);
        LiveBean classOpenBean3 = new LiveBean("许毓玲",R.mipmap.gxt_icon,"09:00","早盘点晴","接替永吉股份上位题材切换运用…",LiveBean.SET_UP);
        arrLiveBean.add(classOpenBean3);
        LiveBean classOpenBean4 = new LiveBean("王新华",R.mipmap.gxt_icon,"09:00","早盘点晴","接替永吉股份上位题材切换运用…",LiveBean.LIVE_REMINDING);
        arrLiveBean.add(classOpenBean4);
        LiveBean classOpenBean5 = new LiveBean("沈志华",R.mipmap.gxt_icon,"09:00","早盘点晴","接替永吉股份上位题材切换运用…",LiveBean.PLAYBACK);
        arrLiveBean.add(classOpenBean5);
        return arrLiveBean;
    }
    public static List<ResponseFragmentHallBean> initDataForHall()
    {

         ResponseFragmentHallBean.LiveEvery1 liveEvery1 = new ResponseFragmentHallBean.LiveEvery1("[玲珑量价]掌握行情动脉","高级导师许毓玲","9:30-10:30");
         ResponseFragmentHallBean.LiveEvery2 liveEvery2 = new ResponseFragmentHallBean.LiveEvery2(R.mipmap.my_head,"王新华","2018-01-02  13:08:03","王新华老师上证50指数11连阳案例",
                 "2018年1月3日，王新华老师在19:30档实战培训直播课26分53",R.mipmap.gxt_integral_pic);
         ResponseFragmentHallBean.LiveEvery3 liveEvery3 = new ResponseFragmentHallBean.LiveEvery3(R.mipmap.my_head,"许毓玲","2018-01-25 20:30","2018有气体制改革深化年：改革意见细则或于…",
                 "早盘，主板指数在银行和券商带领下小幅高开，随即银行保险纷纷出现震荡下跌走势，导致指数在开盘价附近维持震荡。受消息面的影响，军工板块开…");
         ResponseFragmentHallBean.LiveEvery4 liveEvery4 = new ResponseFragmentHallBean.LiveEvery4("2018有气体制改革深化年：改革意见细则或于上半年下发","2018-01-02  13:08:03",R.mipmap.gxt_class_icon);
         ResponseFragmentHallBean.LiveEvery5 liveEvery5 = new ResponseFragmentHallBean.LiveEvery5("2018有气体制改革深化年：改革意见细则或于上半年下发","2018-01-02  13:08:03",R.mipmap.gxt_class_icon);
        List<ResponseFragmentHallBean> responseFragmentHallBeans = new ArrayList<>();
        responseFragmentHallBeans.add(new ResponseFragmentHallBean(liveEvery1,liveEvery2,liveEvery3,liveEvery4,liveEvery5));
        return responseFragmentHallBeans ;
    }

    public static List<ResponseFragmentLiveBean> initDataForLive()
    {
        List<LiveOverBean> liveMsgBeans = new ArrayList<>();
        List<LiveBeginingBean> liveVedioBeans = new ArrayList<>();
        List<LiveWillBean> liveInformationBeans = new ArrayList<>();
        liveMsgBeans.add(new LiveOverBean("09:00-10:30","已结束","导师名","直播主题1行"));
        liveVedioBeans.add(new LiveBeginingBean("09:00-10:30","直播中","吴磊","横盘震荡 跳空低开",R.mipmap.my_head,
                "潜龙战队-杨老师","显示导师简介 2行 行间距18 字号12pt #888888 左间距5右间距10pt 超出不显示显示导师简介 2行 行间距18 字号12pt #888888 左间距5右间距10pt 超出不显示"));
        liveInformationBeans.add(new LiveWillBean("09:00-10:30","即将开始","导师名","直播主题1行"));
        liveInformationBeans.add(new LiveWillBean("09:00-10:30","即将开始","导师名","直播主题1行"));
        List<ResponseFragmentLiveBean> responseFragmentHallBeans = new ArrayList<>();
        responseFragmentHallBeans.add(new ResponseFragmentLiveBean(liveMsgBeans,liveVedioBeans,liveInformationBeans));
        return responseFragmentHallBeans ;
    }

    public static List<TeacherDetailsBean> initTeacherDetails(){
        List<TeacherDetailsBean> arrClassOpenBean = new ArrayList<>();
        for(int i = 0 ; i < 8 ; i ++){
            TeacherDetailsBean classOpenBean = new TeacherDetailsBean(R.mipmap.gxt_class_icon,"两市强势上攻，后市攻击…","时长 1:16:30","王新华"+i,3980 + i);
            arrClassOpenBean.add(classOpenBean);
        }
        return arrClassOpenBean;
    }

    public static List<VideoPlayBackBean> initVideoPlayBackBean(){
        List<VideoPlayBackBean> videoPlayBackBeans = new ArrayList<>();
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        videoPlayBackBeans.add(new VideoPlayBackBean(0,"左间距5pt 右15pt 1行","王新华","谈股论期","2018/02/13   "));
        return videoPlayBackBeans;
    }
}
