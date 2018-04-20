package com.jy.jgcjj;

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
import com.jgcj.library.util.Utils;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.NewsAnalysisAdapter;
import com.jy.jgcjj.adapter.VideoTypeCheckedAdapter;
import com.jy.jgcjj.base.BasePopListActivity;
import com.jy.jgcjj.base.BasePopListFragment;
import com.jy.jgcjj.bean.base.BannerBean;
import com.jy.jgcjj.bean.base.TeacherAnalysisBean;
import com.jy.jgcjj.bean.base.TeacherDetailsBean;
import com.jy.jgcjj.util.DataUtil;
import com.jy.jgcjj.view.recyclerview.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created on 2/8/18.
 */
public class FragmentNewsAnalysis extends BasePopListFragment<TeacherAnalysisBean> {

    Unbinder unbinder;

    private NewsAnalysisAdapter m_adapterNewsAnalysisAdapter = new NewsAnalysisAdapter();

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
        return R.layout.fragment_news_analysis;
    }

    @Override
    protected void initData() {
        unbinder = ButterKnife.bind(this, getContentView());
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
        mRecyclerView.setLoadMoreEnabled(false);
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

            }

        });

    }

    @Override
    protected BaseAdapter setInitAdapter() {
        return new VideoTypeCheckedAdapter(getMContext(), DataUtil.initVideoPlayBackBean());
    }

    private void initBanner() {

        final PagerOptions pagerOptions = new PagerOptions.Builder(getMContext())
                .setTurnDuration(2000)
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
                if(m_bannerBean.get(position).getB_turn_link() != null && !"".equals(m_bannerBean.get(position).getB_turn_link())){
                    Intent intent = new Intent();
                    intent.setAction("android.intent.action.VIEW");
                    Uri content_url = Uri.parse(m_bannerBean.get(position).getB_turn_link());
                    intent.setData(content_url);
                    startActivity(intent);
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
        List<TeacherAnalysisBean> arrTeacherAnalysisBeans = new ArrayList<>();
        for(int i = 0 ; i < 5 ; i ++){
            TeacherAnalysisBean teacherAnalysisBean = new TeacherAnalysisBean(R.mipmap.my_head,"许毓玲","2018-01-25 20:30","2018有气体制改革深化年：改革意见细则或于…",
                    "早盘，主板指数在银行和券商带领下小幅高开，随即银行保险纷纷出现震荡下跌走势，导致指数在开盘价附近维持震荡。受消息面的影响，军工板块开…",i);
            arrTeacherAnalysisBeans.add(teacherAnalysisBean);
        }
        executeOnLoadDataSuccess(arrTeacherAnalysisBeans);
        totalPage = arrTeacherAnalysisBeans.size();
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
