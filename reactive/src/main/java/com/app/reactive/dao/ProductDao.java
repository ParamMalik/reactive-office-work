package com.app.reactive.dao;

import com.app.reactive.dto.ProductDto;
import com.app.reactive.model.ProductModel;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ProductDao {

    Mono<ProductDto> getProductById(String id);

    Flux<ProductDto> getAllProduct();

    Mono<ProductDto> addProduct(ProductDto productDto);


    Mono<ProductDto> removeProductById(String id);


}
