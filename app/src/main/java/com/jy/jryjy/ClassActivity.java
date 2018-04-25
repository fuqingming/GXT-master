package com.jy.jryjy;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
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
import com.jy.jryjy.adapter.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.ClassAdapter;
import com.jy.jryjy.adapter.TeacherListDetailsAdapter;
import com.jy.jryjy.adapter.ClassTypeCheckedAdapter;
import com.jy.jryjy.base.BasePopListActivity;
import com.jy.jryjy.bean.base.BannerBean;
import com.jy.jryjy.bean.base.ClassBean;
import com.jy.jryjy.bean.base.TeacherDetailsBean;
import com.jy.jryjy.bean.response.ResponseBaseBean;
import com.jy.jryjy.bean.response.ResponseClassBean;
import com.jy.jryjy.util.DataUtil;
import com.jy.jryjy.view.error.ErrorLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

@SuppressLint("Registered")
public class ClassActivity extends BasePopListActivity<ClassBean> {

    private ClassAdapter teacherListDetailsAdapter = new ClassAdapter();

    private String m_strTeacherId;
    private BannerPager m_bpBanner;
    private List<BannerBean> m_bannerBean;
    private ArrayList<String> m_arrBanner;
    private View m_headerBanner;

    private LinearLayoutManager m_linearLayoutManager;

    private CheckBox m_ckTeacher;
    private CheckBox m_cbType;
    private CheckBox m_cbClass;

    @BindView(R.id.cb_teacher)
    CheckBox m_ckTeacherTop;
    @BindView(R.id.cb_type)
    CheckBox m_cbTypeTop;
    @BindView(R.id.cb_class)
    CheckBox m_cbClassTop;

