package com.jy.jryjy;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.RegexUtil;
import com.jgcj.library.util.SmsSendCounter;
import com.jgcj.library.util.Utils;
import com.jgcj.library.base.BaseAppCompatActivity;
import com.jy.jryjy.bean.response.ResponseBaseBean;
import com.jgcj.library.util.HUDProgressUtils;
import com.jgcj.library.util.alert.AlertUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import java.util.concurrent.TimeUnit;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class ForgetPwdActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "ForgetPwdActivity";
    private static final int RESEND_VERIFY_CODE_SECOND = 60;
    private SmsSendCounter m_myCount = null;

    @BindView(R.id.et_phone)
    EditText m_etPhone;
    @BindView(R.id.et_verify_number)
    EditText m_etVerifyNumber;
    @BindView(R.id.tv_send_verify_code)
    TextView m_tvSendVerifyCode;
    @BindView(R.id.tv_title_right)
    TextView m_tvNext;

    private String m_strPhone;
    private String m_strVerifyNumber;
    private String m_strPhoneSend = "";

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_forget_pwd;
    }

    @Override
    protected void setUpView() {
        EventBus.getDefault().register(this);
        Utils.initCommonTitle(this,"忘记密码",true);
        m_tvNext.setText("下一步");
        m_tvSendVerifyCode.setEnabled(false);
        RxTextView.textChanges(m_etPhone)
                .debounce(300, TimeUnit.MILLISECONDS)
                .switchMap(new Function<CharSequence, ObservableSource<String>>() {
                    @Override
                    public ObservableSource<String> apply(CharSequence charSequence) throws Exception {
                        return Observable.just(charSequence.toString());
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new io.reactivex.functions.Consumer<String>() {
                    @Override
                    public void accept(String queryData) throws Exception {
                        if(isPhoneValid()){
                            m_tvSendVerifyCode.setEnabled(true);
                            m_tvSendVerifyCode.setTextColor(getResources().getColor(R.color.red));
                        }else{
                            m_tvSendVerifyCode.setEnabled(false);
                            m_tvSendVerifyCode.setTextColor(getResources().getColor(R.color.black_dark));
                        }
                    }
                });
    }

    private boolean isPhoneValid(){
        m_strPhone = m_etPhone.getText().toString().trim();
        if(m_strPhone.isEmpty())
        {
            m_etPhone.requestFocus();
            return false;
        }
        else if(m_strPhone.length() < 11)
        {
            m_etPhone.requestFocus();
            return false;
        }
        else if(!RegexUtil.checkMobile(m_strPhone))
        {
            m_etPhone.requestFocus();
            return false;
        }
        return true;
    }

    // 检查输入项是否输入正确
    private boolean isInputValid() {

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

        if("".equals(m_strPhoneSend)){
            Utils.showToast(this,"请获取验证码！");
            return false;
        }

        // 验证码
        m_strVerifyNumber = m_etVerifyNumber.getText().toString().trim();
        if(m_strVerifyNumber.isEmpty())
        {
            Utils.showToast(this, "请输入验证码");
            m_etVerifyNumber.requestFocus();
            return false;
        }
        else if(m_strVerifyNumber.length() < 4)
        {
            Utils.showToast(this, "验证码错误");
            m_etVerifyNumber.requestFocus();
            return false;
        }

        return true;
    }

    @OnClick({R.id.tv_send_verify_code,R.id.tv_title_right})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_send_verify_code:
                callHttpForSendYzm(m_strPhone);
                break;
            case R.id.tv_title_right:
                if(isInputValid()){
                    if(!m_strPhoneSend.equals(m_strPhone)){
                        Utils.showToast(ForgetPwdActivity.this,"手机号已改变，请重新获取验证码");
                        return;
                    }
                    callHttpForChangePwd();
                }
                break;

        }
    }

    private void callHttpForSendYzm(final String userPhone){
        String urlDataString = "?u_telphone="+userPhone+"&type=2";
        HttpClient.get(ApiStores.user_send_yzm + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                Log.d("",response.toString());
                if(response.getResult()){
                    m_strPhoneSend = userPhone;
                    m_tvSendVerifyCode.setEnabled(false);
                    m_tvSendVerifyCode.setText(String.valueOf(RESEND_VERIFY_CODE_SECOND));
                    m_myCount = new SmsSendCounter(ForgetPwdActivity.this,m_tvSendVerifyCode, RESEND_VERIFY_CODE_SECOND * 1000, 1000);
                    m_myCount.start();
                }else{
                    Utils.showToast(ForgetPwdActivity.this,response.getMessage());
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

    private void callHttpForChangePwd(){
        String urlDataString = "?u_telphone="+m_strPhone+"&u_code="+m_strVerifyNumber;
        HttpClient.get(ApiStores.chengePwdOne + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                Log.d("",response.toString());
                if(response.getResult()){
                    Intent it = new Intent(ForgetPwdActivity.this,NewPwdActivity.class);
                    it.putExtra("strPhone",m_strPhone);
                    startActivity(it);
                }else{
                    Utils.showToast(ForgetPwdActivity.this,response.getMessage());
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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onDataSynEvenBus(String isFinished) {
        ForgetPwdActivity.this.finish();
    }
}
