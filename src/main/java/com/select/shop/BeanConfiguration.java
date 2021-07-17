package com.select.shop;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean                                           // productRepository를 IoC에 Bean으로 등록하겠다.
    public ProductRepository productRepository() {
        String dbId = "sa";
        String dbPassword = "";
        String dbUrl = "jdbc:h2:mem:shopdb";
        return new ProductRepository(dbId, dbPassword, dbUrl);
    }

    @Bean                                           // productService를 IoC에 Bean으로 등록하겠다.
    @Autowired                                      // productService 생성할 때 Bean인 ProductRepository를 꺼내서 사용하겠다.
    public ProductService productService(ProductRepository productRepository) {
        return new ProductService(productRepository);
    }
}
