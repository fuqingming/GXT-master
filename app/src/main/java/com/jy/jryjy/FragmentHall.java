package com.jy.jryjy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.cache.AsyncImageLoader;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Utils;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;
import com.joker.pager.transformer.ScaleTransformer;
import com.jy.jryjy.adapter.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.FragmentHallAdapter;
import com.jy.jryjy.adapter.MyGridViewAdpter;
import com.jy.jryjy.adapter.MyGridViewPapgerAdpter;
import com.jy.jryjy.adapter.MyViewPagerAdapter;
import com.jy.jryjy.base.BaseListFragment;
import com.jy.jryjy.bean.base.BannerBean;
import com.jy.jryjy.bean.base.ProdctBean;
import com.jy.jryjy.bean.base.ProdctSeletBean;
import com.jy.jryjy.bean.response.ResponseHallBean;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * */
public class FragmentHall extends BaseListFragment<ResponseHallBean> {

	private FragmentHallAdapter m_fragmentTrainAdapter= new FragmentHallAdapter();
	private BannerPager m_bpBanner;
	private List<BannerBean> m_bannerBean;
	private ArrayList<String> m_arrBanner;

	private ViewPager m_viewPager;
	private LinearLayout m_llGroup;//圆点指示器
	private ImageView[] m_ivPoints;
	private int m_iTotalPage;
	private int m_iPageSize = 3;
	private int m_iViewPageSize = 3;
	private List<ProdctBean> m_listDatas;
	private String[] m_arrProName = {"期学堂","视频回放","名师"};
	private int[] m_arrProPic = {R.mipmap.xueqitang,R.mipmap.live_icon,R.mipmap.mingshi};
	private List<ProdctSeletBean> m_viewListDatas;
	private List<View> m_listViewPager;

	private ViewPager m_vPager;
	private int m_iViewTotalPage;

	private ImageView m_ivMore;
	private TextView m_tvMore;
	private LinearLayout m_llMore;

