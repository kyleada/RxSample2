package com.oncedoing.bikeshop.model;

import java.util.List;

/**
 * Created by Administrator on 2016/3/23.
 */
public class BikeStockListFeed {
    /**
     * rows : [{"product_id":"c8ef316cb2c64d5fb2f762da452b6baf","product_name":"崔克-山地车-marlin5.1","product_color":null,"product_size":null,"product_other":"marlin5.1","product_price":0,"product_list_time":null,"product_brand":19,"product_category":1,"product_isprivate":0,"stockin_price":2000,"stockin_num":2,"stockin_time":1458640598000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"9d3b057aee6f442fa97bdca534999ff7"},{"product_id":"66ee060b790f47ef92adf607eebf990f","product_name":"千里达-山地车-8.3DS","product_color":"黑","product_size":"54","product_other":"8.3DS","product_price":0,"product_list_time":null,"product_brand":107,"product_category":2,"product_isprivate":0,"stockin_price":4400,"stockin_num":1,"stockin_time":1457600004000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"d2b477909a3a43fa982370e3be74b6d5"},{"product_id":"2527d9715c3d4171ac4b9faf0b66e99e","product_name":"千里达-山地车-M306","product_color":"","product_size":"17","product_other":"M306","product_price":0,"product_list_time":null,"product_brand":107,"product_category":1,"product_isprivate":0,"stockin_price":5400,"stockin_num":7,"stockin_time":1457600004000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"4cb43721cd514509b9cf540c31d2c4af"},{"product_id":"ea2ae4d861c146318dd225504aa11243","product_name":"千里达-山地车-M136","product_color":"黑","product_size":"54","product_other":"M316","product_price":0,"product_list_time":null,"product_brand":107,"product_category":2,"product_isprivate":0,"stockin_price":4650,"stockin_num":1,"stockin_time":1457600004000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"5a84fb52fa254dfcb82997f9d95a295a"},{"product_id":"86f966c2649e46ff929baeb5db797a4b","product_name":"千里达-公路车-XC-7","product_color":"黑","product_size":"54","product_other":"XC-7","product_price":0,"product_list_time":null,"product_brand":107,"product_category":2,"product_isprivate":0,"stockin_price":15400,"stockin_num":1,"stockin_time":1457600004000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"263e1819d6404044b0cbeb3141990fed"},{"product_id":"2ce1b9de81014f4a909ff640efe677d9","product_name":"千里达-山地车-JET20","product_color":"黑","product_size":"54","product_other":"JET20","product_price":0,"product_list_time":null,"product_brand":107,"product_category":2,"product_isprivate":0,"stockin_price":5400,"stockin_num":1,"stockin_time":1457600004000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"9c463464bcc84480ae732b1e0482e89e"},{"product_id":"893ae83ba8d94fb9bb3b770adfedd73b","product_name":"千里达-山地车-MALIN5","product_color":"黑","product_size":"54","product_other":"MARLINS5","product_price":0,"product_list_time":null,"product_brand":107,"product_category":2,"product_isprivate":0,"stockin_price":7750,"stockin_num":1,"stockin_time":1457600004000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"0e5f6fb0bd8a4621b9361fc7b802b895"},{"product_id":"f92bd0e80ceb4a609efc5224c3863a5c","product_name":"千里达-公路车-S5","product_color":"黑","product_size":"54","product_other":"MADONE","product_price":0,"product_list_time":null,"product_brand":107,"product_category":2,"product_isprivate":0,"stockin_price":80000,"stockin_num":1,"stockin_time":1457600004000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"480f81b5386e43dca6157767ad64f7ca"},{"product_id":"3f5492377a4448eab62c21d537e510a6","product_name":"千里达-山地车-8.3DS","product_color":"黑","product_size":"54","product_other":"8.3DS","product_price":0,"product_list_time":null,"product_brand":107,"product_category":2,"product_isprivate":0,"stockin_price":4400,"stockin_num":1,"stockin_time":1457599625000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"efeebd4e0ad24286be04d1a01df05056"},{"product_id":"f8eb5e7438834fc692be7e8d930cd351","product_name":"千里达-山地车-M306","product_color":"","product_size":"17","product_other":"M306","product_price":0,"product_list_time":null,"product_brand":107,"product_category":1,"product_isprivate":0,"stockin_price":5400,"stockin_num":7,"stockin_time":1457599625000,"paid_money":0,"supplier_name":null,"supplier_id":null,"note":null,"tenant_id":"oncebikeadmin","stockin_code":"c52c553254aa456d96c288e2575b06ac"}]
     * total : 79
     */

    private int total;
    /**
     * product_id : c8ef316cb2c64d5fb2f762da452b6baf
     * product_name : 崔克-山地车-marlin5.1
     * product_color : null
     * product_size : null
     * product_other : marlin5.1
     * product_price : 0
     * product_list_time : null
     * product_brand : 19
     * product_category : 1
     * product_isprivate : 0
     * stockin_price : 2000
     * stockin_num : 2
     * stockin_time : 1458640598000
     * paid_money : 0
     * supplier_name : null
     * supplier_id : null
     * note : null
     * tenant_id : oncebikeadmin
     * stockin_code : 9d3b057aee6f442fa97bdca534999ff7
     */

    private List<StockInEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<StockInEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<StockInEntity> getRows() {
        return rows;
    }


}
