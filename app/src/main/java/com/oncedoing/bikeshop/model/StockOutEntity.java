package com.oncedoing.bikeshop.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kw on 2016/3/25.12:22.
 */
public class StockOutEntity implements Parcelable {

    private String stockout_id;
    private String tenant_id;
    private long stockout_time;
    private String note;
    private String customer_id;
    private String customer_name;
    private String product_general;
    //private List<ProductGeneralEntity> product_general;
    private int turnover;
    private int unreceived_money;
    private int stockout_type;
    private int payment_type;
    private int reward_point_id;
    private int cost;


    public void setStockout_id(String stockout_id) {
        this.stockout_id = stockout_id;
    }


    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }


    public void setStockout_time(long stockout_time) {
        this.stockout_time = stockout_time;
    }


    public void setNote(String note) { this.note = note;}


    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }


    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }


    public void setProduct_general(String product_general) {
        this.product_general = product_general;
    }


    public void setTurnover(int turnover) { this.turnover = turnover;}


    public void setUnreceived_money(int unreceived_money) {
        this.unreceived_money = unreceived_money;
    }


    public void setStockout_type(int stockout_type) {
        this.stockout_type = stockout_type;
    }


    public void setPayment_type(int payment_type) {
        this.payment_type = payment_type;
    }


    public void setReward_point_id(int reward_point_id) {
        this.reward_point_id = reward_point_id;
    }


    public void setCost(int cost) { this.cost = cost;}


    public String getStockout_id() { return stockout_id;}


    public String getTenant_id() { return tenant_id;}


    public long getStockout_time() { return stockout_time;}


    public String getNote() { return note;}


    public String getCustomer_id() { return customer_id;}


    public String getCustomer_name() { return customer_name;}


    public  String getProduct_general() { return product_general;}


    public int getTurnover() { return turnover;}


    public int getUnreceived_money() { return unreceived_money;}


    public int getStockout_type() { return stockout_type;}


    public int getPayment_type() { return payment_type;}


    public int getReward_point_id() { return reward_point_id;}


    public int getCost() { return cost;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.stockout_id);
        dest.writeString(this.tenant_id);
        dest.writeLong(this.stockout_time);
        dest.writeString(this.note);
        dest.writeString(this.customer_id);
        dest.writeString(this.customer_name);
        dest.writeString(this.product_general);
        dest.writeInt(this.turnover);
        dest.writeInt(this.unreceived_money);
        dest.writeInt(this.stockout_type);
        dest.writeInt(this.payment_type);
        dest.writeInt(this.reward_point_id);
        dest.writeInt(this.cost);
    }

    public StockOutEntity() {
    }

    protected StockOutEntity(Parcel in) {
        this.stockout_id = in.readString();
        this.tenant_id = in.readString();
        this.stockout_time = in.readLong();
        this.note = in.readString();
        this.customer_id = in.readString();
        this.customer_name = in.readString();
        this.product_general = in.readString();
        this.turnover = in.readInt();
        this.unreceived_money = in.readInt();
        this.stockout_type = in.readInt();
        this.payment_type = in.readInt();
        this.reward_point_id = in.readInt();
        this.cost = in.readInt();
    }

    public static final Parcelable.Creator<StockOutEntity> CREATOR = new Parcelable.Creator<StockOutEntity>() {
        @Override
        public StockOutEntity createFromParcel(Parcel source) {
            return new StockOutEntity(source);
        }

        @Override
        public StockOutEntity[] newArray(int size) {
            return new StockOutEntity[size];
        }
    };
}
