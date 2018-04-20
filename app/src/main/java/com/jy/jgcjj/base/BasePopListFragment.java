package com.jy.jgcjj.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.OvershootInterpolator;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.PopupWindow;

import com.github.jdsjlzx.recyclerview.LRecyclerView;
import com.github.jdsjlzx.recyclerview.LRecyclerViewAdapter;
import com.github.jdsjlzx.util.RecyclerViewStateUtils;
import com.github.jdsjlzx.view.CommonHeader;
import com.github.jdsjlzx.view.LoadingFooter;
import com.jgcj.library.R;
import com.jgcj.library.http.HttpClient;
import com.jgcj.library.util.Constant;
import com.jy.jgcjj.adapter.BaseRecyclerAdapter;
import com.jy.jgcjj.view.error.ErrorLayout;
import com.jy.jgcjj.view.popupwindow.CommonFilterPop;

import java.util.ArrayList;
import java.util.List;

import jp.wasabeef.recyclerview.adapters.AnimationAdapter;
import jp.wasabeef.recyclerview.adapters.ScaleInAnimationAdapter;

public abstract class BasePopListFragment<T> extends Fragment {
    /**每一页展示多少条数据*/
    protected int mCurrentPage = 0;
    protected int totalPage = 10;
    protected final int REQUEST_COUNT = 10;
    protected LayoutInflater mInflater;
    private Context mContext;
    protected LRecyclerView mRecyclerView;
    ErrorLayout mErrorLayout;
    protected Button toTopBtn;

    protected BaseRecyclerAdapter<T> mListAdapter;
    protected LRecyclerViewAdapter mRecyclerViewAdapter;

    protected boolean isRequestInProcess = false;
    protected boolean mIsStart = false;

    protected CommonHeader headerView;
    private View mContentView;

    private View.OnClickListener mFooterClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            RecyclerViewStateUtils.setFooterViewState(getActivity(), mRecyclerView, getPageSize(), LoadingFooter.State.Loading, null);
            requestData();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if(mContentView != null)
        {
            ViewGroup vgParent = (ViewGroup) mContentView.getParent();
            if (vgParent != null)
            {
                vgParent.removeView(mContentView);
            }
            return mContentView;
        }
        HttpClient.init(getContext().getApplicationContext(),false);
        mContext = getContext();
        mContentView = inflater.inflate(getLayoutId(),null);
        this.mInflater = inflater;

