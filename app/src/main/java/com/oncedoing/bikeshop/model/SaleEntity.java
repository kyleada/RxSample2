package com.oncedoing.bikeshop.model;

/**
 * Created by kw on 2016/3/24.14:33.
 */
public class SaleEntity {
    boolean isSelected;
    int saleCount;
    BikeInfoEntity commodityInfo;


    public boolean isSelected() {
        return isSelected;
    }


    public void setSelected(boolean selected) {
        isSelected = selected;
    }


    public int getSaleCount() {
        return saleCount;
    }


    public void setSaleCount(int saleCount) {
        this.saleCount = saleCount;
    }


    public BikeInfoEntity getCommodityInfo() {
        return commodityInfo;
    }


    public void setCommodityInfo(BikeInfoEntity commodityInfo) {
        this.commodityInfo = commodityInfo;
    }
}
