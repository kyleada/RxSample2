package com.oncedoing.bikeshop.manage_bike.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.model.ProductGeneralEntity;

/**
 * Created by kw on 2016/3/25.13:41.
 */
public class GeneralListAdapter extends RecyclerArrayAdapter<ProductGeneralEntity> {



    public GeneralListAdapter(Context context){
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    class ViewHolder extends BaseViewHolder<ProductGeneralEntity>{

        private TextView tvGeneralName;
        private TextView tvGeneralColor;
        private TextView tvGeneralPrice;


        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_general);
            tvGeneralName = $(R.id.tv_general_name);
            tvGeneralColor = $(R.id.tv_general_color);
            tvGeneralPrice = $(R.id.tv_general_price);
        }

        @Override
        public void setData(final ProductGeneralEntity productGeneralEntity, final int position){

            tvGeneralName.setText(productGeneralEntity.getName());
            tvGeneralColor.setText(productGeneralEntity.getColor());
            tvGeneralPrice.setText("￥" + productGeneralEntity.getStockout_price());
        }
    }
}
