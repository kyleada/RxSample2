package com.oncedoing.bikeshop.manage_customer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.text.InputType;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.afollestad.materialdialogs.MaterialDialog;
import com.jude.swipbackhelper.SwipeBackHelper;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.manage_bike.activity.SaleAddActivity;
import com.oncedoing.bikeshop.manage_customer.utils.MyDatePickerTheme;
import com.oncedoing.bikeshop.model.CustomerEntity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.aigestudio.datepicker.bizs.themes.DPTManager;
import cn.aigestudio.datepicker.cons.DPMode;
import cn.aigestudio.datepicker.views.DatePicker;
import me.kkwang.commonlib.base.BaseToolBarActivity;
import me.kkwang.commonlib.utils.DateUtils;
import me.kkwang.commonlib.utils.TimeUtils;

public class CustomerEditActivity extends BaseToolBarActivity {


    @Bind(R.id.tv_customer_tel)
    TextView tvCustomerTel;
    @Bind(R.id.rl_customer_tel)
    RelativeLayout rlCustomerTel;
    @Bind(R.id.tv_customer_gender)
    TextView tvCustomerGender;
    @Bind(R.id.rl_customer_gender)
    RelativeLayout rlCustomerGender;
    @Bind(R.id.tv_customer_now_bike)
    TextView tvCustomerNowBike;
    @Bind(R.id.rl_customer_now_bike)
    RelativeLayout rlCustomerNowBike;
    @Bind(R.id.tv_customer_age)
    TextView tvCustomerAge;
    @Bind(R.id.rl_customer_age)
    RelativeLayout rlCustomerAge;
    @Bind(R.id.tv_address)
    TextView tvCustomerAddress;
    @Bind(R.id.rl_customer_address)
    RelativeLayout rlCustomerAddress;
    @Bind(R.id.tv_time_in)
    TextView tvTimeIn;
    @Bind(R.id.rl_customer_in_time)
    RelativeLayout rlCustomerInTime;
    @Bind(R.id.tv_bio)
    TextView tvCustomerBio;
    @Bind(R.id.rl_bio)
    LinearLayout rlBio;
    @Bind(R.id.ll_bottombar_wrapper)
    LinearLayout llBottombarWrapper;
    private String state;
    private CustomerEntity customerEntity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // TODO: add setContentView(...) invocation
        ButterKnife.bind(this);
    }

    @OnClick({R.id.rl_customer_tel, R.id.rl_customer_gender, R.id.rl_customer_now_bike, R.id.rl_customer_age, R.id.rl_customer_address, R.id.rl_customer_in_time, R.id.rl_bio})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.rl_customer_tel:
                editTel();
                break;
            case R.id.rl_customer_gender:
                editGender();
                break;
            case R.id.rl_customer_now_bike:
                editNowBike();
                break;
            case R.id.rl_customer_age:
                editAge();
                break;
            case R.id.rl_customer_address:
                editAddress();
                break;
            case R.id.rl_customer_in_time:
                editTimeIn();
                break;
            case R.id.rl_bio:
                editBio();
                break;
        }
    }

    public static interface NAV_STATE {

        String NAVIGATION_KEY = "navigation_key";
        String SELL_ADD_CUSTOMER = "sell_add_customer";
        String ADD_CUSTOMER = "add_customer";
        String EDIT_CUSTOMER = "edit_customer";
    }


    @Bind(R.id.tv_save_customer)
    TextView tvSaveCustomer;
    @Bind(R.id.tv_save_sell)
    TextView tvSaveSell;
    @Bind(R.id.tv_continue_add)
    TextView tvContinueAdd;


    @Bind(R.id.tv_customer_name)
    TextView tvCustomerName;

    @OnClick(R.id.rl_customer_name)
    public void onClickEditName() {
        new MaterialDialog.Builder(this)
                .title("客户姓名")
                .inputRangeRes(1, 8, R.color.material_red_400)
                .input(null, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(@NonNull MaterialDialog dialog, CharSequence input) {
                        tvCustomerName.setText(input);
                    }
                }).show();
    }

    //一种是从首页的新增用户图标进入，此时点击保存只需要保存到服务端和数据库即可
    //一种是从客户列表进入，此时点击保存应该是要把数据回传到列表页
    @OnClick(R.id.tv_save_customer)
    public void onClickSaveCustomer() {
        getAddedCustomerData();
        saveLocal();
        saveServer();
        if (TextUtils.equals(state, NAV_STATE.EDIT_CUSTOMER)) {
            CustomerEntity customerEntity = wrapControlData();
            Intent intent = CustomerHelper.wrapCustomerIntent(customerEntity);
            setResult(RESULT_OK, intent);
        }
        finish();
    }

    //一种是从新增销售进入，保存并销售需要回传数据, SELL_ADD_CUSTOMER
    //一种情况是从首页新增客户进入，保存并销售需要携带用户信息主动进入新增销售页面,ADD_CUSTOMER
    @OnClick(R.id.tv_save_sell)
    public void onClickSaveSell() {
        getAddedCustomerData();
        saveLocal();
        saveServer();
        if (TextUtils.equals(state, NAV_STATE.SELL_ADD_CUSTOMER)) {
            CustomerEntity customerEntity = wrapControlData();
            Intent intent = CustomerHelper.wrapCustomerIntent(customerEntity);
            setResult(RESULT_OK, intent);
            finish();
        } else if (TextUtils.equals(state, NAV_STATE.ADD_CUSTOMER)) {
            CustomerEntity customerEntity = wrapControlData();
            SaleAddActivity.start(CustomerEditActivity.this, customerEntity);
            finish();
        } else if(TextUtils.equals(state, NAV_STATE.EDIT_CUSTOMER)){
            CustomerEntity customerEntity = wrapControlData();
            SaleAddActivity.start(CustomerEditActivity.this, customerEntity);
            finish();
        }

    }

    @OnClick(R.id.tv_continue_add)
    public void onClickContinueAdd() {
        clearAllCtrl();
        customer_name = "";
    }


    @Override
    protected void beforeSetContentView() {
        super.beforeSetContentView();
        SwipeBackHelper.onCreate(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_customer_edit;
    }


    @Override
    protected boolean isActionBarNeedBackEnable() {
        return true;
    }


    @Override
    public String getToolbarTitle() {
        return "编辑客户信息";
    }


    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);
        Intent intent = getIntent();
        state = intent.getStringExtra(NAV_STATE.NAVIGATION_KEY);
        if (TextUtils.equals(state, NAV_STATE.SELL_ADD_CUSTOMER) || TextUtils.equals(state, NAV_STATE.EDIT_CUSTOMER)) {
            tvContinueAdd.setVisibility(View.GONE);
        }

        if (TextUtils.equals(state, NAV_STATE.EDIT_CUSTOMER)) {
            customerEntity = intent.getParcelableExtra(AppConstants.CUSTOMER_INFO_KEY);
            tvCustomerName.setText(customerEntity.getCustomer_name());
            tvTimeIn.setText(DateUtils.formatDate(customerEntity.getCreate_time(),DateUtils.TYPE_02));
            tvCustomerGender.setText(customerEntity.getCustomer_gender());
            tvCustomerTel.setText(customerEntity.getCustomer_phone());
            tvCustomerNowBike.setText(customerEntity.getCustomer_bike());
            tvCustomerAddress.setText(customerEntity.getCustomer_address());

        }else{
            tvTimeIn.setText(TimeUtils.getCurrentDate());
        }


    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        SwipeBackHelper.onPostCreate(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SwipeBackHelper.onDestroy(this);
    }


    String customer_name;

    private void getAddedCustomerData() {
        customer_name = tvCustomerName.getText().toString();

    }

    private CustomerEntity wrapControlData() {
        CustomerEntity customer = new CustomerEntity();
        customer.setCustomer_name(customer_name);
        return customer;
    }

    private void saveLocal() {

    }



    private void saveServer() {

    }

    void editTel(){
        new MaterialDialog.Builder(this)
                .title("电话号码")
//                .content(R.string.input_content)
                .inputType(InputType.TYPE_NUMBER_VARIATION_NORMAL)
                .inputRange(8,11)
                .input(null, null, new MaterialDialog.InputCallback() {
                    @Override
                    public void onInput(MaterialDialog dialog, CharSequence input) {
                        tvCustomerTel.setText(input);
                    }
                }).show();
    }

    void editGender(){
        new MaterialDialog.Builder(this)
                .title("性别")
                .items(R.array.items_gender)
                .itemsCallback(new MaterialDialog.ListCallback() {
                    @Override
                    public void onSelection(MaterialDialog dialog, View view, int which, CharSequence text) {
                        tvCustomerGender.setText(text);
                    }
                })
                .show();
    }

    void editNowBike(){

    }

    void editAge(){

    }

    void editAddress(){

    }

    void editTimeIn(){

        final AlertDialog dialog = new AlertDialog.Builder(mActivity).create();
        dialog.show();
        DPTManager.getInstance().initCalendar(new MyDatePickerTheme());
        DatePicker picker = new DatePicker(mActivity);
        picker.setDate(TimeUtils.getCurrentYear(), TimeUtils.getCurrentMonth());
        picker.setFestivalDisplay(false);
        picker.setHolidayDisplay(false);
        picker.setDeferredDisplay(false);
        picker.setMode(DPMode.SINGLE);
        picker.setOnDatePickedListener(new DatePicker.OnDatePickedListener() {
            @Override
            public void onDatePicked(String date) {
//                Toast.makeText(mActivity, date, Toast.LENGTH_LONG).show();
                tvTimeIn.setText(date);
                dialog.dismiss();
            }
        });
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.getWindow().setContentView(picker, params);
        dialog.getWindow().setGravity(Gravity.CENTER);
    }

    void editBio(){

    }

    void clearAllCtrl(){
        tvCustomerNowBike.setText("");
        tvCustomerTel.setText("");
        tvCustomerName.setText("");
        tvCustomerAge.setText("");
        tvCustomerAddress.setText("");
        tvCustomerBio.setText("");

    }

    public static void start(Activity context, String state) {
        Intent intent = new Intent(context, CustomerEditActivity.class);
        intent.putExtra(NAV_STATE.NAVIGATION_KEY, state);
        context.startActivity(intent);
    }

    public static void startForResult(Activity context, String state, int request_code) {
        Intent intent = new Intent(context, CustomerEditActivity.class);
        intent.putExtra(NAV_STATE.NAVIGATION_KEY, state);
        context.startActivityForResult(intent, request_code);
    }


    public static void startForResult(Activity context, CustomerEntity customerEntity, String state, int request_code) {
        Intent intent = new Intent(context, CustomerEditActivity.class);
        intent.putExtra(NAV_STATE.NAVIGATION_KEY, state);
        intent.putExtra(AppConstants.CUSTOMER_INFO_KEY, customerEntity);
        context.startActivityForResult(intent, request_code);
    }

}
