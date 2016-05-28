package com.oncedoing.bikeshop.model;

import java.util.List;

/**
 * Created by kw on 2016/3/25.12:37.
 */
public class StockOutFeed {

    /**
     * rows : [{"stockout_id":"33db281fa8b449fc8c119231ac336e08","tenant_id":"oncebikeadmin","stockout_time":1457517669000,"note":"手套为赠品","customer_id":"18e17ac9a8a04927952a02e699d1db64","customer_name":"楚天阔","product_general":"[{\"name\":\"崔克-山地车-MARLIN7\",\"color\":\"红白\",\"size\":\"17.5\",\"other\":\"MARLIN\",\"amount\":1,\"code\":\"CJ2508945\",\"stockout_price\":3580},{\"name\":\"崔克-码表-Trip100-433762\",\"color\":\"白\",\"size\":\"无\",\"other\":\"trip\",\"amount\":1,\"code\":\"无\",\"stockout_price\":368},{\"name\":\"崔克-手套-Solstice-433816\",\"color\":\"黑\",\"size\":\"L\",\"other\":\"半指\",\"amount\":1,\"code\":\"无\",\"stockout_price\":0}]","turnover":394800,"unreceived_money":0,"stockout_type":0,"payment_type":0,"reward_point_id":151,"cost":360000},{"stockout_id":"32992cff19c549399e3f4a6d2b107ccc","tenant_id":"oncebikeadmin","stockout_time":1457516083000,"note":null,"customer_id":"463f0605ea5c404986bf8a85315f9514","customer_name":"陈文婷","product_general":"[{\"name\":\"千里达-山地车-PRECALIBER  6S\",\"color\":\"绿色\",\"size\":\"18\",\"other\":\"PRECALIBER  6S\",\"amount\":1}]","turnover":158000,"unreceived_money":0,"stockout_type":0,"payment_type":0,"reward_point_id":150,"cost":145000},{"stockout_id":"46356ed016d341f7b410c085e7491edb","tenant_id":"oncebikeadmin","stockout_time":1457331695000,"note":null,"customer_id":"5accf12cdba54ee4bad239a761d35ad4","customer_name":"胡广利","product_general":"[{\"name\":\"崔克-码表-数字Speed传感器-508126\",\"color\":\"银\",\"size\":\"无\",\"other\":\"数字传感器\",\"amount\":1,\"code\":\"无\",\"stockout_price\":358}]","turnover":35800,"unreceived_money":0,"stockout_type":0,"payment_type":20,"reward_point_id":144,"cost":30000},{"stockout_id":"ae954d66a46e4911a70aca504ceaaafa","tenant_id":"oncebikeadmin","stockout_time":1457331681000,"note":null,"customer_id":"463f0605ea5c404986bf8a85315f9514","customer_name":"陈文婷","product_general":"[{\"name\":\"崔克-码表-数字Speed传感器-508126\",\"color\":\"银\",\"size\":\"无\",\"other\":\"数字传感器\",\"amount\":1,\"code\":\"无\",\"stockout_price\":358}]","turnover":35800,"unreceived_money":0,"stockout_type":0,"payment_type":20,"reward_point_id":143,"cost":30000},{"stockout_id":"7c4eb94ff73a4bc6b27165b25d33b8ac","tenant_id":"oncebikeadmin","stockout_time":1457331669000,"note":null,"customer_id":"18e17ac9a8a04927952a02e699d1db64","customer_name":"楚天阔","product_general":"[{\"name\":\"崔克-码表-数字Speed传感器-508126\",\"color\":\"银\",\"size\":\"无\",\"other\":\"数字传感器\",\"amount\":1,\"code\":\"无\",\"stockout_price\":358}]","turnover":35800,"unreceived_money":0,"stockout_type":0,"payment_type":20,"reward_point_id":142,"cost":30000},{"stockout_id":"a235a8c7a3d3425181f03354995a6ae1","tenant_id":"oncebikeadmin","stockout_time":1457331649000,"note":null,"customer_id":"1089c1a87d2b4ba3b0c8534fd8150b59","customer_name":"吕金伟","product_general":"[{\"name\":\"崔克-手套-队版防风-502851\",\"color\":\"黑色\",\"size\":\"L\",\"other\":\"队版防风\",\"amount\":1,\"code\":\"无\",\"stockout_price\":228}]","turnover":22800,"unreceived_money":0,"stockout_type":0,"payment_type":20,"reward_point_id":141,"cost":17000},{"stockout_id":"b109553d7aab49428bbd0b6649fda92a","tenant_id":"oncebikeadmin","stockout_time":1457331636000,"note":null,"customer_id":"46922ec0f0ad4919965995ab571f7610","customer_name":"罗琦","product_general":"[{\"name\":\"崔克-手套-队版防风-502851\",\"color\":\"黑色\",\"size\":\"L\",\"other\":\"队版防风\",\"amount\":1,\"code\":\"无\",\"stockout_price\":228}]","turnover":22800,"unreceived_money":0,"stockout_type":0,"payment_type":20,"reward_point_id":140,"cost":17000},{"stockout_id":"a004accf6c634b15a74dfd170f286834","tenant_id":"oncebikeadmin","stockout_time":1457331622000,"note":null,"customer_id":"2e26ae96438e4565895fe66f8696916d","customer_name":"郭明祥","product_general":"[{\"name\":\"崔克-手套-队版防风-502851\",\"color\":\"黑色\",\"size\":\"L\",\"other\":\"队版防风\",\"amount\":1,\"code\":\"无\",\"stockout_price\":228}]","turnover":22800,"unreceived_money":0,"stockout_type":0,"payment_type":20,"reward_point_id":139,"cost":17000},{"stockout_id":"f67a7971344c4ba19f549e61e1a3ca17","tenant_id":"oncebikeadmin","stockout_time":1457331609000,"note":null,"customer_id":"2124319cf6024ba1a5ee884494e619a6","customer_name":"古天悯","product_general":"[{\"name\":\"崔克-手套-队版防风-502851\",\"color\":\"黑色\",\"size\":\"L\",\"other\":\"队版防风\",\"amount\":1,\"code\":\"无\",\"stockout_price\":228}]","turnover":22800,"unreceived_money":0,"stockout_type":0,"payment_type":20,"reward_point_id":138,"cost":17000},{"stockout_id":"1107556fba94449da7f9f3780d5aa6d0","tenant_id":"oncebikeadmin","stockout_time":1457325043000,"note":null,"customer_id":"40b0c39e28f0427aa1efef41012a1ae9","customer_name":"李天一","product_general":"[{\"name\":\"崔克-手套-队版防风-502851\",\"color\":\"黑色\",\"size\":\"L\",\"other\":\"队版防风\",\"amount\":1,\"code\":\"无\",\"stockout_price\":228}]","turnover":22800,"unreceived_money":0,"stockout_type":0,"payment_type":20,"reward_point_id":137,"cost":17000}]
     * total : 60
     */

    private int total;
    /**
     * stockout_id : 33db281fa8b449fc8c119231ac336e08
     * tenant_id : oncebikeadmin
     * stockout_time : 1457517669000
     * note : 手套为赠品
     * customer_id : 18e17ac9a8a04927952a02e699d1db64
     * customer_name : 楚天阔
     * product_general : [{"name":"崔克-山地车-MARLIN7","color":"红白","size":"17.5","other":"MARLIN","amount":1,"code":"CJ2508945","stockout_price":3580},{"name":"崔克-码表-Trip100-433762","color":"白","size":"无","other":"trip","amount":1,"code":"无","stockout_price":368},{"name":"崔克-手套-Solstice-433816","color":"黑","size":"L","other":"半指","amount":1,"code":"无","stockout_price":0}]
     * turnover : 394800
     * unreceived_money : 0
     * stockout_type : 0
     * payment_type : 0
     * reward_point_id : 151
     * cost : 360000
     */

    private List<StockOutEntity> rows;


    public void setTotal(int total) { this.total = total;}


    public void setRows(List<StockOutEntity> rows) { this.rows = rows;}


    public int getTotal() { return total;}


    public List<StockOutEntity> getRows() { return rows;}



}
