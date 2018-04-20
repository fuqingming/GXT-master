package com.jy.jgcjj;

import android.annotation.SuppressLint;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.util.Utils;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.TeacherListDetailsAdapter;
import com.jy.jgcjj.base.BaseListActivity;
import com.jy.jgcjj.bean.base.TeacherDetailsBean;
import com.jy.jgcjj.util.DataUtil;
import java.util.List;

@SuppressLint("Registered")
public class TeacherListDetailsActivity extends BaseListActivity<TeacherDetailsBean> {

    private String m_strTeacherId;

    private TeacherListDetailsAdapter teacherListDetailsAdapter = new TeacherListDetailsAdapter();

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_list_details;
    }

    @Override
    protected BaseRecyclerAdapter<TeacherDetailsBean> getListAdapter() {
        return teacherListDetailsAdapter;
    }

    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLoadMoreEnabled(false);
        View header = LayoutInflater.from(this).inflate(R.layout.common_teacher_list_head,mRecyclerView, false);
        mRecyclerViewAdapter.addHeaderView(header);
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
        List<TeacherDetailsBean> videoPlayBackBeans = DataUtil.initTeacherDetails();
        executeOnLoadDataSuccess(videoPlayBackBeans);
        totalPage = videoPlayBackBeans.size();
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
