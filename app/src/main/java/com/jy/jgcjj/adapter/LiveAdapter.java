package com.jy.jgcjj.adapter;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jgcj.library.util.NiceUtil;
import com.jgcj.library.util.Utils;
import com.jy.jgcjj.ChatLiveActivity;
import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.base.LiveBean;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewAdapter;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

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
        m_ivName.setText(data.getName());
        m_ivIcon.setImageResource(data.getIcon());
        m_ivTime.setText(data.getTime());
        m_ivTimeType.setText(data.getTimeType());
        m_ivText.setText(data.getText());
        if(data.getLiveType() == LiveBean.LIVEING){
            m_ivType.setImageResource(R.mipmap.live_live);
        }else if(data.getLiveType() == LiveBean.PLAYBACK){
            m_ivType.setImageResource(R.mipmap.live_return);
        }else if(data.getLiveType() == LiveBean.LIVE_REMINDING){
            m_ivType.setImageResource(R.mipmap.live_notice);
        }else if(data.getLiveType() == LiveBean.SET_UP){
            m_ivType.setImageResource(R.mipmap.live_notice);
        }
        m_ivType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(data.getLiveType() == LiveBean.LIVE_REMINDING){
//                    Utils.showDialogLive(mContext,new Intent(mContext,LiveMessageActivity.class));
                }else if(data.getLiveType() == LiveBean.LIVEING){
                    if(!NiceUtil.isNetworkAvalible(mContext)){
                        Utils.showDialogWifi( (Activity) mContext);
                        return;
                    }
                    Intent it = new Intent(mContext, ChatLiveActivity.class);
                    it.putExtra("strRoomId","45447918977025");
					it.putExtra("strLiveUrl","rtmp://live.hkstv.hk.lxdns.com/live/hks");
                    mContext.startActivity(it);
                }
            }
        });
    }

}
