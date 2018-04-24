package com.jy.jryjy.util;

import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.LiveBeginingBean;
import com.jy.jryjy.bean.base.LiveOverBean;
import com.jy.jryjy.bean.base.LiveWillBean;
import com.jy.jryjy.bean.base.TeacherDetailsBean;
import com.jy.jryjy.bean.base.VideoPlayBackBean;
import com.jy.jryjy.bean.response.ResponseFragmentHallBean;
import com.jy.jryjy.bean.response.ResponseFragmentLiveBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by asus on 2018/4/9.
 */

public class DataUtil {



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
