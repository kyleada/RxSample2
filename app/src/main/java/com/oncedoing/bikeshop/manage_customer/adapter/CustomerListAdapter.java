package com.oncedoing.bikeshop.manage_customer.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.SectionIndexer;
import android.widget.TextView;

import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.model.CustomerSortEntity;
import com.oncedoing.bikeshop.model.SortModel;

import java.util.List;
import java.util.Locale;



/**
 * Created by Administrator on 2016/3/19.
 */
public class CustomerListAdapter extends RecyclerArrayAdapter<CustomerSortEntity> implements SectionIndexer {


    private List<CustomerSortEntity> mList;
    private List<CustomerSortEntity> mSelectedList;
    private Context mContext;


    public CustomerListAdapter(Context context, List<CustomerSortEntity> list){
        super(context);
        mContext = context;
        mList = list;
    }

    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(parent);
    }


    private  class ViewHolder extends BaseViewHolder<CustomerSortEntity>{

        public TextView tvLetter;
        public TextView tvTitle;
        public TextView tvNumber;
        public CheckBox cbChecked;


        public ViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_customer);
            tvLetter = $(R.id.catalog);
            tvTitle = $(R.id.title);
            tvNumber = $(R.id.number);
        }

        @Override
        public void setData(final CustomerSortEntity customerSortEntity,int position){

            SortModel sortModel = customerSortEntity.getSortModel();
            //根据position获取分类的首字母的Char ascii值
            int section = getSectionForPosition(position);

            //如果当前位置等于该分类首字母的Char的位置 ，则认为是第一次出现
            if (position == getPositionForSection(section)) {
                tvLetter.setVisibility(View.VISIBLE);
                tvLetter.setText(sortModel.sortLetters);
            } else {
                tvLetter.setVisibility(View.GONE);
            }

            tvTitle.setText(sortModel.name);
            tvNumber.setText(sortModel.number);

        }


    }


    @Override
    public Object[] getSections() {
        return null;
    }

    @Override
    public int getPositionForSection(int section) {
        for (int i = 0; i < getCount(); i++) {
            String sortStr = getItem(i).getSortModel().sortLetters;
            char firstChar = sortStr.toUpperCase(Locale.CHINESE).charAt(0);
            if (firstChar == section) {
                return i;
            }
        }

        return -1;
    }

    @Override
    public int getSectionForPosition(int position) {
        int section = getItem(position).getSortModel().sortLetters.charAt(0);
        return section;
    }
}
