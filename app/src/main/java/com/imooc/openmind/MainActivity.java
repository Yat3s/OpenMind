package com.imooc.openmind;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.imooc.openmind.base.BaseActivity;
import com.imooc.openmind.base.BasePresenter;
import com.imooc.openmind.topic.FeedFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import devlight.io.library.ntb.NavigationTabBar;

public class MainActivity extends BaseActivity implements FeedFragment.OnNavigationActionListener {

    private static final int TAB_INDEX_FEED = 0;
    private static final int TAB_INDEX_TAG = 1;
    private static final int TAB_INDEX_SEARCH = 2;
    private static final int TAB_INDEX_PROFILE = 3;

    @BindView(R.id.content_vp)
    ViewPager mContentVp;
    @BindView(R.id.navigation_tab_bar)
    NavigationTabBar mNavigationTabBar;

    List<Fragment> mFragments = new ArrayList<>();

    private boolean mIsNavigationTabBarHide;

    @Override
    protected int getContentLayoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        ButterKnife.bind(this);

        // Configure fragments.
        mFragments.add(FeedFragment.newInstance());
        mFragments.add(FeedFragment.newInstance());
        mFragments.add(FeedFragment.newInstance());
        mFragments.add(FeedFragment.newInstance());
        mContentVp.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return mFragments.get(position);
            }

            @Override
            public int getCount() {
                return mFragments.size();
            }
        });

        // Configure navigation tab.
        final String[] titles = new String[]{"精选", "发现", "社群", "我的"};
        final int[] icons = new int[]{R.mipmap.ic_feed, R.mipmap.ic_explore_search, R.mipmap.ic_group, R.mipmap.ic_profile};
        final ArrayList<NavigationTabBar.Model> models = new ArrayList<>();
        for (int idx = 0; idx < titles.length; idx++) {
            models.add(new NavigationTabBar.Model.Builder(
                    getResources().getDrawable(icons[idx]),
                    Color.parseColor("#392d30"))
                    .title(titles[idx])
                    .build()
            );
        }
        mNavigationTabBar.setModels(models);
        mNavigationTabBar.setBackgroundResource(R.color.md_grey_200);
        mNavigationTabBar.setViewPager(mContentVp);
    }

    @Override
    protected BasePresenter providePresenter() {
        return null;
    }

    @Override
    public void hide() {
        if (!mIsNavigationTabBarHide) {
            mNavigationTabBar.animate().translationY(mNavigationTabBar.getHeight());
        }
        mIsNavigationTabBarHide = true;
    }

    @Override
    public void show() {
        if (mIsNavigationTabBarHide) {
            mNavigationTabBar.animate().translationY(0);
        }
        mIsNavigationTabBarHide = false;
    }
}
