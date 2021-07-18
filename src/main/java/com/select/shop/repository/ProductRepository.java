package com.select.shop.repository;

import com.select.shop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> { //테이블에 Product 타입으로 저장될 거고, id가 Long이다.

}