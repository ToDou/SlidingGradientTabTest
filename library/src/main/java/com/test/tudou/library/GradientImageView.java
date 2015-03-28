package com.test.tudou.library;

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
import android.widget.ImageView;

/**
 * Created by tudou on 15-3-28.
 */
public class GradientImageView extends View {

    private float currentOffset = 1;

    private Bitmap selectBitmap;
    private Bitmap normalBitmap;

    private Paint selectPaint;
    private Paint normalPaint;

    private Matrix mSelectMatrix;
    private Matrix mNormalMatrix;

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
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureHeight(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {
        // TODO Auto-generated method stub
        int result=0; //结果
        int specMode=MeasureSpec.getMode(heightMeasureSpec);
        int specSize=MeasureSpec.getSize(heightMeasureSpec);
        switch (specMode) {
            case MeasureSpec.AT_MOST:
                result=specSize;
                break;
            case MeasureSpec.EXACTLY:
                result=specSize;
                break;
            case MeasureSpec.UNSPECIFIED:
                result=1500;
                break;
            default:
                break;
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        calculatePaint();
        //ImageView
        if (selectBitmap != null && normalBitmap != null) {
            canvas.drawBitmap(selectBitmap, mSelectMatrix, selectPaint);
            canvas.drawBitmap(normalBitmap, mNormalMatrix, normalPaint);
            requestLayout();
        }

    }

    private void calculatePaint() {
        selectPaint.setAlpha((int) (100 * (1 - currentOffset)));
        normalPaint.setAlpha((int) (100 * currentOffset));
    }

    public void setPaintData(float offset) {
        currentOffset = offset;
        invalidate();
    }

    public void setDrawables(int selectDrawable, int normalDrawable) {
        selectBitmap = BitmapFactory.decodeResource(getResources(), selectDrawable);
        normalBitmap = BitmapFactory.decodeResource(getResources(), normalDrawable);
        invalidate();
        requestLayout();
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        initMatirx();
    }

    private void initMatirx() {
        mSelectMatrix = calculateMatrix(selectBitmap);
        mNormalMatrix = calculateMatrix(normalBitmap);
        invalidate();
    }

    private Matrix calculateMatrix(Bitmap bitmap) {
        Matrix matrix = new Matrix();
        int oldWidth = bitmap.getWidth();
        int oldHeight = bitmap.getHeight();
        int vwidth = getWidth();
        int vheight = getHeight();

        float scale;
        float dx;
        float dy;


        if (oldHeight <= vwidth && oldHeight <= vheight) {
            scale = 1.0f;
        } else {
            scale = Math.min((float) vwidth / (float) oldWidth,
                    (float) vheight / (float) oldHeight);
        }

        dx = (int) ((vwidth - oldWidth * scale) * 0.5f + 0.5f);
        dy = (int) ((vheight - oldHeight * scale) * 0.5f + 0.5f);

        matrix.setScale(scale, scale);
        matrix.postTranslate(dx, dy);
        return matrix;
    }

}
