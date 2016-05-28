package com.oncedoing.bikeshop.model;

import java.util.List;

/**
 * Created by Administrator on 2016/3/16.
 */
public class CustomInfoFeed {

    /**
     * success : true
     * message :
     * paginator : {"limit":10,"page":1,"totalCount":30}
     * result : [{"customer_id":"20bbbee010244f85abac894a812427f4","weixin_openid":null,"nick_name":null,"customer_name":"于超","customer_age":"30~40","customer_gender":"男","customer_phone":"18640986081","customer_address":null,"customer_remark":null,"customer_bike":"崔克-山地车","point_amount":10,"point_total":10,"coin_amount":10,"coin_total":10,"consumption_total":15800,"source_type":"ACTIVITY","source_id":"17","search_key":"于超;18640986081","create_time":1456485009554,"membership_level_name":null,"membership_discount":null,"membership_level_id":null},{"customer_id":"5fdc92b45ef94445afcee383c51330b9","weixin_openid":null,"nick_name":null,"customer_name":"刘长军","customer_age":"30~40","customer_gender":"男","customer_phone":"13254784569","customer_address":null,"customer_remark":null,"customer_bike":"崔克-公路车","point_amount":237,"point_total":237,"coin_amount":100,"coin_total":100,"consumption_total":2376200,"source_type":"ACTIVITY","source_id":"84","search_key":"刘长军;13254784569","create_time":1450627200000,"membership_level_name":"绿钻会员","membership_discount":90,"membership_level_id":8},{"customer_id":"8f6624622bef4ca387b1b90cdcdd05e3","weixin_openid":null,"nick_name":null,"customer_name":"徐庶","customer_age":"20-30","customer_gender":"男","customer_phone":"15011221144","customer_address":"","customer_remark":"","customer_bike":"","point_amount":48,"point_total":48,"coin_amount":100,"coin_total":100,"consumption_total":485800,"source_type":"ADMIN","source_id":"890d9e62d6c84a3aa1cd79782d1aad6e","search_key":"徐庶;15011221144","create_time":1441641600000,"membership_level_name":null,"membership_discount":null,"membership_level_id":null},{"customer_id":"40b0c39e28f0427aa1efef41012a1ae9","weixin_openid":null,"nick_name":null,"customer_name":"李天一","customer_age":"20-30","customer_gender":"男","customer_phone":"15145784532","customer_address":"","customer_remark":"","customer_bike":"","point_amount":107,"point_total":107,"coin_amount":100,"coin_total":100,"consumption_total":645400,"source_type":"ADMIN","source_id":"890d9e62d6c84a3aa1cd79782d1aad6e","search_key":"李天一;15145784532","create_time":1439395200000,"membership_level_name":"普通会员","membership_discount":98,"membership_level_id":7},{"customer_id":"1b9b7c01c7d34d21b98e319b7f5319d2","weixin_openid":null,"nick_name":null,"customer_name":"楚云天","customer_age":"20-30","customer_gender":"男","customer_phone":"150235645689","customer_address":"","customer_remark":"","customer_bike":"","point_amount":485,"point_total":485,"coin_amount":100,"coin_total":100,"consumption_total":592200,"source_type":"ADMIN","source_id":"890d9e62d6c84a3aa1cd79782d1aad6e","search_key":"楚云天;150235645689","create_time":1433952000000,"membership_level_name":null,"membership_discount":null,"membership_level_id":null},{"customer_id":"aa0e73db066d42b88d1ef25d401920f3","weixin_openid":null,"nick_name":null,"customer_name":"余周周","customer_age":"20-30","customer_gender":"男","customer_phone":"13245788985","customer_address":"","customer_remark":"","customer_bike":"","point_amount":15,"point_total":15,"coin_amount":100,"coin_total":100,"consumption_total":155600,"source_type":"ADMIN","source_id":"890d9e62d6c84a3aa1cd79782d1aad6e","search_key":"余周周;13245788985","create_time":1431964800000,"membership_level_name":null,"membership_discount":null,"membership_level_id":null},{"customer_id":"c69d63356792497e9fafd4dfb40c050f","weixin_openid":null,"nick_name":null,"customer_name":"詹玉明","customer_age":"20-30","customer_gender":"男","customer_phone":"15789505062","customer_address":"","customer_remark":"","customer_bike":"","point_amount":49,"point_total":49,"coin_amount":120,"coin_total":120,"consumption_total":497800,"source_type":"ADMIN","source_id":"890d9e62d6c84a3aa1cd79782d1aad6e","search_key":"詹玉明;15789505062","create_time":1431532800000,"membership_level_name":null,"membership_discount":null,"membership_level_id":null},{"customer_id":"a84a8b699a5548d094a4d00b97a1597c","weixin_openid":null,"nick_name":null,"customer_name":"李昕锐","customer_age":"20-30","customer_gender":"男","customer_phone":"13023232323","customer_address":"","customer_remark":"","customer_bike":"","point_amount":46,"point_total":46,"coin_amount":120,"coin_total":120,"consumption_total":468600,"source_type":"ADMIN","source_id":"890d9e62d6c84a3aa1cd79782d1aad6e","search_key":"李昕锐;13023232323","create_time":1431446400000,"membership_level_name":null,"membership_discount":null,"membership_level_id":null},{"customer_id":"18e17ac9a8a04927952a02e699d1db64","weixin_openid":null,"nick_name":null,"customer_name":"楚天阔","customer_age":"20-30","customer_gender":"男","customer_phone":"13023232340","customer_address":"","customer_remark":"","customer_bike":"","point_amount":3970,"point_total":3970,"coin_amount":100,"coin_total":100,"consumption_total":3096600,"source_type":"ADMIN","source_id":"890d9e62d6c84a3aa1cd79782d1aad6e","search_key":"楚天阔;13023232340","create_time":1431360000000,"membership_level_name":null,"membership_discount":null,"membership_level_id":null},{"customer_id":"d6ca03530c294a59aaeb86a76ffc6e0f","weixin_openid":null,"nick_name":null,"customer_name":"凌翔倩","customer_age":"20-30","customer_gender":"女","customer_phone":"13247895689","customer_address":"","customer_remark":"","customer_bike":"","point_amount":1,"point_total":1,"coin_amount":120,"coin_total":120,"consumption_total":11000,"source_type":"ADMIN","source_id":"890d9e62d6c84a3aa1cd79782d1aad6e","search_key":"凌翔倩;13247895689","create_time":1431360000000,"membership_level_name":null,"membership_discount":null,"membership_level_id":null}]
     */

