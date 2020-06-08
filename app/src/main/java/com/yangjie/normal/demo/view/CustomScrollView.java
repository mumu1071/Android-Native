package com.yangjie.normal.demo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ScrollView;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/18 10:55 PM
 */
public class CustomScrollView extends ScrollView {
    public Callbacks mCallbacks;

    public CustomScrollView(Context context) {
        super(context);
    }

    public CustomScrollView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomScrollView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setCallbacks(Callbacks callbacks) {
        this.mCallbacks = callbacks;
    }

    @Override
    protected void onScrollChanged(int l, int t, int oldl, int oldt) {
        super.onScrollChanged(l, t, oldl, oldt);
        if (mCallbacks != null) {
            mCallbacks.onScrollChanged(l, t, oldl, oldt);
        }
    }

    //定义接口用于回调
    public interface Callbacks {
        void onScrollChanged(int x, int y, int oldx, int oldy);
    }


    @Override
    protected void dispatchDraw(Canvas canvas) {
        Log.w("yangjietest","dispatchDraw");
        super.dispatchDraw(canvas);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        this.postInvalidate();
        Log.w("yangjietest","onTouchEvent");
        return super.onTouchEvent(ev);
    }
}