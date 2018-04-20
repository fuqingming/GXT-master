package com.jy.jgcjj;

import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.jakewharton.rxbinding2.widget.RxTextView;
import com.jgcj.library.data.Const;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.RegexUtil;
import com.jgcj.library.util.SmsSendCounter;
import com.jgcj.library.util.Utils;
import com.jy.jgcjj.base.BaseAppCompatActivity;
import com.jy.jgcjj.bean.response.ResponseBaseBean;
import com.jy.jgcjj.huanxin.DemoHelper;
import com.jy.jgcjj.util.HUDProgressUtils;
import com.jy.jgcjj.util.alert.AlertUtils;
import com.kaopiz.kprogresshud.KProgressHUD;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class RegisterActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "RegisterActivity";
    private static final int RESEND_VERIFY_CODE_SECOND = 60;
    private SmsSendCounter m_myCount = null;

    KProgressHUD kProgressHUD;

    @BindView(R.id.et_phone)
    EditText m_etPhone;
    @BindView(R.id.et_verify_number)
    EditText m_etVerifyNumber;
    @BindView(R.id.et_password)
    EditText m_etPassword;
    @BindView(R.id.tv_send_verify_code)
    TextView m_tvSendVerifyCode;
    @BindView(R.id.tv_btn)
    TextView m_tvBtn;

    private String m_strPhone;
    private String m_strVerifyNumber;
    private String m_strPassword;
    private String m_strPhoneSend = "";

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_register;
    }

    protected void init(){
        kProgressHUD = new HUDProgressUtils().showLoadingImage(this);
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
        Utils.initCommonTitle(this,"注册",true);
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
                            m_tvSendVerifyCode.setTextColor(getResources().getColor(R.color.black));
                        }
                    }
                });
        RxTextView.textChanges(m_etPassword)
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
                        changeButtonBackground();
                    }
                });
        RxTextView.textChanges(m_etVerifyNumber)
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
                        changeButtonBackground();
                    }
                });
    }

    private void changeButtonBackground(){
        if(isInputValided()){
            m_tvBtn.setBackgroundResource(R.drawable.shape_yellow_red_bg);
        }else{
            m_tvBtn.setBackgroundResource(R.drawable.shape_light_dark_bg);
        }
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

    private boolean isPhoneCode(){
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
            Utils.showToast(this, "验证码为4位");
            m_etVerifyNumber.requestFocus();
            return false;
        }

        m_strPassword = m_etPassword.getText().toString().trim();
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

        return true;
    }

    // 检查输入项是否输入正确
    private boolean isInputValided() {

        if("".equals(m_strPhoneSend)){
            return false;
        }

        if(!m_strPhoneSend.equals(m_strPhone)){
            return false;
        }

        // 验证码
        String strVerifyNumber = m_etVerifyNumber.getText().toString().trim();
        if(strVerifyNumber.isEmpty())
        {
            return false;
        }
        else if(strVerifyNumber.length() < 4)
        {
            return false;
        }

        String strPassword = m_etPassword.getText().toString().trim();
        if(strPassword.isEmpty())
        {
            return false;
        }else if(strPassword.length() < Const.FieldRange.PASSWORD_MIN_LEN){
            return false;
        }else if(!RegexUtil.checkPassword(strPassword)){
            return false;
        }

        return true;
    }

    @OnClick({R.id.tv_send_verify_code,R.id.tv_btn,R.id.ll_agreement})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_send_verify_code:
                if(isPhoneCode()){
                    callHttpForSendYzm(m_strPhone);
                }
                break;
            case R.id.tv_btn:

                if(isInputValid()){
                    if(!m_strPhoneSend.equals(m_strPhone)){
                        Utils.showToast(RegisterActivity.this,"手机号已改变，请重新获取验证码");
                        return;
                    }
                    callHttpForRegister(m_strPhone, m_strPassword,m_strVerifyNumber);
                }
                break;
            case R.id.ll_agreement:
                Intent it = new Intent(RegisterActivity.this,AgreementActivity.class);
                startActivity(it);
                break;
        }
    }

    private void callHttpForRegister(final String userPhone,final String pwd,String code){
        String urlDataString = "?u_telphone="+userPhone+"&u_pwd="+pwd+"&u_code="+code;
        HttpClient.get(ApiStores.user_register + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                if(response.getResult()){
                    DemoHelper.getInstance().setCurrentUserName(userPhone);
                    Toast.makeText(getApplicationContext(), getResources().getString(R.string.Registered_successfully), Toast.LENGTH_SHORT).show();
                    finish();
                }else{
                    Utils.showToast(RegisterActivity.this,response.getMessage());
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

    private void callHttpForSendYzm(String userPhone){
        String urlDataString = "?u_telphone="+userPhone+"&type=1";
        HttpClient.get(ApiStores.user_send_yzm + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                Log.d("",response.toString());
                if(response.getResult()){
                    m_tvSendVerifyCode.setEnabled(false);
                    m_tvSendVerifyCode.setText(String.valueOf(RESEND_VERIFY_CODE_SECOND));
                    m_myCount = new SmsSendCounter(RegisterActivity.this,m_tvSendVerifyCode, RESEND_VERIFY_CODE_SECOND * 1000, 1000);
                    m_myCount.start();

                    m_strPhoneSend = m_strPhone;
                }else{
                    Utils.showToast(RegisterActivity.this,response.getMessage());
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

    private void stopMyCount()
    {
        if (m_myCount != null)
        {
            m_myCount.cancel();
            m_myCount = null;
        }

        m_tvSendVerifyCode.setEnabled(true);
        m_tvSendVerifyCode.setText("获取手机验证码");
    }
}
