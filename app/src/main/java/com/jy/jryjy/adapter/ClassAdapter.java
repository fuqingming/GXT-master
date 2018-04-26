package com.jy.jryjy.adapter;


import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.ClassBean;
import com.jgcj.library.view.recyclerview.BaseRecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class ClassAdapter extends BaseRecyclerAdapter<ClassBean> {

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

    public ClassAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_teacher_details_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder,ClassBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        Glide.with(mContext).load(data.getC_photo()).placeholder(R.mipmap.head_s).into(m_ivPic);
        m_ivName.setText(data.getT_nic_name());
        m_ivTime.setText(data.getM_time());
        m_ivTitle.setText(data.getM_title());
        m_llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(mContext, ClassDetailsActivity.class);
//                mContext.startActivity(it);
            }
        });
    }

}
