package com.oncedoing.bikeshop.manage;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;

/**
 * Created by Administrator on 2016/3/15.
 */
public class ManageListAdapter extends RecyclerArrayAdapter<String> {

    public ManageListAdapter(Context context) {
        super(context);
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new StringViewHolder(parent);
    }

    private static class StringViewHolder extends BaseViewHolder<String>{

        private TextView mTv_name;

        public StringViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_manage_textview);
            mTv_name = $(R.id.simple_tv);
        }

        @Override
        public void setData(final String name,int position){
            mTv_name.setText(name);
        }
    }

}
