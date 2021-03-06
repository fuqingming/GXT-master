package com.jy.jryjy.adapter;


import android.widget.TextView;

import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.MessageBean;
import com.jgcj.library.view.recyclerview.BaseRecyclerViewHolder;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class MessageAdapter extends BaseRecyclerAdapter<MessageBean> {

    @BindView(R.id.tv_title)
    TextView m_ivTitle;
    @BindView(R.id.tv_time)
    TextView m_ivTime;
    @BindView(R.id.tv_text)
    TextView m_ivText;

    public MessageAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_message_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, MessageBean data, int position) {
        ButterKnife.bind(this, holder.getView());
        m_ivTitle.setText(data.getN_title());
        m_ivTime.setText(data.getN_time());
        m_ivText.setText(data.getN_content());
    }

}
