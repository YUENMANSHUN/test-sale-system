package com.test.sale_system.controller;

import com.test.sale_system.model.BasketItem;
import com.test.sale_system.model.Product;
import com.test.sale_system.model.Receipt;
import com.test.sale_system.repository.ProductRepository;
import com.test.sale_system.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
    @Autowired
    private BasketService basketService;

    @PostMapping("/basket")
    public void addToBasket(@RequestBody BasketItem item) {
        basketService.addToBasket(item.getProduct(), item.getQuantity());
    }

    @DeleteMapping("/basket/{productId}")
    public void removeFromBasket(@PathVariable Long productId) {
        basketService.removeFromBasket(productId);
    }

//    @GetMapping("/receipt")
//    public Receipt calculateReceipt() {
//        return basketService.calculateReceipt();
//    }
}
