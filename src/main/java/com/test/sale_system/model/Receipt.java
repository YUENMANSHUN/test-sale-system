package com.test.sale_system.model;

import java.util.List;

public class Receipt {

    //items：購買的商品列表及其數量。
    //totalPrice：總價。
    //discountApplied：應用的優惠。

    private List<BasketItem> items;
    private double totalPrice;
    private String discountApplied;

    public Receipt(List<BasketItem> items, double totalPrice, String discountApplied) {
        this.items = items;
        this.totalPrice = totalPrice;
        this.discountApplied = discountApplied;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getDiscountApplied() {
        return discountApplied;
    }

    public void setDiscountApplied(String discountApplied) {
        this.discountApplied = discountApplied;
    }
}
