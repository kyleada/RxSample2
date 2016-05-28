package com.oncedoing.bikeshop.manage;

import android.os.Handler;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.EasyRecyclerView;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.manage_bike.activity.AnalysisActivity;
import com.oncedoing.bikeshop.manage_bike.activity.ProductEditActivity;
import com.oncedoing.bikeshop.manage_bike.activity.ProductListActivity;
import com.oncedoing.bikeshop.manage_bike.activity.SaleAddActivity;
import com.oncedoing.bikeshop.manage_bike.activity.StockInListActivity;
import com.oncedoing.bikeshop.manage_bike.activity.StockOutListActivity;
import com.oncedoing.bikeshop.manage_customer.CustomerEditActivity;
import com.oncedoing.bikeshop.manage_customer.CustomerQueryActivity;
import com.oncedoing.bikeshop.widget.DividerGridItemDecoration;

import butterknife.Bind;
import butterknife.ButterKnife;
import me.kkwang.commonlib.base.BaseLazyFragment;


/**
 * A simple {@link Fragment} subclass.
 */
public class ManageFragment extends BaseLazyFragment implements
        SwipeRefreshLayout.OnRefreshListener{
    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.app_bar_layout)
    AppBarLayout appBarLayout;
    @Bind(R.id.tv_weekMoney)
    TextView tvWeekMoney;
    @Bind(R.id.tv_week_grossprofit)
    TextView tvWeekGrossprofit;
    @Bind(R.id.tv_monthMoney)
    TextView tvMonthMoney;
    @Bind(R.id.tv_month_grossprofit)
    TextView tvMonthGrossprofit;
    @Bind(R.id.tv_yearMoney)
    TextView tvYearMoney;
    @Bind(R.id.tv_year_grossprofit)
    TextView tvYearGrossprofit;


    EasyRecyclerView rvManageList;



    private ManageListAdapter adapter;

    public ManageFragment() {
        // Required empty public constructor
    }

    public static ManageFragment newInstance() {
        ManageFragment fragment = new ManageFragment();
        return fragment;
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_manage;
    }

    @Override
    protected void initViewsAndEvents(View view) {

        toolbar = ButterKnife.findById(view, R.id.toolbar);
        toolbar.setTitle("骑行易装备旗舰店");

        rvManageList = ButterKnife.findById(view , R.id.rv_managelist);
        final String[] manage_items = getResources().getStringArray(R.array.manage_items);
        adapter = new ManageListAdapter(mContext);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(mContext, 4);
        gridLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        gridLayoutManager.setSpanSizeLookup(adapter.obtainGridSpanSizeLookUp(4));
        rvManageList.setLayoutManager(gridLayoutManager);
        rvManageList.addItemDecoration(new DividerGridItemDecoration(mContext));
        rvManageList.setAdapter(adapter);
        rvManageList.setRefreshListener(this);
        adapter.addHeader(new RecyclerArrayAdapter.ItemView() {
            @Override
            public View onCreateView(ViewGroup parent) {
                LayoutInflater inflate = LayoutInflater.from(mContext);
                View headView = inflate.inflate(R.layout.fragment_manage_header,parent,false);
                ButterKnife.bind(headView);
                return headView;
            }

            @Override
            public void onBindView(View headerView) {

            }
        });
        adapter.addAll(manage_items);
        adapter.setOnItemClickListener(new RecyclerArrayAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position,View view) {
                switch (position){
                    case 0:
                        readyGo(ProductEditActivity.class);
                        break;
                    case 1:
                        readyGo(SaleAddActivity.class);
                        break;
                    case 2:
                        CustomerEditActivity.start(mContext,CustomerEditActivity.NAV_STATE.ADD_CUSTOMER);
                        break;
                    case 3:
                        readyGo(StockInListActivity.class);
                        break;
                    case 4:
                        readyGo(ProductListActivity.class);
                        break;
                    case 5:
                        readyGo(StockOutListActivity.class);
                        break;
                    case 6:
                        readyGo(CustomerQueryActivity.class);
                        break;
                    case 7:
                        break;
                    case 8:
						readyGo(AnalysisActivity.class);
                        break;
                }
            }
        });
    }

    @Override
    protected void onFirstUserVisible() {

    }

    @Override public void onRefresh() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rvManageList.setRefreshing(false);
                ManageFragment.this.showToast("此处收益是假值，TODO");
            }
        },200);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}
