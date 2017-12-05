package com.psi.zjqrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
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
    int itemViewType = getItemViewType(position);
    if (TYPE_NORMAL != itemViewType) {
      return;
    }
    int realPosition = getRealPosition(position);
    Book book = data.get(realPosition);
    BookHolder bookHolder = (BookHolder) holder;
    bookHolder.tvBook.setText(book.getName());
  }

  private int getRealPosition(int position) {
    if (headerView != null) {
      return position - 1;
    }
    return position;
  }

  @Override public int getItemViewType(int position) {
    if (headerView != null && position == 0) {
      return TYPE_HEADER;
    } else if (footerView != null && position == getItemCount() - 1) {
      return TYPE_FOOTER;
    }
    return TYPE_NORMAL;
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
    private TextView tvBook;

    public BookHolder(View itemView) {
      super(itemView);
      tvBook = itemView.findViewById(R.id.tv_book);
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

  public void setHeaderView(View headerView) {
    this.headerView = headerView;
    this.notifyItemInserted(0);
  }

  public void addFooterView(View footerView) {
    this.footerView = footerView;
    footerView.post(new Runnable() {
      @Override public void run() {
        notifyItemInserted(getItemCount());
      }
    });
  }

  public View getFooterView() {
    return footerView;
  }

  public void removeFooterView() {
    this.notifyItemRemoved(getItemCount());
    this.footerView = null;
  }

  public void setData(List<Book> data) {
    this.data = data;
    this.notifyDataSetChanged();
  }

  public void addData(Book data) {
    if (data == null) {
      return;
    }
    this.data.add(data);
    notifyItemInserted(getItemCount());
  }
}
