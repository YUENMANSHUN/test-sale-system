package com.test.sale_system;

import com.test.sale_system.model.Product;
import com.test.sale_system.repository.ProductRepository;
import com.test.sale_system.service.BasketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ProductServiceTest {
    @Autowired
    private BasketService basketService;

    @Autowired
    private ProductRepository productRepository;

    @Test
    public void testCreateProduct() {
        // 創建商品 1
        Product product1 = new Product();
        product1.setName("Laptop");
        product1.setPrice(1200.50);
        product1.setDiscountConditionType(Product.DiscountConditionType.QUANTITY_THRESHOLD);
        product1.setDiscountConditionValue(3); // 購買滿 3 件
        product1.setDiscountOfferType(Product.DiscountOfferType.X_QUANTITY_HAVE_PERCENTAGE);
        product1.setDiscountOfferQuantity(1);
        product1.setDiscountOfferValue(50.0); // 50% 折扣
        productRepository.save(product1);

        // 創建商品 2
        Product product2 = new Product();
        product2.setName("Smartphone");
        product2.setPrice(800.00);
        product2.setDiscountConditionType(Product.DiscountConditionType.NULL);
        product2.setDiscountOfferType(Product.DiscountOfferType.NULL);
        productRepository.save(product2);

        //use productRepository findByName to get product1 and product2
        Product product1FromDB = productRepository.findByName("Laptop");
        Product product2FromDB = productRepository.findByName("Smartphone");
        assertEquals(1200.50, product1FromDB.getPrice());
        assertEquals(800.00, product2FromDB.getPrice());
    }

}