	private String m_strMoreUrl;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_hall;
	}

	@Override
	public void initView() {
		super.initView();
		Utils.initCommonTitle(getContentView(),"首页");

		initViewPapger();
	}

	private void initViewPapger() {
		m_iTotalPage = (int) Math.ceil(m_listDatas.size() * 1.0 / m_iPageSize);
		m_listViewPager = new ArrayList();
		for(int i = 0; i < m_iTotalPage; i++){
			final GridView gridView = (GridView)View.inflate(getMContext(), R.layout.item_viewpapger_info, null);
			gridView.setAdapter(new MyGridViewAdpter(getMContext(), m_listDatas, i, m_iPageSize));
			gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
					Object obj = gridView.getItemAtPosition(position);
					if(obj != null && obj instanceof ProdctBean){

					}
				}
			});
			m_listViewPager.add(gridView);
		}
		m_viewPager.setAdapter(new MyViewPagerAdapter(m_listViewPager));
		m_ivPoints = new ImageView[m_iTotalPage];
		for(int i = 0; i < m_iTotalPage; i++){
			m_ivPoints[i] = new ImageView(getMContext());
			if(i==0){
				m_ivPoints[i].setImageResource(R.drawable.indicator_selected_defaulted);
			}else {
				m_ivPoints[i].setImageResource(R.drawable.indicator_normal_default);
			}
			m_ivPoints[i].setPadding(8, 8, 8, 8);
			m_llGroup.addView(m_ivPoints[i]);
		}
		m_viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener(){
			@Override
			public void onPageSelected(int position) {
				for(int i=0 ; i < m_iTotalPage; i++){
					if(i == position){
						m_ivPoints[i].setImageResource(R.drawable.indicator_selected_defaulted);
					}else {
						m_ivPoints[i].setImageResource(R.drawable.indicator_normal_default);
					}
				}
			}
		});

		m_iViewTotalPage = (int) Math.ceil(m_viewListDatas.size() * 1.0 / m_iViewPageSize);
		m_listViewPager = new ArrayList();
		for(int i = 0; i < m_iViewTotalPage; i++){
			final GridView gridView = (GridView)View.inflate(getMContext(), R.layout.item_viewpapger_selet_info, null);
			gridView.setAdapter(new MyGridViewPapgerAdpter(getMContext(), m_viewListDatas, i, m_iViewPageSize));
			gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

				@Override
				public void onItemClick(AdapterView<?> arg0, View arg1,int position, long arg3) {
					Object obj = gridView.getItemAtPosition(position);
					if(obj != null && obj instanceof ProdctSeletBean){
						Intent it;
						switch (((ProdctSeletBean)obj).getName()){
							case "期学堂":
								it = new Intent(getMContext(),NewsActivity.class);
								startActivity(it);
								break;
							case "视频回放":
								it = new Intent(getMContext(),ClassActivity.class);
								startActivity(it);
								break;
							case "名师":
								it = new Intent(getMContext(),TeacherListActivity.class);
								startActivity(it);
								break;
						}
					}
				}
			});
			m_listViewPager.add(gridView);
		}
		m_vPager.setAdapter(new MyViewPagerAdapter(m_listViewPager));
	}

	@Override
	protected BaseRecyclerAdapter<ResponseHallBean> getListAdapter() {
		return m_fragmentTrainAdapter;
	}

	@Override
	public void initData() {
		super.initData();
		m_bannerBean = new ArrayList<>();
		m_arrBanner = new ArrayList<>();
		m_listDatas = new ArrayList();
		m_listDatas.add(new ProdctBean("沪银主力", "3391.55","-0.20","-0.01%"));
		m_listDatas.add(new ProdctBean("沪银主力", "3391.55","-0.20","-0.01%"));
		m_listDatas.add(new ProdctBean("沪银主力", "3391.55","-0.20","-0.01%"));
		m_listDatas.add(new ProdctBean("沪银主力", "3391.55","-0.20","-0.01%"));
		m_listDatas.add(new ProdctBean("沪银主力", "3391.55","-0.20","-0.01%"));

		m_viewListDatas = new ArrayList();
		for(int i =0 ; i < m_arrProName.length; i++){
			m_viewListDatas.add(new ProdctSeletBean(m_arrProName[i], m_arrProPic[i]));
		}
	}

	@Override
	protected void initLayoutManager() {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.setLoadMoreEnabled(false);
		View header = LayoutInflater.from(getMContext()).inflate(R.layout.common_fragment_hall_banner,mRecyclerView, false);
		m_ivMore = header.findViewById(R.id.iv_image_more);
		m_tvMore = header.findViewById(R.id.tv_name_more);

		m_llMore = header.findViewById(R.id.ll_more);
		WindowManager manager = getActivity().getWindowManager();
		DisplayMetrics outMetrics = new DisplayMetrics();
		manager.getDefaultDisplay().getMetrics(outMetrics);
		int width = outMetrics.widthPixels;

		LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) m_llMore.getLayoutParams();
		params.width=width/4;//设置当前控件布局的高度
		m_llMore.setLayoutParams(params);//将设置好的布局参数应用到控件中
		m_llMore.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent it = new Intent(getMContext(),NewsWebViewActivity.class);
				it.putExtra("webViewUrl",m_strMoreUrl);
				startActivity(it);
			}
		});

		m_bpBanner = header.findViewById(R.id.banner_pager);
		m_viewPager = header.findViewById(R.id.viewpager);
		m_vPager = header.findViewById(R.id.view_pager);
		m_llGroup =  header.findViewById(R.id.points);
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

		m_fragmentTrainAdapter.onSelectFragmentClickListener(new BaseRecyclerAdapter.OnSelectClickListener() {
			@Override
			public void OnSelectClick(Bundle bundle) {

			}

		});
	}

	private void initBanner() {

		final PagerOptions pagerOptions = new PagerOptions.Builder(getMContext())
				.setTurnDuration(4000)
				.setPageTransformer(new ScaleTransformer())
				.setIndicatorSize(Utils.dp2px(getMContext(),6))
				.setPagePadding(16)
				.setPrePagerWidth(60)
				.setIndicatorVisibility(View.VISIBLE)
				.setIndicatorColor(getMContext().getResources().getColor(R.color.dark),getMContext().getResources().getColor(R.color.red) )
				.setIndicatorAlign(RelativeLayout.CENTER_IN_PARENT)
				.setIndicatorMarginBottom(0)
				.build();

		m_bpBanner.setPagerOptions(pagerOptions).setPages(m_arrBanner, new ViewHolderCreator<BannerPagerHolder>() {
			@Override
			public BannerPagerHolder createViewHolder() {
				final View view = LayoutInflater.from(getMContext()).inflate(R.layout.item_image_banner_hall, null);
				return new BannerPagerHolder(view);
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
		StatService.onPageStart(getMContext(), "主界面");
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		StatService.onPageEnd(getMContext(), "主界面");
	}

	@Override
	public void onStop() {
		super.onStop();
		if(m_bpBanner!=null){
			m_bpBanner.stopTurning();
		}
	}

	protected void requestData(){

		HttpClient.get(ApiStores.banner, new HttpCallback<ResponseHallBean>() {//ResponseHallBean
			@Override
			public void OnSuccess(ResponseHallBean response) {
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

					if(response.getContent().getIndex_icon().size() > 0){
						Glide.with(getMContext()).load( response.getContent().getIndex_icon().get(0).getI_img()).placeholder(R.mipmap.station_pic).into(m_ivMore);
						m_tvMore.setText(response.getContent().getIndex_icon().get(0).getI_title());
						m_strMoreUrl = response.getContent().getIndex_icon().get(0).getI_url();
						m_llMore.setVisibility(View.VISIBLE);
					}else{
						m_strMoreUrl = "";
						m_llMore.setVisibility(View.INVISIBLE);
					}

					List<ResponseHallBean> responseFragmentHallBeen = new ArrayList<>();
					responseFragmentHallBeen.add(response);
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