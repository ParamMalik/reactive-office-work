package com.app.reactive.repository;

import com.app.reactive.model.ProductModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface ProductRepository extends ReactiveMongoRepository<ProductModel, String> {
    Mono<ProductModel> findByName(String productName);
}
