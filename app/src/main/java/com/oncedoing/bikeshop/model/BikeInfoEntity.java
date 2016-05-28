package com.oncedoing.bikeshop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Administrator on 2016/3/14.
 */
public class BikeInfoEntity implements Parcelable {



    /**
     * id : 2527d9715c3d4171ac4b9faf0b66e99e
     * name : 千里达-山地车-M306
     * tenant_id : oncebikeadmin
     * price : 5560.0
     * color :
     * size : 17
     * other : M306
     * brand : 107
     * brandname : 千里达
     * category : 1
     * note : 无
     * existnum : 7
     * stockin_num : null
     * list_time : 2015
     * isprivate : null
     * create_time : 1457600004000
     * update_time : 1457600004000
     * search_key : null
     * stockin_price : null
     */

    //额外加的，表示在是否被选中
    //private boolean isSelected;
    //public boolean isSelected() {
    //    return isSelected;
    //}
    //
    //public void setSelected(boolean selected) {
    //    isSelected = selected;
    //}


    private String id;
    private String name;
    private String tenant_id;
    private double price;
    private String color;
    private String size;
    private String other;
    private int brand;
    private String brandname;
    private int category;
    private String note;
    private int existnum;
    private int stockin_num;
    private String list_time;
    private int isprivate;
    private long create_time;
    private long update_time;
    private String search_key;
    private float stockin_price;

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public void setOther(String other) {
        this.other = other;
    }

    public void setBrand(int brand) {
        this.brand = brand;
    }

    public void setBrandname(String brandname) {
        this.brandname = brandname;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setExistnum(int existnum) {
        this.existnum = existnum;
    }

    public void setStockin_num(int stockin_num) {
        this.stockin_num = stockin_num;
    }

    public void setList_time(String list_time) {
        this.list_time = list_time;
    }

    public void setIsprivate(int isprivate) {
        this.isprivate = isprivate;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public void setUpdate_time(long update_time) {
        this.update_time = update_time;
    }

    public void setSearch_key(String search_key) {
        this.search_key = search_key;
    }

    public void setStockin_price(float stockin_price) {
        this.stockin_price = stockin_price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public double getPrice() {
        return price;
    }

    public String getColor() {
        return color;
    }

    public String getSize() {
        return size;
    }

    public String getOther() {
        return other;
    }

    public int getBrand() {
        return brand;
    }

    public String getBrandname() {
        return brandname;
    }

    public int getCategory() {
        return category;
    }

    public String getNote() {
        return note;
    }

    public int getExistnum() {
        return existnum;
    }

    public int getStockin_num() {
        return stockin_num;
    }

    public String getList_time() {
        return list_time;
    }

    public Object getIsprivate() {
        return isprivate;
    }

    public long getCreate_time() {
        return create_time;
    }

    public long getUpdate_time() {
        return update_time;
    }

    public Object getSearch_key() {
        return search_key;
    }

    public Object getStockin_price() {
        return stockin_price;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.name);
        dest.writeString(this.tenant_id);
        dest.writeDouble(this.price);
        dest.writeString(this.color);
        dest.writeString(this.size);
        dest.writeString(this.other);
        dest.writeInt(this.brand);
        dest.writeString(this.brandname);
        dest.writeInt(this.category);
        dest.writeString(this.note);
        dest.writeInt(this.existnum);
        dest.writeInt(this.stockin_num);
        dest.writeString(this.list_time);
        dest.writeInt(this.isprivate);
        dest.writeLong(this.create_time);
        dest.writeLong(this.update_time);
        dest.writeString(this.search_key);
        dest.writeFloat(this.stockin_price);
    }

    public BikeInfoEntity() {
    }

    protected BikeInfoEntity(Parcel in) {
        this.id = in.readString();
        this.name = in.readString();
        this.tenant_id = in.readString();
        this.price = in.readDouble();
        this.color = in.readString();
        this.size = in.readString();
        this.other = in.readString();
        this.brand = in.readInt();
        this.brandname = in.readString();
        this.category = in.readInt();
        this.note = in.readString();
        this.existnum = in.readInt();
        this.stockin_num = in.readInt();
        this.list_time = in.readString();
        this.isprivate = in.readInt();
        this.create_time = in.readLong();
        this.update_time = in.readLong();
        this.search_key = in.readString();
        this.stockin_price = in.readFloat();
    }

    public static final Parcelable.Creator<BikeInfoEntity> CREATOR = new Parcelable.Creator<BikeInfoEntity>() {
        @Override
        public BikeInfoEntity createFromParcel(Parcel source) {
            return new BikeInfoEntity(source);
        }

        @Override
        public BikeInfoEntity[] newArray(int size) {
            return new BikeInfoEntity[size];
        }
    };
}
