package com.jy.jgcjj.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.base.TeacherDetailsBean;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewAdapter;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class TeacherListDetailsAdapter extends BaseRecyclerAdapter<TeacherDetailsBean> {

    @BindView(R.id.iv_pic)
    ImageView m_ivPic;
    @BindView(R.id.tv_title)
    TextView m_ivTitle;
    @BindView(R.id.tv_time)
    TextView m_ivTime;
    @BindView(R.id.tv_name)
    TextView m_ivName;
    @BindView(R.id.ll_item_click)
    LinearLayout m_llItemClick;

    public TeacherListDetailsAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_teacher_details_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, TeacherDetailsBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        m_ivPic.setImageResource(data.getPic());
        m_ivName.setText(data.getName());
        m_ivTime.setText(data.getTime());
        m_ivTitle.setText(data.getTitle());
        m_llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(mContext, ClassDetailsActivity.class);
//                mContext.startActivity(it);
            }
        });
    }

}
