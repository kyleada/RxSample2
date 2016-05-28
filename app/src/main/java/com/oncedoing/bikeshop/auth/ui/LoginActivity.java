package com.oncedoing.bikeshop.auth.ui;

import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.KeyEvent;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;
import com.oncedoing.bikeshop.BikeShopApp;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.home.HomeActivity;
import com.oncedoing.bikeshop.network.Repository;
import com.oncedoing.bikeshop.widget.CleanEditText;

import javax.inject.Inject;

import butterknife.OnClick;
import me.kkwang.commonlib.base.BaseActivity;
import me.kkwang.commonlib.utils.RegexUtils;
import me.kkwang.commonlib.utils.ViewUtils;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @desc 登录界面
 * Created by devilwwj on 16/1/24.
 */
public class LoginActivity extends BaseActivity{

    private static final String TAG = "loginActivity";


    @Inject
    Repository rep;

    CleanEditText accountEdit;
    CleanEditText passwordEdit;
    MaterialDialog pDialog;




    @OnClick(R.id.btn_login)
    public void onClickLogin(){
         String username = "18640986081";//accountEdit.getText().toString();
         String password = "123456";//passwordEdit.getText().toString()
                ;
        pDialog = new MaterialDialog.Builder(this)
                .content("登陆中...")
                .progress(true, 0)
                .show();
        pDialog.getWindow().setLayout(ViewUtils.dpToPxInt(240,getApplicationContext()), ViewGroup.LayoutParams.WRAP_CONTENT);;
        rep.login(username, password)
           .subscribeOn(Schedulers.io())
           .observeOn(AndroidSchedulers.mainThread())
           .subscribe(new Subscriber<Void>() {
               @Override public void onCompleted() {

               }


               @Override public void onError(Throwable e) {
                   pDialog.cancel();
                   e.printStackTrace();
                   showToast("失败");
               }


               @Override public void onNext(Void aVoid) {
//                   pDialog.dismissWithAnimation();
                   pDialog.cancel();
                   showToast("成功");
                   startActivityClass(HomeActivity.class);
                   LoginActivity.this.finish();
                   overridePendingTransition(R.anim.fade_in, R.anim.scale_out);
               }
           });
    }


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        BikeShopApp app = (BikeShopApp) getApplication();
        app.component().inject(this);
        initViews();
    }


    /**
     * 初始化视图
     */
    private void initViews() {
        accountEdit = (CleanEditText) this.findViewById(R.id.et_email_phone);
        accountEdit.setImeOptions(EditorInfo.IME_ACTION_NEXT);
        accountEdit.setTransformationMethod(HideReturnsTransformationMethod
                .getInstance());
        passwordEdit = (CleanEditText) this.findViewById(R.id.et_password);
        passwordEdit.setImeOptions(EditorInfo.IME_ACTION_DONE);
        passwordEdit.setImeOptions(EditorInfo.IME_ACTION_GO);
        passwordEdit.setTransformationMethod(PasswordTransformationMethod
                .getInstance());
        passwordEdit.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            @Override
            public boolean onEditorAction(TextView v, int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_DONE
                        || actionId == EditorInfo.IME_ACTION_GO) {
                    clickLogin();
                }
                return false;
            }
        });
    }

    private void clickLogin() {
        String account = accountEdit.getText().toString();
        String password = passwordEdit.getText().toString();
        if (checkInput(account, password)) {
            // TODO: 请求服务器登录账号
        }
    }

    /**
     * 检查输入
     *
     * @param account
     * @param password
     * @return
     */
    public boolean checkInput(String account, String password) {
        // 账号为空时提示
        if (account == null || account.trim().equals("")) {
            Toast.makeText(this, R.string.tip_account_empty, Toast.LENGTH_LONG)
                    .show();
        } else {
            // 账号不匹配手机号格式（11位数字且以1开头）
            if (!RegexUtils.checkMobile(account)) {
                Toast.makeText(this, R.string.tip_account_regex_not_right,
                        Toast.LENGTH_LONG).show();
            } else if (password == null || password.trim().equals("")) {
                Toast.makeText(this, R.string.tip_password_can_not_be_empty,
                        Toast.LENGTH_LONG).show();
            } else {
                return true;
            }
        }

        return false;
    }

}
