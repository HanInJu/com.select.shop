package com.select.shop.controller;

import com.select.shop.model.Product;
import com.select.shop.dto.ProductMypriceRequestDto;
import com.select.shop.dto.ProductRequestDto;
import com.select.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<Product> getProducts() { //throws Exception { 원래 이거였지만 Service에서 SQLException이 필요없어지면서 여기도 X
        return productService.getProducts();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id,
                              @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }
}
