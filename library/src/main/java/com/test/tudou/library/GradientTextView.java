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

    private int selectColor = getResources().getColor(android.R.color.holo_blue_dark);
    private int normalColor = getResources().getColor(android.R.color.darker_gray);
    private int paintColor;

    private String content = "淘宝哈安徽";
    private float currentOffset = 1;

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

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    private void initPaint() {
        textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        textPaint.setColor(normalColor);
        textPaint.setTextSize(30f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        calculatePaintColor();
        Paint.FontMetrics fontMetrics = textPaint.getFontMetrics();
        //计算文字高度
        float fontHeight = fontMetrics.bottom - fontMetrics.top;
        //计算文字baseline
        float textWidth = textPaint.measureText(content);
        float textBaseY = getHeight() - (getHeight() - fontHeight)/2 - fontMetrics.bottom;
        canvas.drawText(content, getWidth() / 2 - textWidth / 2, textBaseY, textPaint);
    }

    private void calculatePaintColor() {
        paintColor = calculateGradientColor(selectColor, normalColor, currentOffset);
        textPaint.setColor(paintColor);
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

    public void setData(int selectColor, int normalColor, String s) {
        content = s;
        this.selectColor = selectColor;
        this.normalColor = normalColor;
        invalidate();
    }

    public void setColors(int selectColor, int normalColor) {
        this.selectColor = selectColor;
        this.normalColor = normalColor;
        invalidate();
    }

    public String getText() {
        return content;
    }

}
