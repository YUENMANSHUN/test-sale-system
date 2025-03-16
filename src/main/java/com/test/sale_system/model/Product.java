package com.test.sale_system.model;

import jakarta.persistence.*;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 商品名稱
    private double price; // 商品價格

    @Enumerated(EnumType.STRING)
    private DiscountConditionType discountConditionType; // 優惠條件類型

    private int discountConditionValue; // 優惠條件值

    @Enumerated(EnumType.STRING)
    private DiscountOfferType discountOfferType; // 優惠內容類型

    private int discountOfferQuantity; // 優惠內容數量
    private double discountOfferValue; // 優惠內容值（如折扣百分比）

    // 枚舉類型
    public enum DiscountConditionType {
        NULL, // 無
        QUANTITY_THRESHOLD, // 購買滿 X 個數量
//        AMOUNT_THRESHOLD    // 購買滿 X 金額
    }

    public enum DiscountOfferType {
        NULL, // 無
        X_QUANTITY_HAVE_PERCENTAGE // 購買滿 X 件享受一定百分比折扣
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public DiscountConditionType getDiscountConditionType() {
        return discountConditionType;
    }

    public void setDiscountConditionType(DiscountConditionType discountConditionType) {
        this.discountConditionType = discountConditionType;
    }

    public int getDiscountConditionValue() {
        return discountConditionValue;
    }

    public void setDiscountConditionValue(int discountConditionValue) {
        this.discountConditionValue = discountConditionValue;
    }

    public DiscountOfferType getDiscountOfferType() {
        return discountOfferType;
    }

    public void setDiscountOfferType(DiscountOfferType discountOfferType) {
        this.discountOfferType = discountOfferType;
    }

    public int getDiscountOfferQuantity() {
        return discountOfferQuantity;
    }

    public void setDiscountOfferQuantity(int discountOfferQuantity) {
        this.discountOfferQuantity = discountOfferQuantity;
    }

    public double getDiscountOfferValue() {
        return discountOfferValue;
    }

    public void setDiscountOfferValue(double discountOfferValue) {
        this.discountOfferValue = discountOfferValue;
    }
}
