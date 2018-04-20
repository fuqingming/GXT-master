package com.jy.jgcjj;

import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.blankj.utilcode.util.SPUtils;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Utils;
import com.jy.jgcjj.base.BaseAppCompatActivity;
import com.jy.jgcjj.bean.response.ResponseBaseBean;
import com.jy.jgcjj.util.HUDProgressUtils;
import com.jy.jgcjj.util.alert.AlertUtils;
import com.kaopiz.kprogresshud.KProgressHUD;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class OpinionActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "OpinionActivity";

    KProgressHUD kProgressHUD;

    @BindView(R.id.et_text)
    EditText m_etText;

    private String m_strMsg;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_opinion;
    }

    protected void init(){
        kProgressHUD = new HUDProgressUtils().showLoadingImage(this);
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
        Utils.initCommonTitle(this,"意见反馈",true);

        Utils.showKeyboard(this);
        m_etText.requestFocus();
    }

    @OnClick({R.id.tv_commit,R.id.iv_title_back})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_commit:
                m_strMsg = m_etText.getText().toString().trim();
                if(m_strMsg.isEmpty()){
                    Utils.showToast(this, "请输入您的意见");
                    m_etText.requestFocus();
                    return;
                }
                callHttpForAttention();
                break;
            case R.id.iv_title_back:
                Utils.hintKeyboard(OpinionActivity.this,m_etText);
                finish();
                break;
        }
    }

    private void callHttpForAttention(){
        String urlDataString = "?u_id="+ SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserId) +"&content="+m_strMsg;
        HttpClient.get(ApiStores.User_Feedback + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                Log.d("",response.toString());
                if(response.getResult()){
                    Utils.showToast(OpinionActivity.this,response.getMessage());
                    Utils.hintKeyboard(OpinionActivity.this,m_etText);
                    finish();
                }else{
                    messageCenter("提示",response.getMessage());
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
