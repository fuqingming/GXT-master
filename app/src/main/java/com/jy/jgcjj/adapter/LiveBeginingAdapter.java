package com.jy.jgcjj.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.base.LiveBeginingBean;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewAdapter;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class LiveBeginingAdapter extends BaseRecyclerViewAdapter<LiveBeginingBean> {

    @BindView(R.id.tv_time)
    TextView tvTime;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_type)
    TextView tvType;
    @BindView(R.id.iv_icon)
    ImageView ivIcon;
    @BindView(R.id.tv_team_t_name)
    TextView tvTeamTName;
    @BindView(R.id.tv_text)
    TextView tvText;

    @BindView(R.id.view_height)
    View vHeight;

    @BindView(R.id.ll_item_click)
    RelativeLayout m_llItemClick;

    public LiveBeginingAdapter(Context context, List<LiveBeginingBean> datas) {
        super(context, datas);
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_live_begining;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, LiveBeginingBean data, final int position) {

        tvTime.setText(data.getTime());
        tvName.setText(data.getName());
        tvTitle.setText(data.getTitle());
        tvType.setText(data.getType());
        ivIcon.setImageResource(data.getIcon());
        tvTeamTName.setText(data.getTeamName());
        tvText.setText(data.getText());

        vHeight.post(new Runnable() {
            @Override
            public void run() {
                int height = m_llItemClick.getMeasuredHeight();

                ViewGroup.LayoutParams tvShowAllPara = vHeight.getLayoutParams();
                tvShowAllPara.height = height;
                vHeight.setLayoutParams(tvShowAllPara);
            }
        });

        m_llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_ListenerSelectFragment.OnSelectClick(position);
            }
        });
    }

}
