
package com.jy.jgcjj.adapter;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.base.LiveBeginingBean;
import com.jy.jgcjj.bean.base.LiveOverBean;
import com.jy.jgcjj.bean.base.LiveWillBean;
import com.jy.jgcjj.bean.response.ResponseFragmentLiveBean;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class FragmentLiveAdapter extends BaseRecyclerAdapter<ResponseFragmentLiveBean> {

    public static final int LIVE_OVER = 0;
    public static final int LIVE_BEGINING = 1;
    public static final int LIVE_WILL = 2;

    @BindView(R.id.recycler_view_over)
    RecyclerView m_recyclerOver;
    @BindView(R.id.recycler_view_begining)
    RecyclerView m_recyclerBegining;
    @BindView(R.id.recycler_view_will)
    RecyclerView m_recyclerVill;

    public FragmentLiveAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_fragment_live_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, final ResponseFragmentLiveBean data, final int position) {
        ButterKnife.bind(this, holder.getView());

        List<LiveOverBean> m_arrLiveOverBean = data.getLiveOverBeans();
        List<LiveBeginingBean> m_arrLiveBeginingBean = data.getLiveBeginingBeans();
        List<LiveWillBean> m_arrLiveWillBean = data.getLiveWillBeans();

        LiveOverAdapter m_adapterLive = new LiveOverAdapter(mContext, m_arrLiveOverBean);
        m_recyclerOver.setLayoutManager(new LinearLayoutManager(mContext));
        m_recyclerOver.setHasFixedSize(true);
        m_recyclerOver.setAdapter(m_adapterLive);
        m_recyclerOver.setNestedScrollingEnabled(false);



        LiveBeginingAdapter m_adapterVedio = new LiveBeginingAdapter(mContext, m_arrLiveBeginingBean);
        m_recyclerBegining.setLayoutManager(new LinearLayoutManager(mContext));
        m_recyclerBegining.setHasFixedSize(true);
        m_recyclerBegining.setAdapter(m_adapterVedio);
        m_recyclerBegining.setNestedScrollingEnabled(false);
        m_adapterVedio.onSelectFragmentClickListener(new VedioAdapter.OnSelectClickListener()
        {
            @Override
            public void OnSelectClick(int position)
            {
                Bundle bundle = new Bundle();
                bundle.putInt("iType",LIVE_BEGINING);
                bundle.putInt("position",position);
                m_ListenerSelectFragment.OnSelectClick(bundle);
            }
        });

        LiveWillAdapter m_adapterPlayback = new LiveWillAdapter(mContext, m_arrLiveWillBean);
        m_recyclerVill.setLayoutManager(new LinearLayoutManager(mContext));
        m_recyclerVill.setHasFixedSize(true);
        m_recyclerVill.setAdapter(m_adapterPlayback);
        m_recyclerVill.setNestedScrollingEnabled(false);
        m_adapterPlayback.onSelectFragmentClickListener(new InformationAdapter.OnSelectClickListener()
        {

            @Override
            public void OnSelectClick(int position)
            {
                Bundle bundle = new Bundle();
                bundle.putInt("iType",LIVE_WILL);
                bundle.putInt("position",position);
                m_ListenerSelectFragment.OnSelectClick(bundle);
            }
        });
    }

}