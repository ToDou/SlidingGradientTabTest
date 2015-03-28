package com.test.tudou.library;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by tudou on 15-3-28.
 */
public class GradientTextView extends View {

    private Paint textPaint;

    private int selectColor = getResources().getColor(android.R.color.holo_blue_light);
    private int normalColor = getResources().getColor(android.R.color.holo_green_light);
    private int paintColor;

    private String content = "淘宝哈安徽";
    private float currentOffset;

    public GradientTextView(Context context) {
        this(context, null);
    }

    public GradientTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaint();
    }

    private void initPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(normalColor);
        textPaint.setTextSize(40f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        calculatePaintColor();
        canvas.drawText(content, 100, 100, textPaint);
    }

    private void calculatePaintColor() {
        paintColor = calculateGradientColor(selectColor, normalColor, currentOffset);
        textPaint.setColor(paintColor);
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

    public void setText(String s) {
        content = s;
        invalidate();
    }

    public String getText() {
        return content;
    }

}
