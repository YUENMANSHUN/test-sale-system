package com.test.sale_system.service;

import com.test.sale_system.model.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class BasketService {
    private List<BasketItem> basket = Collections.synchronizedList(new ArrayList<>());

    public void addToBasket(Product product, int quantity) {
        basket.add(new BasketItem(product, quantity));
    }

    public void removeFromBasket(Long productId) {
        basket.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    public List<BasketItem> getBasketItems() {
        return basket;
    }

 /*   public Receipt calculateReceipt(List<BasketItem> basketItems) {
        double total = 0; // 總價
        double discount = 0; // 優惠金額

        for (BasketItem item : basketItems) {
            Product product = item.getProduct();
            double itemTotal = product.getPrice() * item.getQuantity();
            total += itemTotal;

            // 檢查是否有優惠條件和優惠內容
            DiscountCondition condition = product.getDiscountCondition();
            DiscountOffer offer = product.getDiscountOffer();

            if (condition != null && offer != null) {
                if (condition.getType() == DiscountCondition.DiscountConditionType.QUANTITY_THRESHOLD &&
                        item.getQuantity() >= condition.getRequiredValue() &&
                        offer.getType() == DiscountOffer.DiscountOfferType.X_QUANTITY_HAVE_PERCENTAGE) {
                    // 購買滿 X 件享受一定百分比折扣
                    discount += itemTotal * (offer.getDiscountValue() / 100);
                }
            }
        }

        double finalTotal = total - discount;
        return new Receipt(basketItems, total, discount, finalTotal);
    }*/
}
