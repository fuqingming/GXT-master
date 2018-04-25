package com.jy.jryjy;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.text.TextPaint;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
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
import com.jy.jryjy.adapter.BaseRecyclerAdapter;
import com.jy.jryjy.adapter.FragmentLiveAdapter;
import com.jy.jryjy.adapter.LiveAdapter;
import com.jy.jryjy.base.BaseListFragment;
import com.jy.jryjy.bean.base.BannerBean;
import com.jy.jryjy.bean.base.LiveBean;
import com.jy.jryjy.bean.response.ResponseLiveBean;
import com.jy.jryjy.view.error.ErrorLayout;

import java.util.ArrayList;
import java.util.List;

/**
 *
 *
 * */
public class FragmentLiveVideo extends BaseListFragment<LiveBean> {

	private LiveAdapter m_fragmentTrainAdapter= new LiveAdapter();
	private ImageView m_bpBanner;

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
		mRecyclerView.setLoadMoreEnabled(true);
		View header = LayoutInflater.from(getMContext()).inflate(R.layout.common_fragment_live_banner,mRecyclerView, false);
		m_bpBanner = header.findViewById(R.id.banner_pager);
		TextView tv_shikebiao = header.findViewById(R.id.tv_shikebiao);
		TextPaint tp = tv_shikebiao.getPaint();
		tp.setFakeBoldText(true);
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



	@Override
	public void onResume() {
		super.onResume();
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
	}

	protected void requestData(){

		HttpClient.get(ApiStores.getClass, new HttpCallback<ResponseLiveBean>() {
			@Override
			public void OnSuccess(ResponseLiveBean response) {
				if(response.getResult()){
					if(response.getContent().getBanner().size()>0){
						Glide.with(getMContext()).load(response.getContent().getBanner().get(0).getB_link()).into(m_bpBanner);
					}

					List<LiveBean> responseFragmentHallBeen = new ArrayList<>();
					responseFragmentHallBeen.addAll(response.getContent().getTrailer_info());
					if(responseFragmentHallBeen.size() > 0){
						executeOnLoadDataSuccess(responseFragmentHallBeen);
					}else{
						if(response.getContent().getBanner().size() > 0){
							mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
						}else{
							executeOnLoadDataSuccess(responseFragmentHallBeen);
						}
					}
					totalPage = responseFragmentHallBeen.size();
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