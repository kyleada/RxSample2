package com.oncedoing.bikeshop.model;

/**
 * Created by Administrator on 2016/3/23.
 */
public class StockInEntity {
    /**
     * product_id : 66ee060b790f47ef92adf607eebf990f
     * product_name : 千里达-山地车-8.3DS
     * product_color : 黑
     * product_size : 54
     * product_other : 8.3DS
     * product_price : 0
     * product_list_time : null
     * product_brand : 107
     * product_category : 2
     * product_isprivate : 0
     * stockin_price : 4400
     * stockin_num : 1
     * stockin_time : 1457600004000
     * paid_money : 0
     * supplier_name : null
     * supplier_id : null
     * note : null
     * tenant_id : oncebikeadmin
     * stockin_code : d2b477909a3a43fa982370e3be74b6d5
     */

    private String product_id;
    private String product_name;
    private String product_color;
    private String product_size;
    private String product_other;
    private int product_price;
    private String product_list_time;
    private int product_brand;
    private int product_category;
    private int product_isprivate;
    private int stockin_price;
    private int stockin_num;
    private long stockin_time;
    private int paid_money;
    private String supplier_name;
    private String supplier_id;
    private String note;
    private String tenant_id;
    private String stockin_code;

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public void setProduct_color(String product_color) {
        this.product_color = product_color;
    }

    public void setProduct_size(String product_size) {
        this.product_size = product_size;
    }

    public void setProduct_other(String product_other) {
        this.product_other = product_other;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }

    public void setProduct_list_time(String product_list_time) {
        this.product_list_time = product_list_time;
    }

    public void setProduct_brand(int product_brand) {
        this.product_brand = product_brand;
    }

    public void setProduct_category(int product_category) {
        this.product_category = product_category;
    }

    public void setProduct_isprivate(int product_isprivate) {
        this.product_isprivate = product_isprivate;
    }

    public void setStockin_price(int stockin_price) {
        this.stockin_price = stockin_price;
    }

    public void setStockin_num(int stockin_num) {
        this.stockin_num = stockin_num;
    }

    public void setStockin_time(long stockin_time) {
        this.stockin_time = stockin_time;
    }

    public void setPaid_money(int paid_money) {
        this.paid_money = paid_money;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public void setSupplier_id(String supplier_id) {
        this.supplier_id = supplier_id;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setTenant_id(String tenant_id) {
        this.tenant_id = tenant_id;
    }

    public void setStockin_code(String stockin_code) {
        this.stockin_code = stockin_code;
    }

    public String getProduct_id() {
        return product_id;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getProduct_color() {
        return product_color;
    }

    public String getProduct_size() {
        return product_size;
    }

    public String getProduct_other() {
        return product_other;
    }

    public int getProduct_price() {
        return product_price;
    }

    public String getProduct_list_time() {
        return product_list_time;
    }

    public int getProduct_brand() {
        return product_brand;
    }

    public int getProduct_category() {
        return product_category;
    }

    public int getProduct_isprivate() {
        return product_isprivate;
    }

    public int getStockin_price() {
        return stockin_price;
    }

    public int getStockin_num() {
        return stockin_num;
    }

    public long getStockin_time() {
        return stockin_time;
    }

    public int getPaid_money() {
        return paid_money;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public String getSupplier_id() {
        return supplier_id;
    }

    public String getNote() {
        return note;
    }

    public String getTenant_id() {
        return tenant_id;
    }

    public String getStockin_code() {
        return stockin_code;
    }
}
