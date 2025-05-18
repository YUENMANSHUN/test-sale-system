package com.test.sale_system;

import com.test.sale_system.model.BasketItem;
import com.test.sale_system.model.Product;
import com.test.sale_system.model.Receipt;
import com.test.sale_system.service.ReceiptService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ReceiptServiceTest {

    private ReceiptService receiptService;

    @BeforeEach
    void setUp() {
        receiptService = new ReceiptService();
    }

    @Test
    void testCalculateReceipt_NoDiscount() {
        // 準備測試數據
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setPrice(1000.0);
        product1.setDiscountConditionType(Product.DiscountConditionType.NULL); // 無優惠
        product1.setDiscountOfferType(Product.DiscountOfferType.NULL);

        BasketItem basketItem1 = new BasketItem(product1, 2); // 購買 2 台筆記本

        List<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(basketItem1);

        // 計算收據
        Receipt receipt = receiptService.calculateReceipt(basketItems);

        // 驗證結果
        assertEquals(2000.0, receipt.getTotal()); // 總價
        assertEquals(0.0, receipt.getDiscount()); // 無優惠
        assertEquals(2000.0, receipt.getFinalTotal()); // 最終總價
    }

    @Test
    void testCalculateReceipt_WithPercentageDiscount() {
        // 準備測試數據
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setPrice(1000.0);
        product1.setDiscountConditionType(Product.DiscountConditionType.QUANTITY_THRESHOLD); // 購買滿 3 件
        product1.setDiscountConditionValue(3);
        product1.setDiscountOfferType(Product.DiscountOfferType.X_QUANTITY_HAVE_PERCENTAGE); // 享受百分比折扣
        product1.setDiscountOfferQuantity(3); // 3 件享受折扣
        product1.setDiscountOfferValue(10.0); // 10% 折扣

        BasketItem basketItem1 = new BasketItem(product1, 3); // 購買 3 台筆記本

        List<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(basketItem1);

        // 計算收據
        Receipt receipt = receiptService.calculateReceipt(basketItems);

        // 驗證結果
        assertEquals(3000.0, receipt.getTotal()); // 總價
        assertEquals(300.0, receipt.getDiscount()); // 10% 折扣
        assertEquals(2700.0, receipt.getFinalTotal()); // 最終總價
    }

    @Test
    void testCalculateReceipt_MultipleItemsWithAndWithoutDiscount() {
        // 準備測試數據
        Product product1 = new Product();
        product1.setId(1L);
        product1.setName("Laptop");
        product1.setPrice(1000.0);
        product1.setDiscountConditionType(Product.DiscountConditionType.QUANTITY_THRESHOLD); // 購買滿 3 件
        product1.setDiscountConditionValue(3);
        product1.setDiscountOfferType(Product.DiscountOfferType.X_QUANTITY_HAVE_PERCENTAGE); // 享受百分比折扣
        product1.setDiscountOfferQuantity(3); // 3 件享受折扣
        product1.setDiscountOfferValue(10.0); // 10% 折扣

        Product product2 = new Product();
        product2.setId(2L);
        product2.setName("Smartphone");
        product2.setPrice(800.0);
        product2.setDiscountConditionType(Product.DiscountConditionType.NULL); // 無優惠
        product2.setDiscountOfferType(Product.DiscountOfferType.NULL);

        BasketItem basketItem1 = new BasketItem(product1, 3); // 購買 3 台筆記本
        BasketItem basketItem2 = new BasketItem(product2, 2); // 購買 2 部手機

        List<BasketItem> basketItems = new ArrayList<>();
        basketItems.add(basketItem1);
        basketItems.add(basketItem2);

        // 計算收據
        Receipt receipt = receiptService.calculateReceipt(basketItems);

        // 驗證結果
        assertEquals(4600.0, receipt.getTotal()); // 總價
        assertEquals(300.0, receipt.getDiscount()); // 筆記本的 10% 折扣
        assertEquals(4300.0, receipt.getFinalTotal()); // 最終總價
    }

    @Test
    void testCalculateReceipt_NoItems() {
        // 準備空的購物籃
        List<BasketItem> basketItems = new ArrayList<>();

        // 計算收據
        Receipt receipt = receiptService.calculateReceipt(basketItems);

        // 驗證結果
        assertEquals(0.0, receipt.getTotal()); // 總價
        assertEquals(0.0, receipt.getDiscount()); // 無優惠
        assertEquals(0.0, receipt.getFinalTotal()); // 最終總價
    }
}
