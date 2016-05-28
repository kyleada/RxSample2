package com.oncedoing.bikeshop.model;

import com.litesuits.orm.db.annotation.Mapping;
import com.litesuits.orm.db.annotation.PrimaryKey;
import com.litesuits.orm.db.annotation.Table;
import com.litesuits.orm.db.enums.AssignType;
import com.litesuits.orm.db.enums.Relation;


/**
 * Created by Administrator on 2016/3/20.
 */

@Table("customer_sort_entity")
public class CustomerSortEntity   {

    @PrimaryKey(AssignType.AUTO_INCREMENT)
    private long id;

    @Mapping(Relation.OneToOne)
    private SortModel sortModel;
    @Mapping(Relation.OneToOne)
    private CustomerEntity customerEntity;

    public CustomerSortEntity(SortModel sortModel, CustomerEntity customerEntity) {
        this.sortModel = sortModel;
        this.customerEntity = customerEntity;
    }

    public void setCustomerEntity(CustomerEntity customerEntity){
        this.customerEntity = customerEntity;
    }

    public CustomerEntity getCustomerEntity() {
        return customerEntity;
    }

    public SortModel getSortModel() {
        return sortModel;
    }

    public void setSortModel(SortModel sortModel) {
        this.sortModel = sortModel;
    }
}
