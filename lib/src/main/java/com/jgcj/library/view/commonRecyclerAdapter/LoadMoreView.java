package com.jgcj.library.view.commonRecyclerAdapter;

/**
 * $desc
 *
 * @author zxb
 * @date 16/8/6 下午8:50
 */
public interface LoadMoreView {
    enum LoadState{
        IDLE,LOADING,ERROR
    }

    LoadState getLoadState();

    void setLoadState(LoadState loadState);
}
