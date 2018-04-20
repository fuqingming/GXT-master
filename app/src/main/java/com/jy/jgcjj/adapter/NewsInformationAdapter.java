package com.jy.jgcjj.adapter;


import android.content.Context;
import android.text.TextPaint;
import android.widget.ImageView;
import android.widget.TextView;

import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.base.GXInformationBean;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class NewsInformationAdapter extends BaseRecyclerAdapter<GXInformationBean> {

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
    protected void covert(BaseRecyclerViewHolder holder, GXInformationBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        m_ivTitle.setText(data.getTitle());
        m_ivTime.setText(data.getTime());
        m_ivPic.setImageResource(data.getPic());
        TextPaint paint = m_ivTitle.getPaint();
        paint.setFakeBoldText(true);
    }

}
