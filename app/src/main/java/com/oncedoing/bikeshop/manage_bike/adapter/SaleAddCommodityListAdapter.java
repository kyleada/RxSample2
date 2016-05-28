package com.oncedoing.bikeshop.manage_bike.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigkoo.snappingstepper.SnappingStepper;
import com.bigkoo.snappingstepper.listener.SnappingStepperValueChangeListener;
import com.jude.easyrecyclerview.adapter.BaseViewHolder;
import com.jude.easyrecyclerview.adapter.RecyclerArrayAdapter;
import com.oncedoing.bikeshop.R;
import com.oncedoing.bikeshop.model.SaleEntity;

import java.util.HashSet;

/**
 * Created by kw on 2016/3/15.11:52.
 */
public class SaleAddCommodityListAdapter extends RecyclerArrayAdapter<SaleEntity> {


    //private ArrayList<BikeInfoEntity> mSelectedList = new ArrayList<>();
    //同时用在新增销售页和商品列表页标识是否选中
    //SparseBooleanArray mCheckStatusList = new SparseBooleanArray(8);
   //用在新增销售业，标识数字个数
   // ArrayMap<String, Integer> mCountList = new ArrayMap(8);
    //SparseIntArray mSparseIntArray = new SparseIntArray(8);

    //如果本页已有，再从车辆列表页选择同样，这里要过滤
    //更好的做法是在车辆列表页不再显示本页已有的，但那个实现更复杂
    HashSet<String> setBikes = new HashSet<>(8);


    public SaleAddCommodityListAdapter(Context context) {
        super(context);
    }




    @Override
    public BaseViewHolder OnCreateViewHolder(ViewGroup parent, int viewType) {

            return new CommodityWithNumViewHolder(parent);


    }

    public boolean containsBike(String id){
        return setBikes.contains(id);
    }

    public void addSaleBikeId(String id){

        setBikes.add(id);
    }

    //public ArrayList<BikeInfoEntity> getSelectedList() {
    //    mSelectedList.clear();
    //    int count = mCheckStatusList.size();
    //    for (int i = 0; i < count; i++) {
    //        if (mCheckStatusList.get(i, false)) {
    //            mSelectedList.add(getItem(i));
    //        }
    //    }
    //    return mSelectedList;
    //}

    public int calculateTotalCount(){
        int count = getCount();
        int total = 0;
        for (int i = 0; i<count;i++){
            total += getItem(i).getSaleCount();
        }
        return total;
    }

    public float calculateTotalMoney(){
        float tempTotal = 0;
        int size = getCount();
        for(int i = 0; i < size; i++){
            tempTotal += getItem(i).getSaleCount()* getItem(i)
                    .getCommodityInfo().getPrice();
        }
        return tempTotal;
    }

    public  void removeSelected(){
        int size = getCount();
        for(int i = size - 1; i >=0; i--){
            if(getItem(i).isSelected()){
                setBikes.remove(getItem(i).getCommodityInfo().getId());
                remove(i);
            }
        }
    }
    //
    //public void deleteSelectedItem(){
    //    re
    //}
    //public void initSelectedNumWithOne(){
    //    int count = getCount();
    //    for (int i = 0; i<count;i++){
    //        mSparseIntArray.put(i, 1);
    //    }
    //}
    public  class CommodityWithNumViewHolder
            extends BaseViewHolder<SaleEntity> {

        public CheckBox cb;
        SnappingStepper snappingStepper;
        private TextView tvBikeName;
        private ImageView ivBikeImage;
        private TextView tvBikePrice;
        //private TextView tvBikeModule;


        public CommodityWithNumViewHolder(ViewGroup parent) {
            super(parent, R.layout.item_bikelist_with_num);
            cb = $(R.id.cbCheck);
            snappingStepper = $(R.id.stepper);
            tvBikeName = $(R.id.tv_bike_name);
            tvBikePrice = $(R.id.tv_bike_price);
            ivBikeImage = $(R.id.iv_bike);
            //tvBikeModule = $(R.id.tv_bike_module);
            //cb.setOnClickListener(new View.OnClickListener() {
            //    @Override public void onClick(View v) {
            //
            //    }
            //});
            cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox cb = (CheckBox) view;
                    SaleEntity saleEntity = (SaleEntity) cb.getTag();
                    saleEntity.setSelected(cb.isChecked());
                }
            });
//            cb.setOnCheckedChangeListener(
//                    new CompoundButton.OnCheckedChangeListener() {
//                        @Override
//                        public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
//                            CheckBox cb = (CheckBox) buttonView;
//                            SaleEntity saleEntity = (SaleEntity) cb.getTag();
//                            saleEntity.setSelected(isChecked);
//
//                            //BikeInfoEntity bikeInfoEntity
//                            //        = (BikeInfoEntity) cc.getTag(0);
//                            //if (bikeInfoEntity != null) {
//                            //    //person.setChecked(cc.isChecked());
//                            //    mCheckStatusList.put(
//                            //            bikeInfoEntity.getId().hashCode(),
//                            //            isChecked);
//                            //}
//                        }
//                    });

            snappingStepper.setOnValueChangeListener(new SnappingStepperValueChangeListener() {
                @Override public void onValueChange(View view, int value) {

                    SnappingStepper snappingStepper = (SnappingStepper) view;
                    SaleEntity saleEntity = (SaleEntity) snappingStepper.getTag();
                    saleEntity.setSaleCount(snappingStepper.getValue());
                    if(onCommodityCountStepListener != null){
                        onCommodityCountStepListener.onCountChange();
                    }


                    //String bikeId = (String) view.getTag(1);
                    //if (!TextUtils.isEmpty(bikeId)) {
                    //    //person.setChecked(cc.isChecked());
                    //    mCountList.put(bikeId,value);
                    //    if(onCommodityCountStepListener != null){
                    //        onCommodityCountStepListener.onCountChange
                    //                (bikeId, value);
                    //    }
                    //}
                }
            });
        }


        @Override
        public void setData(final SaleEntity saleEntity, final int position) {
            //cb.setTag(0,bikeInfoEntity);
            //cb.setChecked(
            //        mCheckStatusList.get(bikeInfoEntity.getId().hashCode(),
            //                false));
            //snappingStepper.setTag(1,bikeInfoEntity.getId());
            //snappingStepper.setValue(mCountList.get(bikeInfoEntity.getId()));
            cb.setTag(saleEntity);
            snappingStepper.setTag(saleEntity);
            boolean isChecked = saleEntity.isSelected();
            cb.setChecked(isChecked);
            snappingStepper.setValue(saleEntity.getSaleCount());

            tvBikeName.setText(saleEntity.getCommodityInfo().getName());
            tvBikePrice.setText("￥" + saleEntity.getCommodityInfo().getPrice());
            //tvBikeModule.setText(bikeInfoEntity.getBrandname());
        }
    }
    public onCommodityCountStepListener onCommodityCountStepListener;

    public interface onCommodityCountStepListener{
        void onCountChange();
    }

    public void setOnCommodityCountChangeListener
            (onCommodityCountStepListener onCommodityCountStepListener){
        this.onCommodityCountStepListener = onCommodityCountStepListener;
    }
}