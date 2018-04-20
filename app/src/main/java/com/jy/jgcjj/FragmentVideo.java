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
import android.widget.TextView;

import com.baidu.mobstat.StatService;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.util.Utils;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.LivePlayBackAdapter;
import com.jy.jgcjj.adapter.VideoTypeCheckedAdapter;
import com.jy.jgcjj.base.BaseListFragment;
import com.jy.jgcjj.base.BasePopListFragment;
import com.jy.jgcjj.bean.base.BannerBean;
import com.jy.jgcjj.bean.base.VideoPlayBackBean;
import com.jy.jgcjj.util.DataUtil;
import com.jy.jgcjj.view.recyclerview.RecycleViewDivider;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 *
 *
 * */
public class FragmentVideo extends BaseListFragment<VideoPlayBackBean> {

	Unbinder unbinder;

	private LinearLayoutManager m_linearLayoutManager;
	private View m_headerBanner;
	private LivePlayBackAdapter livePlayBackAdapter = new LivePlayBackAdapter();
	private BannerPager m_bpBanner;
	private List<BannerBean> m_bannerBean;
	private ArrayList<String> m_arrBanner;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_video;
	}

	@Override
	public void initView() {
		super.initView();
		Utils.initCommonTitle(getContentView(), "视频回顾");
		unbinder = ButterKnife.bind(this, getContentView());

	}

	@Override
	protected BaseRecyclerAdapter<VideoPlayBackBean> getListAdapter() {
		return livePlayBackAdapter;
	}

	@Override
	public void initData() {
		super.initData();
		m_bannerBean = new ArrayList<>();
		m_arrBanner = new ArrayList<>();
	}

	@Override
	protected void initLayoutManager() {
		m_linearLayoutManager = new LinearLayoutManager(getMContext(),LinearLayoutManager.VERTICAL, false);
		mRecyclerView.setLayoutManager(m_linearLayoutManager);
		mRecyclerView.setLoadMoreEnabled(true);
		m_headerBanner = LayoutInflater.from(getMContext()).inflate(R.layout.common_fragment_live_playback_banner,mRecyclerView, false);
		m_bpBanner = m_headerBanner.findViewById(R.id.banner_pager);
		mRecyclerViewAdapter.addHeaderView(m_headerBanner);
		mRecyclerView.addItemDecoration(new RecycleViewDivider(getMContext(), LinearLayoutManager.VERTICAL, 1, getMContext().getResources().getColor(R.color.app_backgrount_color)));
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
					isRequestInProcess = true;
					requestData();
				} else {
					mRecyclerView.setNoMore(true);
				}
			}
		});
	}

	private void initBanner() {

		final PagerOptions pagerOptions = new PagerOptions.Builder(getMContext())
				.setTurnDuration(2000)
				.setIndicatorSize(Utils.dp2px(getMContext(),6))
				.setIndicatorColor(getMContext().getResources().getColor(R.color.dark),getMContext().getResources().getColor(R.color.red) )
				.setIndicatorAlign(RelativeLayout.CENTER_HORIZONTAL)
				.setIndicatorMarginBottom(15)
				.build();

		m_bpBanner.setPagerOptions(pagerOptions).setPages(m_arrBanner, new ViewHolderCreator<BannerPagerHolder>() {
			@Override
			public BannerPagerHolder createViewHolder() {
				final View view = LayoutInflater.from(getMContext()).inflate(R.layout.item_image_banner_live, null);
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
		List<VideoPlayBackBean> videoPlayBackBeans = DataUtil.initVideoPlayBackBean();
		if(mCurrentPage == 2){
			videoPlayBackBeans.remove(5);
		}
		executeOnLoadDataSuccess(videoPlayBackBeans);

		totalPage = videoPlayBackBeans.size();
		executeOnLoadFinish();
//		HttpClient.get(ApiStores.banner, new HttpCallback<ResponseHallBean>() {
//			@Override
//			public void OnSuccess(ResponseHallBean response) {
//				if(response.getResult()){
//					if(m_bannerBean.size() > 0 ){
//						m_bannerBean.clear();
//						m_arrBanner.clear();
//					}
//					m_bannerBean.addAll(response.getContent().getBanner());
//					for(int i = 0 ; i < m_bannerBean.size() ; i ++){
//						m_arrBanner.add(m_bannerBean.get(i).getB_link());
//					}
//					initBanner();
//				}
//			}
//
//			@Override
//			public void OnFailure(String message) {
//				executeOnLoadDataError(null);
//			}
//
//			@Override
//			public void OnRequestStart() {
//			}
//
//			@Override
//			public void OnRequestFinish() {
//				executeOnLoadFinish();
//			}
//		});
	}
}