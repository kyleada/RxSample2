package com.oncedoing.bikeshop.model;

import java.util.List;

/**
 * Created by Administrator on 2016/3/14.
 */
public class BikeListFeed {


    /**
     * rows : [{"id":"2527d9715c3d4171ac4b9faf0b66e99e","name":"千里达-山地车-M306","tenant_id":"oncebikeadmin","price":5560,"color":"","size":"17","other":"M306","brand":107,"brandname":"千里达","category":1,"note":"无","existnum":7,"stockin_num":null,"list_time":"2015","isprivate":null,"create_time":1457600004000,"update_time":1457600004000,"search_key":null,"stockin_price":null},{"id":"86f966c2649e46ff929baeb5db797a4b","name":"千里达-公路车-XC-7","tenant_id":"oncebikeadmin","price":15470,"color":"黑","size":"54","other":"XC-7","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457600004000,"update_time":1457600004000,"search_key":null,"stockin_price":null},{"id":"893ae83ba8d94fb9bb3b770adfedd73b","name":"千里达-山地车-MALIN5","tenant_id":"oncebikeadmin","price":7850,"color":"黑","size":"54","other":"MARLINS5","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457600004000,"update_time":1457600004000,"search_key":null,"stockin_price":null},{"id":"ea2ae4d861c146318dd225504aa11243","name":"千里达-山地车-M136","tenant_id":"oncebikeadmin","price":4750,"color":"黑","size":"54","other":"M316","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457600004000,"update_time":1457600004000,"search_key":null,"stockin_price":null},{"id":"2ce1b9de81014f4a909ff640efe677d9","name":"千里达-山地车-JET20","tenant_id":"oncebikeadmin","price":5540,"color":"黑","size":"54","other":"JET20","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457600004000,"update_time":1457600004000,"search_key":null,"stockin_price":null},{"id":"66ee060b790f47ef92adf607eebf990f","name":"千里达-山地车-8.3DS","tenant_id":"oncebikeadmin","price":4580,"color":"黑","size":"54","other":"8.3DS","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457600004000,"update_time":1457600004000,"search_key":null,"stockin_price":null},{"id":"f92bd0e80ceb4a609efc5224c3863a5c","name":"千里达-公路车-S5","tenant_id":"oncebikeadmin","price":98000,"color":"黑","size":"54","other":"MADONE","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457600004000,"update_time":1457600004000,"search_key":null,"stockin_price":null},{"id":"fbd11abcb22343a9bb91857d06f24508","name":"千里达-山地车-M136","tenant_id":"oncebikeadmin","price":4750,"color":"黑","size":"54","other":"M316","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457599625000,"update_time":1457599625000,"search_key":null,"stockin_price":null},{"id":"e8c92f5075d24c748af27815793af739","name":"千里达-山地车-JET20","tenant_id":"oncebikeadmin","price":5540,"color":"黑","size":"54","other":"JET20","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457599625000,"update_time":1457599625000,"search_key":null,"stockin_price":null},{"id":"b191a5222ecf4510b5506d0d9b73d1e9","name":"千里达-公路车-XC-7","tenant_id":"oncebikeadmin","price":15470,"color":"黑","size":"54","other":"XC-7","brand":107,"brandname":"千里达","category":2,"note":"无","existnum":1,"stockin_num":null,"list_time":"2016","isprivate":null,"create_time":1457599625000,"update_time":1457599625000,"search_key":null,"stockin_price":null}]
     * total : 43
     */

    private int total;
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

    private List<BikeInfoEntity> rows;

    public void setTotal(int total) {
        this.total = total;
    }

    public void setRows(List<BikeInfoEntity> rows) {
        this.rows = rows;
    }

    public int getTotal() {
        return total;
    }

    public List<BikeInfoEntity> getRows() {
        return rows;
    }
}
