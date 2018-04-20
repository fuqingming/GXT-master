package com.jy.jgcjj;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.baidu.mobstat.StatService;
import com.bumptech.glide.Glide;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.util.TimeUtils;
import com.jgcj.library.util.Utils;
import com.joker.pager.BannerPager;
import com.joker.pager.PagerOptions;
import com.joker.pager.holder.ViewHolder;
import com.joker.pager.holder.ViewHolderCreator;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.FragmentLiveAdapter;
import com.jy.jgcjj.adapter.LiveAdapter;
import com.jy.jgcjj.base.BaseListFragment;
import com.jy.jgcjj.bean.base.BannerBean;
import com.jy.jgcjj.bean.base.LiveBean;
import com.jy.jgcjj.bean.response.ResponseFragmentLiveBean;
import com.jy.jgcjj.util.DataUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 *
 *
 * */
public class FragmentLiveVideo extends BaseListFragment<LiveBean> {

	private LiveAdapter m_fragmentTrainAdapter= new LiveAdapter();
	private BannerPager m_bpBanner;
	private List<BannerBean> m_bannerBean;
	private ArrayList<String> m_arrBanner;

//	private CheckBox m_cbTop1;
//	private CheckBox m_cbTop2;
//	private CheckBox m_cbTop3;
//	private CheckBox m_cbTop4;
//	private CheckBox m_cbTop5;
//	private View viewTop1;
//	private View viewTop2;
//	private View viewTop3;
//	private View viewTop4;
//	private View viewTop5;
//
//	@BindView(R.id.cb_bottom1)
//	CheckBox m_cbBottom1;
//	@BindView(R.id.cb_bottom2)
//	CheckBox m_cbBottom2;
//	@BindView(R.id.cb_bottom3)
//	CheckBox m_cbBottom3;
//	@BindView(R.id.cb_bottom4)
//	CheckBox m_cbBottom4;
//	@BindView(R.id.cb_bottom5)
//	CheckBox m_cbBottom5;
//	@BindView(R.id.view_bottom1)
//	View viewBottom1;
//	@BindView(R.id.view_bottom2)
//	View viewBottom2;
//	@BindView(R.id.view_bottom3)
//	View viewBottom3;
//	@BindView(R.id.view_bottom4)
//	View viewBottom4;
//	@BindView(R.id.view_bottom5)
//	View viewBottom5;
//
//	private List<CheckBox> m_cbListTop;
//	private List<CheckBox> m_cbListBottom;
//	private List<View> m_viewListTop;
//	private List<View> m_viewListBottom;
//	private List<LiveBean> m_arrLiveBean;
//	private List<String> m_arrDate;

//	private int m_iCheckedTopId = 0;
//	private int m_iCheckedBottomId = 0;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_live;
	}

	@Override
	public void initView() {
		super.initView();
		Utils.initCommonTitle(getContentView(),"直播");

//		m_cbListTop.add(m_cbTop1);
//		m_cbListTop.add(m_cbTop2);
//		m_cbListTop.add(m_cbTop3);
//		m_cbListTop.add(m_cbTop4);
//		m_cbListTop.add(m_cbTop5);
//
//		m_cbListBottom.add(m_cbBottom1);
//		m_cbListBottom.add(m_cbBottom2);
//		m_cbListBottom.add(m_cbBottom3);
//		m_cbListBottom.add(m_cbBottom4);
//		m_cbListBottom.add(m_cbBottom5);
//
//		m_viewListTop.add(viewTop1);
//		m_viewListTop.add(viewTop2);
//		m_viewListTop.add(viewTop3);
//		m_viewListTop.add(viewTop4);
//		m_viewListTop.add(viewTop5);
//
//		m_viewListBottom.add(viewBottom1);
//		m_viewListBottom.add(viewBottom2);
//		m_viewListBottom.add(viewBottom3);
//		m_viewListBottom.add(viewBottom4);
//		m_viewListBottom.add(viewBottom5);
//
//		for(int i = 0 ; i < 5 ;i ++){
//			m_cbListTop.get(i).setText(m_arrDate.get(i));
//			m_cbListBottom.get(i).setText(m_arrDate.get(i));
//			if("今天".equals(m_arrDate.get(i))){
//				initCheckBox(m_cbListTop.get(i),m_viewListTop.get(i),m_cbListBottom.get(i),m_viewListBottom.get(i));
//			}
//		}

//		setOnClickView();
	}

