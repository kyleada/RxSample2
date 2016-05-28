package com.oncedoing.bikeshop.home;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.widget.Toast;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.manage.ManageFragment;
import com.oncedoing.bikeshop.market.MarketFragment;

import java.util.ArrayList;

import butterknife.Bind;
import im.fir.sdk.FIR;
import im.fir.sdk.VersionCheckCallback;
import me.kkwang.commonlib.base.BaseActivity;

public class HomeActivity extends BaseActivity {

    private ArrayList<Fragment> mFragments = new ArrayList<>();

    private String[] mTitles = {"管理", "营销", "发现", "我的"};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private int[] mIconUnselectIds = {
            R.drawable.tab_home_unselect, R.drawable.tab_speech_unselect,
            R.drawable.tab_contact_unselect, R.drawable.tab_more_unselect};

    private int[] mIconSelectIds = {
            R.drawable.tab_home_select, R.drawable.tab_speech_select,
            R.drawable.tab_contact_select, R.drawable.tab_more_select};


    @Bind(R.id.vp)
    ViewPager mViewPager;
    @Bind(R.id.tabbar)
    CommonTabLayout mTabLayout;
    

    @Override
    protected int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        initFragmentAndVp();
        initTabLayout();
		checkUpdate();
    }

	private void checkUpdate(){
		FIR.checkForUpdateInFIR(AppConstants.FIR_API_TOKEN, new VersionCheckCallback() {
			@Override
			public void onSuccess(String versionJson) {
				Log.i("fir","check from fir.im success! " + "\n" + versionJson);
			}

			@Override
			public void onFail(Exception exception) {
				Log.i("fir", "check fir.im fail! " + "\n" + exception.getMessage());
			}

			@Override
			public void onStart() {
				Toast.makeText(getApplicationContext(), "正在获取", Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onFinish() {
				Toast.makeText(getApplicationContext(), "获取完成", Toast.LENGTH_SHORT).show();
			}
		});
	}

    private void initFragmentAndVp(){

        mFragments.add(ManageFragment.newInstance());
        mFragments.add(MarketFragment.newInstance());
        mFragments.add(MarketFragment.newInstance());
        mFragments.add(MarketFragment.newInstance());

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

    }

    private void initTabLayout(){
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        mTabLayout.setTabData(mTabEntities);
        mTabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                mViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

        mViewPager.setOffscreenPageLimit(3);
        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                mTabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private class MyPagerAdapter extends FragmentPagerAdapter {
        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }
    }

}
