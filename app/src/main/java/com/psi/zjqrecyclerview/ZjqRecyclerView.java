package com.psi.zjqrecyclerview;

import android.content.Context;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by dorado on 2017/12/4.
 */

public class ZjqRecyclerView extends FrameLayout implements SwipeRefreshLayout.OnRefreshListener {
  private Context context;
  private RecyclerView recyclerView;
  private SwipeRefreshLayout swipeRefreshLayout;

  public ZjqRecyclerView(@NonNull Context context) {
    super(context);
    init(context);
  }

  public ZjqRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    init(context);
  }

  public ZjqRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    init(context);
  }

  private void init(Context context) {
    this.context = context;
    View view = inflate(context, R.layout.layout_zjq_recycler_view, this);
    recyclerView = view.findViewById(R.id.recycler_view);
    swipeRefreshLayout = view.findViewById(R.id.refresh_layout);
  }

  public void setLayoutManager(RecyclerView.LayoutManager layoutManager) {
    this.recyclerView.setLayoutManager(layoutManager);
  }

  public void setAdapter(RecyclerView.Adapter adapter) {
    this.recyclerView.setAdapter(adapter);
  }

  @Override public void onRefresh() {

  }
}
