package com.test.sale_system;

import com.test.sale_system.model.BasketItem;
import com.test.sale_system.model.Product;
import com.test.sale_system.repository.ProductRepository;
import com.test.sale_system.service.BasketService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class BasketServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private BasketService basketService;

    private Product product1;
    private Product product2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // 初始化測試數據
        product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setPrice(1000.0);

        product2 = new Product();
        product2.setId(2L);
        product2.setName("Smartphone");
        product2.setPrice(800.0);
    }

    @Test
    void testAddProductToBasket() {
        // 模擬 productRepository 的行為
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        // 添加商品到購物籃
        basketService.addProductToBasket(1L, 2);

        // 驗證購物籃內容
        List<BasketItem> basketItems = basketService.getBasketItems();
        assertEquals(1, basketItems.size());
        assertEquals(product1, basketItems.get(0).getProduct());
        assertEquals(2, basketItems.get(0).getQuantity());

        // 驗證 productRepository 的調用
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    void testAddProductToBasket_ProductNotFound() {
        // 模擬 productRepository 的行為
        when(productRepository.findById(3L)).thenReturn(Optional.empty());

        // 測試添加不存在的商品
        assertThrows(NoSuchElementException.class, () -> basketService.addProductToBasket(3L, 1));

        // 驗證 productRepository 的調用
        verify(productRepository, times(1)).findById(3L);
    }

    @Test
    void testReduceProductQuantity() {
        // 模擬 productRepository 的行為
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        // 添加商品到購物籃
        basketService.addProductToBasket(1L, 5);

        // 減少商品數量
        basketService.reduceProductQuantity(1L, 3);

        // 驗證購物籃內容
        List<BasketItem> basketItems = basketService.getBasketItems();
        assertEquals(1, basketItems.size());
        assertEquals(2, basketItems.get(0).getQuantity());
    }

    @Test
    void testReduceProductQuantity_RemoveWhenZero() {
        // 模擬 productRepository 的行為
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        // 添加商品到購物籃
        basketService.addProductToBasket(1L, 2);

        // 減少商品數量至 0
        basketService.reduceProductQuantity(1L, 2);

        // 驗證購物籃內容
        List<BasketItem> basketItems = basketService.getBasketItems();
        assertTrue(basketItems.isEmpty());
    }

    @Test
    void testRemoveProductFromBasket() {
        // 模擬 productRepository 的行為
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));

        // 添加商品到購物籃
        basketService.addProductToBasket(1L, 2);

        // 移除商品
        basketService.removeProductFromBasket(1L);

        // 驗證購物籃內容
        List<BasketItem> basketItems = basketService.getBasketItems();
        assertTrue(basketItems.isEmpty());
    }

    @Test
    void testClearBasket() {
        // 模擬 productRepository 的行為
        when(productRepository.findById(1L)).thenReturn(Optional.of(product1));
        when(productRepository.findById(2L)).thenReturn(Optional.of(product2));

        // 添加商品到購物籃
        basketService.addProductToBasket(1L, 2);
        basketService.addProductToBasket(2L, 1);

        // 清空購物籃
        basketService.clearBasket();

        // 驗證購物籃內容
        List<BasketItem> basketItems = basketService.getBasketItems();
        assertTrue(basketItems.isEmpty());
    }
}
