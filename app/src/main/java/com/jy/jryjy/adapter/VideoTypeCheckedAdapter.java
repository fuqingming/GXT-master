package com.jy.jryjy.adapter;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.jy.jryjy.R;
import com.jy.jryjy.bean.base.TeacherAnalysisBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by HH
 * Date: 2017/11/13
 */

public class VideoTypeCheckedAdapter extends BaseAdapter{

    /**
     * 筛选条件数据
     */
    private List<TeacherAnalysisBean> mDatas = new ArrayList<>();
    /**
     * 布局加载器
     */
    private LayoutInflater mInflater;

    public VideoTypeCheckedAdapter(Context context, List<TeacherAnalysisBean> mDatas) {
        this.mDatas = mDatas;
        mInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public Object getItem(int i) {
        return mDatas.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup viewGroup) {
        ViewHolder viewHolder = null;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = mInflater.inflate(R.layout.item_video_type_checked, null);
            viewHolder.mTitleTv = convertView.findViewById(R.id.tv_name_checkbox);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if("".equals(mDatas.get(i).getNeican_id())){
            viewHolder.mTitleTv.setText(mDatas.get(i).getT_nic_name());
        }else{
            if("1".equals(mDatas.get(i).getNeican_id())){
                viewHolder.mTitleTv.setText("早盘内参");
            }else{
                viewHolder.mTitleTv.setText("夜盘内参");
            }
        }

        return convertView;
    }

    /**
     * vh
     */
    public class ViewHolder {
        /**
         * 筛选项文字tv
         */
        TextView mTitleTv;
    }
}
