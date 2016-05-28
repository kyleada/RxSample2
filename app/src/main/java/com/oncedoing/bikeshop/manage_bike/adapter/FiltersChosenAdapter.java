package com.oncedoing.bikeshop.manage_bike.adapter;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;

/**
 * Created by Administrator on 2016/3/19.
 */
public class FiltersChosenAdapter extends RecyclerArrayAdapter<String> {


    public FiltersChosenAdapter(Context context) {
        super(context);
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }

    private static class ViewHolder extends BaseViewHolder<String>{

        TextView tvFilter;

        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_filters_textview);
            tvFilter = $(R.id.tv_filter);

        }

        @Override
        public void setData(String data, int position) {
            tvFilter.setText(data);
        }
    }
}