        mRecyclerView = mContentView.findViewById(R.id.recycler_view);
        toTopBtn = mContentView.findViewById(R.id.top_btn);
        mErrorLayout = mContentView.findViewById(R.id.error_layout);
        initData();
        initView();
        return mContentView;
    }

    protected View getContentView() {
        return mContentView;
    }

    protected int getLayoutId() {
        return 0;
    }

    public Context getMContext() {
        return mContext;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    protected void initData(){};

    protected void initView() {

        if (mListAdapter != null) {
            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
        } else {
            mListAdapter = getListAdapter();

            if (requestDataIfViewCreated()) {
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
                mCurrentPage++;
                isRequestInProcess = true;
                requestData();
            } else {
                mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            }
        }

        AnimationAdapter adapter = new ScaleInAnimationAdapter(mListAdapter);
        adapter.setFirstOnly(false);
        adapter.setDuration(500);
        adapter.setInterpolator(new OvershootInterpolator(.5f));

        mRecyclerViewAdapter = new LRecyclerViewAdapter(adapter);
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setHasFixedSize(true);

        initLayoutManager();

        mRecyclerView.setLScrollListener(new LRecyclerView.LScrollListener() {

            @Override
            public void onScrollUp() {
                // 滑动时隐藏float button
                if (toTopBtn.getVisibility() == View.VISIBLE) {
                    toTopBtn.setVisibility(View.GONE);
                    animate(toTopBtn, R.anim.floating_action_button_hide);
                }
            }

            @Override
            public void onScrollDown() {
                if (toTopBtn.getVisibility() != View.VISIBLE) {
                    toTopBtn.setVisibility(View.VISIBLE);
                    animate(toTopBtn, R.anim.floating_action_button_show);
                }
            }

            @Override
            public void onScrolled(int distanceX, int distanceY) {

                if (null != headerView) {
                    if (distanceY == 0 || distanceY < headerView.getHeight()) {
                        toTopBtn.setVisibility(View.GONE);
                    }
                } else {
                    if (distanceY == 0) {
                        toTopBtn.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onScrollStateChanged(int state) {

            }

        });

        mErrorLayout.setOnLayoutClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                mCurrentPage = 0;
                mErrorLayout.setErrorType(ErrorLayout.NETWORK_LOADING);
                mCurrentPage++;
                isRequestInProcess = true;
                requestData();
            }
        });

        toTopBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.scrollToPosition(0);
                toTopBtn.setVisibility(View.GONE);
            }
        });

        //设置头部加载颜色
        mRecyclerView.setHeaderViewColor(R.color.gray_text, R.color.gray_text, R.color.app_bg);
        //设置底部加载颜色
        mRecyclerView.setFooterViewColor(R.color.gray_text, R.color.gray_text, R.color.app_bg);
    }

    protected boolean requestDataIfViewCreated() {
        return true;
    }

    private void animate(View view, int anim) {
        if (anim != 0) {
            Animation a = AnimationUtils.loadAnimation(view.getContext(), anim);
            view.startAnimation(a);
        }
    }

    /** 设置顶部正在加载的状态 */
    protected void setSwipeRefreshLoadingState() {
    }

    /**
     * 设置顶部加载完毕的状态
     */
    protected void setSwipeRefreshLoadedState() {
        if(null != mRecyclerView) {
            mRecyclerView.refreshComplete(REQUEST_COUNT);
        }

    }

    // 完成刷新
    protected void executeOnLoadFinish() {
        setSwipeRefreshLoadedState();
        isRequestInProcess = false;
        mIsStart = false;
    }

    protected abstract BaseRecyclerAdapter<T> getListAdapter();

    protected void requestData() {
    }

    protected void onRefreshView() {
        if (isRequestInProcess) {
            return;
        }
        // 设置顶部正在刷新
        setSwipeRefreshLoadingState();
        mCurrentPage = 0;
        mCurrentPage++;
        isRequestInProcess = true;
        requestData();

    }

    protected abstract void initLayoutManager();

    protected int getPageSize() {
        return Constant.PAGE_SIZE;
    }

    protected void executeOnLoadDataSuccess(List<T> data) {
        if (data == null) {
            data = new ArrayList<T>();
        }

        mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);

        // 判断等于是因为最后有一项是listview的状态
        if (mListAdapter.getItemCount() == 0) {

            if (needShowEmptyNoData()) {
                mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            }
        }

        if (mCurrentPage == 1) {
            mListAdapter.setDataList(data);
            if(mListAdapter.getItemCount() == 0){
                mErrorLayout.setErrorType(ErrorLayout.NODATA);
            }
        } else {
            mListAdapter.addAll(data);
        }
    }

    protected boolean needShowEmptyNoData() {
        return true;
    }

    protected void executeOnLoadDataError(String error) {
        executeOnLoadFinish();
        if (mCurrentPage == 1) {
            mErrorLayout.setErrorType(ErrorLayout.NETWORK_ERROR);
        } else {

            //在无网络时，滚动到底部时，mCurrentPage先自加了，然而在失败时却
            //没有减回来，如果刻意在无网络的情况下上拉，可以出现漏页问题
            //find by TopJohn
            mCurrentPage--;

            mErrorLayout.setErrorType(ErrorLayout.HIDE_LAYOUT);
            mListAdapter.notifyDataSetChanged();
        }
    }

    protected String getNoDataTip() {
        return "";
    }

    protected abstract BaseAdapter setInitAdapter() ;
    /**
     * 筛选pop
     */
    private CommonFilterPop mPopupWindow;

    /**
     * 列表选择popupWindow
     *
     * @param parentView        父View
     * @param itemClickListener 列表项点击事件
     */
    public void showFilterPopupWindow(View parentView,
                                      AdapterView.OnItemClickListener itemClickListener,
                                      CustomerDismissListener dismissListener) {
        showFilterPopupWindow(parentView, itemClickListener, dismissListener, 0);
    }

    /**
     * 列表选择popupWindow
     *
     * @param parentView        父View
     * @param itemClickListener 列表项点击事件
     */
    public void showFilterPopupWindow(View parentView,
                                      AdapterView.OnItemClickListener itemClickListener,
                                      CustomerDismissListener dismissListener, float alpha) {

        // 判断当前是否显示
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
        mPopupWindow = new CommonFilterPop(getMContext(), setInitAdapter());
        mPopupWindow.setOnDismissListener(dismissListener);
        // 绑定筛选点击事件
        mPopupWindow.setOnItemSelectedListener(itemClickListener);
        // 如果透明度设置为0的话,则默认设置为0.6f
        if (0 == alpha) {
            alpha = 0.6f;
        }
        // 设置背景透明度
//        WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
//        lp.alpha = alpha;
//        getActivity().getWindow().setAttributes(lp);
        // 显示pop
        mPopupWindow.showAsDropDown(parentView);
    }

    /**
     * Tab筛选栏切换
     *  @param isChecked         选中状态
     * @param showView          展示pop的跟布局
     * @param itemClickListener 点击回调
     * @param tabs              所有的cb(需要几个输入几个就可以,cb1,cb2....)
     */
    public void filterTabToggle(boolean isChecked, View showView, AdapterView.OnItemClickListener itemClickListener, final CheckBox... tabs) {
        if (isChecked) {
            if (tabs.length <= 0) {
                return;
            }
            // 第一个checkBox为当前点击选中的cb,其他cb进行setChecked(false);
            for (int i = 1; i < tabs.length; i++) {
                tabs[i].setChecked(false);
            }

            showFilterPopupWindow(showView, itemClickListener, new CustomerDismissListener() {
                @Override
                public void onDismiss() {
                    super.onDismiss();
                    // 当pop消失时对第一个cb进行.setChecked(false)操作
                    tabs[0].setChecked(false);
                }
            });
        } else {
            // 关闭checkBox时直接隐藏popuwindow
            hidePopListView();
        }
    }
    /**
     * Tab筛选栏切换
     *
     * @param isChecked         选中状态
     * @param showView          展示pop的跟布局
     * @param itemClickListener 点击回调
     * @param tabs              所有的cb(需要几个输入几个就可以,cb1,cb2....)
     */
    public void filterTabToggleT(boolean isChecked, View showView, AdapterView.OnItemClickListener itemClickListener, final CheckBox... tabs) {
        if (isChecked) {
            if (tabs.length <= 0) {
                return;
            }
            // 第一个checkBox为当前点击选中的cb,其他cb进行setChecked(false);
            for (int i = 1; i < tabs.length; i++) {
                tabs[i].setChecked(false);
            }

            showFilterPopupWindow(showView, itemClickListener, new CustomerDismissListener() {
                @Override
                public void onDismiss() {
                    super.onDismiss();
                    // 当pop消失时对第一个cb进行.setChecked(false)操作
                    tabs[0].setChecked(false);
                }
            });
        } else {
            // 关闭checkBox时直接隐藏popuwindow
            hidePopListView();
        }
    }

    /**
     * 自定义OnDismissListener
     */
    public class CustomerDismissListener implements PopupWindow.OnDismissListener {
        @Override
        public void onDismiss() {
            // 当pop消失的时候,重置背景色透明度
            WindowManager.LayoutParams lp = getActivity().getWindow().getAttributes();
            lp.alpha = 1.0f;
            getActivity().getWindow().setAttributes(lp);
        }
    }

    /**
     * 隐藏pop
     */
    public void hidePopListView() {
        // 判断当前是否显示,如果显示则dismiss
        if (mPopupWindow != null && mPopupWindow.isShowing()) {
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
    }
}
