package com.jy.jryjy;

import android.view.View;
import android.widget.RelativeLayout;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.util.Utils;
import com.jy.jryjy.base.BaseAppCompatActivity;
import com.xiao.nicevideoplayer.NiceUtil;
import com.xiao.nicevideoplayer.NiceVideoPlayer;
import com.xiao.nicevideoplayer.NiceVideoPlayerManager;
import com.xiao.nicevideoplayer.TxVideoPlayerController;

import butterknife.BindView;
import butterknife.OnClick;

public class LivePlaybackActivity extends BaseAppCompatActivity {

    private NiceVideoPlayer mNiceVideoPlayer;

    @BindView(R.id.ll_play)
    RelativeLayout m_llPlay;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_live_playback;
    }

    @Override
    protected void setUpView() {
        if(SPUtils.getInstance(GlobalVariables.serverSp).getBoolean(GlobalVariables.serverWifiPlay)){
            if(!NiceUtil.isWiFiActive(LivePlaybackActivity.this)){
                m_llPlay.setVisibility(View.VISIBLE);
            }else{
                m_llPlay.setVisibility(View.GONE);
            }
        }else{
            m_llPlay.setVisibility(View.GONE);
        }

        mNiceVideoPlayer = findViewById(R.id.nice_video_player);
        mNiceVideoPlayer.setPlayerType(NiceVideoPlayer.TYPE_IJK); // IjkPlayer or MediaPlayer
//        String videoUrl = "http://gxtvod.58hengku.com/gxt/record/2018-03-14/gxt/beiyong1/2018-03-14-14:02:27_2018-03-14-15:03:29.m3u8";
        String videoUrl = getIntent().getStringExtra("strPlayUrl");
//        videoUrl = Environment.getExternalStorageDirectory().getPath().concat("/办公室小野.mp4");
        mNiceVideoPlayer.setUp(videoUrl, null);
        TxVideoPlayerController controller = new TxVideoPlayerController(this);
        controller.setTitle("");
        Glide.with(this)
                .load("")
                .placeholder(R.mipmap.station_pic)
                .crossFade()
                .into(controller.imageView());
        mNiceVideoPlayer.setController(controller);//isFullScreen
    }

    @OnClick({R.id.tv_play})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_play:
                if(!NiceUtil.isNetworkAvalible(LivePlaybackActivity.this)){
                    Utils.showDialogWifi(LivePlaybackActivity.this);
                    return;
                }
                m_llPlay.setVisibility(View.GONE);
                if (mNiceVideoPlayer.isIdle()) {
                    mNiceVideoPlayer.start();
                }
                break;

        }
    }

    @Override
    protected void setUpData() {
        super.setUpData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        NiceVideoPlayerManager.instance().releaseNiceVideoPlayer();
    }

    @Override
    public void onBackPressed() {
//        if (NiceVideoPlayerManager.instance().onBackPressd()) return;
        super.onBackPressed();
    }
}
