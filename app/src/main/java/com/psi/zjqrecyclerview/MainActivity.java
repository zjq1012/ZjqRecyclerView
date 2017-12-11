package com.psi.zjqrecyclerview;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private ZjqRecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private List<Book> data = new ArrayList<>();
    private int extraCount = 1;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = (ZjqRecyclerView) findViewById(R.id.rcv);
        data.add(new Book("1"));
        data.add(new Book("2"));
        data.add(new Book("3"));
        data.add(new Book("4"));
        data.add(new Book("5"));
        data.add(new Book("6"));
        data.add(new Book("7"));
        data.add(new Book("8"));
        data.add(new Book("9"));
        data.add(new Book("10"));
        data.add(new Book("11"));
        data.add(new Book("12"));
        data.add(new Book("14"));
        data.add(new Book("15"));
        bookAdapter = new BookAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.addOnScrollListener(new OnLoadMoreListener() {
            @Override public void onLoadMore() {
                if (recyclerView.isLoading()) return;
                View footerView = LayoutInflater.from(MainActivity.this).inflate(R.layout.layout_simple_footer, null);
                footerView.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                bookAdapter.addFooterView(footerView);
                recyclerView.setLoading(true);
                new LoadMoreThread().start();
            }
        });
        recyclerView.setAdapter(bookAdapter);

        View headerView = LayoutInflater.from(this).inflate(R.layout.layout_simple_header, null);
        bookAdapter.setHeaderView(headerView);

        bookAdapter.setData(data);
    }


    class LoadMoreThread extends Thread {
        @Override public void run() {
            super.run();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Book extra = new Book();
            Message message = new Message();
            message.what = 1;
            message.obj = extra;
            handler.sendMessage(message);
        }
    }


    private Handler handler = new Handler() {
        @Override public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1) {
                recyclerView.setLoading(false);
                Book book = (Book) msg.obj;
                book.setName("Extra" + extraCount);
                extraCount++;
                bookAdapter.removeFooterView();
                bookAdapter.addData(book);
            }
        }
    };
}
