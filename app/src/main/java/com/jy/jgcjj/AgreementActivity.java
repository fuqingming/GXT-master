package com.jy.jgcjj;


import com.jgcj.library.util.Utils;
import com.jy.jgcjj.base.BaseAppCompatActivity;

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
