package com.test.tudou.slidinggradienttabtest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;
import android.widget.TextView;

import com.test.tudou.slidinggradienttabtest.R;
import com.test.tudou.slidinggradienttabtest.ui.view.GradientImageView;
import com.test.tudou.slidinggradienttabtest.ui.view.GradientTextView;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tudou on 15-3-27.
 */
public class FragmentTest extends Fragment {

    private int position;
    @InjectView(R.id.text_content)
    TextView mText;
    @InjectView(R.id.gradient_text_fragment_test)
    GradientTextView mGradientTextView;
    @InjectView(R.id.seek_bar_fragment_test)
    SeekBar mSeekBar;
    @InjectView(R.id.gradient_image_fragment_test)
    GradientImageView mGradientImageView;

    public static FragmentTest newInstance(int postion) {
        FragmentTest fragmentTest = new FragmentTest();
        fragmentTest.position = postion;
        return fragmentTest;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        mText.setText("position: " + position);
        mGradientImageView.setDrawables(R.mipmap.ic_tab_c0_selected, R.mipmap.ic_launcher);
        mSeekBar.setMax(100);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                mGradientTextView.setPaintData((float)progress / 100f);
                mGradientImageView.setPaintData((float)progress / 100f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
