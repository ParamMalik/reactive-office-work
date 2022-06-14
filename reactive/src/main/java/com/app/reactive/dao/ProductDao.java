package com.app.reactive.dao;

import com.app.reactive.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDao {

    Mono<ProductDto> getProductById(String id);

    Flux<ProductDto> getAllProduct();

    Mono<ProductDto> addProduct(ProductDto productDto);


    Mono<Void> removeProductById(String id);

    public Mono<Void> removeAllProducts();

    Mono<ProductDto> updateProduct(ProductDto productDto);

    Mono<ProductDto> partialUpdateProduct(ProductDto productDto);


}
