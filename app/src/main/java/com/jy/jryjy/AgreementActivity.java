package com.jy.jryjy;


import com.jgcj.library.util.Utils;
import com.jy.jryjy.base.BaseAppCompatActivity;

public class AgreementActivity extends BaseAppCompatActivity {

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_agreement;
    }

    protected void init(){
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"用户服务协议",true);
    }
}
