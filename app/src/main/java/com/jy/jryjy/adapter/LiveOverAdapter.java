package com.jy.jryjy.adapter;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.LiveOverBean;
import com.jgcj.library.view.recyclerview.BaseRecyclerViewAdapter;
import com.jgcj.library.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class LiveOverAdapter extends BaseRecyclerViewAdapter<LiveOverBean> {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.ll_item_click)
    RelativeLayout m_llItemClick;

    public LiveOverAdapter(Context context, List<LiveOverBean> datas) {
        super(context, datas);
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_live_over;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, LiveOverBean data, final int position) {
        tvTime.setText(data.getTime());
        tvName.setText(data.getName());
        tvTitle.setText(data.getTitle());
        tvType.setText(data.getType());

        m_llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_ListenerSelectFragment.OnSelectClick(position);
            }
        });
    }

}
