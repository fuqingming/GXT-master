package com.jy.jgcjj;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.baidu.mobstat.StatService;
import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.backhandler.OnTaskSuccessComplete;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Utils;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.FragmentTrainAdapter;
import com.jy.jgcjj.base.BaseListFragment;
import com.jy.jgcjj.bean.base.RoomBean;
import com.jy.jgcjj.bean.response.ResponseTrainBean;
import com.jy.jgcjj.huanxin.DemoHelper;
import com.jy.jgcjj.view.recyclerview.RecycleViewDivider;

/**
 *
 *
 * */
public class FragmentLiveTrain extends BaseListFragment<RoomBean> {

	private FragmentTrainAdapter m_fragmentTrainAdapter= new FragmentTrainAdapter();

	protected String toChatUsername;
	private String m_strTeacherPhoto;
	private String m_strTeacherName;
	private String m_strTeacherBreif;
	private String m_strTeacherId;

	@Override
	protected int getLayoutId() {
		return R.layout.fragment_train;
	}

	@Override
	public void initView() {
		super.initView();
	}

	@Override
	protected BaseRecyclerAdapter<RoomBean> getListAdapter() {
		return m_fragmentTrainAdapter;
	}

	@Override
	public void initData() {
		super.initData();
	}

	@Override
	protected void initLayoutManager() {
		mRecyclerView.addItemDecoration(new RecycleViewDivider(getMContext(), LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.app_backgrount_color)));
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
		mRecyclerView.setLoadMoreEnabled(false);
//		View header = LayoutInflater.from(getMContext()).inflate(R.layout.common_title,mRecyclerView, false);
//		mRecyclerViewAdapter.addHeaderView(header);
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
				if (!DemoHelper.getInstance().isLoggedIn()) {
//					Intent it = new Intent(getMContext(),LoginActivity.class);
//					startActivity(it);
					return;
				}
				toChatUsername = m_fragmentTrainAdapter.getListData().get(position).getR_room_id();
				m_strTeacherPhoto = m_fragmentTrainAdapter.getListData().get(position).getT_photo();
				m_strTeacherName = m_fragmentTrainAdapter.getListData().get(position).getT_nic_name();
				m_strTeacherBreif = m_fragmentTrainAdapter.getListData().get(position).getR_room_breif();
				m_strTeacherId = m_fragmentTrainAdapter.getListData().get(position).getT_id();
				if(m_fragmentTrainAdapter.getListData().get(position).getR_is_screte() == RoomBean.isLocked){
					Utils.showCommonDialogEdit(getMContext(),m_fragmentTrainAdapter.getListData().get(position).getR_pwd(),new OnTaskSuccessComplete()
					{
						@Override
						public void onSuccess(Object obj)
						{
							intentChatActivity();
						}
					});
				}else{
					intentChatActivity();
				}
			}

		});
	}

	private void intentChatActivity(){
		Intent it = new Intent(getMContext(),ChatActivity.class);
		it.putExtra("strRoomId",toChatUsername);
		it.putExtra("strTeacherPhoto",m_strTeacherPhoto);
		it.putExtra("strTeacherName",m_strTeacherName);
		it.putExtra("strTeacherBreif",m_strTeacherBreif);
		it.putExtra("strTeacherId",m_strTeacherId);
		startActivity(it);
	}

	protected void requestData(){
		HttpClient.get(ApiStores.room_info, new HttpCallback<ResponseTrainBean>() {
			@Override
			public void OnSuccess(ResponseTrainBean response) {
				if(response.getResult()){
//					List<RoomBean> list = new ArrayList<>();
//					if(mCurrentPage == 3){
//						executeOnLoadDataSuccess(response.getContent().getRoom());
//						totalPage = response.getContent().getRoom().size();
//						return;
//					}
//					for(int i = 0 ; i < 4 ; i ++){
//						list.addAll(response.getContent().getRoom());
//					}
//					executeOnLoadDataSuccess(list);
					executeOnLoadDataSuccess(response.getContent().getRoom());
					totalPage = response.getContent().getRoom().size();
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

	@Override
	public void onResume() {
		StatService.onPageStart(getMContext(), "实战培训");
		super.onResume();
	}

	@Override
	public void onPause() {
		StatService.onPageEnd(getMContext(), "实战培训");
		super.onPause();
	}
}