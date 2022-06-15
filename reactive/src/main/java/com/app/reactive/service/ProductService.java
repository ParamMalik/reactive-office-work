package com.app.reactive.service;

import com.app.reactive.dto.ProductDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductService {
    Mono<ProductDto> getProductById(String id);

    Flux<ProductDto> getAllProduct();

    Mono<ProductDto> addProduct(ProductDto productDto);


    Mono<ProductDto> removeProductById(String id);

    Mono<Void> removeAllProducts();

    Mono<ProductDto> updateProduct(ProductDto productDto);

    Mono<ProductDto> partialUpdateProduct(ProductDto productDto);

}
