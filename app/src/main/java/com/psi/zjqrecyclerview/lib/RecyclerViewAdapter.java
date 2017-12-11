package com.psi.zjqrecyclerview.lib;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import com.psi.zjqrecyclerview.Book;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by dorado on 2017/12/4.
 */

public abstract class RecyclerViewAdapter<T> extends RecyclerView.Adapter<BaseViewHolder<T>> {
    private static final int TYPE_NORMAL = 1;
    private static final int TYPE_HEADER = 2;
    private static final int TYPE_FOOTER = 3;

    private List<T> data = new ArrayList();
    private View headerView;
    private View footerView;

    private Context context;


    public RecyclerViewAdapter(Context context) {
        this.context = context;
    }


    @Override public BaseViewHolder<T> onCreateViewHolder(ViewGroup parent, int viewType) {
        switch (viewType) {
            case TYPE_HEADER:
                return new BaseViewHolder<T>(headerView);
            case TYPE_FOOTER:
                return new BaseViewHolder<T>(footerView);
        }
        return createBaseViewHolder(parent);
    }


    public abstract BaseViewHolder createBaseViewHolder(ViewGroup parent);


    @Override public void onBindViewHolder(BaseViewHolder<T> holder, int position) {
        int itemViewType = getItemViewType(position);
        if (TYPE_NORMAL != itemViewType) {
            return;
        }
        int realPosition = getRealPosition(position);
        T currentData = data.get(realPosition);
        holder.bindData(currentData);
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


    /**
     * HeaderView
     */
    public void setHeaderView(View headerView) {
        this.headerView = headerView;
        this.notifyItemInserted(0);
    }


    /**
     * FooterView
     */
    public void addFooterView(View footerView) {
        this.footerView = footerView;
        footerView.post(new Runnable() {
            @Override public void run() {
                notifyItemInserted(getItemCount());
            }
        });
    }


    public void removeFooterView() {
        this.notifyItemRemoved(getItemCount());
        this.footerView = null;
    }


    public View getFooterView() {
        return footerView;
    }


    /**
     * 设置Data
     */
    public void setData(List<T> data) {
        this.data = data;
        this.notifyDataSetChanged();
    }


    public void addData(T data) {
        if (data == null) {
            return;
        }
        this.data.add(data);
        notifyItemInserted(getItemCount());
    }
}
