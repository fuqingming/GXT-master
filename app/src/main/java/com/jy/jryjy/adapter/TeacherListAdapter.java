package com.jy.jryjy.adapter;


import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jy.jryjy.R;
import com.jy.jryjy.TeacherListDetailsActivity;
import com.jy.jryjy.bean.base.TeacherListBean;
import com.jgcj.library.view.recyclerview.BaseRecyclerViewHolder;

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
    protected void covert(BaseRecyclerViewHolder holder,final TeacherListBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        Glide.with(mContext).load(data.getT_photo()).placeholder(R.mipmap.head_s).into(m_ivIcon);
        m_tvName.setText(data.getT_nic_name());
//        m_tvClassCount.setText(data.getClassCount());
//        m_tvPersons.setText(data.getPersons());
//        m_tvText.setText(data.getM_content());
        m_llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(mContext, TeacherListDetailsActivity.class);
                it.putExtra("strTID",data.getT_id());
                mContext.startActivity(it);
            }
        });
    }

}
