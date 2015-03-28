package com.test.tudou.library;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import com.test.tudou.library.model.TabValue;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tudou on 15-3-28.
 */
public class GradientTabView extends LinearLayout {

    @InjectView(android.R.id.icon)
    GradientImageView mGradientIcon;
    @InjectView(android.R.id.text1)
    GradientTextView mGradientText;

    public GradientTabView(Context context) {
        this(context, null);
    }

    public GradientTabView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public GradientTabView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater.from(getContext()).inflate(R.layout.tab_image_text, this);
        ButterKnife.inject(this);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        ButterKnife.reset(this);
    }

    public void setData(TabValue tabValue) {
        if (tabValue == null) return;
        mGradientIcon.setDrawables(tabValue.selectIcon, tabValue.normalIcon);
        mGradientText.setData(tabValue.selectColor, tabValue.normalColor, tabValue.title);
    }

    public void updateOffset(float offset) {
        mGradientIcon.setPaintData(offset);
        mGradientText.setPaintData(offset);
    }
}
