package com.jy.jryjy.adapter;

import android.content.Context;
import android.text.Layout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.LiveInformationBean;
import com.jy.jryjy.view.recyclerview.BaseRecyclerViewAdapter;
import com.jy.jryjy.view.recyclerview.BaseRecyclerViewHolder;

import java.util.List;

import butterknife.BindView;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class InformationAdapter extends BaseRecyclerViewAdapter<LiveInformationBean> {


    @BindView(R.id.ll_item_click)
    LinearLayout m_llItemClick;

    @BindView(R.id.iv_icon)
    ImageView m_ivIcon;
    @BindView(R.id.tv_name)
    TextView m_tvName;
    @BindView(R.id.tv_title)
    TextView m_tvTitle;
    @BindView(R.id.tv_time)
    TextView m_tvTime;
    @BindView(R.id.tv_share_count)
    TextView m_tvShareCount;
    @BindView(R.id.tv_msg_count)
    TextView m_tvMsgCount;

    public InformationAdapter(Context context, List<LiveInformationBean> datas) {
        super(context, datas);
    }

    @Override
    protected int getContentView(int viewType) {
        return R.layout.item_information;
    }

    @Override
    protected void covert(BaseRecyclerViewHolder holder, LiveInformationBean data, final int position) {

        m_ivIcon.setImageResource(data.getIcon());
        m_tvName.setText(data.getName());
        m_tvTitle.setText(data.getTitle());
        m_tvTime.setText(data.getTime());
        m_tvShareCount.setText(data.getShareCount());
        m_tvMsgCount.setText(data.getCommentCount());

        final TextView tvText = holder.getView().findViewById(R.id.tv_text);
        final TextView tvTextALl = holder.getView().findViewById(R.id.tv_text_line_all);
        final TextView tvShowALl = holder.getView().findViewById(R.id.tv_all);
        tvTextALl.setText(data.getText());
        tvText.setText(data.getText());
        tvTextALl.post(new Runnable() {
            @Override
            public void run() {
                int iLineCount = tvTextALl.getLineCount();
                tvTextALl.setVisibility(View.GONE);
                if(iLineCount > 3){
                    tvShowALl.setVisibility(View.VISIBLE);




                    Layout layout = tvText.getLayout();
                    String text = tvText.getText().toString();
                    int start = 0;
                    int end;
                    StringBuilder strTextBuilder = new StringBuilder();

                    for (int i = 0; i < 3; i++) {
                        end = layout.getLineEnd(i);
                        String line = text.substring(start, end); //指定行的内容
                        if(i == 2){
                            strTextBuilder.append(line.substring(0, (int) Math.floor(line.length()/2)));
                            strTextBuilder.append("...");
                        }else{
                            strTextBuilder.append(line);
                        }
                        start = end;
                    }
                    tvText.setText(strTextBuilder.toString());

                    int height = tvText.getMeasuredHeight();

                    ViewGroup.LayoutParams tvShowAllPara = tvShowALl.getLayoutParams();
                    tvShowAllPara.height = height;
                    tvShowALl.setLayoutParams(tvShowAllPara);
                }else{
                    tvShowALl.setVisibility(View.GONE);
                }
            }
        });

        tvShowALl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tvTextALl.setVisibility(View.VISIBLE);
                tvText.setVisibility(View.GONE);
                tvShowALl.setVisibility(View.GONE);
            }
        });

        m_llItemClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                m_ListenerSelectFragment.OnSelectClick(position);
            }
        });
    }

}