//	private void setOnClickView() {
//		m_cbTop1.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop1,viewTop1,m_cbBottom1,viewBottom1);
//			}
//		});
//		m_cbTop2.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop2,viewTop2,m_cbBottom2,viewBottom2);
//			}
//		});
//		m_cbTop3.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop3,viewTop3,m_cbBottom3,viewBottom3);
//			}
//		});
//		m_cbTop4.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop4,viewTop4,m_cbBottom4,viewBottom4);
//			}
//		});
//		m_cbTop5.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop5,viewTop5,m_cbBottom5,viewBottom5);
//			}
//		});
//		m_cbBottom1.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop1,viewTop1,m_cbBottom1,viewBottom1);
//			}
//		});
//		m_cbBottom2.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop2,viewTop2,m_cbBottom2,viewBottom2);
//			}
//		});
//		m_cbBottom3.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop3,viewTop3,m_cbBottom3,viewBottom3);
//			}
//		});
//		m_cbBottom4.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop4,viewTop4,m_cbBottom4,viewBottom4);
//			}
//		});
//		m_cbBottom5.setOnClickListener(new View.OnClickListener() {
//			@Override
//			public void onClick(View view) {
//				initCheckBox(m_cbTop5,viewTop5,m_cbBottom5,viewBottom5);
//			}
//		});
//	}

	@Override
	protected BaseRecyclerAdapter<LiveBean> getListAdapter() {
		return m_fragmentTrainAdapter;
	}

	@Override
	public void initData() {
		super.initData();
		m_bannerBean = new ArrayList<>();
		m_arrBanner = new ArrayList<>();
//		m_arrLiveBean = DataUtil.initLiveBean();
//		m_arrDate = TimeUtils.findThisWeekDates();
//		m_cbListTop = new ArrayList<>();
//		m_cbListBottom = new ArrayList<>();
//		m_viewListTop = new ArrayList<>();
//		m_viewListBottom = new ArrayList<>();
	}

	@Override
	protected void initLayoutManager() {
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.setLoadMoreEnabled(false);
		View header = LayoutInflater.from(getMContext()).inflate(R.layout.common_fragment_live_banner,mRecyclerView, false);
		m_bpBanner = header.findViewById(R.id.banner_pager);
//		m_cbTop1 = header.findViewById(R.id.cb_top1);
//		m_cbTop2 = header.findViewById(R.id.cb_top2);
//		m_cbTop3 = header.findViewById(R.id.cb_top3);
//		m_cbTop4 = header.findViewById(R.id.cb_top4);
//		m_cbTop5 = header.findViewById(R.id.cb_top5);
//		viewTop1 = header.findViewById(R.id.view_top1);
//		viewTop2 = header.findViewById(R.id.view_top2);
//		viewTop3 = header.findViewById(R.id.view_top3);
//		viewTop4 = header.findViewById(R.id.view_top4);
//		viewTop5 = header.findViewById(R.id.view_top5);
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
				int type = bundle.getInt("iType");
				int position = bundle.getInt("position");

				switch (type){
					case FragmentLiveAdapter.LIVE_OVER:

						break;
					case FragmentLiveAdapter.LIVE_BEGINING:
//						Intent it = new Intent(getMContext(),ChoiceClassActivity.class);
//						it.putExtra("strUrl","http://gxtvod.58hengku.com/gxt/record/2018-03-16/gxt/beiyong1/2018-03-16-08:47:45_2018-03-16-10:17:01.m3u8");

						break;
					case FragmentLiveAdapter.LIVE_WILL:
						break;
				}
			}

		});
	}

//	private void initCheckBox(CheckBox checkBox, View view, CheckBox checkBox1, View viewBottom){
//		if(m_iCheckedTopId == checkBox.getId() || m_iCheckedBottomId == checkBox1.getId()){
//			return;
//		}
//
//		m_iCheckedTopId = checkBox.getId();
//		m_iCheckedBottomId = checkBox1.getId();
//
//		for(int i = 0 ; i < 5 ; i ++){
//			m_cbListTop.get(i).setChecked(false);
//			m_cbListBottom.get(i).setChecked(false);
//			m_viewListTop.get(i).setVisibility(View.GONE);
//			m_viewListBottom.get(i).setVisibility(View.GONE);
//		}
//
//		checkBox.setChecked(true);
//		checkBox1.setChecked(true);
//		viewBottom.setVisibility(View.VISIBLE);
//		view.setVisibility(View.VISIBLE);
//	}

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
		List<LiveBean> responseFragmentHallBeen = DataUtil.initLiveBean();
					executeOnLoadDataSuccess(responseFragmentHallBeen);
					totalPage = responseFragmentHallBeen.size();
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