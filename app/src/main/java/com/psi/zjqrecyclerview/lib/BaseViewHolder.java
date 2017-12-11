package com.psi.zjqrecyclerview.lib;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by dorado on 2017/12/11.
 */

public class BaseViewHolder<T> extends RecyclerView.ViewHolder {
    private T data;


    public BaseViewHolder(View itemView) {
        super(itemView);
    }


    public BaseViewHolder(ViewGroup parent, int holderRes) {
        super(LayoutInflater.from(parent.getContext()).inflate(holderRes, parent, false));
    }


    public void bindData(T data) {
        this.data = data;
    }
}
