package com.jy.jryjy;

import android.content.Intent;
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
import android.widget.TextView;

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
import com.jy.jryjy.adapter.NewsAnalysisAdapter;
import com.jy.jryjy.adapter.VideoTypeCheckedAdapter;
import com.jgcj.library.base.BasePopListFragment;
import com.jy.jryjy.bean.base.TeacherAnalysisBean;
import com.jy.jryjy.bean.response.ResponseNewsAnalysisBean;
import com.jy.jryjy.bean.response.ResponseTeacherTypeBean;
import com.jgcj.library.view.error.ErrorLayout;
import com.jgcj.library.view.recyclerview.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 2/8/18.
 */
public class FragmentNewsAnalysis extends BasePopListFragment<TeacherAnalysisBean> {

    private NewsAnalysisAdapter m_adapterNewsAnalysisAdapter = new NewsAnalysisAdapter();

    private BannerPager m_bpBanner;
    private List<TeacherAnalysisBean> m_bannerBean;
    private ArrayList<String> m_arrBanner;
    private View m_headerBanner;
    private ArrayList<TeacherAnalysisBean> m_arrTeacherName;
    private ArrayList<TeacherAnalysisBean> m_arrTeacherType;
    private String m_strUrl = "";

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
        return R.layout.fragment_news_analysis;
    }

    @Override
    protected void initData() {

        m_bannerBean = new ArrayList<>();
        m_arrBanner = new ArrayList<>();
        m_arrTeacherName = new ArrayList<>();
        m_arrTeacherType = new ArrayList<>();
        m_arrTeacherName.add(new TeacherAnalysisBean("全部","",""));
        m_arrTeacherType.add(new TeacherAnalysisBean("全部","",""));
        m_arrTeacherType.add(new TeacherAnalysisBean("早盘内参","","1"));
        m_arrTeacherType.add(new TeacherAnalysisBean("晚盘内参","","2"));
    }

    @Override
    public void initView() {
        super.initView();
        m_ckTeacher.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(m_arrTeacherName.size() > 1){
                    MoveToPosition();
                    filterTabToggleT(isChecked, m_ckTeacher,m_arrTeacherName,new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {

                            m_strUrl = "t_id="+m_arrTeacherName.get(position).getT_id();
                            hidePopListView();
                            onRefreshView();
                        }
                    }, m_ckTeacher, m_cbType,m_cbClass);
                }else{
                    getTypeTeacher1(isChecked);
                }

            }
        });
//        m_cbClass.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                MoveToPosition();
//                filterTabToggleT(isChecked, m_cbType, new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                        hidePopListView();
//                    }
//                }, m_cbType, m_ckTeacher,m_cbClass);
//            }
//        });
        m_cbType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                MoveToPosition();
                filterTabToggleT(isChecked, m_cbType, m_arrTeacherType,new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        m_strUrl = "neican_id="+m_arrTeacherType.get(position).getT_id();
                        hidePopListView();
                        onRefreshView();
                    }
                },m_cbType, m_cbClass, m_ckTeacher);
            }
        });

        m_ckTeacherTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if(m_arrTeacherName.size() > 1){
                    filterTabToggleT(isChecked, m_ckTeacherTop, m_arrTeacherName,new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            m_strUrl = "t_id="+m_arrTeacherName.get(position).getT_id();
                            hidePopListView();
                            onRefreshView();
                        }
                    }, m_ckTeacherTop, m_cbTypeTop,m_cbClassTop);
                }else{
                    getTypeTeacher2(isChecked);
                }
            }
        });
