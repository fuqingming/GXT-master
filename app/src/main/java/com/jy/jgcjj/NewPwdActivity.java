package com.jy.jgcjj;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.jgcj.library.data.Const;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.MD5;
import com.jgcj.library.util.RegexUtil;
import com.jgcj.library.util.Utils;
import com.jy.jgcjj.base.BaseAppCompatActivity;
import com.jy.jgcjj.bean.response.ResponseBaseBean;
import com.jy.jgcjj.util.HUDProgressUtils;
import com.jy.jgcjj.util.alert.AlertUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class NewPwdActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "NewPwdActivity";

    KProgressHUD kProgressHUD;

    @BindView(R.id.et_pwd1)
    EditText m_etPwd1;
    @BindView(R.id.et_pwd2)
    EditText m_etPwd2;
    @BindView(R.id.tv_title_right)
    TextView m_tvNext;

    private String m_strMd5Pwd;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_forget_pwd_new;
    }

    protected void init(){
        kProgressHUD = new HUDProgressUtils().showLoadingImage(this);
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
        Utils.initCommonTitle(this,"忘记密码",true);
        m_tvNext.setText("完成");
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {

        String m_strPassword1 = m_etPwd1.getText().toString().trim();
        if(m_strPassword1.isEmpty())
        {
            Utils.showToast(this, "请输入密码");
            m_etPwd1.requestFocus();
            return false;
        }else if(m_strPassword1.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            Utils.showToast(this,"密码不能少于6位");
            m_etPwd1.requestFocus();
            return false;
        }else if(!RegexUtil.checkPassword(m_strPassword1)){
            Utils.showToast(this,"输入6～18位数字字母组合");
            m_etPwd1.requestFocus();
            return false;
        }

        String m_strPassword2 = m_etPwd2.getText().toString().trim();
        if(m_strPassword2.isEmpty())
        {
            Utils.showToast(this, "请输入确认密码");
            m_etPwd2.requestFocus();
            return false;
        }else if(m_strPassword2.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            Utils.showToast(this,"确认密码不能少于6位");
            m_etPwd2.requestFocus();
            return false;
        }else if(!RegexUtil.checkPassword(m_strPassword2)){
            Utils.showToast(this,"输入6～18位数字字母组合确认密码");
            m_etPwd2.requestFocus();
            return false;
        }

        if(!m_strPassword1.equals(m_strPassword2)){
            Utils.showToast(this,"两次密码不一致！");
            return false;
        }

        m_strMd5Pwd = MD5.encode(m_strPassword2);

        return true;
    }

    // 检查输入项是否输入正确
    private boolean isInputValided() {

        String m_strPassword1 = m_etPwd1.getText().toString().trim();
        if(m_strPassword1.isEmpty()){
            return false;
        }else if(m_strPassword1.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            return false;
        }else if(!RegexUtil.checkPassword(m_strPassword1)){
            return false;
        }

        String m_strPassword2 = m_etPwd2.getText().toString().trim();
        if(m_strPassword2.isEmpty()){
            return false;
        }else if(m_strPassword2.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            return false;
        }else if(!RegexUtil.checkPassword(m_strPassword2)){
            return false;
        }

        if(!m_strPassword1.equals(m_strPassword2)){
            return false;
        }

        return true;
    }

    @OnClick({R.id.tv_title_right})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_title_right:
                if(isInputValid()){
                    callHttpForChangePwd();
                }
                break;
        }
    }

    private void callHttpForChangePwd(){
        String urlDataString = "?u_telphone="+getIntent().getStringExtra("strPhone")+"&u_pwd="+m_strMd5Pwd;
        HttpClient.get(ApiStores.chengePwdTwo + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                Log.d("",response.toString());
                if(response.getResult()){
                    Utils.showToast(NewPwdActivity.this,"修改密码成功！");
                    EventBus.getDefault().post(getIntent().getStringExtra("strPhone"));
                    finish();
                }else{
                    Utils.showToast(NewPwdActivity.this,response.getMessage());
                }
            }

            @Override
            public void OnFailure(String message) {
                messageCenter("错误",message);
            }

            @Override
            public void OnRequestStart() {
                kProgressHUD.show();
            }

            @Override
            public void OnRequestFinish() {
                kProgressHUD.dismiss();
            }
        });
    }

    private void messageCenter(String title,String message){
        AlertUtils.MessageAlertShow(this, title, message);
    }

}
