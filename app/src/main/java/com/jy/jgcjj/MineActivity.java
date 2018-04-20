package com.jy.jgcjj;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import com.blankj.utilcode.util.SPUtils;
import com.hyphenate.EMCallBack;
import com.jgcj.library.backhandler.OnTaskSuccessComplete;
import com.jgcj.library.cache.AsyncImageLoader;
import com.jgcj.library.constants.GlobalVariables;
import com.jgcj.library.util.DirSettings;
import com.jgcj.library.util.FileUtil;
import com.jgcj.library.util.Utils;
import com.jy.jgcjj.base.BaseAppCompatActivity;
import com.jy.jgcjj.bean.response.ResponseChangeHeadBean;
import com.jy.jgcjj.huanxin.DemoHelper;
import com.jy.jgcjj.util.Upload;
import org.greenrobot.eventbus.EventBus;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.File;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.finalteam.rxgalleryfinal.RxGalleryFinal;
import cn.finalteam.rxgalleryfinal.imageloader.ImageLoaderType;
import cn.finalteam.rxgalleryfinal.rxbus.RxBusResultDisposable;
import cn.finalteam.rxgalleryfinal.rxbus.event.ImageRadioResultEvent;

public class MineActivity extends BaseAppCompatActivity {
    private static final String LOG_TAG = "MineActivity";

    @BindView(R.id.tv_nickname)
    TextView m_tvNickname;
    @BindView(R.id.iv_icon)
    ImageView m_ivIcon;

    @Override
    protected int setLayoutResourceId() {
        return R.layout.activity_mine;
    }

    @Override
    protected void setUpView() {
        ButterKnife.bind(this);
        Utils.initCommonTitle(this,"我的",true);

        AsyncImageLoader.getInstace(this).loadBitmap(m_ivIcon,  SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserIcon), R.mipmap.head_s);
    }

    @OnClick({R.id.ll_icon,R.id.ll_nickname,R.id.btn_logout,R.id.ll_change_pwd})
    public void onViewClick(View view){
        switch (view.getId()){
            case R.id.ll_icon:
                openRadio();
                break;
            case R.id.ll_nickname:
                Intent it = new Intent(this,ChangeNicknameActivity.class);
                startActivity(it);
                break;
            case R.id.btn_logout:
                logout();
                break;
            case R.id.ll_change_pwd:
                Intent intent = new Intent(MineActivity.this,ChangePwdActivity.class);
                startActivity(intent);
                break;
        }
    }

    private void logout() {
        final ProgressDialog pd = new ProgressDialog(this);
        String st = getResources().getString(R.string.Are_logged_out);
        pd.setMessage(st);
        pd.setCanceledOnTouchOutside(false);
        pd.show();
        DemoHelper.getInstance().logout(true,new EMCallBack() {

            @Override
            public void onSuccess() {
               runOnUiThread(new Runnable() {
                    public void run() {
                        pd.dismiss();
                        SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverWifiPlay,true);
                        SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverIsReceiveMessage,true);
                        SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserId,"");
                        SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserIcon, "");
                        SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserTelphone, "");
                        SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserNickame, "");
                        finish();

                    }
                });
            }

            @Override
            public void onProgress(int progress, String status) {

            }

            @Override
            public void onError(int code, String message) {
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        pd.dismiss();
                        Toast.makeText(MineActivity.this, "unbind devicetokens failed", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        m_tvNickname.setText(SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserNickame));
    }

    /**
     * 自定义单选
     */
    private void openRadio() {
        RxGalleryFinal
                .with(this)
                .image()
                .radio()
                .imageLoader(ImageLoaderType.FRESCO)
                .subscribe(new RxBusResultDisposable<ImageRadioResultEvent>() {
                    @SuppressLint("NewApi")
                    @Override
                    protected void onEvent(ImageRadioResultEvent imageRadioResultEvent) throws Exception {
                        kProgressHUD.show();
                        String strUrl = "http://wap.ngwatch.top/index/User/changePhoto&u_id="+ SPUtils.getInstance(GlobalVariables.serverSp).getString(GlobalVariables.serverUserId);
                        final String path = imageRadioResultEvent.getResult().getOriginalPath();

                        // 压缩图片
                        Bitmap bitmap = Utils.centerSquareScaleBitmap(BitmapFactory.decodeFile(path),MineActivity.this);

                        // 如果有必要，对图片进行旋转
                        int nDegree = Utils.readPictureDegree(path);
                        if(nDegree != 0)
                        {
                            bitmap = Utils.rotateBitmap(bitmap, nDegree);
                        }

                        // 保存图片
                        FileUtil.creatDirsIfNeed(DirSettings.getAppCacheDir());
                        if(!Utils.saveBitmap(bitmap, DirSettings.getAppCacheDir(), "myself_tmp_head_pic.png"))
                        {
                            Utils.showToast(MineActivity.this, "保存图片失败");
                        }

                        File image = new File(DirSettings.getAppCacheDir()+"myself_tmp_head_pic.png");
                        final Bitmap finalBitmap = bitmap;
                        new Upload(image,MineActivity.this,kProgressHUD,new OnTaskSuccessComplete()
                        {
                            @Override
                            public void onSuccess(Object obj)
                            {
                                ResponseChangeHeadBean responseChangeHeadBean = transform((String) obj);
                                if(responseChangeHeadBean.getResult()){
                                    Toast.makeText(MineActivity.this,responseChangeHeadBean.getMessage(),Toast.LENGTH_SHORT).show();
                                    SPUtils.getInstance(GlobalVariables.serverSp).put(GlobalVariables.serverUserIcon,responseChangeHeadBean.getU_photo());
                                    m_ivIcon.setImageBitmap(finalBitmap);
                                    EventBus.getDefault().post(finalBitmap);
                                }
                            }
                        }).execute(strUrl);
                    }
                })
                .openGallery();
    }

    private ResponseChangeHeadBean transform(String response){
        JSONObject jsonObject = null;
        ResponseChangeHeadBean responseChangeHeadBean = new ResponseChangeHeadBean();
        try {
            jsonObject = new JSONObject(response);
            boolean result = jsonObject.getBoolean("result");
            String message = jsonObject.getString("message");
            int code = jsonObject.getInt("code");
            String content = jsonObject.getString("content");

            JSONObject jsonObjectContent = new JSONObject(content);
            String u_photo = jsonObjectContent.getString("u_photo");

            responseChangeHeadBean.setCode(code);
            responseChangeHeadBean.setResult(result);
            responseChangeHeadBean.setMessage(message);
            responseChangeHeadBean.setU_photo(u_photo);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return responseChangeHeadBean;
    }
}
