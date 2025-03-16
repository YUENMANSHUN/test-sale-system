package com.test.sale_system.service;

import com.test.sale_system.model.BasketItem;
import com.test.sale_system.model.Product;
import com.test.sale_system.model.Receipt;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {

    public Receipt calculateReceipt(List<BasketItem> basketItems) {
        double total = 0;       // 總價
        double discount = 0;    // 優惠金額

        for (BasketItem item : basketItems) {
            Product product = item.getProduct();
            int quantity = item.getQuantity();
            double itemTotal = product.getPrice() * quantity; // 單項商品總價
            total += itemTotal;

            // 應用優惠
            if (product.getDiscountConditionType() == Product.DiscountConditionType.QUANTITY_THRESHOLD && quantity >= product.getDiscountConditionValue()){
                if (product.getDiscountOfferType() == Product.DiscountOfferType.X_QUANTITY_HAVE_PERCENTAGE){
                    discount += product.getDiscountOfferQuantity() * product.getPrice() * (product.getDiscountOfferValue() / 100);
                }
            }
        }

        double finalTotal = total - discount;
        return new Receipt(basketItems, total, discount, finalTotal);
    }
}
