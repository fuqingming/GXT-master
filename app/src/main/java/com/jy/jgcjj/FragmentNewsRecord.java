package com.jy.jgcjj;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.NewsRecordAdapter;
import com.jy.jgcjj.base.BaseListFragment;
import com.jy.jgcjj.bean.base.RecordBean;
import com.jy.jgcjj.bean.base.TeacherAnalysisBean;
import com.jy.jgcjj.view.recyclerview.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *  on 5/18/16.
 */
public class FragmentNewsRecord extends BaseListFragment<RecordBean> {

    Unbinder unbinder;
    private NewsRecordAdapter m_adapterNewsAnalysisAdapter = new NewsRecordAdapter();

    @Override
    protected void initData() {
        unbinder = ButterKnife.bind(this, getContentView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_record;
    }

    @Override
    protected BaseRecyclerAdapter<RecordBean> getListAdapter() {
        return m_adapterNewsAnalysisAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(getMContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        mRecyclerView.setLoadMoreEnabled(false);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getMContext(), LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.app_backgrount_color)));
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
        List<RecordBean> arrRecordBean = new ArrayList<>();
        for(int i = 0 ; i < 8 ; i ++){
            RecordBean recordBean = new RecordBean(R.mipmap.my_head,"王新华","2018-01-02  13:08:03","王新华老师上证50指数11连阳案例",
                    "2018年1月3日，王新华老师在19:30档实战培训直播课26分53",R.mipmap.gxt_integral_pic);
            arrRecordBean.add(recordBean);
        }
        executeOnLoadDataSuccess(arrRecordBean);
        totalPage = arrRecordBean.size();
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
