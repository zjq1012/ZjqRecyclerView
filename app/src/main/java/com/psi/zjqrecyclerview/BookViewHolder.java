package com.psi.zjqrecyclerview;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.psi.zjqrecyclerview.lib.BaseViewHolder;

/**
 * Created by dorado on 2017/12/11.
 */

public class BookViewHolder extends BaseViewHolder<Book> {
    private TextView tvBook;


    public BookViewHolder(ViewGroup parent) {
        super(parent, R.layout.item_book);
        tvBook = itemView.findViewById(R.id.tv_book);
    }


    @Override public void bindData(Book data) {
        super.bindData(data);
        tvBook.setText(data.getName());
    }
}