    private boolean success;
    private String message;
    /**
     * limit : 10
     * page : 1
     * totalCount : 30
     */

    private PaginatorEntity paginator;
    /**
     * customer_id : 20bbbee010244f85abac894a812427f4
     * weixin_openid : null
     * nick_name : null
     * customer_name : 于超
     * customer_age : 30~40
     * customer_gender : 男
     * customer_phone : 18640986081
     * customer_address : null
     * customer_remark : null
     * customer_bike : 崔克-山地车
     * point_amount : 10
     * point_total : 10
     * coin_amount : 10
     * coin_total : 10
     * consumption_total : 15800
     * source_type : ACTIVITY
     * source_id : 17
     * search_key : 于超;18640986081
     * create_time : 1456485009554
     * membership_level_name : null
     * membership_discount : null
     * membership_level_id : null
     */

    private List<CustomEntity> result;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setPaginator(PaginatorEntity paginator) {
        this.paginator = paginator;
    }

    public void setResult(List<CustomEntity> result) {
        this.result = result;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getMessage() {
        return message;
    }

    public PaginatorEntity getPaginator() {
        return paginator;
    }

    public List<CustomEntity> getResult() {
        return result;
    }

    public static class PaginatorEntity {
        private int limit;
        private int page;
        private int totalCount;

        public void setLimit(int limit) {
            this.limit = limit;
        }

        public void setPage(int page) {
            this.page = page;
        }

        public void setTotalCount(int totalCount) {
            this.totalCount = totalCount;
        }

        public int getLimit() {
            return limit;
        }

        public int getPage() {
            return page;
        }

        public int getTotalCount() {
            return totalCount;
        }
    }

}
