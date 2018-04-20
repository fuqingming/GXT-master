
package com.jy.jgcjj.adapter;


import android.content.Intent;
import android.text.TextPaint;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.jgcjj.ClassActivity;
import com.jy.jgcjj.FragmentLive;
import com.jy.jgcjj.MainActivity;
import com.jy.jgcjj.view.recyclerview.BaseRecyclerViewHolder;
import com.jy.jgcjj.R;
import com.jy.jgcjj.bean.response.ResponseFragmentHallBean;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class FragmentHallAdapter extends BaseRecyclerAdapter<ResponseFragmentHallBean> {

    @BindView(R.id.tv_title1)
    TextView ivTitle;
    @BindView(R.id.tv_time1)
    TextView ivTime;
    @BindView(R.id.ll_intent1)
    LinearLayout llIntentLive1;

    @BindView(R.id.tv_title2)
    TextView ivTitleRecord;
    @BindView(R.id.iv_pic2)
    ImageView ivPicRecord;
    @BindView(R.id.tv_time2)
    TextView ivTimeRecord;
    @BindView(R.id.tv_title_text2)
    TextView ivTitleText2;

    @BindView(R.id.iv_icon3)
    ImageView ivIconAnalysis;
    @BindView(R.id.tv_name3)
    TextView ivNameAnalysis;
    @BindView(R.id.tv_time3)
    TextView ivTimeAnalysis;
    @BindView(R.id.tv_title3)
    TextView ivTitleAnalysis;
    @BindView(R.id.tv_title_text3)
    TextView ivTitleText3;

    @BindView(R.id.tv_title4)
    TextView ivTitleUmoversoty;
    @BindView(R.id.tv_time4)
    TextView ivTimeUmoversoty;
    @BindView(R.id.iv_pic4)
    ImageView ivPicUmoversoty;

    @BindView(R.id.tv_title5)
    TextView ivTitleUmoversoty5;
    @BindView(R.id.tv_time5)
    TextView ivTimeUmoversoty5;
    @BindView(R.id.iv_pic5)
    ImageView ivPicUmoversoty5;


    public FragmentHallAdapter() {
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_fragment_hall_info;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, final ResponseFragmentHallBean data, final int position) {
        TextPaint tp;
        ivTitle.setText(data.getLiveEvery1().getTitle());
        tp = ivTitle .getPaint();
        tp.setFakeBoldText(true);
        ivTime.setText(data.getLiveEvery1().getTime());
        llIntentLive1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent it = new Intent(mContext, ClassActivity.class);
//                mContext.startActivity(it);
                ((MainActivity)mContext).setCurrentTab(FragmentLive.class);
            }
        });

        ivTitleRecord.setText(data.getLiveEvery2().getTitle());
        ivPicRecord.setImageDrawable(mContext.getResources().getDrawable(data.getLiveEvery2().getPic()));
        ivTimeRecord.setText(data.getLiveEvery2().getTime());
        ivTitleText2.setText(data.getLiveEvery2().getText());
        tp = ivTitleRecord .getPaint();
        tp.setFakeBoldText(true);

        ivIconAnalysis.setImageDrawable(mContext.getResources().getDrawable(data.getLiveEvery3().getIcon()));
        ivNameAnalysis.setText(data.getLiveEvery3().getName());
        ivTimeAnalysis.setText(data.getLiveEvery3().getTime());
        ivTitleAnalysis.setText(data.getLiveEvery3().getTitle());
        ivTitleText3.setText(data.getLiveEvery3().getTitleText());
        tp = ivTitleAnalysis .getPaint();
        tp.setFakeBoldText(true);

        ivTitleUmoversoty.setText(data.getLiveEvery4().getTitle());
        ivTimeUmoversoty.setText(data.getLiveEvery4().getTime());
        ivPicUmoversoty.setImageDrawable(mContext.getResources().getDrawable(data.getLiveEvery4().getPic()));
        tp = ivTitleUmoversoty .getPaint();
        tp.setFakeBoldText(true);

        ivTitleUmoversoty5.setText(data.getLiveEvery5().getTitle());
        ivTimeUmoversoty5.setText(data.getLiveEvery5().getTime());
        ivPicUmoversoty5.setImageDrawable(mContext.getResources().getDrawable(data.getLiveEvery5().getPic()));
        tp = ivTitleUmoversoty5 .getPaint();
        tp.setFakeBoldText(true);
    }

}