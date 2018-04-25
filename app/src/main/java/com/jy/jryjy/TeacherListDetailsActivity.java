package com.jy.jryjy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jy.jryjy.adapter.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.TeacherListDetailsAdapter;
import com.jy.jryjy.base.BaseListActivity;
import com.jy.jryjy.bean.response.ResponseTeacherDetailsBean;
import java.util.ArrayList;
import java.util.List;

@SuppressLint("Registered")
public class TeacherListDetailsActivity extends BaseListActivity<ResponseTeacherDetailsBean.TeacherDetail> {

    private String m_strTeacherId;

    private TeacherListDetailsAdapter teacherListDetailsAdapter = new TeacherListDetailsAdapter();

    private ImageView iv_icon;
    private TextView tv_name;
    private TextView tv_name_title;
    private TextView iv_text;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_teacher_list_details;
    }

    @Override
    protected BaseRecyclerAdapter<ResponseTeacherDetailsBean.TeacherDetail> getListAdapter() {
        return teacherListDetailsAdapter;
    }

    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLoadMoreEnabled(true);
        View header = LayoutInflater.from(this).inflate(R.layout.common_teacher_list_head,mRecyclerView, false);
        iv_icon = header.findViewById(R.id.iv_icon);
        tv_name = header.findViewById(R.id.tv_name);
         tv_name_title = header.findViewById(R.id.tv_name_title);
         iv_text = header.findViewById(R.id.iv_text);


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
                Intent it = new Intent(TeacherListDetailsActivity.this, LivePlaybackActivity.class);
                it.putExtra("strPlayUrl",teacherListDetailsAdapter.getListData().get(position).getVideo_url());
                startActivity(it);
            }

        });

    }

    protected void requestData(){

        String url = "?t_id="+getIntent().getStringExtra("strTID");
        HttpClient.get(ApiStores.getTeacherDetail+url, new HttpCallback<ResponseTeacherDetailsBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseTeacherDetailsBean response) {
				if(response.getResult()){
                    Glide.with(TeacherListDetailsActivity.this).load(response.getContent().getTeacher().getT_photo()).placeholder(R.mipmap.head_s).into(iv_icon);
                    tv_name.setText( response.getContent().getTeacher().getT_nic_name());
                    tv_name_title.setText( response.getContent().getTeacher().getT_strategy());
                    iv_text.setText( response.getContent().getTeacher().getT_brief());
                    List<ResponseTeacherDetailsBean.TeacherDetail> TeacherDetail = new ArrayList<>();
                    TeacherDetail.addAll(response.getContent().getTeacherDetail());
                    executeOnLoadDataSuccess(TeacherDetail);
                    totalPage = TeacherDetail.size();
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
