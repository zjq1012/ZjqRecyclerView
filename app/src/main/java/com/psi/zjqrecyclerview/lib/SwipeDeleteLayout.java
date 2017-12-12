package com.psi.zjqrecyclerview.lib;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.widget.ViewDragHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

/**
 * Created by dorado on 2017/12/11.
 */

public class SwipeDeleteLayout extends FrameLayout {
    private View contentView;
    private View deleteView;
    private int deleteViewWidth;
    private int releaseEdge;
    private ViewDragHelper viewDragHelper;

    /**
     * Delete Callback
     */
    private OnDeleteViewClickListener onDeleteViewClickListener;


    public void setOnDeleteViewClickListener(OnDeleteViewClickListener onDeleteViewClickListener) {
        this.onDeleteViewClickListener = onDeleteViewClickListener;
    }


    /**
     * Constructor
     */
    public SwipeDeleteLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public SwipeDeleteLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    private void init() {
        viewDragHelper = viewDragHelper.create(this, 1.0f, new ViewDragHelper.Callback() {
            @Override public boolean tryCaptureView(View child, int pointerId) {
                return child == contentView;
            }


            @Override public int clampViewPositionHorizontal(View child, int left, int dx) {
                int leftBound = -deleteViewWidth;
                int rightBound = 0;
                if (left <= leftBound) {
                    left = leftBound;
                }
                if (left >= rightBound) {
                    left = rightBound;
                }
                return left;
            }


            @Override public void onViewReleased(View releasedChild, float xvel, float yvel) {
                // 展开
                if (-contentView.getLeft() >= releaseEdge) {
                    viewDragHelper.settleCapturedViewAt(-deleteViewWidth, getTop());
                }
                // 关闭
                else {
                    viewDragHelper.settleCapturedViewAt(0, getTop());
                }
                invalidate();
            }


            @Override public int getViewHorizontalDragRange(View child) {
                return 1;
            }
        });
    }


    @Override public boolean onInterceptTouchEvent(MotionEvent ev) {
        return viewDragHelper.shouldInterceptTouchEvent(ev);
    }


    @Override public boolean onTouchEvent(MotionEvent event) {
        viewDragHelper.processTouchEvent(event);
        return true;
    }


    @Override protected void onFinishInflate() {
        super.onFinishInflate();
        contentView = getChildAt(1);
        deleteView = getChildAt(0);
        deleteView.setOnClickListener(new OnClickListener() {
            @Override public void onClick(View v) {
                if (onDeleteViewClickListener != null) {
                    onDeleteViewClickListener.onDeleteClick();
                    if (viewDragHelper.smoothSlideViewTo(contentView, 0, getTop())) {
                       invalidate();
                    }
                }
            }
        });
    }


    @Override protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        deleteViewWidth = deleteView.getMeasuredWidth();
    }


    @Override protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        deleteView.layout(getWidth() - deleteViewWidth, getTop(), getWidth(), getBottom());
        releaseEdge = deleteViewWidth / 2;
    }


    @Override
    public void computeScroll() {
        if (viewDragHelper.continueSettling(true)) {
            invalidate();
        }
    }

}
