package com.select.shop.service;

import com.select.shop.model.Product;
import com.select.shop.dto.ProductMypriceRequestDto;
import com.select.shop.repository.ProductRepository;
import com.select.shop.dto.ProductRequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    @Autowired
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getProducts() {
//        ArrayList<Product> products = productRepository.getProducts();
//        return products;
        return productRepository.findAll();
    }

    public Product createProduct(ProductRequestDto requestDto) { //throws Exception { 원래 이거였지만 직접 쿼리를 다루지 않아서 필요X
        Product product = new Product(requestDto);
        LocalDateTime now = LocalDateTime.now();
        product.setCreatedAt(now);
        product.setModifiedAt(now);

        //Product resultProduct = productRepository.createProduct(product);
        Product resultProduct = productRepository.save(product);
        return resultProduct;
    }

    //방법2 @Transactional
    public Long updateProduct(Long id, ProductMypriceRequestDto requestDto) {
//        if(requestDto.getMyprice() < 0) {
//            throw new IllegalAccessError("희망 최저가는 0원 이상이어야 합니다.");
//        }
//        if(productRepository.isValidId(id)) {
//            Product product = productRepository.updateProduct(id, requestDto);
//            return product.getId();
//        }
//        else {
//            throw new NullPointerException("해당 아이디가 존재하지 않습니다.");
//        }
        //방법1
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalAccessError("희망 최저가는 0원 이상이어야 합니다."));
        product.setMyprice(requestDto.getMyprice());
        productRepository.save(product);

        //방법2
//        Product product = productRepository.findById(id)
//                .orElseThrow(() -> new IllegalAccessError("희망 최저가는 0원 이상이어야 합니다."));
//        product.updateMyPrice(requestDto.getMyprice()); //Product class에 updateMyPrice 만들어야 한다.
        //이 방법으로는 .save 안 해도 @Transactional의 도움을 받아서 Entity가 바뀔 때는 알아서 저장해 준다.

        return product.getId();
    }
}
