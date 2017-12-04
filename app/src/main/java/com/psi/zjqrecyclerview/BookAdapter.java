package com.psi.zjqrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dorado on 2017/12/4.
 */

public class BookAdapter extends RecyclerView.Adapter {
  private static final int TYPE_NORMAL = 1;
  private static final int TYPE_HEADER = 2;
  private static final int TYPE_FOOTER = 3;

  private List<Book> data = new ArrayList();
  private View headerView;
  private View footerView;

  private Context context;

  public BookAdapter(Context context) {
    this.context = context;
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    switch (viewType) {
      case TYPE_HEADER:
        return new HeaderHolder(headerView);
      case TYPE_FOOTER:
        return new FooterHolder(footerView);
    }
    View view = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);
    return new BookHolder(view);
  }

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

  }

  @Override public int getItemViewType(int position) {
   if (position == 0 && headerView != null){
     return TYPE_HEADER;
   } else if (position ==)
  }

  @Override public int getItemCount() {
    if (headerView == null && footerView == null) {
      return data.size();
    } else if (headerView != null && footerView != null) {
      return data.size() + 2;
    } else {
      return data.size() + 1;
    }
  }

  class BookHolder extends RecyclerView.ViewHolder {
    private TextView textView;

    public BookHolder(View itemView) {
      super(itemView);
      textView = itemView.findViewById(R.id.tv_book);
    }
  }

  class HeaderHolder extends RecyclerView.ViewHolder {

    public HeaderHolder(View itemView) {
      super(itemView);
    }
  }

  class FooterHolder extends RecyclerView.ViewHolder {

    public FooterHolder(View itemView) {
      super(itemView);
    }
  }
}
