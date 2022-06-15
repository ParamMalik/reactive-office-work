package com.app.reactive.service.impl;

import com.app.reactive.dao.ProductDao;
import com.app.reactive.dto.ProductDto;
import com.app.reactive.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;

    @Override
    public Mono<ProductDto> getProductById(String id) {
        return productDao.getProductById(id);
    }

    @Override
    public Flux<ProductDto> getAllProduct() {
        return productDao.getAllProduct();
    }

    @Override
    public Mono<ProductDto> addProduct(ProductDto productDto) {
        return productDao.addProduct(productDto);
    }


    @Override
    public Mono<ProductDto> removeProductById(String id) {
        return productDao.removeProductById(id);
    }

    @Override
    public Mono<Void> removeAllProducts() {
        return productDao.removeAllProducts();
    }

    @Override
    public Mono<ProductDto> updateProduct(ProductDto productDto) {
        return productDao.updateProduct(productDto);
    }

    @Override
    public Mono<ProductDto> partialUpdateProduct(ProductDto productDto) {
        return productDao.partialUpdateProduct(productDto);
    }
}
