package com.psi.zjqrecyclerview.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import com.psi.zjqrecyclerview.R;

/**
 * Created by dorado on 2017/12/11.
 */

public class ZjqRecyclerView extends FrameLayout {
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private Context context;
    private boolean isLoading;


    public ZjqRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        init(attrs);
    }


    public ZjqRecyclerView(
        @NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        init(attrs);
    }


    private void init(AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.ZjqRecyclerView);
        boolean refreshEnable = attributes.getBoolean(R.styleable.ZjqRecyclerView_refreshEnable, false);

        View view = inflate(context, R.layout.layout_zjq_recycler_view, this);
        swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
        recyclerView = view.findViewById(R.id.recycler_view);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override public void onRefresh() {
                if (onRefreshListener != null) {
                    onRefreshListener.onRefresh();
                }
            }
        });
        if (!refreshEnable) {
            swipeRefreshLayout.setEnabled(false);
        }

    }


    /**
     * 设置Adapter
     */

    public void setAdapter(RecyclerView.Adapter adapter) {
        this.recyclerView.setAdapter(adapter);
    }


    /**
     * 设置LayoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
        this.recyclerView.setLayoutManager(layoutManager);
    }


    /**
     * RefreshListener
     */
    private OnRefreshListener onRefreshListener;


    public void setRefreshListener(OnRefreshListener onRefreshListener) {
        this.onRefreshListener = onRefreshListener;
    }


    /**
     * ScrollListener
     */
    public void addOnScrollListener(RecyclerView.OnScrollListener onScrollListener) {
        this.recyclerView.addOnScrollListener(onScrollListener);
    }


    /**
     * 是否正在加载数据
     */
    public boolean isLoading() {
        return isLoading;
    }


    public void setLoading(boolean loading) {
        isLoading = loading;
    }


    /**
     * 正在刷新
     */
    public void setRefreshing(boolean refreshing) {
        swipeRefreshLayout.setRefreshing(refreshing);
    }
}
