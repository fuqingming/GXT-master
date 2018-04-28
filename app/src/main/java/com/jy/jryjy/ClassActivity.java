package com.jy.jryjy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Utils;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;
import com.jgcj.library.util.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.ClassAdapter;
import com.jy.jryjy.adapter.ClassTypeCheckedAdapter;
import com.jgcj.library.base.BasePopListActivity;
import com.jy.jryjy.bean.base.BannerBean;
import com.jy.jryjy.bean.base.ClassBean;
import com.jy.jryjy.bean.response.ResponseClassBean;
import com.jy.jryjy.util.DataUtil;
import com.jgcj.library.view.error.ErrorLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressLint("Registered")
public class ClassActivity extends BasePopListActivity<ClassBean> {

    private ClassAdapter teacherListDetailsAdapter = new ClassAdapter();

    private LinearLayoutManager m_linearLayoutManager;

    private ImageView m_ivBanner;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_class;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this, "直播回放",true);
    }

    @Override
    protected BaseRecyclerAdapter<ClassBean> getListAdapter() {
        return teacherListDetailsAdapter;
    }

    @Override
    protected void initLayoutManager() {
        m_linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        mRecyclerView.setLoadMoreEnabled(true);
        View m_headerBanner = LayoutInflater.from(this).inflate(R.layout.common_class_head,mRecyclerView, false);
        m_ivBanner = m_headerBanner.findViewById(R.id.banner_pager);
        mRecyclerViewAdapter.addHeaderView(m_headerBanner);
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
                Intent it = new Intent(ClassActivity.this, LivePlaybackActivity.class);
                it.putExtra("strPlayUrl",teacherListDetailsAdapter.getListData().get(position).getVideo_url());
                startActivity(it);
            }

        });

    }

    @Override
    protected BaseAdapter setInitAdapter() {
        return new ClassTypeCheckedAdapter(this,DataUtil.initVideoPlayBackBean());
    }
    
    protected void requestData(){

        HttpClient.get(ApiStores.getafterClass, new HttpCallback<ResponseClassBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseClassBean response) {
				if(response.getResult()){
					if(response.getContent().getBanner().size() > 0){
                        Glide.with(ClassActivity.this).load(response.getContent().getBanner().get(0).getB_link()).into(m_ivBanner);
					}else{
                        m_ivBanner.setVisibility(View.GONE);
                    }

                    List<ClassBean> videoPlayBackBeans = new ArrayList<>();
                    videoPlayBackBeans.addAll(response.getContent().getTrailer_after());
                    if(videoPlayBackBeans.size() > 0){
                        executeOnLoadDataSuccess(videoPlayBackBeans);
                    }else{
                        if(response.getContent().getBanner().size() > 0){
                            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
                        }else{
                            executeOnLoadDataSuccess(videoPlayBackBeans);
                        }
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
