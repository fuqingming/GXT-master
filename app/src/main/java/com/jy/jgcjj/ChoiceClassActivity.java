package com.jy.jgcjj;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.github.jdsjlzx.interfaces.OnItemClickListener;
import com.github.jdsjlzx.interfaces.OnLoadMoreListener;
import com.github.jdsjlzx.interfaces.OnRefreshListener;
import com.jgcj.library.util.NiceUtil;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.adapter.ChoiceClassAdapter;
import com.jy.jgcjj.base.BaseListActivity;
import com.jy.jgcjj.bean.base.VideoPlayBackBean;
import com.jy.jgcjj.util.DataUtil;
import com.jy.jgcjj.view.recyclerview.RecycleViewDivider;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class ChoiceClassActivity extends BaseListActivity<VideoPlayBackBean> {

    private ChoiceClassAdapter choiceClassAdapter = new ChoiceClassAdapter();

    @BindView(R.id.nice_video_player)
    NiceVideoPlayer mNiceVideoPlayer;
    @BindView(R.id.ll_play)
    RelativeLayout m_llPlay;
    @BindView(R.id.iv_enlarge)
    ImageView m_ivEnlarge;
    @BindView(R.id.rl_live)
    RelativeLayout m_rlLive;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_choice_class;
    }

    @Override
    public void initView() {
        super.initView();
        if(!NiceUtil.isWiFiActive(ChoiceClassActivity.this)){
            m_llPlay.setVisibility(View.VISIBLE);
        }else{
            m_llPlay.setVisibility(View.GONE);
            m_ivEnlarge.setVisibility(View.VISIBLE);
            initPlayer();
        }
    }

    @Override
    protected BaseRecyclerAdapter<VideoPlayBackBean> getListAdapter() {
        return choiceClassAdapter;
    }

    @Override
    protected void initLayoutManager() {
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setLoadMoreEnabled(false);
        mRecyclerView.addItemDecoration(new RecycleViewDivider(ChoiceClassActivity.this, LinearLayoutManager.VERTICAL, 1, ChoiceClassActivity.this.getResources().getColor(R.color.app_backgrount_color)));
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

            }

        });

    }

    private void initPlayer() {
        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK); // IjkPlayer or MediaPlayer
        String videoUrl = getIntent().getStringExtra("strUrl");
        mNiceVideoPlayer.setUp(videoUrl, null);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        mNiceVideoPlayer.setController(controller);
    }

    @OnClick({R.id.tv_play,R.id.iv_back_live,R.id.iv_enlarge})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_play:
                m_llPlay.setVisibility(View.GONE);
                m_ivEnlarge.setVisibility(View.VISIBLE);
                initPlayer();
                break;
            case R.id.iv_back_live:
                if (mNiceVideoPlayer.isFullScreen()) {
                    mNiceVideoPlayer.exitFullScreen();
                    return;
                }
                finish();
                break;
            case R.id.iv_enlarge:
                if (mNiceVideoPlayer.isNormal() || mNiceVideoPlayer.isTinyWindow()) {
                    mNiceVideoPlayer.enterFullScreen();
                } else if (mNiceVideoPlayer.isFullScreen()) {
                    mNiceVideoPlayer.exitFullScreen();
                }
                break;
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onBackPressed() {
        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }

    protected void requestData(){
        List<VideoPlayBackBean> videoPlayBackBeans = DataUtil.initVideoPlayBackBean();
        executeOnLoadDataSuccess(videoPlayBackBeans);
        totalPage = videoPlayBackBeans.size();
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
