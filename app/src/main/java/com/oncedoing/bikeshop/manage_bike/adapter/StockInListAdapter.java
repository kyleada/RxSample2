package com.oncedoing.bikeshop.manage_bike.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.model.StockInEntity;

/**
 * Created by kw on 2016/3/15.11:52.
 */
public class StockInListAdapter extends RecyclerArrayAdapter<StockInEntity> {



    public StockInListAdapter(Context context) {
        super(context);

    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

        return new BikeInfoViewHolder(parent);

    }



    private  static class BikeInfoViewHolder extends BaseViewHolder<StockInEntity> {

        private TextView tvProductName;
        private TextView tvProductColor;
        private TextView tvStockinPrice;

        public BikeInfoViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_stockin);
            tvProductName = $(R.id.tv_product_name);
            tvProductColor = $(R.id.tv_product_color);
            tvStockinPrice = $(R.id.tv_stockin_price);

        }

        @Override
        public void setData(final StockInEntity stockEntity, final int position){
            tvProductName.setText(stockEntity.getProduct_name());
            tvProductColor.setText(stockEntity.getProduct_color());
            tvStockinPrice.setText("￥" + stockEntity.getStockin_price());
        }
    }

}