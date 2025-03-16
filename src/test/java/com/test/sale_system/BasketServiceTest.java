package com.test.sale_system;

import com.test.sale_system.model.BasketItem;
import com.test.sale_system.model.Product;
import com.test.sale_system.model.Receipt;
import com.test.sale_system.repository.ProductRepository;
import com.test.sale_system.service.BasketService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class BasketServiceTest {
    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductRepository productRepository;

    /*@Test
    public void testAddToBasket() {
        // 添加商品
        Product product = productRepository.save(new Product("Laptop", 1000.0 , ));
        basketService.addToBasket(1L, product.getId(), 2);

        // 驗證購物籃內容
        List<BasketItem> items = basketService.getBasketItems(1L);
        assertEquals(1, items.size());
        assertEquals(2, items.get(0).getQuantity());
    }

    @Test
    public void testRemoveFromBasket() {
        // 添加商品
        Product product = productRepository.save(new Product("Laptop", 1000.0));
        basketService.addToBasket(1L, product.getId(), 2);

        // 移除商品
        basketService.removeFromBasket(1L, product.getId());

        // 驗證購物籃內容
        List<BasketItem> items = basketService.getBasketItems(1L);
        assertTrue(items.isEmpty());
    }

    @Test
    public void testCalculateReceipt() {
        // 添加商品
        Product product1 = productRepository.save(new Product("Laptop", 1000.0));
        Product product2 = productRepository.save(new Product("Mouse", 50.0));
        basketService.addToBasket(1L, product1.getId(), 1);
        basketService.addToBasket(1L, product2.getId(), 2);

        // 計算收據
        Receipt receipt = basketService.calculateReceipt(1L);
        assertEquals(1100.0, receipt.getTotalPrice(), 0.01);
    }*/
}
