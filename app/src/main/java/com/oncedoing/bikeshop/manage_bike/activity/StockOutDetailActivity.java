package com.oncedoing.bikeshop.manage_bike.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.config.AppConstants;
import com.oncedoing.bikeshop.kbase.KBaseSwipeToolbarActivity;
import com.oncedoing.bikeshop.manage_bike.adapter.GeneralListAdapter;
import com.oncedoing.bikeshop.manage_bike.utils.LayoutUtils;
import com.oncedoing.bikeshop.manage_bike.utils.ManageUtils;
import com.oncedoing.bikeshop.model.ProductGeneralEntity;
import com.oncedoing.bikeshop.model.StockOutEntity;
import com.oncedoing.bikeshop.widget.LabelView;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

import butterknife.Bind;
import me.kkwang.commonlib.utils.DateUtils;
import me.kkwang.commonlib.widget.recyclerview.DividerItemDecoration;

public class StockOutDetailActivity extends KBaseSwipeToolbarActivity {

    @Bind(R.id.rv)
    RecyclerView rv;


    StockOutEntity stockOutEntity;
    List<ProductGeneralEntity> generalEntityList;
    @Bind(R.id.tv_customer_name)
    TextView tvCustomerName;
    @Bind(R.id.tv_cost)
    LabelView tvCost;
    @Bind(R.id.tv_turnover)
    LabelView tvTurnover;
    @Bind(R.id.tv_profit)
    LabelView tvProfit;
    @Bind(R.id.tv_stock_out_time)
    LabelView tvStockOutTime;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_stock_out_detail;
    }

    @Override
    public String getToolbarTitle() {
        return "销售详情";
    }

    @Override
    protected boolean isActionBarNeedBackEnable() {
        return true;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void afterCreate(Bundle savedInstanceState) {

        Intent intent = getIntent();
        if (intent != null) {

            stockOutEntity = intent.getParcelableExtra(AppConstants.STOCK_OUT_INFO_INFO_KEY);
            tvCustomerName.setText(stockOutEntity.getCustomer_name());
            NumberFormat currencyFormat = NumberFormat.getCurrencyInstance();
            tvCost.setText(currencyFormat.format(stockOutEntity.getCost()/100.0f));
            tvTurnover.setText(currencyFormat.format(stockOutEntity.getTurnover()/100.0f));
            tvStockOutTime.setText(DateUtils.formatDate(stockOutEntity.getStockout_time(), DateUtils.TYPE_01));
            float profit = (stockOutEntity.getTurnover() - stockOutEntity.getCost())/stockOutEntity.getTurnover();
            DecimalFormat df = (DecimalFormat)NumberFormat.getPercentInstance();
            df.setMinimumFractionDigits(2);
            df.setMaximumFractionDigits(2);
            tvProfit.setText(df.format(profit));

            rv.setLayoutManager(LayoutUtils.getVerticalLinearLayoutManager(this));
            GeneralListAdapter generalListAdapter = new GeneralListAdapter(this);
            generalEntityList = ManageUtils.parseGeneral(stockOutEntity.getProduct_general());
            generalListAdapter.addAll(generalEntityList);
            rv.addItemDecoration(new DividerItemDecoration(this, LinearLayoutManager.VERTICAL));
            rv.setAdapter(generalListAdapter);

        }


    }

    @Override
    protected void initPresenter() {

    }



    public static void start(Activity activity, StockOutEntity stockOutEntity){
        Intent intent = new Intent(activity, StockOutDetailActivity.class);
        intent.putExtra(AppConstants.STOCK_OUT_INFO_INFO_KEY, stockOutEntity);
        activity.startActivity(intent);


    }


}
