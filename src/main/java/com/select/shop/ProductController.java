package com.select.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
public class ProductController {

    private final ProductService productService;
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/api/products")
    public List<Product> getProducts() throws Exception {
        return productService.getProducts();
    }

    @PostMapping("/api/products")
    public Product createProduct(@RequestBody ProductRequestDto requestDto) throws Exception {
        return productService.createProduct(requestDto);
    }

    @PutMapping("/api/products/{id}")
    public Long updateProduct(@PathVariable Long id,
                              @RequestBody ProductMypriceRequestDto requestDto) throws Exception {
        return productService.updateProduct(id, requestDto);
    }
}
