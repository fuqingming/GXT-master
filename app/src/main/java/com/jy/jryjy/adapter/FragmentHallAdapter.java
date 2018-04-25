
package com.jy.jryjy.adapter;

import android.content.Intent;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.util.TimeUtils;
import com.jy.jryjy.FragmentLive;
import com.jy.jryjy.MainActivity;
import com.jy.jryjy.NewsActivity;
import com.jy.jryjy.NewsWebViewActivity;
import com.jy.jryjy.TeacherListDetailsActivity;
import com.jy.jryjy.bean.response.ResponseHallBean;
import com.jy.jryjy.view.recyclerview.BaseRecyclerViewHolder;
import com.jy.jryjy.R;
import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class FragmentHallAdapter extends BaseRecyclerAdapter<ResponseHallBean> {

    @BindView(R.id.tv_live_type)
    TextView tvLiveType;

    @BindView(R.id.tv_title1)
    TextView ivTitle;
    @BindView(R.id.tv_time1)
    TextView ivTime;

    @BindView(R.id.tv_title2)
    TextView ivTitleRecord;
    @BindView(R.id.iv_pic2)
    ImageView ivPicRecord;
    @BindView(R.id.tv_time2)
    TextView ivTimeRecord;
    @BindView(R.id.tv_title_text2)
    TextView ivTitleText2;
    @BindView(R.id.rl_mingshi)
    RelativeLayout rlMingshi;
    @BindView(R.id.ll_mingshi)
    LinearLayout llMingshi;

    @BindView(R.id.iv_icon3)
    ImageView ivIconAnalysis;
    @BindView(R.id.tv_name3)
    TextView ivNameAnalysis;
    @BindView(R.id.tv_time3)
    TextView ivTimeAnalysis;
    @BindView(R.id.tv_title3)
    TextView ivTitleAnalysis;
    @BindView(R.id.tv_title_text3)
    TextView ivTitleText3;
    @BindView(R.id.rl_juemi)
    RelativeLayout rlJuemi;
    @BindView(R.id.ll_juemi)
    LinearLayout llJuemi;

    @BindView(R.id.tv_title4)
    TextView ivTitleUmoversoty;
    @BindView(R.id.tv_time4)
    TextView ivTimeUmoversoty;
    @BindView(R.id.iv_pic4)
    ImageView ivPicUmoversoty;
    @BindView(R.id.rl_jingxuan)
    RelativeLayout rlJingxuan;
    @BindView(R.id.ll_jingxuan)
    LinearLayout llJingxuan;

    @BindView(R.id.tv_title5)
    TextView ivTitleUmoversoty5;
    @BindView(R.id.tv_time5)
    TextView ivTimeUmoversoty5;
    @BindView(R.id.iv_pic5)
    ImageView ivPicUmoversoty5;
    @BindView(R.id.rl_shichang)
    RelativeLayout rlShichang;
    @BindView(R.id.ll_shichang)
    LinearLayout llShichang;

    @BindView(R.id.ll_intent1)
    LinearLayout llIntent1;
    @BindView(R.id.ll_intent2)
    LinearLayout llIntent2;
    @BindView(R.id.ll_intent3)
    LinearLayout llIntent3;
    @BindView(R.id.ll_intent4)
    LinearLayout llIntent4;
    @BindView(R.id.ll_intent5)
    LinearLayout llIntent5;

    public FragmentHallAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_fragment_hall_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, final ResponseHallBean data, final int position) {
        TextPaint tp;
        if(data.getContent().getTrailer_now().size() == 0){
            llIntent1.setVisibility(View.GONE);
        }else{
            ivTitle.setText(data.getContent().getTrailer_now().get(0).getR_room_breif());
            tp = ivTitle .getPaint();
            tp.setFakeBoldText(true);
            String time = TimeUtils.time2String(data.getContent().getTrailer_now().get(0).getM_start_time()*1000, "HH:mm") + TimeUtils.time2String(data.getContent().getTrailer_now().get(0).getM_end_time()*1000, "HH:mm");
            ivTime.setText(time);
            if("1".equals(data.getContent().getTrailer_now().get(0).getType())){
                tvLiveType.setText("直播中");
            }else{
                tvLiveType.setText("待直播");
            }
        }
        llIntent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainActivity)mContext).setCurrentTab(FragmentLive.class);
            }
        });

        if("".equals(data.getContent().getMingshi().getCate_id())){
            llIntent2.setVisibility(View.GONE);
        }else{
            ivTitleRecord.setText(data.getContent().getMingshi().getTitle());
            Glide.with(mContext).load(data.getContent().getMingshi().getN_photo()).placeholder(R.mipmap.station_pic).into(ivPicRecord);
            ivTimeRecord.setText(data.getContent().getMingshi().getCreatetime());
            ivTitleText2.setText(data.getContent().getMingshi().getDesc());
            tp = ivTitleRecord .getPaint();
            tp.setFakeBoldText(true);
        }
        rlMingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.INTENT_NEWS_SIGN,1);
                Intent it = new Intent(mContext, NewsActivity.class);
                mContext.startActivity(it);
            }
        });
        llMingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext,NewsWebViewActivity.class);
                it.putExtra("webViewUrl",data.getContent().getMingshi().getDetail_url());
                mContext.startActivity(it);
            }
        });

        if("".equals(data.getContent().getJuemi().getCate_id())){
            llIntent2.setVisibility(View.GONE);
        }else{
            Glide.with(mContext).load(data.getContent().getJuemi().getN_photo()).placeholder(R.mipmap.head_s).into(ivIconAnalysis);
            ivNameAnalysis.setText(data.getContent().getJuemi().getT_nic_name());
            ivTimeAnalysis.setText(data.getContent().getJuemi().getCreatetime());
            ivTitleAnalysis.setText(data.getContent().getJuemi().getTitle());
            ivTitleText3.setText(data.getContent().getJuemi().getDesc());
            tp = ivTitleAnalysis .getPaint();
            tp.setFakeBoldText(true);
        }
        rlJuemi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.INTENT_NEWS_SIGN,0);
                Intent it = new Intent(mContext, NewsActivity.class);
                mContext.startActivity(it);
            }
        });
        llJuemi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext,NewsWebViewActivity.class);
                it.putExtra("webViewUrl",data.getContent().getJuemi().getDetail_url());
                mContext.startActivity(it);
            }
        });


        if("".equals(data.getContent().getJingxuan().getCate_id())){
            llIntent2.setVisibility(View.GONE);
        }else{
            ivTitleUmoversoty.setText(data.getContent().getJingxuan().getTitle());
            ivTimeUmoversoty.setText(data.getContent().getJingxuan().getCreatetime());
            Glide.with(mContext).load(data.getContent().getJingxuan().getN_photo()).into(ivPicUmoversoty);
            tp = ivTitleUmoversoty .getPaint();
            tp.setFakeBoldText(true);
        }
        rlJingxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.INTENT_NEWS_SIGN,2);
                Intent it = new Intent(mContext, NewsActivity.class);
                mContext.startActivity(it);
            }
        });
        llJingxuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext,NewsWebViewActivity.class);
                it.putExtra("webViewUrl",data.getContent().getJingxuan().getDetail_url());
                mContext.startActivity(it);
            }
        });

        if("".equals(data.getContent().getJingxuan().getCate_id())){
            llIntent2.setVisibility(View.GONE);
        }else{
            ivTitleUmoversoty5.setText(data.getContent().getJingxuan().getTitle());
            ivTimeUmoversoty5.setText(data.getContent().getJingxuan().getCreatetime());
            Glide.with(mContext).load(data.getContent().getJingxuan().getN_photo()).into(ivPicUmoversoty5);
            tp = ivTitleUmoversoty5 .getPaint();
            tp.setFakeBoldText(true);
        }
        rlShichang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.INTENT_NEWS_SIGN,3);
                Intent it = new Intent(mContext, NewsActivity.class);
                mContext.startActivity(it);
            }
        });
        llShichang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext,NewsWebViewActivity.class);
                it.putExtra("webViewUrl",data.getContent().getShichang().getDetail_url());
                mContext.startActivity(it);
            }
        });
    }

}