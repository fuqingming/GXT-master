package com.jy.jgcjj;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.blankj.utilcode.util.SPUtils;
import com.hyphenate.EMCallBack;
import com.jgcj.library.backhandler.OnTaskSuccessComplete;
import com.jgcj.library.cache.AsyncImageLoader;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.util.CleanMessageUtil;
import com.jgcj.library.util.DirSettings;
import com.jgcj.library.util.FileUtil;
import com.jgcj.library.util.Utils;
import com.jgcj.library.view.switchbutton.FSwitchButton;
import com.jy.jgcjj.base.BaseAppCompatActivity;
import com.jy.jgcjj.bean.response.ResponseChangeHeadBean;
import com.jy.jgcjj.huanxin.DemoHelper;
import com.jy.jgcjj.util.Upload;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

public class SetActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_clean)
    TextView m_tvClean;
    @BindView(R.id.sb_switch)
    FSwitchButton m_sbSwitch;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_set;
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
        Utils.initCommonTitle(this,"设置",true);


        if(SPUtils.getInstance(GlobalVariables.serverSp).getBoolean(GlobalVariables.serverWifiPlay)){
            m_sbSwitch.setChecked(true,false,true);
        }else{
            m_sbSwitch.setChecked(false,false,false);
        }

        m_sbSwitch.setOnCheckedChangedCallback(new FSwitchButton.OnCheckedChangedCallback()
        {
            @Override
            public void onCheckedChanged(boolean checked, FSwitchButton view)
            {
                SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverWifiPlay,checked);
            }
        });
    }

    @OnClick({R.id.ll_icon,R.id.ll_nickname,R.id.ll_clean,R.id.btn_logout,R.id.ll_change_pwd})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.ll_clean:
                Utils.showDialogClean(SetActivity.this,m_tvClean);
                break;
            case R.id.ll_change_pwd:
                Intent intent = new Intent(SetActivity.this,ChangePwdActivity.class);
                startActivity(intent);
                break;
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        String lenth = CleanMessageUtil.getTotalCacheSize(SetActivity.this);
        m_tvClean.setText(lenth);
    }
}
