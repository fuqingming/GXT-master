package com.jy.jryjy.adapter;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jy.jryjy.ChoiceClassActivity;
import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.VideoPlayBackBean;
import com.jgcj.library.view.recyclerview.BaseRecyclerViewHolder;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class LivePlayBackAdapter extends BaseRecyclerAdapter<VideoPlayBackBean> {

    @BindView(R.id.iv_pic)
    ImageView m_ivPic;
    @BindView(R.id.tv_title)
    TextView m_ivTitle;
    @BindView(R.id.tv_name)
    TextView m_ivName;
    @BindView(R.id.tv_text)
    TextView m_ivText;
    @BindView(R.id.tv_time)
    TextView m_ivTime;
    @BindView(R.id.v_view)
    View m_vView;
    @BindView(R.id.ll_item_click)
    LinearLayout llItemClick;

    public LivePlayBackAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_live_play_back;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, VideoPlayBackBean data, int position) {

        if(position == 0){
            m_vView.setVisibility(View.GONE);
        }else{
            m_vView.setVisibility(View.VISIBLE);
        }
//        Glide.with(mContext).load(data.getT_photo()).placeholder(R.mipmap.head_s).into(m_ivIcon);
        m_ivTitle.setText(data.getTitle());
        m_ivName.setText(data.getName());
        m_ivText.setText(data.getText());
        m_ivTime.setText(data.getTime());

        llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext,ChoiceClassActivity.class);
                it.putExtra("strUrl","http://gxtvod.58hengku.com/gxt/record/2018-03-16/gxt/beiyong1/2018-03-16-08:47:45_2018-03-16-10:17:01.m3u8");
                mContext.startActivity(it);
            }
        });
    }

}
