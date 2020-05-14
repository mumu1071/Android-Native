package com.yangjie.normal.demo.page;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

public class PageReader extends FrameLayout {

    PageView prePage;
    PageView nextPage;
    PageView curPage;

    public PageReader(Context context) {
        super(context);
        initPageView(context);
    }

    public PageReader(Context context, AttributeSet attrs) {
        super(context, attrs);
        initPageView(context);
    }

    public PageReader(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPageView(context);
    }

    private void initPageView(Context context) {
        prePage = new PageView(context, "上页", Color.RED);
        nextPage = new PageView(context, "下页", Color.GREEN);
        curPage = new PageView(context, "当前页", Color.WHITE);

        this.addView(nextPage);
        this.addView(curPage);
        this.addView(prePage);

        this.prePage.mX = -1080;

    }

    private float mDownX = 0;
    private float mLastX = 0;
    private float moveX = 0;
    private boolean isLeftMove;

    @Override
    protected void dispatchDraw(Canvas canvas) {
//        prePage.mX = -getWidth();
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                mDownX = event.getX();
                mLastX = mDownX;
                break;
            case MotionEvent.ACTION_MOVE:
                float x = event.getX();
                moveX += (x - mLastX);
                mLastX = x;
                if (x < mDownX) {//左滑-显示下页-当页移走
                    isLeftMove = true;
                    curPage.mX = moveX;
                }
                if (x > mDownX) {//右滑-显示上页-上页进入
                    isLeftMove = false;
                    prePage.mX = moveX;
                }
                invalidate();
                break;
            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:

        }
        return true;
    }

    @Override
    protected boolean drawChild(Canvas canvas, View child, long drawingTime) {
        if (curPage == child && isLeftMove) {//左滑-显示下页-当页移走
            boolean flag;
            canvas.save();
            //canvas.clipRect(0, 0, moveX+10, getHeight());
            canvas.translate(moveX, 0);
            flag = super.drawChild(canvas, child, drawingTime);
            canvas.restore();
            return flag;
        }

        if (prePage == child && !isLeftMove) {//右滑-显示上页-上页进入
            boolean flag;
            canvas.save();
            //canvas.clipRect(0, 0, moveX+10, getHeight());
            canvas.translate(moveX, 0);
            flag = super.drawChild(canvas, child, drawingTime);
            canvas.restore();
            return flag;
        }
        if (nextPage == child) {
            return super.drawChild(canvas, child, drawingTime);
        }

        return super.drawChild(canvas, child, drawingTime);
    }


}
