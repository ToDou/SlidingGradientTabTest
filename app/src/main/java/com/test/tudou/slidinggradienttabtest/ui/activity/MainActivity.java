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
import com.test.tudou.library.model.TabValue;
import com.test.tudou.library.transform.ZoomOutPageTransformer;
import com.test.tudou.slidinggradienttabtest.R;
import com.test.tudou.slidinggradienttabtest.ui.fragment.FragmentTest;
import com.test.tudou.slidinggradienttabtest.ui.fragment.FragmentTest2;

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
        mViewPager.setPageTransformer(true, new ZoomOutPageTransformer());
        mSlidingTab.setTextColor(getResources().getColor(R.color.theme_primary_light));
        mSlidingTab.setIndicatorHeight(0);
        mSlidingTab.setIndicatorColor(getResources().getColor(R.color.theme_primary_light));
        mSlidingTab.setViewPager(mViewPager);
        final int pageMargin = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 4, getResources()
                .getDisplayMetrics());
        mViewPager.setPageMargin(pageMargin);
    }

    public class MyPagerAdapter extends FragmentPagerAdapter implements SlidingGradientTab.GradientTabProvider {

        private final String[] TITLE_CONSIGNOR = {"Account", "Favorite", "World", "Chart"};
        private final int[] SELECT_COLOR = {R.mipmap.ic_tab_c0_select, R.mipmap.ic_tab_c1_select, R.mipmap.ic_tab_c2_select, R.mipmap.ic_tab_c3_select};
        private final int[] NORMAL_COLOR = {R.mipmap.ic_tab_c0, R.mipmap.ic_tab_c1, R.mipmap.ic_tab_c2, R.mipmap.ic_tab_c3};

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public TabValue getPageGradientView(int position) {
            TabValue tabValue = new TabValue(SELECT_COLOR[position], NORMAL_COLOR[position], getResources().getColor(R.color.tab_color_select), getResources().getColor(R.color.tab_color_normal), TITLE_CONSIGNOR[position]);
            return tabValue;
        }

        @Override
        public Fragment getItem(int position) {
            Fragment fragment;
            fragment = FragmentTest2.newInstance(position);
            return fragment;
        }

        @Override
        public int getCount() {
            return TITLE_CONSIGNOR.length;
        }
    }

}
