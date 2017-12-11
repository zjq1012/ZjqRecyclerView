package com.psi.zjqrecyclerview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import com.psi.zjqrecyclerview.lib.BaseViewHolder;
import com.psi.zjqrecyclerview.lib.RecyclerViewAdapter;

/**
 * Created by dorado on 2017/12/11.
 */

public class BookAdapter extends RecyclerViewAdapter<Book> {

    public BookAdapter(Context context) {
        super(context);
    }


    @Override public BaseViewHolder createBaseViewHolder(ViewGroup parent) {
        return new BookViewHolder(parent);
    }

}
