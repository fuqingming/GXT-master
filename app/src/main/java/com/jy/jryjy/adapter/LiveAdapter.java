package com.jy.jryjy.adapter;


import android.app.Activity;
import android.content.Intent;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jgcj.library.util.NiceUtil;
import com.jgcj.library.util.TimeUtils;
import com.jgcj.library.util.Utils;
import com.jy.jryjy.ChatLiveActivity;
import com.jy.jryjy.LivePlaybackActivity;
import com.jy.jryjy.LoginActivity;
import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.LiveBean;
import com.jy.jryjy.huanxin.DemoHelper;
import com.jgcj.library.view.recyclerview.BaseRecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class LiveAdapter extends BaseRecyclerAdapter<LiveBean> {

    @BindView(R.id.tv_name)
    TextView m_ivName;
    @BindView(R.id.iv_icon)
    ImageView m_ivIcon;
    @BindView(R.id.tv_time)
    TextView m_ivTime;
    @BindView(R.id.tv_time_type)
    TextView m_ivTimeType;
    @BindView(R.id.tv_text)
    TextView m_ivText;
    @BindView(R.id.iv_type)
    ImageView m_ivType;

    public LiveAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_live_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, final LiveBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        m_ivName.setText(data.getT_nic_name());
        Glide.with(mContext).load(data.getT_photo()).placeholder(R.mipmap.head_s).into(m_ivIcon);
        m_ivTime.setText(TimeUtils.time2String(data.getM_time()*1000, "HH:mm"));
        TextPaint tp = m_ivTime.getPaint();
        tp.setFakeBoldText(true);
        m_ivTimeType.setText(data.getM_title());
        TextPaint tp1 = m_ivTimeType.getPaint();
        tp1.setFakeBoldText(true);
        m_ivText.setText(data.getM_content());
        if(data.getType() == LiveBean.LIVEING){
            m_ivType.setImageResource(R.mipmap.live_live);
        }else if(data.getType() == LiveBean.PLAYBACK){
            m_ivType.setImageResource(R.mipmap.live_return);
        }else if(data.getType() == LiveBean.LIVE_REMINDING){
            m_ivType.setImageResource(R.mipmap.live_notice);
        }
//        else if(data.getLiveType() == LiveBean.SET_UP){
//            m_ivType.setImageResource(R.mipmap.live_notice);
//        }
        m_ivType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getType() == LiveBean.LIVE_REMINDING){
//                    Utils.showDialogLive(mContext,new Intent(mContext,LiveMessageActivity.class));
                }else if(data.getType() == LiveBean.LIVEING){
                    if (!DemoHelper.getInstance().isLoggedIn()) {
                        Intent it = new Intent(mContext,LoginActivity.class);
                        mContext.startActivity(it);
                        return;
                    }
                    if(!NiceUtil.isNetworkAvalible(mContext)){
                        Utils.showDialogWifi( (Activity) mContext);
                        return;
                    }
                    Intent it = new Intent(mContext, ChatLiveActivity.class);
                    it.putExtra("strRoomId",data.getR_room_id());
                    it.putExtra("strLiveUrl",data.getVideo_url());
//                    it.putExtra("strRoomId","45447918977025");
//                    it.putExtra("strLiveUrl","rtmp://live.hkstv.hk.lxdns.com/live/hks");
                    mContext.startActivity(it);
                }else if(data.getType() == LiveBean.PLAYBACK){
                    Intent it = new Intent(mContext, LivePlaybackActivity.class);
                    it.putExtra("strPlayUrl",data.getVideo_url());
                    mContext.startActivity(it);
                }
            }
        });
    }

}
