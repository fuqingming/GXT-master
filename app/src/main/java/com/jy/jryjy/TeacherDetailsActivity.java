package com.jy.jryjy;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.bumptech.glide.Glide;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.http.ApiStores;
import com.jgcj.library.http.HttpCallback;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.base.BaseAppCompatActivity;
import com.jy.jryjy.bean.response.ResponseFollowBean;
import com.jy.jryjy.bean.response.ResponseTeacherBean;
import com.jgcj.library.util.alert.AlertUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TeacherDetailsActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "TeacherListDetailsActivity";

    public static final int UNCONCERNED = 0;                //未关注
    public static final int ALREADY_PAID_ATTENTION_TO = 1;  //已关注

//    @BindView(R.id.recycler_view)
//    RecyclerView m_recyclerView;
//    private TeacherListDetailsAdapter m_adapterTeacherDetails;
    @BindView(R.id.iv_icon)
    ImageView m_ivIcon;
    @BindView(R.id.tv_name)
    TextView m_tvName;
    @BindView(R.id.tv_fans_count)
    TextView m_tvFansCount;
    @BindView(R.id.tv_join_count)
    TextView m_tvJoinCount;

    @BindView(R.id.iv_add)
    ImageView m_ivAdd;
    @BindView(R.id.tv_follow)
    TextView m_tvFollow;

    @BindView(R.id.tv_teacher_details)
    TextView m_tvTeacherDetails;
    @BindView(R.id.tv_text)
    TextView m_tvText;

    private boolean m_isFollow;
    private String m_strTeacherId;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_teacher_details;
    }

    @Override
    protected void init() {
        super.init();
        m_strTeacherId = getIntent().getStringExtra("strTeacherId");
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
//        m_adapterTeacherDetails = new TeacherListDetailsAdapter(this, ResponseData.initTeacherDetailsBean());
//        m_recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        m_recyclerView.setAdapter(m_adapterTeacherDetails);
//        m_recyclerView.addItemDecoration(new RecycleViewDivider(this, LinearLayoutManager.VERTICAL, 10, getResources().getColor(R.color.app_backgrount_color)));
    }

    @Override
    protected void setUpData() {
        super.setUpData();
        callHttpForTeacherDetails();
    }

    private void callHttpForTeacherDetails(){
        String urlDataString = "?t_id="+m_strTeacherId+"&uid="+ SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserId);
        HttpClient.get(ApiStores.info_Teach + urlDataString, new HttpCallback<ResponseTeacherBean>() {
            @Override
            public void OnSuccess(ResponseTeacherBean response) {
                if(response.getResult()){
                    if(response.getContent().getInfo().getA_id_attention() == ALREADY_PAID_ATTENTION_TO){
                        m_isFollow = true;
                    }else if(response.getContent().getInfo().getA_id_attention() == UNCONCERNED){
                        m_isFollow = false;
                    }
                    isFollow();

                    Glide.with(TeacherDetailsActivity.this).load(response.getContent().getInfo().getT_photo()).placeholder(R.mipmap.head_s).into(m_ivIcon);
                    m_tvName.setText(response.getContent().getInfo().getT_name());
                    m_tvFansCount.setText("粉丝 "+response.getContent().getInfo().getT_count());
                    m_tvJoinCount.setText("参与 "+response.getContent().getInfo().getT_join_count());
                    m_tvTeacherDetails.setText(response.getContent().getInfo().getT_brief());
                    m_tvText.setText(response.getContent().getInfo().getT_strategy());
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

    private void callHttpFollow(){
        String urlDataString = "?a_tid="+m_strTeacherId+"&a_uid="+ SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserId);
        HttpClient.get(ApiStores.add_attension + urlDataString, new HttpCallback<ResponseFollowBean>() {
            @Override
            public void OnSuccess(ResponseFollowBean response) {
                if(response.getResult()){
                    if(response.getContent().getInfo() == ALREADY_PAID_ATTENTION_TO){
                        m_isFollow = true;
                    }else if(response.getContent().getInfo() == UNCONCERNED){
                        m_isFollow = false;
                    }
                    isFollow();
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

    private void isFollow(){
        if(m_isFollow){
            m_ivAdd.setVisibility(View.GONE);
            m_tvFollow.setText("已关注");
        }else{
            m_ivAdd.setVisibility(View.VISIBLE);
            m_tvFollow.setText("关注");
        }
    }

    @OnClick({R.id.rl_btn,R.id.iv_back,R.id.iv_share})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.rl_btn:
                callHttpFollow();
                break;
            case R.id.iv_back:
                if(!m_isFollow){
                    setResult(RESULT_OK);
                }
                finish();
                break;
            case R.id.iv_share:
                showShare();
                break;
        }
    }

    private void showShare() {
//        OnekeyShare oks = new OnekeyShare();
//        //关闭sso授权
//        oks.disableSSOWhenAuthorize();
//
//        // title标题，微信、QQ和QQ空间等平台使用
//        oks.setTitle("tilte");
//        // titleUrl QQ和QQ空间跳转链接
//        oks.setTitleUrl("http://sharesdk.cn");
//        // text是分享文本，所有平台都需要这个字段
//        oks.setText("我是分享文本");
//        // imagePath是图片的本地路径，Linked-In以外的平台都支持此参数
////        oks.setImagePath("/sdcard/test.jpg");//确保SDcard下面存在此张图片
//        oks.setImageUrl("http://wap.ngwatch.top/uploads/20180306/7bacbbcc0e371d122d5d95891e435285.jpg");
//        // url在微信、微博，Facebook等平台中使用
//        oks.setUrl("http://sharesdk.cn");
//        // comment是我对这条分享的评论，仅在人人网使用
//        oks.setComment("我是测试评论文本");
//        // 启动分享GUI
//        oks.show(this);
    }

    @Override
    public void onBackPressed() {
        if(!m_isFollow){
            setResult(RESULT_OK);
        }
        finish();
    }
}
