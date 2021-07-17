package com.select.shop;

import org.springframework.beans.factory.annotation.Autowired;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() throws Exception {
        ArrayList<Product> products = productRepository.getProducts();
        return products;
    }

    public Product createProduct(ProductRequestDto requestDto) throws Exception {
        Product product = new Product(requestDto);
        LocalDateTime now = LocalDateTime.now();
        product.setCreatedAt(now);
        product.setModifiedAt(now);

        Product resultProduct = productRepository.createProduct(product);
        return resultProduct;
    }

    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto) throws Exception {
        if(requestDto.getMyprice() < 0) {
            throw new IllegalAccessError("희망 최저가는 0원 이상이어야 합니다.");
        }
        if(productRepository.isValidId(id)) {
            Product product = productRepository.updateProduct(id, requestDto);
            return product.getId();
        }
        else {
            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
        }
    }
}
