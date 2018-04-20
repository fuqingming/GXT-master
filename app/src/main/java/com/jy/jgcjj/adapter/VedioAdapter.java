package com.jy.jgcjj.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewAdapter;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;
import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.base.LiveVedioBean;
import java.util.List;
import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class VedioAdapter extends BaseRecyclerViewAdapter<LiveVedioBean> {

    @BindView(R.id.iv_pic)
    ImageView m_ivPic;
    @BindView(R.id.tv_title)
    TextView m_tvTitle;
    @BindView(R.id.tv_name)
    TextView m_tvName;
    @BindView(R.id.tv_text)
    TextView m_tvText;
    @BindView(R.id.tv_time)
    TextView m_tvTime;
    @BindView(R.id.ll_item_click)
    LinearLayout m_llItemClick;
    @BindView(R.id.v_line)
    View m_vLine;

    public VedioAdapter(Context context, List<LiveVedioBean> datas) {
        super(context, datas);
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_vedio;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, LiveVedioBean data, final int position) {

        m_ivPic.setImageResource(data.getPic());
        m_tvTitle.setText(data.getTitle());
        m_tvName.setText(data.getName());
        m_tvText.setText(data.getText());
        m_tvTime.setText(data.getTime());

        m_llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_ListenerSelectFragment.OnSelectClick(position);
            }
        });

        if(position == getItemCount() - 1){
            m_vLine.setVisibility(View.GONE);
        }
    }

}
