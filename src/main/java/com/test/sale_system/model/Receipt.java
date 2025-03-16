package com.test.sale_system.model;

import java.util.List;

public class Receipt {
    private List<BasketItem> items; // 購物籃中的商品
    private double total;          // 總價
    private double discount;       // 優惠金額
    private double finalTotal;     // 最終總價

    public Receipt(List<BasketItem> items, double total, double discount, double finalTotal) {
        this.items = items;
        this.total = total;
        this.discount = discount;
        this.finalTotal = finalTotal;
    }

    public List<BasketItem> getItems() {
        return items;
    }

    public void setItems(List<BasketItem> items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getFinalTotal() {
        return finalTotal;
    }

    public void setFinalTotal(double finalTotal) {
        this.finalTotal = finalTotal;
    }
}
