package com.jy.jryjy;

import android.support.v7.widget.LinearLayoutManager;

import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Utils;
import com.jy.jryjy.adapter.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.TeacherListAdapter;
import com.jy.jryjy.base.BaseListActivity;
import com.jy.jryjy.bean.base.LiveBean;
import com.jy.jryjy.bean.response.ResponseLiveBean;

import java.util.ArrayList;
import java.util.List;

public class TeacherListActivity extends BaseListActivity<LiveBean> {

    private TeacherListAdapter m_adapterTeacherList = new TeacherListAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_list;
    }

    @Override
    public void initData() {
        Utils.initCommonTitle(this,"名师",true);
    }

    @Override
    protected BaseRecyclerAdapter<LiveBean> getListAdapter() {
        return m_adapterTeacherList;
    }

    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    }
    protected void requestData(){

        HttpClient.get(ApiStores.getAllTeacher, new HttpCallback<ResponseLiveBean>() {
            @Override
            public void OnSuccess(ResponseLiveBean response) {
                if(response.getResult()){
                    List<LiveBean> arrClassOpenBean = new ArrayList<>();
                    arrClassOpenBean.addAll(response.getContent().getTrailer_info());
                    executeOnLoadDataSuccess(arrClassOpenBean);
                    totalPage = arrClassOpenBean.size();
                    executeOnLoadFinish();
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
