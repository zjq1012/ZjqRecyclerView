package com.psi.zjqrecyclerview.lib;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.psi.zjqrecyclerview.BookAdapter;

/**
 * Created by dorado on 2017/12/5.
 */

public abstract class OnLoadMoreListener extends RecyclerView.OnScrollListener {
  private LinearLayoutManager layoutManager;
  private int itemCount, lastPosition, lastItemCount;

  public abstract void onLoadMore();

  @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
    if (recyclerView.getLayoutManager() instanceof LinearLayoutManager) {
      layoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

      itemCount = layoutManager.getItemCount();
      lastPosition = layoutManager.findLastCompletelyVisibleItemPosition();
    } else {
      return;
    }
    if (lastItemCount != itemCount && lastPosition == itemCount - 1) {
      BookAdapter adapter = (BookAdapter) recyclerView.getAdapter();
      lastItemCount = (adapter.getFooterView() == null ? itemCount : itemCount - 1);
      this.onLoadMore();
    }
  }
}