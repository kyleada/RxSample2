package com.oncedoing.bikeshop.kbase;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.StringRes;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Toast;

import com.jude.swipbackhelper.SwipeBackHelper;

import butterknife.ButterKnife;
import me.kkwang.commonlib.utils.ViewUtils;

/**
 * Created by Administrator on 2016/3/22.
 */
public abstract class KBaseSwipeToolbarActivity extends AppCompatActivity {

    protected Activity mActivity;
    protected AppBarLayout mAppBar;
    protected Toolbar mToolbar;
    protected boolean mIsHidden = false;

    protected abstract int getLayoutId();

    public abstract String getToolbarTitle();

    protected abstract boolean isActionBarNeedBackEnable();

    protected abstract void initInject();

    protected abstract void afterCreate(Bundle savedInstanceState);

    protected abstract void initPresenter();


    protected  int getAppBarLayoutId(){ return -1;}
    protected  int getToolbarLayoutId(){return -1;}

    protected  void beforeSetContentView(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
//        ColorDrawable drawable = new ColorDrawable(0xb0000000);
//        this.getWindow().setBackgroundDrawable(drawable);  //不能使主题透明
        SwipeBackHelper.onCreate(this);

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(getLayoutId());
        mActivity = this;
        if(getAppBarLayoutId() != -1){
            mAppBar = ButterKnife.findById(this,getAppBarLayoutId());
        }else{
            mAppBar =  ButterKnife.findById(this, me.kkwang.commonlib.R.id.app_bar_layout);
        }

        if(getToolbarLayoutId() != -1){
            mToolbar = ButterKnife.findById(this,getToolbarLayoutId());
        }else{
            mToolbar =  ButterKnife.findById(this, me.kkwang.commonlib.R.id.toolbar);
        }

        if (mToolbar == null) {
            throw new IllegalStateException("No toolbar");
        }
        mToolbar.setTitle(getToolbarTitle());
        setSupportActionBar(mToolbar);

        if (isActionBarNeedBackEnable()) {
            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                mToolbar.setNavigationOnClickListener(new View.OnClickListener() {
                    @Override public void onClick(View v) {
                        mActivity.finish();
                    }
                });
            }
        }

        ViewCompat.setElevation(mAppBar, ViewUtils.dpToPx(4, this));
        initInject();
        initPresenter();
        afterCreate(savedInstanceState);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }


    @Override public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override protected void onDestroy() {
        super.onDestroy();
        ButterKnife.unbind(this);
        SwipeBackHelper.onDestroy(this);
    }


    protected void setAppBarAlpha(float alpha) {
        mAppBar.setAlpha(alpha);
    }

    protected void hideOrShowToolbar() {
        mAppBar.animate()
                .translationY(mIsHidden ? 0 : -mAppBar.getHeight())
                .setInterpolator(new DecelerateInterpolator(2))
                .start();

        mIsHidden = !mIsHidden;
    }

    public void startActivityClass(Class target){
        Intent intent=new Intent(this,target);
        startActivity(intent);
    }

    public void startActivityForResultClass(Class target, int requestCode){
        Intent intent=new Intent(this,target);
        startActivityForResult(intent,requestCode);
    }




    protected void showToast(String msg) {

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showToast(@StringRes int resId) {

        Toast.makeText(this, resId, Toast.LENGTH_SHORT).show();
    }
}
