package com.select.shop.controller;

import com.select.shop.model.Product;
import com.select.shop.dto.ProductMypriceRequestDto;
import com.select.shop.dto.ProductRequestDto;
import com.select.shop.security.UserDetailsImpl;
import com.select.shop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;

import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /*
    @GetMapping("/api/products")
    public List<Product> getProducts() { //throws Exception { 원래 이거였지만 Service에서 SQLException이 필요없어지면서 여기도 X
        return productService.getProducts();
    }
     */
    /*
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) {
        return productService.createProduct(requestDto);
    }
    */
    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto, @AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return productService.createProduct(requestDto, userId);
    }

    @GetMapping("/api/products")
    public List<Product> getProducts(@AuthenticationPrincipal UserDetailsImpl userDetails) {
        Long userId = userDetails.getUser().getId();
        return productService.getProducts(userId);
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id,
                              @RequestBody ProductMypriceRequestDto requestDto) {
        return productService.updateProduct(id, requestDto);
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/api/admin/products")
    public List<Product> getAllProducts() {
        return productService.getAllProducts();
    }
}
