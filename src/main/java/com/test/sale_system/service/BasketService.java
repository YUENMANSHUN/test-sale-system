package com.test.sale_system.service;

import com.test.sale_system.model.*;
import com.test.sale_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class BasketService {
    private List<BasketItem> basketItems = new ArrayList<>(); // 購物籃
    @Autowired
    private ProductRepository productRepository;

    // 添加商品到購物籃
    public void addProductToBasket(Long productId, int quantity) {
        for (BasketItem item : basketItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        Optional<Product> productOptional = productRepository.findById(productId);
        if (!productOptional.isPresent()) {
            throw new NoSuchElementException("Product not found for ID: " + productId);
        }
        Product product = productOptional.get();
        basketItems.add(new BasketItem(product, quantity));
    }

    //reduce quantity of product in basket and remove if quantity is 0
    public void reduceProductQuantity(Long productId, int quantity) {
        for (BasketItem item : basketItems) {
            if (item.getProduct().getId().equals(productId)) {
                item.setQuantity(item.getQuantity() - quantity);
                if(item.getQuantity() == 0){
                    basketItems.remove(item);
                }
                return;
            }
        }
    }

    // 從購物籃移除商品
    public void removeProductFromBasket(Long productId) {
        basketItems.removeIf(item -> item.getProduct().getId().equals(productId));
    }

    // 獲取購物籃中的所有商品
    public List<BasketItem> getBasketItems() {
        return basketItems;
    }

    // 清空購物籃
    public void clearBasket() {
        basketItems.clear();
    }
}