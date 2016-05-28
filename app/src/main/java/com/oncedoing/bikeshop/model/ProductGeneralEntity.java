package com.oncedoing.bikeshop.model;

/**
 * Created by kw on 2016/3/25.12:22.
 */
public class ProductGeneralEntity {

    /**
     * name : 崔克-山地车-MARLIN7
     * color : 红白
     * size : 17.5
     * other : MARLIN
     * amount : 1
     * code : CJ2508945
     * stockout_price : 3580
     */

    private String name;
    private String color;
    private String size;
    private String other;
    private int amount;
    private String code;
    private int stockout_price;


    public void setName(String name) { this.name = name;}


    public void setColor(String color) { this.color = color;}


    public void setSize(String size) { this.size = size;}


    public void setOther(String other) { this.other = other;}


    public void setAmount(int amount) { this.amount = amount;}


    public void setCode(String code) { this.code = code;}


    public void setStockout_price(int stockout_price) {
        this.stockout_price = stockout_price;
    }


    public String getName() { return name;}


    public String getColor() { return color;}


    public String getSize() { return size;}


    public String getOther() { return other;}


    public int getAmount() { return amount;}


    public String getCode() { return code;}


    public int getStockout_price() { return stockout_price;}
}
