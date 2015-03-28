package com.test.tudou.slidinggradienttabtest.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.test.tudou.slidinggradienttabtest.R;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by tudou on 15-3-29.
 */
public class FragmentTest2 extends Fragment {

    @InjectView(R.id.image_fragment_test2)
    ImageView mImage;
    private int position;

    public static FragmentTest2 newInstance(int postion) {
        FragmentTest2 fragmentTest2 = new FragmentTest2();
        fragmentTest2.position = postion;
        return fragmentTest2;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_test2, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.inject(this, view);

        switch (position) {
            case 0:
                mImage.setImageResource(R.mipmap.ic_image00);
                break;
            case 1:
                mImage.setImageResource(R.mipmap.ic_image01);
                break;
            case 2:
                mImage.setImageResource(R.mipmap.ic_image02);
                break;
            case 3:
                mImage.setImageResource(R.mipmap.ic_image03);
                break;
        }
    }

}
