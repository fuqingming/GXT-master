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
import com.jy.jryjy.adapter.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.NewsInformationAdapter;
import com.jy.jryjy.base.BaseListFragment;
import com.jy.jryjy.bean.base.TeacherAnalysisBean;
import com.jy.jryjy.bean.response.ResponseNewsAnalysisBean;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by HaPBoy on 5/18/16.
 */
public class FragmentNewsInformation extends BaseListFragment<TeacherAnalysisBean> {

    Unbinder unbinder;

    private NewsInformationAdapter m_adapterNewsAnalysisAdapter = new NewsInformationAdapter();

    @Override
    protected void initData() {
        unbinder = ButterKnife.bind(this, getContentView());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_information;
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
        HttpClient.get(ApiStores.more+"?cate_id=3", new HttpCallback<ResponseNewsAnalysisBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseNewsAnalysisBean response) {
                if(response.getResult()){
                    List<TeacherAnalysisBean> responseFragmentHallBeen = new ArrayList<>();
                    responseFragmentHallBeen.addAll(response.getContent().getJuemi());
                    executeOnLoadDataSuccess(responseFragmentHallBeen);
                    totalPage = responseFragmentHallBeen.size();
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
