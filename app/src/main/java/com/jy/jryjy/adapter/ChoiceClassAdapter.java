package com.jy.jryjy.adapter;


import android.widget.ImageView;
import android.widget.TextView;

import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.VideoPlayBackBean;
import com.jy.jryjy.view.recyclerview.BaseRecyclerViewHolder;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class ChoiceClassAdapter extends BaseRecyclerAdapter<VideoPlayBackBean> {

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

    public ChoiceClassAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_choice_class;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, VideoPlayBackBean data, int position) {

//        Glide.with(mContext).load(data.getT_photo()).placeholder(R.mipmap.head_s).into(m_ivIcon);
        m_ivTitle.setText(data.getTitle());
        m_ivName.setText(data.getName());
        m_ivText.setText(data.getText());
        m_ivTime.setText(data.getTime());
    }

}
