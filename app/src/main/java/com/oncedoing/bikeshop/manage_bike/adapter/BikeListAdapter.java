package com.oncedoing.bikeshop.manage_bike.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.model.BikeInfoEntity;
import java.util.ArrayList;

/**
 * Created by kw on 2016/3/15.11:52.
 */
public class BikeListAdapter extends RecyclerArrayAdapter<BikeInfoEntity> {

    boolean isWithCheckbox;
    boolean isWithNum;

    private ArrayList<BikeInfoEntity> mSelectedList;
    //同时用在新增销售页和商品列表页标识是否选中
    //SparseBooleanArray mSparseBooleanArray = new SparseBooleanArray(8);
    ArrayList<String> mSelectedIds = new ArrayList<>();
   //用在新增销售业，标识数字个数
   // ArrayMap<String, Integer> mArrayMap = new ArrayMap(8);


    public BikeListAdapter(Context context) {
        super(context);
        mSelectedList = new ArrayList<>();
    }


    public BikeListAdapter(Context context, boolean isWithCheckbox) {
        this(context);
        this.isWithCheckbox = isWithCheckbox;
    }


    public BikeListAdapter(Context context, boolean isWithCheckbox, boolean isWithNum) {
        this(context);
        this.isWithCheckbox = isWithCheckbox;
        this.isWithNum = isWithNum;
    }


    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {
        //if (isWithNum) {
        //    return new BikeInfoWithNumViewHolder(parent);
        //}
        //else
        if (isWithCheckbox) {
            return new BikeInfoWithCbViewHolder(parent);
        }
        else {
            return new BikeInfoViewHolder(parent);
        }
    }


    public ArrayList<BikeInfoEntity> getSelectedList() {
        mSelectedList.clear();
        //int count = mSparseBooleanArray.size();
        //for (int i = 0; i < count; i++) {
        //    if (mSparseBooleanArray.get(getItem(i).getId().hashCode(), false)) {
        //        mSelectedList.add(getItem(i));
        //    }
        //}

        int count = getCount();
        for(int i=0;i<count; i++){
            BikeInfoEntity bikeInfoEntity = getItem(i);
            if(mSelectedIds.contains(bikeInfoEntity.getId())){
                mSelectedList.add(bikeInfoEntity);
            }
        }
        return mSelectedList;
    }


    public void toggleChecked(String id) {
        if (!mSelectedIds.contains(id)) {
            mSelectedIds.add(id);
        }else{
            mSelectedIds.remove(id);
        }
    }

        private void setSelected(String id) {
            if (!mSelectedIds.contains(id)) {
                mSelectedIds.add(id);
            }
        }
    //
        private void removeSelected(String id) {
            if (mSelectedIds.contains(id)) {
                mSelectedIds.remove(id);
            }
        }

