package com.jy.jryjy;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jgcj.library.util.Utils;
import com.jgcj.library.view.error.ErrorLayout;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;
import com.jy.jryjy.adapter.NewsUniversityAdapter;
import com.jgcj.library.base.BaseListFragment;
import com.jy.jryjy.bean.base.TeacherAnalysisBean;
import com.jy.jryjy.bean.response.ResponseNewsAnalysisBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HaPBoy on 5/18/16.
 */
public class FragmentNewsUniversity extends BaseListFragment<TeacherAnalysisBean> {

    private NewsUniversityAdapter m_adapterNewsAnalysisAdapter = new NewsUniversityAdapter();

    private BannerPager m_bpBanner;
    private List<TeacherAnalysisBean> m_bannerBean;
    private ArrayList<String> m_arrBanner;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_university;
    }

    @Override
    protected void initData() {
        super.initData();
        m_bannerBean = new ArrayList<>();
        m_arrBanner = new ArrayList<>();
    }

    @Override
    protected BaseRecyclerAdapter<TeacherAnalysisBean> getListAdapter() {
        return m_adapterNewsAnalysisAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(getMContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        View m_headerBanner = LayoutInflater.from(getMContext()).inflate(R.layout.common_news_market,mRecyclerView, false);
        m_bpBanner = m_headerBanner.findViewById(R.id.banner_pager);
        mRecyclerViewAdapter.addHeaderView(m_headerBanner);
        mRecyclerView.setLoadMoreEnabled(true);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshView();
            }
        });

        mRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {

                if ( REQUEST_COUNT <= totalPage) {
                    mCurrentPage++;
                    requestData();
                    isRequestInProcess = true;
                } else {
                    mRecyclerView.setNoMore(true);
                }
            }
        });

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent it = new Intent(getMContext(),NewsWebViewActivity.class);
                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
                startActivity(it);
            }

        });

    }

    private void initBanner() {

        final PagerOptions pagerOptions = new PagerOptions.Builder(getMContext())
                .setTurnDuration(2000)
                .setIndicatorSize(Utils.dp2px(getMContext(),6))
                .setIndicatorColor(getMContext().getResources().getColor(R.color.dark),getMContext().getResources().getColor(R.color.red) )
                .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
                .setIndicatorMarginBottom(Utils.dp2px(getMContext(),15))
                .build();

        m_bpBanner.setPagerOptions(pagerOptions).setPages(m_arrBanner, new ViewHolderCreator<BannerPagerHolder>() {
            @Override
            public BannerPagerHolder createViewHolder() {
                final View view = LayoutInflater.from(getMContext()).inflate(R.layout.item_image_banner_analysis, null);
                return new FragmentNewsUniversity.BannerPagerHolder(view);
            }
        });
        m_bpBanner.startTurning();
        m_bpBanner.setOnItemClickListener(new com.joker.pager.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int location, int position) {
                if(m_bannerBean.get(position).getDetail_url() != null && !"".equals(m_bannerBean.get(position).getDetail_url())){
                    Intent it = new Intent(getMContext(),NewsWebViewActivity.class);
                    it.putExtra("webViewUrl",m_bannerBean.get(position).getDetail_url());
                    startActivity(it);
                }
            }
        });
    }

    private class BannerPagerHolder extends ViewHolder<String> {

        private ImageView mImage;
        private TextView mText;

        private BannerPagerHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mText = itemView.findViewById(R.id.text);
        }

        @Override
        public void onBindView(View view, String data, int position) {
            Glide.with(mImage.getContext())
                    .load(data)
                    .into(mImage);
            mText.setText(m_bannerBean.get(position).getTitle());
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(m_bpBanner!=null){
            m_bpBanner.startTurning();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(m_bpBanner!=null){
            m_bpBanner.stopTurning();
        }
    }

    protected void requestData(){
        HttpClient.get(ApiStores.more+"?cate_id=4&page="+mCurrentPage, new HttpCallback<ResponseNewsAnalysisBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseNewsAnalysisBean response) {
                if(m_bannerBean.size() > 0 ){
                    m_bannerBean.clear();
                    m_arrBanner.clear();
                }
                m_bannerBean.addAll(response.getContent().getJuemituijian().getData());
                for(int i = 0 ; i < m_bannerBean.size() ; i ++){
                    m_arrBanner.add(m_bannerBean.get(i).getN_photo());
                }
                if(m_bannerBean.size()>0){
                    initBanner();
                }

                List<TeacherAnalysisBean> responseFragmentHallBeen = new ArrayList<>();
                responseFragmentHallBeen.addAll(response.getContent().getJuemi().getData());
                if(responseFragmentHallBeen.size() > 0){
                    executeOnLoadDataSuccess(responseFragmentHallBeen);
                }else{
                    if(m_arrBanner.size() > 0){
                        mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
                    }else{
                        executeOnLoadDataSuccess(responseFragmentHallBeen);
                    }
                }
            }

            @Override
            public void OnFailure(String message) {
                executeOnLoadDataError(null);
            }

            @Override
            public void OnRequestStart() {
            }

            @Override
            public void OnRequestFinish() {
                executeOnLoadFinish();
            }
        });
    }

}
