package com.test.tudou.slidinggradienttabtest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.test.tudou.slidinggradienttabtest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tudou on 15-3-27.
 */
public class FragmentTest extends Fragment {

    private int position;
    @InjectView(R.id.text_content)
    TextView mText;

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
    }
}
