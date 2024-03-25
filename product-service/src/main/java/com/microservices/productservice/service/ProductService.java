package com.microservices.productservice.service;

import com.microservices.productservice.Repository.ProductRepository;
import com.microservices.productservice.dto.ProductRequest;
import com.microservices.productservice.dto.ProductResponse;
import com.microservices.productservice.model.Product;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    private final ProductRepository productRepository;

    /* The below is constructor parameter but as number of classes increases we need to create for every class
    so instead use @RequiredArgsConstructor
    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }*/

    public void createProduct(ProductRequest productRequest){
        /*This will create object of type product or an instance of product and in order to save these values in
        the database, we need to access ProductRepoitory. so we need to inject the ProductRepository into
        service class and this can be done by constructor injection, we can either manually inject the
        constructor or use @RequiredArgsConstructor*/
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .build();
        productRepository.save(product);
        log.info("product {} is saved",product.getId());
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream().map(this::mapToProductResponse).toList();
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .build();
    }
}