//        m_cbClassTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
//            @Override
//            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
//                filterTabToggleT(isChecked, m_cbTypeTop, new AdapterView.OnItemClickListener() {
//                    @Override
//                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
//                        hidePopListView();
//                    }
//                }, m_cbTypeTop, m_ckTeacherTop,m_cbClassTop);
//            }
//        });
        m_cbTypeTop.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener(){
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                filterTabToggleT(isChecked, m_cbTypeTop, m_arrTeacherType,new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                        m_strUrl = "neican_id="+m_arrTeacherType.get(position).getT_id();
                        hidePopListView();
                        onRefreshView();
                    }
                }, m_cbClassTop, m_ckTeacherTop,m_cbTypeTop);
            }
        });
    }

    private void MoveToPosition() {
        View itemView = m_linearLayoutManager.findViewByPosition(1);
        if (itemView != null) {
            if (-itemView.getTop() < m_headerBanner.getHeight() - m_vArrow.getHeight()) {
                mRecyclerView.scrollBy(0,Utils.dp2px(getMContext(),210)+itemView.getTop());
            }
        }
    }

    private void onScrollChangedListen(){
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                View itemView = m_linearLayoutManager.findViewByPosition(1);
                if (itemView != null) {
                    if (-itemView.getTop() <= Utils.dp2px(getMContext(),210)) {
                        m_vArrow.setVisibility(View.GONE);
                    } else {

                        m_vArrow.setVisibility(View.VISIBLE);
                    }
                }else{
                    m_vArrow.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    protected BaseRecyclerAdapter<TeacherAnalysisBean> getListAdapter() {
        return m_adapterNewsAnalysisAdapter;
    }

    @Override
    protected void initLayoutManager() {
        m_linearLayoutManager = new LinearLayoutManager(getMContext(),LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(m_linearLayoutManager);
        mRecyclerView.setLoadMoreEnabled(true);
        m_headerBanner = LayoutInflater.from(getMContext()).inflate(R.layout.common_news_analysis,mRecyclerView, false);
        m_bpBanner = m_headerBanner.findViewById(R.id.banner_pager);
        m_ckTeacher = m_headerBanner.findViewById(R.id.cb_teacher);
        m_cbType = m_headerBanner.findViewById(R.id.cb_type);
        m_cbClass = m_headerBanner.findViewById(R.id.cb_class);
        mRecyclerViewAdapter.addHeaderView(m_headerBanner);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(getMContext(), LinearLayoutManager.VERTICAL, 5, getResources().getColor(R.color.app_backgrount_color)));
        mRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                m_strUrl = "";
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
                Intent it = new Intent(getMContext(),NewsWebViewActivity.class);
                it.putExtra("webViewUrl",m_adapterNewsAnalysisAdapter.getListData().get(position).getDetail_url());
                startActivity(it);
            }

        });

    }

    @Override
    protected BaseAdapter setInitAdapter(List<TeacherAnalysisBean> bean) {
        return new VideoTypeCheckedAdapter(getMContext(),bean);
    }

    private void initBanner() {

        final PagerOptions pagerOptions = new PagerOptions.Builder(getMContext())
                .setTurnDuration(4000)
                .setIndicatorSize(Utils.dp2px(getMContext(),6))
                .setIndicatorColor(getMContext().getResources().getColor(R.color.dark),getMContext().getResources().getColor(R.color.red) )
                .setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
                .setIndicatorMarginBottom(Utils.dp2px(getMContext(),15))
                .build();

        m_bpBanner.setPagerOptions(pagerOptions).setPages(m_arrBanner, new ViewHolderCreator<FragmentNewsAnalysis.BannerPagerHolder>() {
            @Override
            public FragmentNewsAnalysis.BannerPagerHolder createViewHolder() {
                final View view = LayoutInflater.from(getMContext()).inflate(R.layout.item_image_banner_analysis, null);
                return new FragmentNewsAnalysis.BannerPagerHolder(view);
            }
        });
        m_bpBanner.startTurning();
        m_bpBanner.setOnItemClickListener(new com.joker.pager.listener.OnItemClickListener() {
            @Override
            public void onItemClick(int location, int position) {
                if(m_bannerBean.get(position).getDetail_url() != null && !"".equals(m_bannerBean.get(position).getDetail_url())){
                    Intent it = new Intent(getMContext(),NewsWebViewActivity.class);
                    it.putExtra("webViewUrl",m_bannerBean.get(position).getDetail_url());
                    startActivity(it);
                }
            }
        });
    }

    private class BannerPagerHolder extends ViewHolder<String> {

        private ImageView mImage;
        private TextView mText;

        private BannerPagerHolder(View itemView) {
            super(itemView);
            mImage = itemView.findViewById(R.id.image);
            mText = itemView.findViewById(R.id.text);
        }

        @Override
        public void onBindView(View view, String data, int position) {
            Glide.with(mImage.getContext())
                    .load(data)
                    .into(mImage);
            mText.setText(m_bannerBean.get(position).getTitle());
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
        String url = ApiStores.more+"?cate_id=1&"+m_strUrl+"&page="+mCurrentPage;
        HttpClient.get(url, new HttpCallback<ResponseNewsAnalysisBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseNewsAnalysisBean response) {
				if(response.getResult()){
					if(m_bannerBean.size() > 0 ){
						m_bannerBean.clear();
						m_arrBanner.clear();
					}
					m_bannerBean.addAll(response.getContent().getJuemituijian().getData());
					for(int i = 0 ; i < m_bannerBean.size() ; i ++){
						m_arrBanner.add(m_bannerBean.get(i).getN_photo());
					}
					if(m_bannerBean.size()>0){
                        initBanner();
                    }

                    if(response.getContent().getJuemi().getData().size() > 0){
                        executeOnLoadDataSuccess(response.getContent().getJuemi().getData());
                    }else{
                        if(m_arrBanner.size() > 0){
                            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
                        }else{
                            executeOnLoadDataSuccess(response.getContent().getJuemi().getData());
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

    private void getTypeTeacher1(final boolean isChecked){
        HttpClient.get(ApiStores.getAllTeacher, new HttpCallback<ResponseTeacherTypeBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseTeacherTypeBean response) {
                if(response.getResult()){

                    for(int i = 0 ; i < response.getContent().size() ; i ++){
                        m_arrTeacherName.add(new TeacherAnalysisBean(response.getContent().get(i).getT_nic_name(),"",response.getContent().get(i).getT_id()));
                    }

                    MoveToPosition();
                    filterTabToggleT(isChecked, m_ckTeacher,m_arrTeacherName,new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            m_strUrl = "t_id="+m_arrTeacherType.get(position).getT_id();
                            hidePopListView();
                            onRefreshView();
                        }
                    }, m_ckTeacher, m_cbType,m_cbClass);
                }
            }

            @Override
            public void OnFailure(String message) {
            }

            @Override
            public void OnRequestStart() {
            }

            @Override
            public void OnRequestFinish() {

            }
        });
    }

    private void getTypeTeacher2(final boolean isChecked){
        HttpClient.get(ApiStores.getAllTeacher, new HttpCallback<ResponseTeacherTypeBean>() {//ResponseHallBean
            @Override
            public void OnSuccess(ResponseTeacherTypeBean response) {
                if(response.getResult()){

                    for(int i = 0 ; i < response.getContent().size() ; i ++){
                        m_arrTeacherName.add(new TeacherAnalysisBean(response.getContent().get(i).getT_nic_name(),"",response.getContent().get(i).getT_id()));
                    }

                    filterTabToggleT(isChecked, m_ckTeacherTop, m_arrTeacherName,new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                            m_strUrl = "t_id="+m_arrTeacherType.get(position).getT_id();
                            hidePopListView();
                            onRefreshView();
                        }
                    }, m_ckTeacherTop, m_cbTypeTop,m_cbClassTop);
                }
            }

            @Override
            public void OnFailure(String message) {

            }

            @Override
            public void OnRequestStart() {
            }

            @Override
            public void OnRequestFinish() {

            }
        });
    }

}
