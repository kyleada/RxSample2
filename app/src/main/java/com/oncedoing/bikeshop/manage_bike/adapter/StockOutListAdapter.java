package com.oncedoing.bikeshop.manage_bike.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.manage_bike.utils.ManageUtils;
import com.oncedoing.bikeshop.model.ProductGeneralEntity;
import com.oncedoing.bikeshop.model.StockOutEntity;

import java.util.List;

/**
 * Created by kw on 2016/3/25.13:41.
 */
public class StockOutListAdapter extends RecyclerArrayAdapter<StockOutEntity> {

    Gson mGson;

    public StockOutListAdapter(Context context){
        super(context);
        mGson = new Gson();
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    class ViewHolder extends BaseViewHolder<StockOutEntity>{

        private TextView tvProductName;
        private TextView tvCustomerName;
        private TextView tvTurnOverPrice;
        private TextView tvMulti;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_stockout);
            tvProductName = $(R.id.tv_product_name);
            tvCustomerName = $(R.id.tv_customer_name);
            tvTurnOverPrice = $(R.id.tv_turnover_price);
            tvMulti= $(R.id.tv_isMulti);
        }

        @Override
        public void setData(final StockOutEntity stockOutEntity, final int position){

            List<ProductGeneralEntity> generalList = ManageUtils.parseGeneral(stockOutEntity.getProduct_general());
            if(generalList.size() > 1){
                tvMulti.setVisibility(View.VISIBLE);
            }else{
                tvMulti.setVisibility(View.GONE);
            }
            tvProductName.setText(generalList.get(0).getName());
            tvCustomerName.setText(stockOutEntity.getCustomer_name());
            tvTurnOverPrice.setText(ManageUtils.convertMoney(stockOutEntity
                    .getTurnover()));
        }
    }


}