    @BindView(R.id.arrow_bottom)
    LinearLayout m_vArrow;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_class;
    }

    @Override
    protected void initData() {
        Utils.initCommonTitle(this, "直播回放",true);
        m_bannerBean = new ArrayList<>();
        m_arrBanner = new ArrayList<>();
    }

    @Override
    public void initView() {
        super.initView();
        m_ckTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                MoveToPosition();
                filterTabToggleT(isChecked, m_ckTeacher, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                    }
                }, m_ckTeacher, m_cbType,m_cbClass);
            }
        });
        m_cbType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                MoveToPosition();
                filterTabToggleT(isChecked, m_cbType, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                    }
                }, m_cbType, m_ckTeacher,m_cbClass);
            }
        });
        m_cbClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                MoveToPosition();
                filterTabToggleT(isChecked, m_cbType, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                    }
                }, m_cbClass,m_cbType, m_ckTeacher);
            }
        });

        m_ckTeacherTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggleT(isChecked, m_ckTeacherTop, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                    }
                }, m_ckTeacherTop, m_cbTypeTop,m_cbClassTop);
            }
        });
        m_cbTypeTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggleT(isChecked, m_cbTypeTop, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                    }
                }, m_cbTypeTop, m_ckTeacherTop,m_cbClassTop);
            }
        });
        m_cbClassTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggleT(isChecked, m_cbClassTop, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        hidePopListView();
                    }
                }, m_cbClassTop, m_ckTeacherTop,m_cbTypeTop);
            }
        });
    }

    private void MoveToPosition() {
//        View itemView = m_linearLayoutManager.findViewByPosition(1);
//        if (itemView != null) {
//            if (-itemView.getTop() < m_headerBanner.getHeight() - m_vArrow.getHeight()) {
//                mRecyclerView.scrollBy(0,Utils.dp2px(ClassActivity.this,164)+itemView.getTop());
//            }
//        }
    }

    private void onScrollChangedListen(){
//        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
//            @Override
//            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
//                super.onScrolled(recyclerView, dx, dy);
//                View itemView = m_linearLayoutManager.findViewByPosition(1);
//                if (itemView != null) {
//                    if (-itemView.getTop() <= Utils.dp2px(ClassActivity.this,164)) {
//                        m_vArrow.setVisibility(View.GONE);
//                    } else {
//
//                        m_vArrow.setVisibility(View.VISIBLE);
//                    }
//                }else{
//                    m_vArrow.setVisibility(View.VISIBLE);
//                }
//            }
//        });

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
        m_headerBanner = LayoutInflater.from(this).inflate(R.layout.common_class_head,mRecyclerView, false);
        m_bpBanner = m_headerBanner.findViewById(R.id.banner_pager);
        m_ckTeacher = m_headerBanner.findViewById(R.id.cb_teacher);
        m_cbType = m_headerBanner.findViewById(R.id.cb_type);
        m_cbClass = m_headerBanner.findViewById(R.id.cb_class);
        mRecyclerViewAdapter.addHeaderView(m_headerBanner);
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                onRefreshView();
            }
        });
        onScrollChangedListen();

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

    private void initBanner() {

        final PagerOptions pagerOptions = new PagerOptions.Builder(this)
                .setTurnDuration(2000)
                .setIndicatorSize(Utils.dp2px(this,6))
                .setIndicatorColor(this.getResources().getColor(R.color.dark),this.getResources().getColor(R.color.red) )
                .setIndicatorAlign(RelativeLayout.CENTER_HORIZONTAL)
                .setIndicatorMarginBottom(15)
                .build();

        m_bpBanner.setPagerOptions(pagerOptions).setPages(m_arrBanner, new ViewHolderCreator<BannerPagerHolder>() {
            @Override
            public BannerPagerHolder createViewHolder() {
                final View view = LayoutInflater.from(ClassActivity.this).inflate(R.layout.item_image_banner_class, null);
                return new BannerPagerHolder(view);
            }
        });
        m_bpBanner.startTurning();
        m_bpBanner.setOnItemClickListener(new com.joker.pager.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int location, int position) {
//                if(m_bannerBean.get(position).getB_turn_link() != null && !"".equals(m_bannerBean.get(position).getB_turn_link())){
//                    Intent intent = new Intent();
//                    intent.setAction("android.intent.action.VIEW");
//                    Uri content_url = Uri.parse(m_bannerBean.get(position).getB_turn_link());
//                    intent.setData(content_url);
//                    startActivity(intent);
//                }
                if(m_bannerBean.get(position).getB_turn_link() != null && !"".equals(m_bannerBean.get(position).getB_turn_link())){
                    Intent it = new Intent(ClassActivity.this, LivePlaybackActivity.class);
                    it.putExtra("strPlayUrl",m_bannerBean.get(position).getB_turn_link());
                    startActivity(it);
                }

            }
        });
    }

    private class BannerPagerHolder extends ViewHolder<String> {

        private ImageView mImage;

        private BannerPagerHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
        }

        @Override
        public void onBindView(View view, String data, int position) {
            Glide.with(mImage.getContext())
                    .load(data)
                    .into(mImage);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if(m_bpBanner!=null){
            m_bpBanner.startTurning();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if(m_bpBanner!=null){
            m_bpBanner.stopTurning();
        }
    }

    protected void requestData(){

        HttpClient.get(ApiStores.getafterClass, new HttpCallback<ResponseClassBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseClassBean response) {
				if(response.getResult()){
					if(m_bannerBean.size() > 0 ){
						m_bannerBean.clear();
						m_arrBanner.clear();
					}
					m_bannerBean.addAll(response.getContent().getBanner());
					for(int i = 0 ; i < m_bannerBean.size() ; i ++){
						m_arrBanner.add(m_bannerBean.get(i).getB_link());
					}
					initBanner();

                    List<ClassBean> videoPlayBackBeans = new ArrayList<>();
                    videoPlayBackBeans.addAll(response.getContent().getTrailer_after());
                    if(videoPlayBackBeans.size() > 0){
                        executeOnLoadDataSuccess(videoPlayBackBeans);
                    }else{
                        if(m_arrBanner.size() > 0){
                            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
                        }else{
                            executeOnLoadDataSuccess(videoPlayBackBeans);
                        }
                    }
                    totalPage = videoPlayBackBeans.size();
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