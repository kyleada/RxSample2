package com.oncedoing.bikeshop.manage_bike.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.model.BikeStockEntity;

/**
 * Created by kw on 2016/3/15.11:52.
 */
public class BikeStockListAdapter extends RecyclerArrayAdapter<BikeStockEntity> {



    public BikeStockListAdapter(Context context) {
        super(context);

    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

        return new BikeInfoViewHolder(parent);

    }



    private  static class BikeInfoViewHolder extends BaseViewHolder<BikeStockEntity> {

        private TextView tvBikeName;

        public BikeInfoViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_bikein_list);
            tvBikeName = $(R.id.tv_bike_name);

        }

        @Override
        public void setData(final BikeStockEntity bikeInfoEntity, final int position){
            tvBikeName.setText(bikeInfoEntity.getProduct_name());
        }
    }

}