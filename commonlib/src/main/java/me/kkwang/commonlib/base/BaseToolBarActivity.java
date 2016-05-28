package me.kkwang.commonlib.base;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;

import butterknife.ButterKnife;
import me.kkwang.commonlib.R;
import me.kkwang.commonlib.utils.ToastUtils;
import me.kkwang.commonlib.utils.ViewUtils;

/**
 * Created by kw on 2016/3/11.
 */
public abstract class BaseToolBarActivity extends AppCompatActivity {


    protected Activity mActivity;
    protected AppBarLayout mAppBar;
    protected Toolbar mToolbar;
    protected boolean mIsHidden = false;

    protected abstract int getLayoutId();

    public abstract String getToolbarTitle();

    protected abstract boolean isActionBarNeedBackEnable();

    protected  void beforeSetContentView(){

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
    };
    protected abstract void afterCreate(Bundle savedInstanceState);

    protected  int getAppBarLayoutId(){ return -1;};

    protected  int getToolbarLayoutId(){return -1;};



    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeSetContentView();
        setContentView(getLayoutId());
        mActivity = this;
        if(getAppBarLayoutId() != -1){
            mAppBar = ButterKnife.findById(this,getAppBarLayoutId());
        }else{
            mAppBar =  ButterKnife.findById(this,R.id.app_bar_layout);
        }

        if(getToolbarLayoutId() != -1){
            mToolbar = ButterKnife.findById(this,getToolbarLayoutId());
        }else{
            mToolbar =  ButterKnife.findById(this,R.id.toolbar);
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

        //if (Build.VERSION.SDK_INT >= 21) {
        //    mAppBar.setElevation(10.6f);
        //}
        ViewCompat.setElevation(mAppBar, ViewUtils.dpToPx(4, this));
        afterCreate(savedInstanceState);

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

    public void startActivityForResultClass(Class target, Bundle bundle, int requestCode){
        Intent intent=new Intent(this,target);
        intent.putExtra("info", bundle);
        startActivityForResult(intent,requestCode);
    }

    public void setTitle(String title){
        getSupportActionBar().setTitle(title);
    }

    protected void showToast(String msg) {
        ToastUtils.show(msg);
    }

}
