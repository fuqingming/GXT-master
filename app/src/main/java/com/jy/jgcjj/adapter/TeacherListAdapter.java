package com.jy.jgcjj.adapter;


import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.jgcjj.R;
import com.jy.jgcjj.TeacherDetailsActivity;
import com.jy.jgcjj.TeacherListDetailsActivity;
import com.jy.jgcjj.bean.base.TeacherListBean;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class TeacherListAdapter extends BaseRecyclerAdapter<TeacherListBean> {

    @BindView(R.id.iv_icon)
    ImageView m_ivIcon;
    @BindView(R.id.tv_name)
    TextView m_tvName;
    @BindView(R.id.tv_class_count)
    TextView m_tvClassCount;
    @BindView(R.id.tv_persons)
    TextView m_tvPersons;
    @BindView(R.id.tv_text)
    TextView m_tvText;
    @BindView(R.id.ll_item_click)
    LinearLayout m_llItemClick;

    public TeacherListAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_teacher_list_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, TeacherListBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        m_ivIcon.setImageResource(data.getIcon());
        m_tvName.setText(data.getName());
        m_tvClassCount.setText(data.getClassCount());
        m_tvPersons.setText(data.getPersons());
        m_tvText.setText(data.getText());
        m_llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext, TeacherListDetailsActivity.class);
                mContext.startActivity(it);
            }
        });
    }

}
