package com.test.sale_system.configuration;

import com.test.sale_system.model.Product;
import com.test.sale_system.model.Product.DiscountConditionType;
import com.test.sale_system.model.Product.DiscountOfferType;
import com.test.sale_system.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner initDatabase(ProductRepository productRepository) {
        return args -> {
            // 創建商品 1
            Product product1 = new Product();
            product1.setName("Gaming PC");
            product1.setPrice(1200.50);
            product1.setDiscountConditionType(DiscountConditionType.QUANTITY_THRESHOLD);
            product1.setDiscountConditionValue(3); // 購買滿 3 件
            product1.setDiscountOfferType(DiscountOfferType.X_QUANTITY_HAVE_PERCENTAGE);
            product1.setDiscountOfferQuantity(1);
            product1.setDiscountOfferValue(50.0); // 50% 折扣
            productRepository.save(product1);

            /*// 創建商品 2
            Product product2 = new Product();
            product2.setName("Smartphone");
            product2.setPrice(800.00);
            product2.setDiscountConditionType(DiscountConditionType.AMOUNT_THRESHOLD);
            product2.setDiscountConditionValue(2000); // 滿 2000 元
            product2.setDiscountOfferType(DiscountOfferType.X_QUANTITY_HAVE_PERCENTAGE);
            product2.setDiscountOfferQuantity(2);
            product2.setDiscountOfferValue(15.0); // 15% 折扣
            productRepository.save(product2);*/
        };
    }
}
