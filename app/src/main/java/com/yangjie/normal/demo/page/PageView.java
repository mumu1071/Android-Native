package com.yangjie.normal.demo.page;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import com.yangjie.normal.R;

/**
 * @author yangjie
 * @description:
 * @date :2020/3/18 10:43 PM
 */
public class PageView extends View {

    private Paint myPaint;
    private String pageTitle;
    private int colorInt;
    public float mX;

    public PageView(Context context) {
        super(context);
    }

    public PageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public PageView(Context context, String pageTitle, int colorInt) {
        super(context);
        this.pageTitle = pageTitle;
        this.colorInt = colorInt;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(colorInt);
        int width = getWidth();
        int height = getHeight();
        myPaint = new Paint();
        myPaint.setColor(Color.BLACK);
        myPaint.setTextSize(100);
        canvas.drawText(pageTitle, (float) width / 2, (float) height / 2, myPaint);

    }
}
