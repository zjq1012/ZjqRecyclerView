package com.psi.zjqrecyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;
import com.psi.zjqrecyclerview.lib.OnDeleteViewClickListener;
import com.psi.zjqrecyclerview.lib.SwipeDeleteLayout;

public class SwipeActivity extends AppCompatActivity {
private SwipeDeleteLayout swipeDeleteLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);
        swipeDeleteLayout = (SwipeDeleteLayout) findViewById(R.id.swipe_layout);
        swipeDeleteLayout.setOnDeleteViewClickListener(new OnDeleteViewClickListener() {
            @Override public void onDeleteClick() {
                Toast.makeText(SwipeActivity.this, "Delete", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
