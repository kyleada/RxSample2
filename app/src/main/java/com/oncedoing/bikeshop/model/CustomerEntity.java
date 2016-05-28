package com.oncedoing.bikeshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;

/**
 * Created by Administrator on 2016/3/16.
 */

@Table("customer_entity")
public class CustomerEntity implements Parcelable {

    @PrimaryKey(AssignType.BY_MYSELF)
    String customer_id;
    private String weixin_openid;
    private String nick_name;
    private String customer_name;
    private String customer_age;
    private String customer_gender;
    private String customer_phone;
    private String customer_address;
    private String customer_remark;
    private String customer_bike;
    private int point_amount;
    private int point_total;
    private int coin_amount;
    private int coin_total;
    private int consumption_total;
    private String source_type;
    private String source_id;
    private String search_key;
    private long create_time;
    private String membership_level_name;
    private String membership_discount;
    private String membership_level_id;

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public void setWeixin_openid(String weixin_openid) {
        this.weixin_openid = weixin_openid;
    }

    public void setNick_name(String nick_name) {
        this.nick_name = nick_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public void setCustomer_age(String customer_age) {
        this.customer_age = customer_age;
    }

    public void setCustomer_gender(String customer_gender) {
        this.customer_gender = customer_gender;
    }

    public void setCustomer_phone(String customer_phone) {
        this.customer_phone = customer_phone;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public void setCustomer_remark(String customer_remark) {
        this.customer_remark = customer_remark;
    }

    public void setCustomer_bike(String customer_bike) {
        this.customer_bike = customer_bike;
    }

    public void setPoint_amount(int point_amount) {
        this.point_amount = point_amount;
    }

    public void setPoint_total(int point_total) {
        this.point_total = point_total;
    }

    public void setCoin_amount(int coin_amount) {
        this.coin_amount = coin_amount;
    }

    public void setCoin_total(int coin_total) {
        this.coin_total = coin_total;
    }

    public void setConsumption_total(int consumption_total) {
        this.consumption_total = consumption_total;
    }

    public void setSource_type(String source_type) {
        this.source_type = source_type;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public void setSearch_key(String search_key) {
        this.search_key = search_key;
    }

    public void setCreate_time(long create_time) {
        this.create_time = create_time;
    }

    public void setMembership_level_name(String membership_level_name) {
        this.membership_level_name = membership_level_name;
    }

    public void setMembership_discount(String membership_discount) {
        this.membership_discount = membership_discount;
    }

    public void setMembership_level_id(String membership_level_id) {
        this.membership_level_id = membership_level_id;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public Object getWeixin_openid() {
        return weixin_openid;
    }

    public Object getNick_name() {
        return nick_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public String getCustomer_age() {
        return customer_age;
    }

    public String getCustomer_gender() {
        return customer_gender;
    }

    public String getCustomer_phone() {
        return customer_phone;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public Object getCustomer_remark() {
        return customer_remark;
    }

    public String getCustomer_bike() {
        return customer_bike;
    }

    public int getPoint_amount() {
        return point_amount;
    }

    public int getPoint_total() {
        return point_total;
    }

    public int getCoin_amount() {
        return coin_amount;
    }

    public int getCoin_total() {
        return coin_total;
    }

    public int getConsumption_total() {
        return consumption_total;
    }

    public String getSource_type() {
        return source_type;
    }

    public String getSource_id() {
        return source_id;
    }

    public String getSearch_key() {
        return search_key;
    }

    public long getCreate_time() {
        return create_time;
    }

    public Object getMembership_level_name() {
        return membership_level_name;
    }

    public Object getMembership_discount() {
        return membership_discount;
    }

    public Object getMembership_level_id() {
        return membership_level_id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.customer_id);
        dest.writeString(this.weixin_openid);
        dest.writeString(this.nick_name);
        dest.writeString(this.customer_name);
        dest.writeString(this.customer_age);
        dest.writeString(this.customer_gender);
        dest.writeString(this.customer_phone);
        dest.writeString(this.customer_address);
        dest.writeString(this.customer_remark);
        dest.writeString(this.customer_bike);
        dest.writeInt(this.point_amount);
        dest.writeInt(this.point_total);
        dest.writeInt(this.coin_amount);
        dest.writeInt(this.coin_total);
        dest.writeInt(this.consumption_total);
        dest.writeString(this.source_type);
        dest.writeString(this.source_id);
        dest.writeString(this.search_key);
        dest.writeLong(this.create_time);
        dest.writeString(this.membership_level_name);
        dest.writeString(this.membership_discount);
        dest.writeString(this.membership_level_id);
    }

    public CustomerEntity() {
    }

    protected CustomerEntity(Parcel in) {
        this.customer_id = in.readString();
        this.weixin_openid = in.readString();
        this.nick_name = in.readString();
        this.customer_name = in.readString();
        this.customer_age = in.readString();
        this.customer_gender = in.readString();
        this.customer_phone = in.readString();
        this.customer_address = in.readString();
        this.customer_remark = in.readString();
        this.customer_bike = in.readString();
        this.point_amount = in.readInt();
        this.point_total = in.readInt();
        this.coin_amount = in.readInt();
        this.coin_total = in.readInt();
        this.consumption_total = in.readInt();
        this.source_type = in.readString();
        this.source_id = in.readString();
        this.search_key = in.readString();
        this.create_time = in.readLong();
        this.membership_level_name = in.readString();
        this.membership_discount = in.readString();
        this.membership_level_id = in.readString();
    }

    public static final Parcelable.Creator<CustomerEntity> CREATOR = new Parcelable.Creator<CustomerEntity>() {
        @Override
        public CustomerEntity createFromParcel(Parcel source) {
            return new CustomerEntity(source);
        }

        @Override
        public CustomerEntity[] newArray(int size) {
            return new CustomerEntity[size];
        }
    };
}
