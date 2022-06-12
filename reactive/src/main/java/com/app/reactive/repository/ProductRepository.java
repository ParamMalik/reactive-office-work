package com.app.reactive.repository;

import com.app.reactive.model.ProductModel;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ProductRepository extends ReactiveMongoRepository<ProductModel, String> {
}
