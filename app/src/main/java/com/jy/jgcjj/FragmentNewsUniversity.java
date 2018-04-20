package com.jy.jgcjj;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.NewsInformationAdapter;
import com.jy.jgcjj.adapter.NewsUniversityAdapter;
import com.jy.jgcjj.base.BaseListFragment;
import com.jy.jgcjj.bean.base.GXInformationBean;
import com.jy.jgcjj.bean.base.GXUniversityBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HaPBoy on 5/18/16.
 */
public class FragmentNewsUniversity extends BaseListFragment<GXUniversityBean> {

    Unbinder unbinder;

    private NewsUniversityAdapter m_adapterNewsAnalysisAdapter = new NewsUniversityAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_university;
    }

    @Override
    protected void initData() {
        unbinder = ButterKnife.bind(this, getContentView());
    }

    @Override
    protected BaseRecyclerAdapter<GXUniversityBean> getListAdapter() {
        return m_adapterNewsAnalysisAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(getMContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        mRecyclerView.setLoadMoreEnabled(false);
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

            }

        });

    }

    protected void requestData(){
        List<GXUniversityBean> arrGXUniversityBeans = new ArrayList<>();
        for(int i = 0 ; i < 8 ; i ++){
            GXUniversityBean gxUniversityBean = new GXUniversityBean("2018有气体制改革深化年：改革意见细则或于上半年下发","2018-01-02  13:08:03",R.mipmap.gxt_class_icon);
            arrGXUniversityBeans.add(gxUniversityBean);
        }
        executeOnLoadDataSuccess(arrGXUniversityBeans);
        totalPage = arrGXUniversityBeans.size();
        executeOnLoadFinish();
//        HttpClient.get(ApiStores.banner, new HttpCallback<ResponseBaseBean>() {//ResponseHallBean
//            @Override
//            public void OnSuccess(ResponseBaseBean response) {
////				if(response.getResult()){
////					if(m_bannerBean.size() > 0 ){
////						m_bannerBean.clear();
////						m_arrBanner.clear();
////					}
////					m_bannerBean.addAll(response.getContent().getBanner());
////					for(int i = 0 ; i < m_bannerBean.size() ; i ++){
////						m_arrBanner.add(m_bannerBean.get(i).getB_link());
////					}
////					initBanner();
////				}
//            }
//
//            @Override
//            public void OnFailure(String message) {
//                executeOnLoadDataError(null);
//            }
//
//            @Override
//            public void OnRequestStart() {
//            }
//
//            @Override
//            public void OnRequestFinish() {
//                executeOnLoadFinish();
//            }
//        });
    }

}
