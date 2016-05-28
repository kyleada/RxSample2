package com.oncedoing.bikeshop.manage_bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.manage_bike.adapter.SaleAddCommodityListAdapter;
import com.oncedoing.bikeshop.manage_customer.CustomerEditActivity;
import com.oncedoing.bikeshop.manage_customer.CustomerQueryActivity;
import com.oncedoing.bikeshop.model.BikeInfoEntity;
import com.oncedoing.bikeshop.model.CustomerEntity;
import com.oncedoing.bikeshop.model.SaleEntity;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import me.kkwang.commonlib.base.BaseToolBarActivity;
import me.kkwang.commonlib.widget.recyclerview.DividerItemDecoration;

public class SaleAddActivity extends BaseToolBarActivity {

    private static final String TAG = "SaleAddActivity";

    @Bind(R.id.tv_choose_customer)
    TextView tvChooseCustomer;
    @Bind(R.id.tv_add_customer)
    TextView tvAddCustomer;

    @Bind(R.id.tv_commodity_delete)
    TextView tvCommodityDelete;
    @Bind(R.id.tv_commodity_add)
    TextView tvCommodityAdd;
    @Bind(R.id.rv_shopcart)
    RecyclerView rvShopcart;
    @Bind(R.id.tv_commodity_count)
    TextView tvTotalCount;
    @Bind(R.id.tv_total_money)
    TextView tvTotalMoney;


    int REQUEST_CHOOSE_CUSTOMER_CODE = 2000;
    int REQUEST_ADD_CUSTOMER_CODE = 2001;  //两者并不需要区分,从页面跳转和需求数据来看
    int REQUEST_CHOOSE_COMMODITY_CODE = 2002;

    CustomerEntity customerEntity;
    SaleAddCommodityListAdapter bikeListAdapter;


    ////如果本页已有，再从车辆列表页选择同样，这里要过滤
    ////更好的做法是在车辆列表页不再显示本页已有的，但那个实现更复杂
    ////拿到Adapter里了，不然删除不同步
    //HashSet<String> setBikes = new HashSet<>(8);
    int totalCount;
    float totalMoney;

    @OnClick(R.id.tv_choose_customer)
    public void onClickChooseCustomer() {

        CustomerQueryActivity.startForResult(this, CustomerQueryActivity.NAV_STATE.GET_ONE_CUSTOMER,REQUEST_CHOOSE_CUSTOMER_CODE);
    }

    @OnClick(R.id.tv_add_customer)
    public void onClickAddCustomer() {
        CustomerEditActivity.startForResult(this,CustomerEditActivity.NAV_STATE.SELL_ADD_CUSTOMER, REQUEST_CHOOSE_CUSTOMER_CODE);
    }

    @OnClick(R.id.tv_commodity_add)
    public void onClickAddCommodity(){
        ProductListActivity.startForResult(this, ProductListActivity.NAV_STATE.CHOOSE_BIKE,REQUEST_CHOOSE_COMMODITY_CODE);
    }

    @OnClick(R.id.tv_commodity_delete)
    public void onClickDeleteCommodity(){
        bikeListAdapter.removeSelected();
        updateTotalCountAndMoney();
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_sale_add;
    }

    @Override
    public String getToolbarTitle() {
        return "新增销售";
    }

    @Override
    protected boolean isActionBarNeedBackEnable() {
        return true;
    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {
        ButterKnife.bind(this);

        //initView

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvShopcart.setLayoutManager(linearLayoutManager);
        rvShopcart.setAdapter(bikeListAdapter = new SaleAddCommodityListAdapter(this));
        bikeListAdapter.setOnCommodityCountChangeListener(new SaleAddCommodityListAdapter.onCommodityCountStepListener() {
            @Override public void onCountChange() {
                updateTotalCountAndMoney();
            }
        });
        bikeListAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, View view) {
                SaleAddCommodityListAdapter.CommodityWithNumViewHolder viewHolder = (SaleAddCommodityListAdapter.CommodityWithNumViewHolder)view.getTag();
                viewHolder.cb.performClick();
            }
        });
        rvShopcart.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL_LIST));
        //bikeListAdapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
        //    @Override
        //    public void onItemClick(int position, View view) {
        //
        //    }
        //});

        //initData
        Intent intent = getIntent();
        if(intent != null){
            customerEntity = intent.getParcelableExtra(AppConstants.CUSTOMER_INFO_KEY);
            if(customerEntity != null){
                tvChooseCustomer.setText(customerEntity.getCustomer_name());
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHOOSE_CUSTOMER_CODE && resultCode == RESULT_OK && data != null) {
            CustomerEntity customerEntity = data.getParcelableExtra(AppConstants.CUSTOMER_INFO_KEY);
            tvChooseCustomer.setText(customerEntity.getCustomer_name());
        }else if(requestCode == REQUEST_CHOOSE_COMMODITY_CODE && resultCode == RESULT_OK && data != null){
            ArrayList<BikeInfoEntity> bikeList = data
                    .getParcelableArrayListExtra(AppConstants.COMMODITY_INFO_KEY);
            int len = bikeList.size();
            Log.e(TAG, "onActivityResult: " + len  );
            ArrayList<SaleEntity> listSale = new ArrayList<>();
            for (int i=0; i< len ; i++){
                BikeInfoEntity bike = bikeList.get(i);
                if(bikeListAdapter.containsBike(bike.getId())){
                   continue;
                }else{
                    SaleEntity saleEntity = new SaleEntity();
                    saleEntity.setCommodityInfo(bike);
                    bikeListAdapter.addSaleBikeId(bikeList.get(i).getId());
                    saleEntity.setSaleCount(1);
                    saleEntity.setSelected(false);
                    listSale.add(saleEntity);
                }
            }
            bikeListAdapter.addAll(listSale);
            listSale = null;
            updateTotalCountAndMoney();
        }
    }


    private void updateTotalCountAndMoney() {
        totalCount = bikeListAdapter.calculateTotalCount();
        totalMoney = bikeListAdapter.calculateTotalMoney();
        tvTotalMoney.setText(String.format(getString(R.string
                .commodity_total_money),totalMoney));
        tvTotalCount.setText(String.format(getString(R.string
                .commodity_add_total_count),totalCount));
    }


    public static void start(Activity activity, CustomerEntity customerEntity){
        Intent intent = new Intent(activity, SaleAddActivity.class);
        intent.putExtra(AppConstants.CUSTOMER_INFO_KEY, customerEntity);
        activity.startActivity(intent);
    }

}
