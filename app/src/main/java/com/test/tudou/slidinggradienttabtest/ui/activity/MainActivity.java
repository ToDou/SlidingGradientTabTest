package com.test.tudou.slidinggradienttabtest.ui.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;

import com.test.tudou.library.SlidingGradientTab;
import com.test.tudou.slidinggradienttabtest.R;
import com.test.tudou.slidinggradienttabtest.ui.fragment.FragmentTest;

import butterknife.ButterKnife;
import butterknife.InjectView;


public class MainActivity extends ActionBarActivity {

    @InjectView(R.id.view_pager)
    ViewPager mViewPager;
    @InjectView(R.id.tab)
    SlidingGradientTab mSlidingTab;

    private MyPagerAdapter mAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.inject(this);

        mAdapter = new MyPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);
        mSlidingTab.setTextColor(getResources().getColor(R.color.theme_primary_light));
        mSlidingTab.setIndicatorColor(getResources().getColor(R.color.theme_primary_light));
        mSlidingTab.setViewPager(mViewPager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        mViewPager.setPageMargin(pageMargin);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter {

        private final String[] TITLE_CONSIGNOR = {"待支付", "待出发", "已出发", "已完成"};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return TITLE_CONSIGNOR[position];
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment = FragmentTest.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return TITLE_CONSIGNOR.length;
        }
    }

}
