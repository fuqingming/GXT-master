package com.jy.jgcjj.adapter;


import android.content.Context;
import android.text.TextPaint;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.base.TeacherAnalysisBean;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewAdapter;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

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
        m_ivIcon.setImageResource(data.getIcon());
        m_ivName.setText(data.getName());
        m_ivTime.setText(data.getTime());
        m_ivTitle.setText(data.getTitle());
        TextPaint paint = m_ivTitle.getPaint();
        paint.setFakeBoldText(true);
        m_ivText.setText(data.getTitleText());
        if(data.getType() == TeacherAnalysisBean.MORNING){
            m_ivType.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.news_zp));
        }else if(data.getType() == TeacherAnalysisBean.NOON){
            m_ivType.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.news_wsjp));
        }else if(data.getType() == TeacherAnalysisBean.NIGHT){
            m_ivType.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.news_ws));
        }else if(data.getType() == TeacherAnalysisBean.WEEK){
            m_ivType.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.news_zqs));
        }else if(data.getType() == TeacherAnalysisBean.MONTH){
            m_ivType.setImageDrawable(mContext.getResources().getDrawable(R.mipmap.news_yqs));
        }
    }

}