    private static class BikeInfoViewHolder
            extends BaseViewHolder<BikeInfoEntity> {

        private TextView tvBikeName;
        private ImageView ivBikeImage;
        private TextView tvBikePrice;
        private TextView tvBikeModule;
        private TextView tvBikeStorageCount;


        public BikeInfoViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_bikelist);
            tvBikeName = $(R.id.tv_bike_name);
            tvBikePrice = $(R.id.tv_bike_price);
            ivBikeImage = $(R.id.iv_bike);
            tvBikeModule = $(R.id.tv_bike_module);
            tvBikeStorageCount = $(R.id.tv_storage_label);
        }


        @Override
        public void setData(final BikeInfoEntity bikeInfoEntity, final int position) {
            tvBikeName.setText(bikeInfoEntity.getName());
            tvBikePrice.setText("￥" + bikeInfoEntity.getPrice());
            tvBikeModule.setText(bikeInfoEntity.getBrandname());
            tvBikeStorageCount.setText(bikeInfoEntity.getStockin_num() + "");
        }
    }

    public class BikeInfoWithCbViewHolder
            extends BaseViewHolder<BikeInfoEntity> {

        public CheckBox cb;
        private TextView tvBikeName;
        private ImageView ivBikeImage;
        private TextView tvBikePrice;
        private TextView tvBikeModule;
        private TextView tvBikeStorageCount;


        public BikeInfoWithCbViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_bikelist_with_cb);
            cb = $(R.id.cbCheck);
            tvBikeName = $(R.id.tv_bike_name);
            tvBikePrice = $(R.id.tv_bike_price);
            ivBikeImage = $(R.id.iv_bike);
            tvBikeModule = $(R.id.tv_bike_module);
            tvBikeStorageCount = $(R.id.tv_storage_label);
            cb.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                    CheckBox cc = (CheckBox) v;
                    BikeInfoEntity bikeInfoEntity
                            = (BikeInfoEntity) cc.getTag();
                    if (bikeInfoEntity != null) {
                        //person.setChecked(cc.isChecked());
                        //mSparseBooleanArray.put(
                        //        bikeInfoEntity.getId().hashCode(),
                        //        ((CheckBox) v).isChecked());
                        toggleChecked(bikeInfoEntity.getId());
                    }
                }
            });
            //cb.setOnCheckedChangeListener(
            //        new CompoundButton.OnCheckedChangeListener() {
            //            @Override
            //            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
            //                CheckBox cc = (CheckBox) buttonView;
            //                BikeInfoEntity bikeInfoEntity
            //                        = (BikeInfoEntity) cc.getTag();
            //                if (bikeInfoEntity != null) {
            //                    //person.setChecked(cc.isChecked());
            //                    mSparseBooleanArray.put(
            //                            bikeInfoEntity.getId().hashCode(),
            //                            isChecked);
            //                }
            //            }
            //        });
        }


        @Override
        public void setData(final BikeInfoEntity bikeInfoEntity, final int position) {

            cb.setTag(bikeInfoEntity);
            //cb.setChecked(
            //        mSparseBooleanArray.get(bikeInfoEntity.getId().hashCode(),
            //                false));

            cb.setChecked(mSelectedIds.contains(bikeInfoEntity.getId()));

            tvBikeName.setText(bikeInfoEntity.getName());
            tvBikePrice.setText("￥" + bikeInfoEntity.getPrice());
            tvBikeModule.setText(bikeInfoEntity.getBrandname());
            tvBikeStorageCount.setText(bikeInfoEntity.getStockin_num() + "");
        }
    }

    //public void initSelectedNumWithOne(){
    //    int count = getCount();
    //    for (int i = 0; i<count;i++){
    //        mArrayMap.put(getItem(i).getId(), 1);
    //    }
    //}
    //public  class BikeInfoWithNumViewHolder
    //        extends BaseViewHolder<BikeInfoEntity> {
    //
    //    public CheckBox cb;
    //    SnappingStepper snappingStepper;
    //    private TextView tvBikeName;
    //    private ImageView ivBikeImage;
    //    private TextView tvBikePrice;
    //    private TextView tvBikeModule;
    //
    //
    //    public BikeInfoWithNumViewHolder(ViewGroup parent) {
    //        super(parent, R.layout.item_bikelist_with_num);
    //        cb = $(R.id.cbCheck);
    //        snappingStepper = $(R.id.stepper);
    //        tvBikeName = $(R.id.tv_bike_name);
    //        tvBikePrice = $(R.id.tv_bike_price);
    //        ivBikeImage = $(R.id.iv_bike);
    //        tvBikeModule = $(R.id.tv_bike_module);
    //        cb.setOnCheckedChangeListener(
    //                new CompoundButton.OnCheckedChangeListener() {
    //                    @Override
    //                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
    //                        CheckBox cc = (CheckBox) buttonView;
    //                        BikeInfoEntity bikeInfoEntity
    //                                = (BikeInfoEntity) cc.getTag(0);
    //                        if (bikeInfoEntity != null) {
    //                            //person.setChecked(cc.isChecked());
    //                            mCheckStatusList.put(
    //                                    bikeInfoEntity.getId().hashCode(),
    //                                    isChecked);
    //                        }
    //                    }
    //                });
    //
    //        snappingStepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
    //            @Override public void onValueChange(View view, int value) {
    //                String bikeId = (String) view.getTag(1);
    //                if (!TextUtils.isEmpty(bikeId)) {
    //                    //person.setChecked(cc.isChecked());
    //                    mCountList.put(bikeId,value);
    //                }
    //            }
    //        });
    //    }
    //
    //
    //    @Override
    //    public void setData(final BikeInfoEntity bikeInfoEntity, final int position) {
    //        cb.setTag(0,bikeInfoEntity);
    //        cb.setChecked(
    //                mCheckStatusList.get(bikeInfoEntity.getId().hashCode(),
    //                        false));
    //        snappingStepper.setTag(1,bikeInfoEntity.getId());
    //        snappingStepper.setValue(mCountList.get(bikeInfoEntity.getId()));
    //
    //        tvBikeName.setText(bikeInfoEntity.getName());
    //        tvBikePrice.setText("￥" + bikeInfoEntity.getPrice());
    //        tvBikeModule.setText(bikeInfoEntity.getBrandname());
    //    }
    //}
}