package com.jy.jryjy;

import android.view.View;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.util.CleanMessageUtil;
import com.jgcj.library.util.Utils;
import com.jgcj.library.view.switchbutton.FSwitchButton;
import com.jgcj.library.base.BaseAppCompatActivity;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SetActivity extends BaseAppCompatActivity {

    @BindView(R.id.tv_clean)
    TextView m_tvClean;
    @BindView(R.id.sb_switch)
    FSwitchButton m_sbSwitch;
    @BindView(R.id.sb_switch_msg)
    FSwitchButton m_sbSwitchMsg;

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

        if(SPUtils.getInstance(GlobalVariables.serverSp).getBoolean(GlobalVariables.serverPlushMsg)){
            m_sbSwitchMsg.setChecked(true,false,true);
        }else{
            m_sbSwitchMsg.setChecked(false,false,false);
        }

        m_sbSwitchMsg.setOnCheckedChangedCallback(new FSwitchButton.OnCheckedChangedCallback()
        {
            @Override
            public void onCheckedChanged(boolean checked, FSwitchButton view)
            {
                SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverPlushMsg,checked);
            }
        });
    }

    @OnClick({R.id.ll_clean})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.ll_clean:
                Utils.showDialogClean(SetActivity.this,m_tvClean);
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
