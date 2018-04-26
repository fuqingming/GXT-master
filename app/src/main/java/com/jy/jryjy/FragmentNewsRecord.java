package com.jy.jryjy;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.NewsRecordAdapter;
import com.jgcj.library.base.BaseListFragment;
import com.jy.jryjy.bean.base.TeacherAnalysisBean;
import com.jy.jryjy.bean.response.ResponseNewsAnalysisBean;
import com.jgcj.library.view.recyclerview.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *  on 5/18/16.
 */
public class FragmentNewsRecord extends BaseListFragment<TeacherAnalysisBean> {

    private NewsRecordAdapter m_adapterNewsAnalysisAdapter = new NewsRecordAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_record;
    }

    @Override
    protected BaseRecyclerAdapter<TeacherAnalysisBean> getListAdapter() {
        return m_adapterNewsAnalysisAdapter;
    }

    @Override
    protected void initLayoutManager() {
        LinearLayoutManager m_linearLayoutManager = new LinearLayoutManager(getMContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        mRecyclerView.setLoadMoreEnabled(true);
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
                Intent it = new Intent(getMContext(),NewsWebViewActivity.class);
                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
                startActivity(it);
            }

        });

    }

    protected void requestData(){
        HttpClient.get(ApiStores.more+"?cate_id=2&page="+mCurrentPage, new HttpCallback<ResponseNewsAnalysisBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseNewsAnalysisBean response) {
                if(response.getResult()){
                    List<TeacherAnalysisBean> responseFragmentHallBeen = new ArrayList<>();
                    responseFragmentHallBeen.addAll(response.getContent().getJuemi().getData());
                    executeOnLoadDataSuccess(responseFragmentHallBeen);
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
