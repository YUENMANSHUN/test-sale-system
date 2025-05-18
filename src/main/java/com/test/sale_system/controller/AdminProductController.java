package com.test.sale_system.controller;

import com.test.sale_system.model.ApiResponse;
import com.test.sale_system.model.Product;
import com.test.sale_system.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/admin/products")
public class AdminProductController {

    @Autowired
    private ProductRepository productRepository;

    @PostMapping
    public ResponseEntity<ApiResponse<Product>> createProduct(@RequestBody Product product) {
        try {
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Product created successfully", savedProduct)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to create product: " + e.getMessage(), null)
            );
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<?>> deleteProduct(@PathVariable Long id) {
        try {
            productRepository.deleteById(id);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Product deleted successfully", null)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to delete product: " + e.getMessage(), null)
            );
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse<Product>> addDiscount(@PathVariable Long id, @RequestBody Product productData) {
        try {
            Product product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found"));
            product.setDiscountConditionType(productData.getDiscountConditionType());
            product.setDiscountConditionValue(productData.getDiscountConditionValue());
            product.setDiscountOfferType(productData.getDiscountOfferType());
            product.setDiscountOfferQuantity(productData.getDiscountOfferQuantity());
            product.setDiscountOfferValue(productData.getDiscountOfferValue());
            Product savedProduct = productRepository.save(product);
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Product created successfully", savedProduct)
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to create product: " + e.getMessage(), null)
            );
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<Iterable<Product>>> getAllProducts() {
        try {
            return ResponseEntity.ok(
                    new ApiResponse<>(true, "Products fetched successfully", productRepository.findAll())
            );
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(
                    new ApiResponse<>(false, "Failed to fetch products: " + e.getMessage(), null)
            );
        }
    }
}
