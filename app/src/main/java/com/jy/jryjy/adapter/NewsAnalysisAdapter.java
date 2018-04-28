package com.jy.jryjy.adapter;


import android.text.TextPaint;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.TeacherAnalysisBean;
import com.jgcj.library.view.recyclerview.BaseRecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class NewsAnalysisAdapter extends BaseRecyclerAdapter<TeacherAnalysisBean> {

    @BindView(R.id.iv_icon)
    ImageView m_ivIcon;
    @BindView(R.id.tv_name)
    TextView m_ivName;
    @BindView(R.id.tv_time)
    TextView m_ivTime;
    @BindView(R.id.tv_title)
    TextView m_ivTitle;
    @BindView(R.id.tv_text)
    TextView m_ivText;
    @BindView(R.id.iv_type)
    ImageView m_ivType;
    public NewsAnalysisAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_news_analysis_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, TeacherAnalysisBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        Glide.with(mContext).load(data.getT_photo()).placeholder(R.mipmap.head_s).into(m_ivIcon);
        m_ivName.setText(data.getT_nic_name());
        m_ivTime.setText(data.getCreatetime());
        m_ivTitle.setText(data.getTitle());
        TextPaint paint = m_ivTitle.getPaint();
        paint.setFakeBoldText(true);
        m_ivText.setText(data.getDesc());
        if(data.getNeican_id() == TeacherAnalysisBean.NOON){
            m_ivType.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.noon_ic));
        }else if(data.getNeican_id() == TeacherAnalysisBean.NIGHT){
            m_ivType.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.night_ic));
        }
    }

}
