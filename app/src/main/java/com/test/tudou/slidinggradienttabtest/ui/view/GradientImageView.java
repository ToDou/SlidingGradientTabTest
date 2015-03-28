package com.test.tudou.slidinggradienttabtest.ui.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by tudou on 15-3-28.
 */
public class GradientImageView extends View {

    private float currentOffset;

    private Bitmap selectBitmap;
    private Bitmap normalBitmap;

    private Paint selectPaint;
    private Paint normalPaint;

    public GradientImageView(Context context) {
        this(context, null);
    }

    public GradientImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        selectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        normalPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        selectPaint.setAlpha(100);
        normalPaint.setAlpha(100);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        calculatePaint();

        if (selectBitmap != null && normalBitmap != null) {
                canvas.drawBitmap(selectBitmap, 0, 0, selectPaint);
            canvas.drawBitmap(normalBitmap, 0, 0, normalPaint);
            requestLayout();
        }

    }

    private void calculatePaint() {
        selectPaint.setAlpha((int)(100 * (1 - currentOffset)));
        normalPaint.setAlpha((int)(100 * currentOffset));
        Log.e("color", currentOffset + "");
    }

    private int calculateGradientColor(int startColor, int endColor, float offset) {
        int r0 = (startColor >> 16) & 0xff;
        int r1 = (endColor >> 16) & 0xff;
        int g0 = (startColor >> 8) & 0xff;
        int g1 = (endColor >> 8) & 0xff;
        int b0 = startColor & 0xff;
        int b1 = endColor & 0xff;

        int r2 = (int)(r0 * (1 - offset) + r1 * offset);
        int g2 = (int)(g0 * (1 - offset) + g1 * offset);
        int b2 = (int)(b0 * (1 - offset) + b1 * offset);

        return Color.argb(100, r2, g2, b2);
    }

    public void setPaintData(float offset) {
        currentOffset = offset;
        invalidate();
    }

    public void setDrawables(int selectDrawable, int normalDrawable) {
        selectBitmap = BitmapFactory.decodeResource(getResources(), selectDrawable);
        normalBitmap = BitmapFactory.decodeResource(getResources(), normalDrawable);
        invalidate();
    }

}
