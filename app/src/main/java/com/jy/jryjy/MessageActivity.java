package com.jy.jryjy;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Utils;
import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.MessageAdapter;
import com.jgcj.library.base.BaseListActivity;
import com.jy.jryjy.bean.base.MessageBean;
import com.jy.jryjy.bean.response.ResponseMessageBean;
import com.jgcj.library.util.alert.AlertUtils;

public class MessageActivity extends BaseListActivity<MessageBean> {

    private MessageAdapter m_adapterMessage = new MessageAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_message;
    }

    @Override
    public void initView() {
        super.initView();
        Utils.initCommonTitle(this,"我的消息",true);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected BaseRecyclerAdapter<MessageBean> getListAdapter() {
        return m_adapterMessage;
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

        mRecyclerViewAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {

            }

        });
    }

    protected void requestData(){
        String urlDataString = "?begin="+mCurrentPage;
        HttpClient.get(ApiStores.my_Notice + urlDataString, new HttpCallback<ResponseMessageBean>() {
            @Override
            public void OnSuccess(ResponseMessageBean response) {
                executeOnLoadDataSuccess(response.getContent());
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

    private void messageCenter(String title,String message){
        AlertUtils.MessageAlertShow(this, title, message);
    }
}
