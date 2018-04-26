package com.jy.jryjy;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import com.blankj.utilcode.util.SPUtils;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.data.Const;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.MD5;
import com.jgcj.library.util.RegexUtil;
import com.jgcj.library.util.Utils;
import com.jgcj.library.base.BaseAppCompatActivity;
import com.jy.jryjy.bean.response.ResponseLoginBean;
import com.jy.jryjy.huanxin.DemoHelper;
import com.jy.jryjy.huanxin.db.DemoDBManager;
import com.jgcj.library.util.HUDProgressUtils;
import com.jgcj.library.util.alert.AlertUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 登录界面
 * @author 付庆明
 *
 */
public class LoginActivity extends BaseAppCompatActivity {

    private static final String TAG = "LoginActivity";

    @BindView(R.id.et_phone)
    EditText m_etPhone;
    @BindView(R.id.et_password)
    EditText m_etPassword;
    private String m_strPhone;
    private String m_strMd5Pwd;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_login;
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
        Utils.initCommonTitle(this,"登录",true);
        m_etPhone.requestFocus();
    }

    @OnClick({R.id.tv_login,R.id.tv_fast_register,R.id.tv_forget_password,R.id.iv_login_wechat,R.id.ll_agreement})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_login:
                if(isInputValid()){
                    callHttpForLogin(m_strPhone,m_strMd5Pwd);
                }
                break;
            case R.id.tv_fast_register:
                Intent it = new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(it);
                break;
            case R.id.tv_forget_password:
                Intent it1 = new Intent(LoginActivity.this,ForgetPwdActivity.class);
                startActivity(it1);
                break;
            case R.id.iv_login_wechat:
                weixinLogin();
                break;
            case R.id.ll_agreement:
                Intent it2 = new Intent(LoginActivity.this,AgreementActivity.class);
                startActivity(it2);
                break;
        }
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {

        m_strPhone = m_etPhone.getText().toString().trim();
        if(m_strPhone.isEmpty())
        {
            Utils.showToast(this, "请输入手机号码");
            m_etPhone.requestFocus();
            return false;
        }
        else if(m_strPhone.length() < 11)
        {
            Utils.showToast(this, "手机号码需要11位长度");
            m_etPhone.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkMobile(m_strPhone))
        {
            Utils.showToast(this, "请输入正确的手机号码");
            m_etPhone.requestFocus();
            return false;
        }

        String m_strPassword = m_etPassword.getText().toString().trim();
		if(m_strPassword.isEmpty())
		{
			Utils.showToast(this, "请输入密码");
            m_etPassword.requestFocus();
			return false;
		}else if(m_strPassword.length() < Const.FieldRange.PASSWORD_MIN_LEN){
		    Utils.showToast(this,"密码不能少于6位");
            m_etPassword.requestFocus();
            return false;
        }else if(!RegexUtil.checkPassword(m_strPassword)){
            Utils.showToast(this,"输入6～18位数字字母组合");
            m_etPassword.requestFocus();
            return false;
        }
        m_strMd5Pwd = MD5.encode(m_strPassword);
        return true;
    }

    private void callHttpForLogin(String userPhone,String strPassword){

        String urlDataString = "?u_telphone="+userPhone+"&pwd="+strPassword;
        HttpClient.get(ApiStores.user_send_login + urlDataString, new HttpCallback<ResponseLoginBean>() {
            @Override
            public void OnSuccess(ResponseLoginBean response) {
                Log.d("",response.toString());
                if(response.getResult()){
                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserId, response.getContent().getInfo().getU_id());
                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserIcon, response.getContent().getInfo().getU_photo());
                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserTelphone, response.getContent().getInfo().getU_telphone());
                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserNickame, response.getContent().getInfo().getU_name());
                    login(response.getContent().getInfo().getU_pwd());
                }else{
                    Utils.showToast(LoginActivity.this,response.getMessage());
                    kProgressHUD.dismiss();
                }
            }

            @Override
            public void OnFailure(String message) {
                messageCenter("错误",message);
                kProgressHUD.dismiss();
            }

            @Override
            public void OnRequestStart() {
                kProgressHUD.show();
            }

            @Override
            public void OnRequestFinish() {

            }
        });
    }

    private void login(String pwd){
        DemoDBManager.getInstance().closeDB();
        DemoHelper.getInstance().setCurrentUserName(m_strPhone);
        EMClient.getInstance().login(m_strPhone, pwd, new EMCallBack() {

            @Override
            public void onSuccess() {
                EMClient.getInstance().groupManager().loadAllGroups();
                EMClient.getInstance().chatManager().loadAllConversations();
                DemoHelper.getInstance().getUserProfileManager().asyncGetCurrentUserInfo();
                kProgressHUD.dismiss();
                finish();
            }

            @Override
            public void onProgress(int progress, String status) {
                Log.d(TAG, "login: onProgress");
            }

            @Override
            public void onError(final int code, final String message) {

                kProgressHUD.dismiss();
                runOnUiThread(new Runnable() {
                    public void run() {
                        Toast.makeText(getApplicationContext(), getString(R.string.Login_failed) + message,Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    private void messageCenter(String title,String message){
        AlertUtils.MessageAlertShow(this, title, message);
    }

    private void weixinLogin() {
//        //初始化新浪平台
//        Platform weixinPf = ShareSDK.getPlatform(Wechat.NAME);
//        weixinPf.SSOSetting(false);//单点登录,设置为false，则在优先采用客户端授权，true会采用网页方式
//        //plat.isClientValid()判断是否有客户端
//        //设置监听
//        weixinPf.setPlatformActionListener(new PlatformActionListener() {
//            @Override
//            public void onComplete(Platform weixinPf, int action, HashMap<String, Object> hashMap) {
//                if (weixinPf != null) {
//                    String id = weixinPf.getDb().getUserId();
//                    String jsonData = weixinPf.getDb().exportData();
////                    disposeResult(id, jsonData, "2");
//                }
//            }
//
//            @Override
//            public void onError(Platform platform, int action, Throwable throwable) {
//                Message msg = new Message();
//                msg.what = 101;
//                msg.arg1 = 2;
//                msg.arg2 = action;
//                msg.obj = throwable;
//            }
//
//            @Override
//            public void onCancel(Platform platform, int action) {
//                Message msg = new Message();
//                msg.what = 101;
//                msg.arg1 = 3;
//                msg.arg2 = action;
//                msg.obj = platform;
//            }
//        });
//        //获取登录用户的信息，如果没有授权，会先授权，然后获取用户信息
//        weixinPf.authorize();
    }
}
