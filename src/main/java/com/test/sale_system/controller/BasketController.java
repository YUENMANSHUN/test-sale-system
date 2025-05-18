package com.test.sale_system.controller;

import com.test.sale_system.model.ApiResponse;
import com.test.sale_system.model.BasketItem;
import com.test.sale_system.model.Product;
import com.test.sale_system.model.Receipt;
import com.test.sale_system.request.BasketApiRequest;
import com.test.sale_system.service.BasketService;
import com.test.sale_system.service.ReceiptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/basket")
public class BasketController {
    @Autowired
    private BasketService basketService;
    @Autowired
    private ReceiptService receiptService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<List<BasketItem>>> addToBasket(@RequestBody BasketApiRequest item) {
        try {
            basketService.addProductToBasket(item.getProductId(), item.getQuantity());
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Basket added successfully", basketService.getBasketItems())
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to add basket: " + e.getMessage(), null)
            );
        }
    }
    @PostMapping("/reduce")
    public ResponseEntity<ApiResponse<List<BasketItem>>> reduceBasket(@RequestBody BasketApiRequest item) {
        try {
            basketService.reduceProductQuantity(item.getProductId(), item.getQuantity());
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Basket reduced successfully", basketService.getBasketItems())
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to reduce basket: " + e.getMessage(), null)
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<List<BasketItem>>> removeFromBasket(@PathVariable Long id) {
        try {
            basketService.removeProductFromBasket(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Basket removed successfully", basketService.getBasketItems())
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to remove basket: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<BasketItem>>> getBasket() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Basket retrieved successfully", basketService.getBasketItems())
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to retrieve basket: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/get_receipt")
    public ResponseEntity<ApiResponse<Receipt>> getReceipt() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Receipt retrieved successfully", receiptService.calculateReceipt(basketService.getBasketItems()))
            );
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to retrieve receipt: " + e.getMessage(), null)
            );
        }
    }

}
