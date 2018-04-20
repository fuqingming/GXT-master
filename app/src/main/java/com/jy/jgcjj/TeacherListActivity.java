package com.jy.jgcjj;

import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.util.Utils;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.TeacherListAdapter;
import com.jy.jgcjj.base.BaseListActivity;
import com.jy.jgcjj.bean.base.TeacherDetailsBean;
import com.jy.jgcjj.bean.base.TeacherListBean;
import com.jy.jgcjj.util.DataUtil;
import com.jy.jgcjj.view.recyclerview.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class TeacherListActivity extends BaseListActivity<TeacherListBean> {

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
    protected BaseRecyclerAdapter<TeacherListBean> getListAdapter() {
        return m_adapterTeacherList;
    }

    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
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
    }

    protected void requestData(){
        List<TeacherListBean> arrClassOpenBean = new ArrayList<>();
        for(int i = 0 ; i < 4 ; i ++){
            TeacherListBean classOpenBean = new TeacherListBean(R.mipmap.gxt_icon,"王新华","234节","1234人","导师详情 限制1行 左间距10pt 右至箭头10pt");
            arrClassOpenBean.add(classOpenBean);
        }
        executeOnLoadDataSuccess(arrClassOpenBean);
        totalPage = arrClassOpenBean.size();
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
