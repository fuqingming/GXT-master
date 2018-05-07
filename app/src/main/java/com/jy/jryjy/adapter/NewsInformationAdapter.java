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

public class NewsInformationAdapter extends BaseRecyclerAdapter<TeacherAnalysisBean> {

    @BindView(R.id.tv_title)
    TextView m_ivTitle;
    @BindView(R.id.tv_time)
    TextView m_ivTime;
    @BindView(R.id.iv_pic)
    ImageView m_ivPic;

    public NewsInformationAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_news_information_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, TeacherAnalysisBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        m_ivTitle.setText(data.getTitle());
        m_ivTime.setText(data.getCreatetime());
        Glide.with(mContext).load(data.getN_photo()).into(m_ivPic);
        TextPaint paint = m_ivTitle.getPaint();
        paint.setFakeBoldText(true);
    }

}
