package com.jy.jryjy;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.EditText;

import com.blankj.utilcode.util.SPUtils;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Utils;
import com.jgcj.library.base.BaseAppCompatActivity;
import com.jy.jryjy.bean.response.ResponseBaseBean;
import com.jgcj.library.util.alert.AlertUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ChangeNicknameActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "OpinionActivity";

    @BindView(R.id.et_text)
    EditText m_etText;

    private String m_strNickname;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_change_nickname;
    }

    @Override
    protected void setUpView() {
        Utils.initCommonTitle(this,"修改昵称","取消","保存");

        Utils.showKeyboard(this);
        m_etText.requestFocus();
        m_etText.setText(SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserNickame));
    }

    @OnClick({R.id.tv_title_back,R.id.tv_title_right})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.tv_title_back:
                Utils.hintKeyboard(ChangeNicknameActivity.this,m_etText);
                finish();
                break;
            case R.id.tv_title_right:
                if(isInputValid()){
                    callHttpForChangeNickname();
                }
                break;
        }
    }
    // 检查输入项是否输入正确
    @SuppressLint("NewApi")
    private boolean isInputValid() {

        m_strNickname = m_etText.getText().toString().trim();
        if (m_strNickname.isEmpty()) {
            Utils.showToast(this, "昵称不能为空");
            m_etText.requestFocus();
            return false;
        }
        if(m_strNickname.length() < 4){
            Utils.showToast(this, "昵称不能少于4个字符");
            m_etText.requestFocus();
            return false;
        }
        return true;
    }

    private void callHttpForChangeNickname(){
        String strUid = SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserId);
        String strPhone = SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserTelphone);
        String urlDataString = "?u_id="+strUid+"&u_telphone="+strPhone+"&userName="+m_strNickname;
        HttpClient.get(ApiStores.changeName + urlDataString, new HttpCallback<ResponseBaseBean>() {
            @Override
            public void OnSuccess(ResponseBaseBean response) {
                if(response.getResult()){
                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserNickame,m_strNickname);
                    finish();
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
